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
public class UsuarioAdm {
    private int id;
    private String loginAdm,SenhaAdm;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLoginAdm() {
        return loginAdm;
    }

    public void setLoginAdm(String loginAdm) {
        this.loginAdm = loginAdm;
    }

    public String getSenhaAdm() {
        return SenhaAdm;
    }

    public void setSenhaAdm(String SenhaAdm) {
        this.SenhaAdm = SenhaAdm;
    }

    public boolean checkLogin(String text, String text0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
}
