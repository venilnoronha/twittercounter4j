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

/**
 * 
 * @author Venil Noronha
 *
 */
public class TwitterCounterException extends Exception {

	private static final long serialVersionUID = 3292768480178473088L;

	private int statusCode = -1;
	
	public TwitterCounterException() {
		super();
	}
	
    public TwitterCounterException(String msg) {
        super(msg);
    }

    public TwitterCounterException(Throwable cause) {
        super(cause);
    }
    
    public TwitterCounterException(String msg, Throwable cause) {
        super(msg, cause);
    }
    
    public TwitterCounterException(int statusCode) {
    	this.statusCode = statusCode;
    }
    
    public TwitterCounterException(int statusCode, Throwable cause) {
    	super(cause);
    	this.statusCode = statusCode;
    }

	public TwitterCounterException(int statusCode, String msg) {
		super(msg);
		this.statusCode = statusCode;
	}

	public int getStatusCode() {
		return statusCode;
	}

}
