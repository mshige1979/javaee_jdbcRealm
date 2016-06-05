/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jdbcrealmhol.bean;

import com.mycompany.jdbcrealmhol.ejb.RoleCheckLogic;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author matsumotoshigeharu
 */
@Named(value = "homePage")
@RequestScoped
public class HomePage {

    @EJB
    RoleCheckLogic roleCheckLogic;
    
    private String roleCheckerString;
    
    public String getRoleCheckerString(){
    
        if(isUserInRole("admin")){
            String adminRoleString = roleCheckLogic.executableByAdmin();
            roleCheckerString = adminRoleString;
        }
        if(isUserInRole("user")){
            String userRoleString = roleCheckLogic.executableByUser();
            roleCheckerString = userRoleString;
        }
        
        return roleCheckerString;
    }
    
    /**
     * ログインしたユーザーが引数で指定した役割を持つユーザーか否かを検証
     */
    public boolean isUserInRole(String role){
        return FacesContext.getCurrentInstance().getExternalContext().isUserInRole(role);
    }
    
}
