package zadaci;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import model.Roba;

import java.sql.SQLException;
import java.util.List;

public class Zadatak3IzmenaVrednosti {
    public static void main(String[] args) {

        JdbcConnectionSource connectionSource = null;

        try {
            connectionSource = new JdbcConnectionSource("jdbc:sqlite:avionRoba.db");

            List<Roba> roba = Zadatak2DodavanjeVrednosti.robaDao.queryForAll();
            for(Roba r: roba){
                System.out.println(r);
            }

            List<Roba> pomocnaRoba = Zadatak2DodavanjeVrednosti.robaDao.queryForEq(Roba.POLJE_OPIS, "Plasticna stolica" );
            Roba robaZaIzmenu = pomocnaRoba.get(0);
            robaZaIzmenu.setOpis("Drvena stolica");
            Zadatak2DodavanjeVrednosti.robaDao.update(robaZaIzmenu);


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
