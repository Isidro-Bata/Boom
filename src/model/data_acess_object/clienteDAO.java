/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.data_acess_object;


import Connection.ConnectionFactory;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.value_object.Cliente;

/**
 *
 * @author nazar
 */
public class clienteDAO {
    
    Cliente scl;
    
    public boolean create(String sql){
    
        Connection con=ConnectionFactory.getConnection();
        PreparedStatement stnt=null;
        try {
            
            stnt=con.prepareStatement(sql);
            stnt.executeUpdate();
            return true;
            
        } catch (SQLException ex) {
            System.out.println("ERRO "+ex);
            return false;
        }finally{
            ConnectionFactory.closeConnection((com.mysql.jdbc.Connection) con,stnt);
            
        }
        
    }//Salvar Cliente na base de dados
    
    public List<Cliente> readAll(){
        Connection con=ConnectionFactory.getConnection();
        PreparedStatement stnt=null;
        ResultSet rs=null;
             
        List<Cliente> clientes=new ArrayList<>();
        try {
            
            stnt=con.prepareStatement("Select*from cliente");
            rs=stnt.executeQuery();
            
            while(rs.next()){
            
                Cliente cl =new Cliente();
                cl.setId(rs.getInt("id"));
                cl.setNome(rs.getString("nome"));
                cl.setSexo(rs.getString("sexo"));
                cl.setNasc(rs.getInt("nasc"));
                cl.setBi(rs.getString("bi"));
                cl.setMorada(rs.getString("morada"));
                cl.setReMs(rs.getFloat("rend"));
                cl.setCtt(rs.getInt("ctt"));
                cl.setEstCv(rs.getString("estCivil"));
                clientes.add(cl);
                
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(clienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection((com.mysql.jdbc.Connection) con, stnt, rs);
        }
        return clientes; 
    }//Ler todos Clientes
    
    public Cliente validaID(int id){
        Connection con=ConnectionFactory.getConnection();
        PreparedStatement stnt=null;
        ResultSet rs=null;
        try {
            
            stnt=con.prepareStatement("Select*from cliente where id = ?");
            stnt.setInt(1, id);
            rs=stnt.executeQuery();
            rs.first();
            
                scl =new Cliente();
                scl.setId(rs.getInt("id"));
                scl.setNome(rs.getString("nome"));
                scl.setSexo(rs.getString("sexo"));
                scl.setNasc(rs.getInt("nasc"));
                scl.setBi(rs.getString("bi"));
                scl.setMorada(rs.getString("morada"));
                scl.setReMs(rs.getFloat("rend"));
                scl.setCtt(rs.getInt("ctt"));
                scl.setEstCv(rs.getString("estCivil"));
                return scl;
            
            
        }  catch (SQLException ex) {
            Logger.getLogger(clienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            ConnectionFactory.closeConnection((com.mysql.jdbc.Connection) con, stnt, rs);
        }
         
    }
    
    
    
    public boolean verID(int id){
        Connection con=ConnectionFactory.getConnection();
        PreparedStatement stnt=null;
        ResultSet rs=null;
        try {
             
            stnt=con.prepareStatement("Select count(nome) from cliente where id = ?");
            stnt.setInt(1, id);
            rs=stnt.executeQuery();
            rs.first();
            int num=rs.getInt("count(nome)");
            if(num==1)
                return true;
            else
                return false;
            
            
        }  catch (SQLException ex) {
            Logger.getLogger(clienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }finally{
            ConnectionFactory.closeConnection((com.mysql.jdbc.Connection) con, stnt, rs);
        }
         
    }
    public boolean delID(int id){
        Connection con=ConnectionFactory.getConnection();
        PreparedStatement stnt=null;
        ResultSet rs=null;
        try {
             
            stnt=con.prepareStatement("Delete from cliente where id = ?");
            stnt.setInt(1, id);
            stnt.executeUpdate();
            return true;
        }  catch (SQLException ex) {
            Logger.getLogger(clienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }finally{
            ConnectionFactory.closeConnection((com.mysql.jdbc.Connection) con, stnt, rs);
        }
         
    }

    
}
















