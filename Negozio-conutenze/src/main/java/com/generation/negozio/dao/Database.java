package com.generation.negozio.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Database implements IDatabase {

    private final String user = "root";
    private final String password = "root";
    private final String url = "jdbc:mysql://localhost:3306/";
    private final String time_zone = "?serverTimezone=UTC";
    private Connection connection;


   public Database() {
    }



    public void openConnection(String db){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url+ db+time_zone,user,password);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void closeConnection(){
        try {
            connection.close();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    public Connection getConnection(){
        return connection;
    }
    
    public void closeCommand(PreparedStatement ps, ResultSet rs) {
        try {
            if(ps != null) {
                ps.close();
            }
            if(rs != null) {
                rs.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int executeUpdate(String query, String... params) {
        openConnection("negozio");
        int row = 0; 
       PreparedStatement ps = null;
       ResultSet rs = null;
       try{
        String[] colums = {"id"};
        ps = connection.prepareStatement(query, colums);
        for (int i = 0; i < params.length; i++) {
            ps.setString(i+1, params[i]);
        }

        row = ps.executeUpdate();
        rs = ps.getGeneratedKeys(); //ritorna un resultset con le chiavi generate
        //se il resultset ha una riga, prendo il valore della prima colonna "id"
        if(rs.next()){
            //prendo il valore che si trova nella colonna 1 che è anche l'unica colonna creata con la query, cioè quella di id
            row = rs.getInt(1);
        }
            return row;
        }catch (SQLException e) {
              e.printStackTrace();     
       }catch (Exception e) {
        e.printStackTrace();
       }finally{
           closeCommand(ps, rs);
           closeConnection();
       }
        return row;
    }

    //ALT + SHIFT + O importa le classi che servono in automatico
    @Override
    public List<Map<String, String>> executeQuery(String query, String... params) {
        openConnection("negozio");
        List<Map<String, String>> result = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Map<String, String> map = null;
        try {
            ps = connection.prepareStatement(query);
            for (int i = 0; i < params.length; i++) {
                ps.setString(i+1, params[i]);
            }
            rs = ps.executeQuery();
            while(rs.next()){
                map = new HashMap<>();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    String column = rs.getMetaData().getColumnName(i);
                    String value = rs.getString(column);
                    System.out.println(column + " " + value);
                    map.put(column, value);
                }
                result.add(map);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            closeCommand(ps, rs);
            closeConnection();
        }
        return result;
    }
    
}
