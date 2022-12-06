package database_connect;

import java.sql.*;

// Import Cats.java from cats_api
import cats_api.Cats;

public class GetCatFact {

    public static void main(String[] args) throws Exception {

        // Object of Cats class
        Cats catFactObj = new Cats();

        // Get fact
        String fact = (String) catFactObj.getCatFact().get("fact");

        // Get fact length
        long catLength = (long) catFactObj.getCatFact().get("length");

        // System.out.println("Cat Fact: " + fact);
        // System.out.println("Cat Length: " + catLength);

        //
        // Connection to database
        try {

            // Create connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/cat_facts", "root", "mysqlpassword");

            // Save cat fact to database
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO facts (fact, fact_length) VALUES (?, ?)");

            pstmt.setString(1, fact);
            pstmt.setLong(2, catLength);

            pstmt.executeUpdate();

            // Read cat facts from database
            ResultSet rs = pstmt.executeQuery(
                    "SELECT * FROM facts");

            // Display cat facts
            while (rs.next()) {
                System.out
                        .println("Fact: " + rs.getString("fact") + " Fact Length: " + rs.getString("fact_length"));
            }
            ;

        } catch (Exception e) {

            System.out.println(e);

        }
        ;

    };

}
