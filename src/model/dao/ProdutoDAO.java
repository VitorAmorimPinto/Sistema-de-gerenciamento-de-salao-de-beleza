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
import ModeloClas.Produto;

/**
 *
 * @author aluno
 */
public class ProdutoDAO {
    
    public void criarProduto(Produto p1) {
        


        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO tb_produto (quantidade,produto,valor)VALUES(?,?,?)");
            
            stmt.setInt(1, p1.getQuantidade());
            stmt.setString(2,p1.getProduto());
            stmt.setDouble(3,p1.getValor());

            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }
    public ArrayList<Produto> read() {

        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<Produto> produtos = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM tb_produto");
            rs = stmt.executeQuery();

            while (rs.next()) {

               Produto p1 = new Produto();

                p1.setId(rs.getInt("id"));
                p1.setQuantidade(rs.getInt("quantidade"));
                p1.setProduto(rs.getString("produto"));
                p1.setValor(rs.getDouble("valor"));
                produtos.add(p1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return produtos;

    }
    
    
    
    
    public ArrayList<Produto> readForProduto(String produto) {

        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<Produto> produtos = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM tb_produto WHERE produto LIKE ? order by produto asc");
            stmt.setString(1, "%"+produto+"%");
            
            rs = stmt.executeQuery();

            while (rs.next()) {

                Produto p1 = new Produto();

                p1.setId(rs.getInt("id"));
                p1.setProduto(rs.getString("produto"));
                p1.setQuantidade(rs.getInt("quantidade"));
                p1.setValor(rs.getDouble("valor"));
                produtos.add(p1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return produtos;

    }
    
     public void deleteProduto(Produto p) {

        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM tb_produto WHERE id = ?");
            stmt.setInt(1, p.getId());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }
    
    public void updateProduto(Produto p) {

        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE tb_produto SET produto = ? ,quantidade = ?,valor = ? WHERE id = ?");
            stmt.setInt(4, p.getId());           
            stmt.setString(1, p.getProduto());            
            stmt.setInt(2, p.getQuantidade());            
            stmt.setDouble(3, p.getValor());
                      
            stmt.executeUpdate();
           

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }
     
     
}
