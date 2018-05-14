package zadaci;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import model.Roba;

import java.sql.SQLException;
import java.util.List;

public class Zadatak4BrisanjeVrednosti {
    public static void main(String[] args) {

        JdbcConnectionSource connectionSource = null;

        try {
            connectionSource = new JdbcConnectionSource("jdbc:sqlite:avionRoba.db");
            List<Roba> roba = Zadatak2DodavanjeVrednosti.robaDao.queryForAll();
            for(Roba r: roba){
                System.out.println(r);
            }

            List<Roba> pomocnaRoba = Zadatak2DodavanjeVrednosti.robaDao.queryForEq(Roba.POLJE_NAZIV, "Voda" );
            Roba robaZaBrisanje = pomocnaRoba.get(0);
            Zadatak2DodavanjeVrednosti.robaDao.delete(robaZaBrisanje);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
