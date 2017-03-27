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

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static gen.Main.totalInt;

class Search {
    private URL url;
    private String returnedResult;
    private Set<URL> urlss;
    private PrintWriter w;
    private MysqlCon con;

    private static final Pattern urlPattern = Pattern.compile(
            "(?:^|[\\W])((ht|f)tp(s?):\\/\\/|www\\.)"
                    + "(([\\w\\-]+\\.){1,}?([\\w\\-.~]+\\/?)*"
                    + "[\\p{Alnum}.,%_=?&#\\-+()\\[\\]\\*$~@!:/{};']*)",
            Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);

    Search(URL url, PrintWriter w, MysqlCon con){
        this.w = w;
        urlss = new TreeSet<>();
        this.url = url;
        this.con = con;

        if (url == null){return;}
        UrlReader ur = new UrlReader(url);
        try {
            returnedResult = ur.readAll();
            Matcher matcher = urlPattern.matcher(returnedResult);
            while (matcher.find()) {
                String newurl =  (returnedResult.substring(matcher.start(1),matcher.end()));
                if (!newurl.contains("http")){newurl = "http://"+newurl;}
                int end =newurl.indexOf("/", 8);
                int end1 =newurl.indexOf("?", 8);
                int end2 =newurl.indexOf("'", 8);

                if (end>0 && end1>0 && end2>0){
                    newurl = newurl.substring(0,Math.min(Math.min(end, end1),end2));
                } else if (end>0 &&end1 >0){
                    newurl = newurl.substring(0,Math.min (end,end1));
                } else if (end>0 && end2 >0){
                    newurl = newurl.substring(0,Math.min (end,end2));
                } else if (end1 > 0 &&end2 >0){
                    newurl = newurl.substring(0,Math.min (end1,end2));
                } else if (end>0){
                    newurl = newurl.substring(0,end);
                } else if (end1>0){
                    newurl = newurl.substring(0,end1);
                } else if (end2>0){
                    newurl = newurl.substring(0,end2);
                }

                URL newer = new URL(newurl);

                //adding to database
                if (con.checkToPut(newurl)) {
                    urlss.add(newer);
                }
            }
        } catch (IOException e) {
            return;
            //System.out.println("read Failed " + url);
        }
        if (!urlss.isEmpty()){
            for (URL lit : urlss){
                totalInt ++;
                if (totalInt%100==0){
                    System.out.println(totalInt);
                }
                //System.out.println (totalInt + ": "+ lit) ;
                w.println(lit);
            }
            w.flush();
            if (totalInt<1000000){
                expanding();
            }
        }
    }

    private void expanding(){
        for (URL lit : urlss){
            //System.out.println("new inst: " + lit);
            new Search(lit, w, con);
        }
    }


    //next How to Implement a database storage
}
