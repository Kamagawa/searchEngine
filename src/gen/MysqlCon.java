package gen;

/**
 * Created by eugene on 3/26/2017.
 */
import java.sql.*;
class MysqlCon{
    String urlLink;
    public MysqlCon (String urlLink){
        this.urlLink = urlLink;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection(
                    urlLink,
                    "root",
                    "");
            //here sonoo is database name, root is username and password
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT VERSION()");
            if (rs.next()) {
                System.out.println(rs.getString(1));
            }
            con.close();
        }catch(Exception e){ e.printStackTrace();}
    }

}
