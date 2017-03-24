package gen;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;

public class Main {
    static long totalInt = 0;
    static HashSet<URL> urlset;
    String star = "https://ca.yahoo.com";
    URL start;

    PrintWriter w;

    public static void main(String[] args) {
        new Main();
    }

    Main (){
        final long startTime = System.currentTimeMillis();
        urlset = new HashSet<>();

        try {
            start = new URL(star);
            w = new PrintWriter(new BufferedWriter(new FileWriter("net.txt", true)));
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
