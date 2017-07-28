package javaandbd;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by P.Xorygij on 26.07.17.
 */
public class ConnectionToDB {
    public static Connection conn;
    public static Statement statmt;
    public static ResultSet resSet;


    //Connecting to DB
    public static void Conn()throws ClassNotFoundException, SQLException{
        conn = null;
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection("jdbc:sqlite:C://FootballPlayers.s3db");
        System.out.println("Connected!");
    }

    //-----------Create a table----------------
    public static void CreateDB() throws ClassNotFoundException, SQLException{
        statmt = conn.createStatement();
        statmt.execute("Create TABLE if not exists 'footballPlayers' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'name' text, 'age' INT, 'number' INT, 'position' text, 'club' text, 'summary' text);");
        System.out.println("Table is created or already exist");
    }
    //-------------Filling in a table-----------
    public static void WriteDB(String name, int age, int number, String position, String club, String summary) throws SQLException{
        statmt.execute("INSERT INTO 'footballPlayers' ('name', 'age', 'number', 'position', 'club', 'summary') VALUES ('"+name+"','"+age+"','"+number+"','"+position+"','"+club+"','"+summary+"');");
        System.out.println("Table is filling!");
    }

    public static ArrayList<Player> getAllPlayers() throws ClassNotFoundException, SQLException{
        statmt = conn.createStatement();
        String sql = "SELECT * FROM footballPlayers";
        resSet = statmt.executeQuery(sql);
        ArrayList<Player> playerList = new ArrayList<>();
        while(resSet.next()){
            Player player = new Player(resSet.getString("name"), resSet.getInt("age"), resSet.getInt("number"), resSet.getString("position"), resSet.getString("club"), resSet.getString("summary"));
            playerList.add(player);
        }
        return playerList;
    }

    public static String getClub(String name) throws ClassNotFoundException, SQLException{
        statmt = conn.createStatement();
        String sql = "SELECT club FROM footballPlayers WHERE name='"+name+"'";
        resSet = statmt.executeQuery(sql);
        String club = resSet.getString("club");
        return club;
    }

    public static int getAge(String name) throws ClassNotFoundException, SQLException{
        statmt = conn.createStatement();
        String sql = "SELECT age FROM footballPlayers WHERE name='"+name+"'";
        resSet = statmt.executeQuery(sql);
        int age = resSet.getInt("age");
        return age;
    }

    public static int getNumber(String name) throws ClassNotFoundException, SQLException{
        statmt = conn.createStatement();
        String sql = "SELECT number FROM footballPlayers WHERE name='"+name+"'";
        resSet = statmt.executeQuery(sql);
        int number = resSet.getInt("number");
        return number;
    }

    public static String getPosition(String name) throws ClassNotFoundException, SQLException{
        statmt = conn.createStatement();
        String sql = "SELECT position FROM footballPlayers WHERE name='"+name+"'";
        resSet = statmt.executeQuery(sql);
        String position = resSet.getString("position");
        return position;
    }

    public static String getSummary(String name) throws ClassNotFoundException, SQLException{
        statmt = conn.createStatement();
        String sql = "SELECT summary FROM footballPlayers WHERE name='"+name+"'";
        resSet = statmt.executeQuery(sql);
        String summary = resSet.getString("summary");
        return summary;
    }


    //Close DB connection
    public static void CloseDB()throws ClassNotFoundException, SQLException{
        conn.close();
        statmt.close();
        resSet.close();
        System.out.println("Connection was closed!");
    }
}
