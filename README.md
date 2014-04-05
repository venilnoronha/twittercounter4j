TwitterCounter4J
================
A Java client for consuming the Twitter Counter API. You can check out the API at http://twittercounter.com/pages/api.

Compiling
---------
The below mentioned libraries are required for compiling the project.

<b>Apache HttpClient</b>
<ul>
<li>commons-codec-1.6.jar</li>
<li>commons-logging-1.1.3.jar</li>
<li>fluent-hc-4.3.3.jar</li>
<li>httpclient-4.3.3.jar</li>
<li>httpclient-cache-4.3.3.jar</li>
<li>httpcore-4.3.2.jar</li>
<li>httpmime-4.3.3.jar</li>
</ul>

<b>Google GSON</b>
<ul>
<li>gson-2.2.4.jar</li>
</ul>

Licenses
--------
This project is based on the <a href="http://www.apache.org/licenses/LICENSE-2.0.html" title="Apache License 2.0">Apache License 2.0</a>

This project requires external libraries from Apache and Google. The licenses for those components can be found at locations mentioned below.

<b>Apache HttpClient:</b> https://hc.apache.org/httpclient-3.x/license.html

<b>Google GSON:</b> http://code.google.com/p/google-gson/source/browse/trunk/gson/LICENSE?r=369


Usage
-----
```java
TwitterCounter twitterCounter = new TwitterCounter.Builder()
                                .withDebugEnabled(false)
                                .withApiKey("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX") // Put your API key here
                                .build();
User user = twitterCounter.showUser("CocaCola");
```

Credits
-------
This codebase is more of based upon the Twitter4J library. You can find it at https://github.com/yusuke/twitter4j/
