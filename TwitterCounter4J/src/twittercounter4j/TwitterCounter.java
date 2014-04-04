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

package twittercounter4j;

import twittercounter4j.api.UserResource;

import com.google.gson.JsonObject;

/**
 * 
 * @author Venil Noronha
 *
 */
public class TwitterCounter extends TwitterCounterBase implements UserResource {

	private TwitterCounter(Builder builder) {
		super(builder.config, builder.auth);
	}
	
	private JsonObject get(String url) throws TwitterCounterException {
		return http.get(url);
	}

	@Override
	public User showUser(long id) throws TwitterCounterException {
		return factory.createUser(get(config.getApiBaseUrl() + "?twitter_id=" + id));
	}

	@Override
	public User showUser(String username) throws TwitterCounterException {
		return factory.createUser(get(config.getApiBaseUrl() + "?username=" + username));
	}
	
	public static class Builder {
		
		private Configuration config = new Configuration();
		private Authentication auth = new Authentication();

		public Builder withDebugEnabled(boolean debugEnabled) {
			checkNotBuilt();
			config.setDebugEnabled(debugEnabled);
			return this;
		}
		
		public Builder withApiBaseUrl(String apiBaseUrl) {
			checkNotBuilt();
			config.setApiBaseUrl(apiBaseUrl);
			return this;
		}
		
		public Builder withApiKey(String apiKey) {
			checkNotBuilt();
			auth.setApiKey(apiKey);
			return this;
		}
		
		public TwitterCounter build() {
			checkNotBuilt();
			config.validate();
			auth.validate();
			try {
				return new TwitterCounter(this);
			}
			finally {
				config = null;
				auth = null;
			}
		}
		
		private void checkNotBuilt() {
			if (null == config && null == auth) {
				throw new IllegalStateException("Cannot use this builder any longer, build() has already been called");
			}
		}
		
	}
	
}
