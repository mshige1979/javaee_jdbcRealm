/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jdbcrealmhol.bean;

import com.mycompany.jdbcrealmhol.ejb.UserRegistManager;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author matsumotoshigeharu
 */
@Named(value = "removePage")
@RequestScoped
public class RemovePage {

    @Getter @Setter
    private String username;
    
    @EJB
    UserRegistManager userManager;
    
    /**
     * DBからユーザー情報の削除
     * @return 
     */
    public String removeDB(){
        userManager.removeUser(getUsername());
        return "rem-success.xhtml";
    }
    
}
