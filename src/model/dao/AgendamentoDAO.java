/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import Apresentacao.TeladeAgendamentoAdm;
import Conexão.ConnectionFactory;
import ModeloClas.Agendamento;
import ModeloClas.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author aluno
 */
public class AgendamentoDAO {
     public void Agendar(Agendamento ag) {
        
         TeladeAgendamentoAdm tg = new TeladeAgendamentoAdm();
         
        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO tb_agendamento (procedimento,dataAg,horario,preco,tb_pessoa_id) VALUES(?,?,?,?,?)");
            
            stmt.setString(1,ag.getProcedimento());
            stmt.setString(2,ag.getData());
            stmt.setDouble(3,ag.getHorario());
            stmt.setDouble(4,ag.getPreco());
            stmt.setString(5,ag.getNome());
            stmt.executeUpdate();          
            
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

        
    }
    
     public ArrayList<Agendamento> Ler() {

        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<Agendamento> agendamentos = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM tb_agendamento");
            rs = stmt.executeQuery();

            while (rs.next()) {

               Agendamento ag = new Agendamento();

               ag.setId(rs.getInt("id"));
                ag.setProcedimento(rs.getString("procedimento"));
                ag.setData(rs.getString("dataAg"));
                ag.setHorario(rs.getDouble("horario"));
                ag.setPreco(rs.getDouble("preco"));
                ag.setNome(rs.getString("tb_pessoa_id"));
                agendamentos.add(ag);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AgendamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return agendamentos;

    }
     
      public ArrayList<Agendamento> BuscaPorAgendamento(String agenda) {

        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<Agendamento> agendamentos = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM tb_agendamento WHERE procedimento LIKE ? order by procedimento asc");
            stmt.setString(1, "%"+agenda+"%");
            
            rs = stmt.executeQuery();

            while (rs.next()) {

               Agendamento ag = new Agendamento();

                ag.setId(rs.getInt("id"));
                ag.setProcedimento(rs.getString("procedimento"));
                ag.setData(rs.getString("dataAg"));
                ag.setHorario(rs.getDouble("horario"));
                ag.setPreco(rs.getDouble("preco"));
                ag.setNome(rs.getString("tb_pessoa_id"));
                agendamentos.add(ag);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AgendamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return agendamentos;

    }
       public void deleteAgendamento(Agendamento ag) {

        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM tb_agendamento WHERE id = ?");
            stmt.setInt(1, ag.getId());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }
    public void updateAgenda(Agendamento ag) {

        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE tb_agendamento SET procedimento = ? ,dataAg = ?,horario = ? ,preco = ? WHERE id = ?");
            stmt.setInt(5, ag.getId());           
            stmt.setString(1, ag.getProcedimento());            
            stmt.setString(2, ag.getData());            
            stmt.setDouble(3, ag.getHorario());
            stmt.setDouble(4,ag.getPreco());
                      
            stmt.executeUpdate();
           

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }
       
}
