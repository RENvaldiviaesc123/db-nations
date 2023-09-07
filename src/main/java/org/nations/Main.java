package org.nations;

import java.sql.*;
import java.util.Scanner;

public class Main {
    //Parametri per la connessione al database
    private static final String DB_URL = "jdbc:mysql://localhost:3306/nations";
    private static final String DB_USER = "root";
    private final static String DB_PASSWORD = "root";

    //Le query da eseguire
    private static final String QUERY_MILESTONE_ONE = "select countries.name as country_name, countries.country_id as country_id, regions.name as region_name, continents.name as continent_name from countries join regions on regions.region_id  = countries.country_id join continents on continents.continent_id = regions.region_id order by countries.name;";
    private static final String QUERY_MILESTONE_THREE = "select countries.name as country_name, countries.country_id as country_id, regions.name as region_name, continents.name as continent_name from countries join regions on regions.region_id  = countries.country_id join continents on continents.continent_id = regions.region_id where countries.name like '%?%' order by countries.name;";
    private static final String QUERY_BONUS_MILESTONE = "";

    public static void main(String[] args) {
        //Inizializzamo lo scanner
        Scanner scan = new Scanner(System.in);

        System.out.print(" PAESE " );
        System.out.print(" ID PAESE ");
        System.out.print(" REGIONE ");
        System.out.print(" CONTINENTE ");
        //Mi connetto al database
        try (Connection connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD)){
            //Preparo la query della milestone 1
            try (PreparedStatement ps = connection.prepareStatement(QUERY_MILESTONE_ONE)){
                try (ResultSet rs = ps.executeQuery()){
                    while (rs.next()) {
                        String nomePaese = rs.getString("country_name");
                        int id = rs.getInt("country_id");
                        String regions = rs.getString("region_name");
                        String continents = rs.getString("continent_name");
                        //Stampiamo i risultati
                        System.out.println("");
                        System.out.print("  " + nomePaese + "  ");

                        System.out.print("  " +id + "  ");

                        System.out.print("  " +regions + "  ");

                        System.out.print("  " +continents + "  ");
                        System.out.println("");
                    }
                }
            }
            //MILESTONE 3
            System.out.println("");
            System.out.println("Cerca un paese per nome: ");
            String choice =scan.nextLine();
            System.out.println("Risultati contenenti: " + choice);
            System.out.print(" PAESE " );
            System.out.print(" ID PAESE ");
            System.out.print(" REGIONE ");
            System.out.print(" CONTINENTE ");
            try (PreparedStatement ps = connection.prepareStatement(QUERY_MILESTONE_THREE)) {
                ps.setString(1,choice);
                //Eseguo la query
                try(ResultSet rs = ps.executeQuery()){
                    if (rs.next()) {
                        String nomePaese = rs.getString("country_name");
                        int id = rs.getInt("country_id");
                        String regions = rs.getString("region_name");
                        String continents = rs.getString("continent_name");
                        //Stampiamo il risultato
                        System.out.println("  " + nomePaese + "  ");
                        System.out.print("  " + id + "  ");
                        System.out.print("  " + regions + "  ");
                        System.out.print("  " + continents + "  ");
                    } else {
                        System.out.println("");
                        System.out.println("0 risultati trovati con: " + choice);
                    }
                }
            }


        } catch (SQLException exception) {
            System.out.println("");
            System.out.println("An error ocurred");
        }

























        //Chiudiamo lo scanner
        scan.close();
    }

}
