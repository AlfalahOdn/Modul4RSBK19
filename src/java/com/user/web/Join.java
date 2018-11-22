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
public class Join {

    /**
     * Creates a new instance of Join
     */
        private String IDgamer;
    public void setIDgamer(String IDgamer) {
    this.IDgamer = IDgamer;
    }
    public String getIDgamer() {
        return IDgamer;
    }

    private String NAMAgame;
    public void setNAMAgame(String NAMAgame) {
        this.NAMAgame = NAMAgame;
    }
    public String getNAMAgame() {
        return NAMAgame;
    }
    private String NAMA;
    public void setNAMA(String NAMA) {
        this.NAMA = NAMA;
    }
    public String getNAMA() {
        return NAMA;
    }

    private Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap(); 
    
     public ArrayList getGet_all_join() throws Exception{
        ArrayList list_of_join=new ArrayList();
             Connection connection=null;
        try {
            Koneksi obj_koneksi = new Koneksi();
            connection = obj_koneksi.get_connection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("Select a.ID_gamer, a.Nama, a.ID_game, b.ID_game, b.Nama_game FROM gamer a LEFT JOIN game b ON a.ID_game = b.ID_game ;");
            while(rs.next()){
                Join obj_Join = new Join();
                obj_Join.setIDgamer(rs.getString("ID_gamer"));
                obj_Join.setNAMA(rs.getString("Nama"));
                obj_Join.setNAMAgame(rs.getString("Nama_game"));
                list_of_join.add(obj_Join);
            }
        } catch (Exception e) {
            System.out.println(e);
        }finally{
            if(connection!=null){
                connection.close();
            }
        }
        return list_of_join;
}

    public Join() {
    }
    
}
