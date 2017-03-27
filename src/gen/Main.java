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

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;

public class Main {
    static long totalInt = 0;
    private URL start;
    private String star = "https://www.google.ca";
    private PrintWriter w;
    private MysqlCon con;
    private String urllink = "jdbc:mysql://localhost:3306/url_store";

    public static void main(String[] args) {
        new Main();
    }

    private Main (){
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

