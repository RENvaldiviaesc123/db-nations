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
    private static final String QUERY_MILESTONE_THREE = "";
    private static final String QUERY_BONUS_MILESTONE = "";

    public static void main(String[] args) {
        //Inizializzamo lo scanner
        Scanner scan = new Scanner(System.in);


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
                        System.out.print(" PAESE : " );
                        System.out.println(nomePaese);
                        System.out.print(" ID PAESE ");
                        System.out.println(id);
                        System.out.print(" REGIONE ");
                        System.out.println(regions);
                        System.out.print(" CONTINENTE ");
                        System.out.println(continents);
                    }
                }
            }



        } catch (SQLException exception) {
            System.out.println("An error ocurred");
        }

























        //Chiudiamo lo scanner
        scan.close();
    }

}
