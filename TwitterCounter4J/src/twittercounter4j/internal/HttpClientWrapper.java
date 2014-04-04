/*
 * Copyright 2014 Venil Noronha
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package twittercounter4j.internal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import twittercounter4j.Authentication;
import twittercounter4j.Configuration;
import twittercounter4j.TwitterCounterException;
import twittercounter4j.util.GsonUtil;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * 
 * @author Venil Noronha
 *
 */
public class HttpClientWrapper {

	private Configuration config;
	private Authentication auth;
	private HttpClient httpClient;
	private Gson gson;
	
	public HttpClientWrapper(Configuration config, Authentication auth) {
		this.config = config;
		this.auth = auth;
		setHttpClient();
		setGson();
	}

	private void setHttpClient() {
		httpClient = HttpClientBuilder.create().build();		
	}
	
	private void setGson() {
		gson = GsonUtil.getGsonInstance();
	}

	public JsonObject get(String url) throws TwitterCounterException {
		JsonObject jsonObject = null;
		String builtUrl = buildUrl(url);
		HttpGet request = new HttpGet(builtUrl);
		HttpResponse response = null;
		try {
			response = httpClient.execute(request);
			BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = br.readLine()) != null) {
				result.append(line);
			}
			String resultStr = result.toString();
			if (config.isDebugEnabled()) {
				System.out.println("API Response: " + resultStr);
			}
			jsonObject = gson.fromJson(resultStr, JsonObject.class);
			if (jsonObject.has("error")) {
				int statusCode = response.getStatusLine().getStatusCode();
				throw new TwitterCounterException(statusCode, jsonObject.get("error").getAsString());
			}
		} catch (ClientProtocolException e) {
			if (response != null) {
				int statusCode = response.getStatusLine().getStatusCode();
				throw new TwitterCounterException(statusCode, e);
			}
			else {
				throw new TwitterCounterException(e);
			}
		} catch (IOException e) {
			if (response != null) {
				int statusCode = response.getStatusLine().getStatusCode();
				throw new TwitterCounterException(statusCode, e);
			}
			else {
				throw new TwitterCounterException(e);
			}
		}
		return jsonObject;
	}
	
	private String buildUrl(String url) {
		return url + "&apikey=" + auth.getApiKey();
	}
	
}
