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
import model.value_object.Emprestimo;

/**
 *
 * @author nazar
 */
public class emprestimoDAO {
    
    model.value_object.Emprestimo emp;
    
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
    }
    
    public List<Emprestimo> readAll(){
        Connection con=ConnectionFactory.getConnection();
        PreparedStatement stnt=null;
        ResultSet rs=null;
             
        List<Emprestimo> emps=new ArrayList<>();
        try {
            
            stnt=con.prepareStatement("Select*from emprestimo");
            rs=stnt.executeQuery();
            
            while(rs.next()){
                emp=new Emprestimo();
                emp.setId(rs.getInt("idEmp"));
                emp.setIdCliente(rs.getInt("idCli"));
                emp.setCredito(rs.getFloat("Cred"));
                emp.setJr(rs.getFloat("jur"));
                emp.setVlTot(rs.getFloat("vT"));
                emp.setVlPrest(rs.getFloat("vlPres"));
                emp.setNumPrest(rs.getInt("num"));
                emp.setStts(rs.getBoolean("stts"));
                emp.setIdFuncio(rs.getInt("idFunc"));
                emps.add(emp);
                
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(clienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection((com.mysql.jdbc.Connection) con, stnt, rs);
        }
        return emps; 
    }

   public Emprestimo validaID(int id){
        Connection con=ConnectionFactory.getConnection();
        PreparedStatement stnt=null;
        ResultSet rs=null;
        try {
            
            stnt=con.prepareStatement("Select*from emprestimo where idEmp = ?");
            stnt.setInt(1, id);
            rs=stnt.executeQuery();
            rs.first();
            
                emp =new Emprestimo();
                emp.setId(rs.getInt("idEmp"));
                emp.setIdCliente(rs.getInt("idCli"));
                emp.setCredito(rs.getFloat("Cred"));
                float vl=rs.getFloat("Cred")+rs.getFloat("Cred")*rs.getFloat("jur");
                emp.setJr(rs.getFloat("jur"));
                emp.setVlTot(rs.getFloat("vT"));
                emp.setVlPrest(rs.getFloat("vlPres"));
                emp.setNumPrest(rs.getInt("num"));
                emp.setStts(rs.getBoolean("stts"));
                emp.setIdFuncio(rs.getInt("idFunc"));
                emp.setVlPago(vl-rs.getFloat("vT"));
                return emp;
            
            
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
             
            stnt=con.prepareStatement("Select count(idEmp) as num from emprestimo where idEmp = ?");
            stnt.setInt(1, id);
            rs=stnt.executeQuery();
            rs.first();
            int num=rs.getInt("num");
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
             
            stnt=con.prepareStatement("Delete from emprestimo where idEmp = ?");
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
