/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jdbcrealmhol.bean;

import java.io.IOException;
import java.security.Principal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author matsumotoshigeharu
 */
@Named(value = "indexPage")
@RequestScoped
public class IndexPage {

    @Getter @Setter
    private String username;
    
    @Getter @Setter
    private String password;
    
    /**
     * ログインボタンが押された際の処理
     * @return 
     */
    public String login(){
        
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        HttpServletRequest request = (HttpServletRequest)externalContext.getRequest();
        
        try{
            request.login(getUsername(), getPassword());
            
        } catch (ServletException ex) {
            context.addMessage(null, 
                    new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, 
                            "ログインに失敗しました。",
                            "ユーザー名、パスワードを正しく入力して下さい。"
                    ));
            return "";
        }
        
        return "home.xhtml?faces-redirect=true";
    }
    
    public String logout(){
        
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        HttpServletRequest request = (HttpServletRequest)externalContext.getRequest();
        
        try{
            request.logout();
        } catch (ServletException ex) {
            Logger.getLogger(IndexPage.class.getName()).log(Level.SEVERE, "ログアウト失敗");
        }
        
        return "index.xhtml?faces-redirect=true";
    }
    
    /**
     * 既にログインすみだった場合、ログイン後のページへリダイレクトし
     * login.xhtmlを非表示
     */
    public void onPageLoad() throws ServletException {
        
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        HttpServletRequest request = (HttpServletRequest)externalContext.getRequest();
        
        Principal principal = request.getUserPrincipal();
        if(principal != null){
            try{
                StringBuilder redirectURL = new StringBuilder(request.getContextPath());
                redirectURL.append("/faces/login/home.xhtml");
                FacesContext.getCurrentInstance().getExternalContext().redirect(redirectURL.toString());
            }catch(IOException ex){
                request.logout();
            }
        }
        
    }
    
}
