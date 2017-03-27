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
    URL start;
    String star = "https://www.google.ca";
    PrintWriter w;
    MysqlCon con;
    String urllink = "jdbc:mysql://localhost:3306/url_store";

    public static void main(String[] args) {
        new Main();
    }

    Main (){
        final long startTime = System.currentTimeMillis();
        con = new MysqlCon(urllink);


        try {
            start = new URL(star);
            w = new PrintWriter(new BufferedWriter(new FileWriter("setSQL.txt", true)));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        new Search(start ,w, con);

        final long endTime = System.currentTimeMillis();
        System.out.println("Finished in: " + (endTime - startTime)/1000 );
    }
}

