package gen;

/**
 * Created by eugene on 3/26/2017.
 * Holy fuck
 */
import java.sql.*;
import java.util.Set;

class MysqlCon{
    Class mother;
    String urlLink;
    boolean created = false;
    Connection con;
    Statement stmt;

    public MysqlCon (String urlLink){
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

    public String get(String content){
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

    public boolean checkToPut (String obj) {
        if (get(obj).equalsIgnoreCase("failed")) {
            put(obj);       //assuming it is always put successful
            return true;
        } else {
            return false;
        }
    }

    public Set<URL> getAll(){
        return null;
    }

    public int put (String content){
        try {
            int rsNum = stmt.executeUpdate("INSERT INTO `urlstore` (`url`) VALUES ('" + content +"');");
            return rsNum;
        } catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

}
