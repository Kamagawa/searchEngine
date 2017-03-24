package gen;
/**
 * Created by eugene on 3/23/2017.
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static gen.Main.totalInt;
import static gen.Main.urlset;

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
        this.w = w;
        urlss = new HashSet<URL>();
        this.url = url;

        if (url == null){return;}
        UrlReader ur = new UrlReader(url);
        try {
            returnedResult = ur.readAll();
            Matcher matcher = urlPattern.matcher(returnedResult);
            while (matcher.find()) {

                String newurl =  (returnedResult.substring(matcher.start(1),matcher.end()));
                if (newurl.contains("php?")){continue;}
                totalInt ++;
                if (!newurl.contains("http")){newurl = "http://"+newurl;}

                if (newurl.contains("https://") ) {
                    String s = "https://" + newurl.substring(8).split("/")[0];
                } else if (newurl.contains("https://")){
                    String s = "http://" + newurl.substring(7).split("/")[0];
                }

                URL newer = new URL(newurl);
                if (urlset.add(newer)) {urlss.add(newer);}
                //System.out.println(totalInt+ ": " +newurl);
                //w.println(newurl);
            }



        } catch (IOException e) {
            System.out.println("read Failed " + url);
        }
        if (!urlss.isEmpty()){
            for (URL lit : urlss){
                System.out.println(totalInt + ": "+ lit);
                w.println(lit);
            }
            if (totalInt<1000000){
                expanding();
            }
        }
    }

    private void expanding(){
        for (URL lit : urlss){
            System.out.println("new inst: " + lit);
            new Search(lit, w);
        }
    }
}
