/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author brian
 */
public class NewEmptyJUnitTest {

    public NewEmptyJUnitTest() {
    }

    @Before
    public void setUp() {
    }

    @Test
    public void hello() {

        Connection connection = null;
        try {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:/home/brian/tools/nodeworkspaces/united_states_of_america.mbtiles");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            ResultSet rs = statement.executeQuery(
                    "SELECT tile_data FROM tiles "
                    + "WHERE zoom_level = 9 AND tile_column = 102 "
                    + "AND tile_row = 292;"
            );
            while (rs.next()) {
                // read the result set
                System.out.println("\n\nrs" + rs);
            }
        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e);
            }
        }
    }
}
