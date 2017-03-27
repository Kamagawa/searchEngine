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

import java.net.MalformedURLException;

public class URL implements Comparable<URL>{
    private java.net.URL url;
    private String rawurl;

    public URL (java.net.URL url){
        this.url = url;
        rawurl = url.toString();
    }

    public URL (String url) throws MalformedURLException {
        this.url = new java.net.URL (url);
        rawurl = url;
    }

    @Override
    public String toString() {
        return rawurl;
    }

    @Override
    public int hashCode() {
        return  rawurl.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if ( obj instanceof URL ) {
            return this.hashCode() == obj.hashCode();
        } else {
            return false; 
        }
    }


    @Override
    public int compareTo(URL o) {
        return this.hashCode() - o.hashCode();
    }

    public java.net.URL e (){
        return url;
    }
}
