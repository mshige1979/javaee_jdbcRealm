/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jdbcrealmhol.bean;

import com.mycompany.jdbcrealmhol.ejb.UserRegistManager;
import java.io.IOException;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author matsumotoshigeharu
 */
@Named(value = "registPage")
@RequestScoped
public class RegistPage {

    @Getter @Setter
    private String username;
    
    @Getter @Setter
    private String mailAddress;
    
    @Getter @Setter
    private String password;
        
    @Getter @Setter
    private String group;
    
    @EJB
    UserRegistManager userRegist;
    
    /**
     * DBへユーザー情報、グループ情報の登録
     * @return 
     * @throws java.io.IOException
     */
    public String registDB() throws IOException {
        userRegist.createUserAndGroup(
                getUsername(),
                getMailAddress(), 
                getPassword(), 
                getGroup());
        
        return "reg-success";
    }
}
