package gen;
/**
 * Created by eugene on 3/23/2017.
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;
import java.util.TreeSet;
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
        urlss = new TreeSet<URL>();
        this.url = url;

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


                if (urlset.add(newer)) {
                    urlss.add(newer);
                }
            }
        } catch (IOException e) {
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
            new Search(lit, w);
        }
    }


    //next How to Implement a database storage
}
