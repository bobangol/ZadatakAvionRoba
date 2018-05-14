package model;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import zadaci.Zadatak2DodavanjeVrednosti;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AvionNit extends Thread {

    private Avion avion;
    private static Object lockObject = new Object();

    public AvionNit(Avion avion){
        this.avion = avion;
    }

    private void proveraOpreme(){
        System.out.println("Pocinju provere za avion "+avion.getId());
        int period = (int)(Math.random() * 2000);
        try {
            sleep(period);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Avion "+avion.getId()+" je spreman za poletanje i ceka dozvolu za poletanje");
    }

    private void poletanje() {
        synchronized (lockObject) {
            try {
                System.out.println("Avion " + avion.getId() + " izlazi na pistu i polece.");
                this.sleep(2000);
                System.out.println("Avion " + avion.getId() + " je poleteo.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void run() {
        proveraOpreme();
        poletanje();
    }

    public static Dao<Avion,Integer> avionDao;

    public static void main(String[] args) {
        JdbcConnectionSource connectionSource = null;
        try {
            connectionSource = new JdbcConnectionSource("jdbc:sqlite:avionRoba.db");
            avionDao = DaoManager.createDao(connectionSource, Avion.class);
            List<Avion> avioni = avionDao.queryForAll();
            ArrayList<AvionNit> niti = new ArrayList<>();
            for (Avion a : avioni) {
                AvionNit av = new AvionNit(a);
                niti.add(av);
                av.start();
            }
            for (AvionNit n : niti){
                try {
                    n.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Svi avioni su poleteli");
    }
    }

