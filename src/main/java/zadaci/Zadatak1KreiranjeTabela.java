package zadaci;

import com.j256.ormlite.jdbc.JdbcConnectionSource;

import java.sql.Connection;
import java.sql.SQLException;

public class Zadatak1KreiranjeTabela {
    public static void main(String[] args) {

        JdbcConnectionSource connectionSource = null;

        try {
            connectionSource = new JdbcConnectionSource("jdbc:sqlite:avionRoba.db");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
