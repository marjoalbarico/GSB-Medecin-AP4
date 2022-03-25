package modele;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionClass {
public Connection connection;
    public  Connection getConnection(){


        String dbName="gsbMedecin";
        String userName="root";
        String password="";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

        connection= DriverManager.getConnection("jdbc:mysql://localhost:3307/"+dbName,userName,password);
        //System.out.println("database connecté");


        } catch (Exception e) {
        	System.out.println("database pas connecté");
            e.printStackTrace();
        }


        return connection;
    }

}
