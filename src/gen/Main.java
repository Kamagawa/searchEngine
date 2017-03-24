package gen;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;

public class Main {
    static PrintWriter w;
    static String star = "https://daohang.qq.com/";
    static URL start;
    static long totalInt = 0;
    public static void main(String[] args) {
        final long startTime = System.currentTimeMillis();
        try {
            start = new URL(star);
            w = new PrintWriter(new BufferedWriter(new FileWriter("net.txt")));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        new Search(start, w);

        final long endTime = System.currentTimeMillis();
        System.out.println("Finished in: " + (endTime - startTime)/1000 );
    }

}
