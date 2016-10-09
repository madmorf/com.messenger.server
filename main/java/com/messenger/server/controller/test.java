package com.messenger.server.controller;


import java.sql.*;

public class test {


    public static void main(String[] args) {

        Connection conn = null;
        PreparedStatement ps;
        try
        {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://127.0.0.1/";
            conn = DriverManager.getConnection(url,"postgres", "ujvjcbre");
            ps=conn.prepareStatement("CREATE DATABASE test ");
            ResultSet rs;
            rs = ps.executeQuery();
                while (rs.next()){
                    System.out.println(rs.getString(1));
                    if (rs.getString(1).equals("test_db")){
                        url.concat("test_db");
                    }
                }
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
            System.exit(1);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.exit(2);
        }


    }
}
