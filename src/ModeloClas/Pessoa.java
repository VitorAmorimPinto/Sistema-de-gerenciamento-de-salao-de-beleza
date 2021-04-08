/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloClas;

/**
 *
 * @author aluno
 */
public class Pessoa {
    private int id;
    private String nome,telefone,NascData;

    

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNascData() {
        return NascData;
    }

    public void setNascData(String NascData) {
        this.NascData = NascData;
    }


}
