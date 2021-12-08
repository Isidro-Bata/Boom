/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.data_acess_object;

import Connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.value_object.Cliente;
import model.value_object.Funcionario;

/**
 *
 * @author nazar
 */
public class funcionarioDAO {
    
    Funcionario fc;
    
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
    
    public List<Funcionario> readAll(){
        Connection con=ConnectionFactory.getConnection();
        PreparedStatement stnt=null;
        ResultSet rs=null;
             
        List<Funcionario> funcs=new ArrayList<>();
        try {
            
            stnt=con.prepareStatement("Select*from funcionario");
            rs=stnt.executeQuery();
            
            while(rs.next()){
            
                fc =new Funcionario();
                fc.setId(rs.getInt("id"));
                fc.setNome(rs.getString("nome"));
                fc.setSx(rs.getString("sexo"));
                fc.setNasc(rs.getInt("nasc"));
                fc.setBi(rs.getString("bi"));
                fc.setSalario(rs.getFloat("salario"));
                fc.setCtt(rs.getInt("ctt"));
                funcs.add(fc);
                
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(clienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection((com.mysql.jdbc.Connection) con, stnt, rs);
        }
        return funcs; 
    }//Ler todos Clientes
    
    public Funcionario validaID(int id){
        Connection con=ConnectionFactory.getConnection();
        PreparedStatement stnt=null;
        ResultSet rs=null;
        try {
            
            stnt=con.prepareStatement("Select*from funcionario where id = ?");
            stnt.setInt(1, id);
            rs=stnt.executeQuery();
            rs.first();
            
                fc =new Funcionario();
                fc.setId(rs.getInt("id"));
                fc.setNome(rs.getString("nome"));
                fc.setSx(rs.getString("sexo"));
                fc.setNasc(rs.getInt("nasc"));
                fc.setBi(rs.getString("bi"));
                fc.setSalario(rs.getFloat("salario"));
                fc.setCtt(rs.getInt("ctt"));
                return fc;
            
            
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
             
            stnt=con.prepareStatement("Select count(nome) from funcionario where id = ?");
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
             
            stnt=con.prepareStatement("Delete from funcionario where id = ?");
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
