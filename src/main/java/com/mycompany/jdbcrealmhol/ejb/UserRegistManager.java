/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jdbcrealmhol.ejb;

import com.mycompany.jdbcrealmhol.common.SHA256Encoder;
import com.mycompany.jdbcrealmhol.entity.Grouptable;
import com.mycompany.jdbcrealmhol.entity.GrouptablePK;
import com.mycompany.jdbcrealmhol.entity.Usertable;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author matsumotoshigeharu
 */
@Stateless
public class UserRegistManager {

    @PersistenceContext
    EntityManager em;
    
    /**
     * 指定したユーザー、メールアドレス、パスワード、グループ名でDBへ登録
     * @param username
     * @param mailAddress
     * @param password
     * @param groupname
     */
    public void createUserAndGroup(String username,
                                   String mailAddress,
                                   String password,
                                   String groupname){
        
        Usertable user = new Usertable();
        user.setUsername(username);
        user.setMailaddress(mailAddress);
        
        SHA256Encoder encoder = new SHA256Encoder();
        String encodePassword = encoder.encodePassword(password);
        user.setPassword(encodePassword);
        
        Grouptable group = new Grouptable();
        group.setGrouptablePK(new GrouptablePK(username, groupname));
        group.setUsertable(user);
        
        em.persist(user);
        em.persist(group);
        
    }
    
    /**
     * DBから指定したユーザーの削除
     * @param username
     */
    public void removeUser(String username){
        Usertable user = em.find(Usertable.class, username);
        em.remove(user);
    }
    
    /**
     * DBから指定したユーザーの検索
     * @param username
     * @return 
     */
    public Usertable findUser(String username){
        Usertable user = em.find(Usertable.class, username);
        return user;
    }
            
}
