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

import java.sql.*;
import java.util.Set;

class MysqlCon{
    private Class mother;
    private String urlLink;
    private boolean created = false;
    private Connection con;
    private Statement stmt;

    MysqlCon (String urlLink){
        this.urlLink = urlLink;

        try{
            mother = Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection(
                    urlLink,
                    "root",
                    "");
            created = true;
            //here sonoo is database name, root is username and password
            stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT VERSION()");
            if (rs.next()) {
                System.out.println(rs.getString(1));
            }

        }catch(Exception e){ e.printStackTrace();}
    }

    String get(String content){
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM `urlstore` WHERE `url` LIKE '" + content + "' ");
            if (rs.next()){
                return (rs.getString(1));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return "failed";
    }

    boolean checkToPut (String obj) {
        if (get(obj).equalsIgnoreCase("failed")) {
            put(obj);       //assuming it is always put successful
            return true;
        } else {
            return false;
        }
    }

    Set<URL> getAll(){
        return null;
    }

    int put (String content){
        try {
            int rsNum = stmt.executeUpdate("INSERT INTO `urlstore` (`url`) VALUES ('" + content +"');");
            return rsNum;
        } catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

}
