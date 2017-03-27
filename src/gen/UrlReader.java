/*
* The MIT License
* Copyright (c) 2013 Eugene Wang (euhome.github.io) 3/23/2017
* Permission is hereby granted, free of charge, to any person obtaining a copy
* of this software and associated documentation files (the "Software"), to deal
* in the Software without restriction, including without limitation the rights
* to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
* copies of the Software, and to permit persons to whom the Software is
* furnished to do so, subject to the following conditions:
* The above copyright notice and this permission notice shall be included in
* all copies or substantial portions of the Software.
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
* IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
* FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
* AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
* LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
* OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
* THE SOFTWARE.
* */
package gen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

class UrlReader {
    private URL url;
    private BufferedReader rd;
    private InputStream is;

    UrlReader (URL url){
        this.url = url;
    }

    String readAll() throws IOException {
        if (url == null) {return "";}
        is = url.e().openStream();
        rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));

        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }
/*
Problem:



Exception in thread "main" java.lang.IllegalArgumentException: URI can't be null.
	at sun.net.spi.DefaultProxySelector.select(DefaultProxySelector.java:147)
	at sun.net.www.protocol.http.HttpURLConnection.plainConnect0(HttpURLConnection.java:1099)
	at sun.net.www.protocol.http.HttpURLConnection.plainConnect(HttpURLConnection.java:999)
	at sun.net.www.protocol.http.HttpURLConnection.connect(HttpURLConnection.java:933)
	at sun.net.www.protocol.http.HttpURLConnection.getInputStream0(HttpURLConnection.java:1513)
	at sun.net.www.protocol.http.HttpURLConnection.getInputStream(HttpURLConnection.java:1441)
	at java.net.URL.openStream(URL.java:1045)
	at gen.UrlReader.readAll(UrlReader.java:45)
	at gen.Search.<init>(Search.java:36)
	at gen.Search.expanding(Search.java:90)
	at gen.Search.<init>(Search.java:82)

 */



}