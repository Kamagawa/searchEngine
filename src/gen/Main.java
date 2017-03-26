package gen;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.util.Set;
import java.util.TreeSet;

public class Main {
    static long totalInt = 0;
    static Set<URL> urlset;
    URL start;
    String star = "http://www.youtube.com";
    PrintWriter w;
    static String urllink = "jdbc:mysql://localhost/phpmyadmin/";

    public static void main(String[] args) {
        new MysqlCon(urllink);
        new Main();
    }

    Main (){
        final long startTime = System.currentTimeMillis();
        urlset = new TreeSet<URL>();


        try {
            start = new URL(star);
            w = new PrintWriter(new BufferedWriter(new FileWriter("lolXDlovely.txt", true)));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        new Search(start ,w);

        final long endTime = System.currentTimeMillis();
        System.out.println("Finished in: " + (endTime - startTime)/1000 );
    }
}

