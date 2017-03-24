package gen;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static gen.Main.totalInt;


/**
 * Created by eugene on 3/23/2017.
 */
public class Search {
    URL url;
    String returnedResult;
    Set<URL> urlss;
    PrintWriter w;

    private static final Pattern urlPattern = Pattern.compile(
            "(?:^|[\\W])((ht|f)tp(s?):\\/\\/|www\\.)"
                    + "(([\\w\\-]+\\.){1,}?([\\w\\-.~]+\\/?)*"
                    + "[\\p{Alnum}.,%_=?&#\\-+()\\[\\]\\*$~@!:/{};']*)",
            Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);

    public Search(URL url, PrintWriter w){
        urlss = new HashSet<URL>();
        this.url = url;

        if (url == null){return;}
        UrlReader ur = new UrlReader(url);
        try {
            returnedResult = ur.readAll();
            Matcher matcher = urlPattern.matcher(returnedResult);
            while (matcher.find()) {
                totalInt ++;
                String newurl =  (returnedResult.substring(matcher.start(1),matcher.end()));
                if (!newurl.contains("http")){newurl = "http://"+newurl;}
                urlss.add(new URL(newurl));
                System.out.println(totalInt+ ": " +newurl);
            }
        } catch (IOException e) {
            System.out.println("read Failed " + url);
            e.printStackTrace();
        }
        if ((!urlss.isEmpty())&&totalInt<1000000){
            expanding();
        }
    }

    private void expanding(){
        for (URL lit : urlss){
            new Search(lit, w);
        }
    }




}
