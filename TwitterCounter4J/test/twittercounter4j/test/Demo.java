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

package twittercounter4j.test;

import twittercounter4j.TwitterCounter;
import twittercounter4j.TwitterCounterException;
import twittercounter4j.User;

/**
 * 
 * @author Venil Noronha
 *
 */
public class Demo {

	public static void main(String args[]) {
		TwitterCounter twitterCounter = new TwitterCounter.Builder()
										.withDebugEnabled(true)
										.withApiKey("e9335031a759f251ee9b4e2e6634e1c5")
										.build();
		try {
			User user = twitterCounter.showUser("CocaCola");
			System.out.println(user);
		} catch (TwitterCounterException e) {
			e.printStackTrace();
		}
	}
	
}
