package gen;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by eugene on 3/23/2017.
 */
public class Search {
    static long totalInt = 0;
    String rawurl;
    URL url;
    String returnedResult;
    static Set<URL> urls;
    Set<URL> urlss;

    private static final Pattern urlPattern = Pattern.compile(
            "(?:^|[\\W])((ht|f)tp(s?):\\/\\/|www\\.)"
                    + "(([\\w\\-]+\\.){1,}?([\\w\\-.~]+\\/?)*"
                    + "[\\p{Alnum}.,%_=?&#\\-+()\\[\\]\\*$~@!:/{};']*)",
            Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);

    public Search (String rawurl){
        if (urls == null){
            urls = new HashSet<URL>();
        }
        urlss = new HashSet<URL>();

        urls = new HashSet<URL>();
        this.rawurl = rawurl;
        try {
            url = new URL(rawurl);
        } catch (MalformedURLException e) {
            System.out.println("url badForm: " + url);
        }

        if (url != null){
            UrlReader ur = new UrlReader(url);
            try {
                returnedResult = ur.readAll();
                Matcher matcher = urlPattern.matcher(returnedResult);
                while (matcher.find()) {
                    totalInt ++;
                    String newurl =  (returnedResult.substring(matcher.start(1),matcher.end()));
                    if (!newurl.contains("http")){
                        newurl = "http://"+newurl;
                    }
                    urlss.add(new URL(newurl));
                    //System.out.println(totalInt+ ": " +newurl);
                }
            } catch (IOException e) {
                System.out.println("read Failed " + url);
                e.printStackTrace();
            }
        }

    }


}
