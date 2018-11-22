/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.user.web;

import com.user.web.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author bleezy
 */
@ManagedBean
@RequestScoped
public class Gamer {

    /**
     * Creates a new instance of Gamer
     */
    private String IDgamer;
    public void setIDgamer(String IDgamer) {
      this.IDgamer = IDgamer;
    }
    public String getIDgamer() {
        return IDgamer;
    }

    private String NAMA;
    public void setNAMA(String NAMA) {
        this.NAMA = NAMA;
    }
    public String getNAMA() {
        return NAMA;
    }
    
    private String IDgame;
    public void setIDgame(String IDgame) {
        this.IDgame = IDgame;
    }
    public String getIDgame() {
        return IDgame;
    }
   
    private Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap(); 

public String Edit_gamer(){
     FacesContext fc = FacesContext.getCurrentInstance();
     Map<String,String > params = fc.getExternalContext().getRequestParameterMap();
     String Field_ID_gamer = params.get("action");
     try {
          Koneksi obj_koneksi = new Koneksi();
          Connection connection = obj_koneksi.get_connection();
          Statement st = connection.createStatement();
          ResultSet rs = st.executeQuery("select * from gamer where ID_gamer="+Field_ID_gamer);
          Gamer obj_gamer = new Gamer();
          rs.next();
          obj_gamer.setIDgamer(rs.getString("ID_gamer"));
          obj_gamer.setNAMA(rs.getString("Nama"));
          obj_gamer.setIDgame(rs.getString("ID_game"));
          sessionMap.put("Editgamer", obj_gamer);  
      } catch (Exception e) {
            System.out.println(e);
      }
     return "/Editgamer.xhtml?faces-redirect=true";   
}

public String Delete_gamer(){
      FacesContext fc = FacesContext.getCurrentInstance();
      Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
      String Field_ID_gamer = params.get("action");
      try {
         Koneksi obj_koneksi = new Koneksi();
         Connection connection = obj_koneksi.get_connection();
         PreparedStatement ps = connection.prepareStatement("delete from gamer where ID_gamer=?");
         ps.setString(1, Field_ID_gamer);
         System.out.println(ps);
         ps.executeUpdate();
        } catch (Exception e) {
         System.out.println(e);
        }
       return "/gamer.xhtml?faces-redirect=true";   
}

public String Update_gamer(){
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
	String Update_ID_gamer= params.get("Update_ID_gamer");
        try {
            Koneksi obj_koneksi = new Koneksi();
            Connection connection = obj_koneksi.get_connection();
            PreparedStatement ps = connection.prepareStatement("update gamer set ID_gamer=?, Nama=?, ID_game=? where ID_gamer=?");
            ps.setString(1, IDgamer);
            ps.setString(2, NAMA);
            ps.setString(3, IDgame);
            ps.setString(4, Update_ID_gamer);
            System.out.println(ps);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
       return "/gamer.xhtml?faces-redirect=true";   
}
    
    public ArrayList getGet_all_gamer() throws Exception{
        ArrayList list_of_gamer=new ArrayList();
             Connection connection=null;
        try {
            Koneksi obj_koneksi = new Koneksi();
            connection = obj_koneksi.get_connection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("Select * from gamer");
            while(rs.next()){
                Gamer obj_gamer = new Gamer();
                obj_gamer.setIDgamer(rs.getString("ID_gamer"));
                obj_gamer.setNAMA(rs.getString("Nama"));
                obj_gamer.setIDgame(rs.getString("ID_game"));
                list_of_gamer.add(obj_gamer);
            }
        } catch (Exception e) {
            System.out.println(e);
        }finally{
            if(connection!=null){
                connection.close();
            }
        }
        return list_of_gamer;
}
    
public String Tambah_gamer(){
        try {
            Connection connection=null;
            Koneksi obj_koneksi = new Koneksi();
            connection = obj_koneksi.get_connection();
            PreparedStatement ps=connection.prepareStatement("insert into gamer(ID_gamer, Nama, ID_game) value('"+IDgamer+"','"+NAMA+"','"+IDgame+"')");
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        return "/gamer.xhtml?faces-redirect=true";
    }
    public Gamer() {
    }
    
}
