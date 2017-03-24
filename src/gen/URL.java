package gen;

import java.net.MalformedURLException;

/**
 * Created by eugene on 3/24/2017.
 */
public class URL implements Comparable<URL>{
    java.net.URL url;
    String rawurl;

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
        return this.hashCode() == obj.hashCode();
    }


    @Override
    public int compareTo(URL o) {
        return this.hashCode() - o.hashCode();
    }

    public java.net.URL e (){
        return url;
    }
}
