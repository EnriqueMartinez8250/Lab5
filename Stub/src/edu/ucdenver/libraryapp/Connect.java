package edu.ucdenver.libraryapp;
import edu.ucdenver.library.User;
import java.sql.*;

public class Connect {

    Connection conn = null;

    public Connect(){

        /**
         *  TODO-1::Modify the following to match your remote SQL Server.
         *  publicIP is the External IP of the MySQL instance.
         *  DB_NAME is the name of the database.
         *  DB_PORT is the port that MySQL uses to establish connections.
         *  Username and Password are the MySQL user with access the library.
         */


        String publicIP = "138.2.228.112";
        String DB_NAME = "Library";
        String DB_PORT = "3306";
        String Username = "cscy3765";
        String Password = "cscY3765@1";

        try {
            // db parameters
            String url = "jdbc:mysql://" + publicIP + ":" + DB_PORT + "/" + DB_NAME + "?user=" + Username
                                         + "&password=" + Password + "&useUnicode=true&characterEncoding=UTF-8";


            // create a connection to the database
            this.conn = DriverManager.getConnection(url);
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    /**
     *  TODO-2: Use query parameters instead of string concatenation to prevent SQL injections.
     *  An example of query parameters:
     *  PreparedStatement stmt = this.conn.prepareStatement("SELECT * FROM table_name WHERE column_1 = ? AND column_2 = ?");
     *  stmt.setString(1, "xyz");
     *  stmt.setInt(2, 4);
    */

    // String query = "UPDATE users SET email = ? WHERE id = ?";

    // PreparedStatement stmt = this.conn.prepareStatement("SELECT Title,PublicationDate,Pages,Author FROM ? WHERE Title = '" + ? + "';");

    public void searchBooks(String bookName){
        try{
            // TODO-2.1: Fix searchBooks SQL Query
            PreparedStatement sql = this.conn.prepareStatement("SELECT Title,PublicationDate,Pages,Author FROM ? WHERE Title = ?");
            sql.setString(1, "Books");
            sql.setString(2, "bookName");
            ResultSet rs = sql.executeQuery();
//            String sql = "SELECT Title,PublicationDate,Pages,Author FROM Books WHERE Title = '" + bookName + "';";
//            Statement stmt  = this.conn.createStatement();

//            ResultSet rs    = sql.executeQuery(sql);
            int columnNumber = rs.getMetaData().getColumnCount();

            // loop through the result set
            System.out.println("ID  |   Title   |   Date |    Pages    | AuthorID");
            System.out.println("--------------------------------------------------------------");
            while (rs.next()) {
                for (int i = 1; i<= columnNumber; i++){
                    System.out.print(rs.getString(i));
                    System.out.print("\t\t");
                }
                System.out.println("");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addUser(User user){
        try {
            //count users
            String countq = "SELECT COUNT(*) AS total FROM Users;"; // count users
            PreparedStatement countStatement = this.conn.prepareStatement(countq);
            ResultSet rs = countStatement.executeQuery();
            int count = 0;
            while (rs.next()) {count = rs.getInt("total");}
            //count users

            // TODO-2.2: Fix addUser SQL Query
//            PreparedStatement sql = this.conn.prepareStatement("INSERT INTO Users(id,Username,Password,Email) VALUES (" + ? + ", '" + ? + "', '" + ? + "', '" + ? + "')");
            PreparedStatement sql = this.conn.prepareStatement("INSERT INTO Users (id, Username, Password, Email) VALUES (?, ?, ?, ?)");

            sql.setInt(1, count);
            sql.setString(2, "user.getUsername()");
            sql.setString(3, "user.getPassword()");
            sql.setString(4, "user.getEmail()");

            sql.executeUpdate();
//            PreparedStatement pstmt = this.conn.prepareStatement(sql);
//
//            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public User getUser(String username, String password){

        try {

            // TODO-2.3: Fix getUser SQL Query
            PreparedStatement sql = this.conn.prepareStatement("SELECT Username,Password,Email FROM Users WHERE Username =  ?  AND Password = ?");
            sql.setString(1,"username");
            sql.setString(2,"password");
            ResultSet rs = sql.executeQuery();
//
//            PreparedStatement pstmt = this.conn.prepareStatement(String.valueOf(sql));
//            PreparedStatement preparedStatement = this.conn.prepareStatement(String.valueOf(sql));
//            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                return new User(rs.getString("Username"), rs.getString("Password"), rs.getString("Email"));
            }
            return null;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public User getAdmin(String username, String password) {

        try {

            // TODO-2.4: Fix getAdmin SQL Query
//            PreparedStatement sql = this.conn.prepareStatement("SELECT * FROM ? WHERE Username = '" + ? + "' AND Password = '" + ? + "';");
            // sql = this.conn.prepareStatement("SELECT * FROM Admins WHERE Username =  + ? +  AND Password =  + ? + ");
            PreparedStatement sql = this.conn.prepareStatement("SELECT * FROM Admins WHERE Username =   ?   AND Password =   ?  ");
            sql.setString(1, "username");
            sql.setString(2, "password");
            ResultSet rs = sql.executeQuery();

//            String sql = "SELECT * FROM Admins WHERE Username = '" + username + "' AND Password = '" + password + "';";
//            PreparedStatement pstmt = this.conn.prepareStatement(sql);
//            PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
//            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                return new User(rs.getString("Username"), rs.getString("Password"), rs.getString("Email"));
            }
            return null;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }

    }
}
