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

package twittercounter4j.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * 
 * @author Venil Noronha
 *
 */
public class ParseUtil {

	private static JsonElement getObject(String key, JsonObject jsonObject) {
		if (jsonObject.has(key)) {
			return jsonObject.get(key);
		}
		return null;
	}
	
	public static String getString(String key, JsonObject jsonObject) {
		JsonElement value = getObject(key, jsonObject);
		if (null != value) {
			return value.getAsString();
		}
		return null;
	}
	
	public static Long getLong(String key, JsonObject jsonObject) {
		JsonElement value = getObject(key, jsonObject);
		if (null != value) {
			return Long.parseLong(value.getAsString().replaceAll("\"", ""));
		}
		return null;
	}
	
	public static Date getDate(String key, JsonObject jsonObject, String dateFormat) throws ParseException {
		JsonElement value = getObject(key, jsonObject);
		if (null != value) {
			return parseDate(value.getAsString(), dateFormat);
		}
		return null;
	}

	public static Date parseDate(String dateStr, String dateFormat) throws ParseException {
		return new SimpleDateFormat(dateFormat).parse(dateStr);
	}
	
}
