/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import Conexão.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import ModeloClas.Pessoa;
import ModeloClas.Produto;
import ModeloClas.UsuarioFun;

/**
 *
 * @author aluno
 */
public class UsuarioFunDAO {
    public void cadastro(UsuarioFun fun){   
     Connection con = ConnectionFactory.getConnection();        
     PreparedStatement stmt = null;    
    try{ 
        stmt=con.prepareStatement("INSERT INTO tb_usuariofun (loginFun,senhaFun,nomeFun) VALUES(?,?,?)");
        stmt.setString(1,fun.getLoginFun());
        stmt.setString(2,fun.getSenhaFun());
        stmt.setString(3,fun.getNomeFun());
        stmt.executeUpdate(); 
        
        JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
 
    
}
      public boolean checkLogin(String login, String senha) {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        boolean check = false;

        try {

            stmt = con.prepareStatement("SELECT * FROM tb_usuariofun WHERE loginFun = ? and senhaFun = ?");
            stmt.setString(1, login);
            stmt.setString(2, senha);
            

            rs = stmt.executeQuery();

            if (rs.next()) {

                
                check = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioFunDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return check;

    }

      
      
      
      public ArrayList<UsuarioFun> readForUsuario(String usuario) {

        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<UsuarioFun> usuarios = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM tb_usuariofun WHERE loginFun LIKE ? order by loginFun asc");
            stmt.setString(1, "%"+usuario+"%");
            
            rs = stmt.executeQuery();

            while (rs.next()) {

                UsuarioFun u = new UsuarioFun();

                u.setId(rs.getInt("id"));
                u.setLoginFun(rs.getString("loginFun"));
                u.setSenhaFun(rs.getString("SenhaFun"));
                u.setNomeFun(rs.getString("nomeFun"));
                usuarios.add(u);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioFunDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return usuarios;

    }
      
      
      
       public void delete(UsuarioFun p) {

        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM tb_usuariofun WHERE id = ?");
            stmt.setInt(1, p.getId());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }
      
      
      
      public ArrayList<UsuarioFun> read() {

        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<UsuarioFun> usuarioFuns = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM tb_usuariofun");
            rs = stmt.executeQuery();

            while (rs.next()) {

               UsuarioFun p1 = new UsuarioFun();

                p1.setId(rs.getInt("id"));
                p1.setLoginFun(rs.getString("loginFun"));
                p1.setSenhaFun(rs.getString("senhaFun"));
                usuarioFuns.add(p1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioFunDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return usuarioFuns;

    }
     public void updateUsuario(UsuarioFun u) {

        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE tb_usuariofun SET loginFun = ? ,senhaFun = ?,nomeFun = ? WHERE id = ?");
            stmt.setInt(4, u.getId());           
            stmt.setString(1, u.getLoginFun());            
            stmt.setString(2, u.getSenhaFun());            
            stmt.setString(3, u.getNomeFun());
                      
            stmt.executeUpdate();
           

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }
    
    
    
    
}
