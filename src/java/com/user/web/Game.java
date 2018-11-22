/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.user.web;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author bleezy
 */
@ManagedBean
@RequestScoped
public class Game {

    /**
     * Creates a new instance of game
     */
    private String IDgame;
    public void setIDgame(String IDgame) {
    this.IDgame = IDgame;
    }
    public String getIDgame() {
        return IDgame;
    }

    private String NAMAgame;
    public void setNAMAgame(String NAMAgame) {
        this.NAMAgame = NAMAgame;
    }
    public String getNAMAgame() {
        return NAMAgame;
    }
    
    private String JENISgame;
    public void setJENISgame(String JENISgame) {
        this.JENISgame = JENISgame;
    }
    public String getJENISgame() {
        return JENISgame;
    }
   
    private Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap(); 

public String Edit_game(){
     FacesContext fc = FacesContext.getCurrentInstance();
     Map<String,String > params = fc.getExternalContext().getRequestParameterMap();
     String Field_ID_game = params.get("action");
     try {
          Koneksi obj_koneksi = new Koneksi();
          Connection connection = obj_koneksi.get_connection();
          Statement st = connection.createStatement();
          ResultSet rs = st.executeQuery("select * from game where ID_game="+Field_ID_game);
          Game obj_game = new Game();
          rs.next();
          obj_game.setIDgame(rs.getString("ID_game"));
          obj_game.setNAMAgame(rs.getString("Nama_game"));
          obj_game.setJENISgame(rs.getString("Jenis_game"));
          sessionMap.put("Editgame", obj_game);  
      } catch (Exception e) {
            System.out.println(e);
      }
     return "/Editgame.xhtml?faces-redirect=true";   
}

public String Delete_game(){
      FacesContext fc = FacesContext.getCurrentInstance();
      Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
      String Field_ID_game = params.get("action");
      try {
         Koneksi obj_koneksi = new Koneksi();
         Connection connection = obj_koneksi.get_connection();
         PreparedStatement ps = connection.prepareStatement("delete from game where ID_game=?");
         ps.setString(1, Field_ID_game);
         System.out.println(ps);
         ps.executeUpdate();
        } catch (Exception e) {
         System.out.println(e);
        }
       return "/game.xhtml?faces-redirect=true";   
}

public String Update_game(){
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
	String Update_ID_game= params.get("Update_ID_game");
        try {
            Koneksi obj_koneksi = new Koneksi();
            Connection connection = obj_koneksi.get_connection();
            PreparedStatement ps = connection.prepareStatement("update game set ID_game=?, Nama_game=?, Jenis_game=? where ID_game=?");
            ps.setString(1, IDgame);
            ps.setString(2, NAMAgame);
            ps.setString(3, JENISgame);
            ps.setString(4, Update_ID_game);
            System.out.println(ps);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
       return "/game.xhtml?faces-redirect=true";   
}
    
    public ArrayList getGet_all_game() throws Exception{
        ArrayList list_of_game=new ArrayList();
             Connection connection=null;
        try {
            Koneksi obj_koneksi = new Koneksi();
            connection = obj_koneksi.get_connection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("Select * from game");
            while(rs.next()){
                Game obj_game = new Game();
                obj_game.setIDgame(rs.getString("ID_game"));
                obj_game.setNAMAgame(rs.getString("Nama_game"));
                obj_game.setJENISgame(rs.getString("Jenis_game"));
                list_of_game.add(obj_game);
            }
        } catch (Exception e) {
            System.out.println(e);
        }finally{
            if(connection!=null){
                connection.close();
            }
        }
        return list_of_game;
}
    
public String Tambah_game(){
        try {
            Connection connection=null;
            Koneksi obj_koneksi = new Koneksi();
            connection = obj_koneksi.get_connection();
            PreparedStatement ps=connection.prepareStatement("insert into game(ID_game, Nama_game, Jenis_game) value('"+IDgame+"','"+NAMAgame+"','"+JENISgame+"')");
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        return "/game.xhtml?faces-redirect=true";
    }
    public Game() {
    }
    
}
