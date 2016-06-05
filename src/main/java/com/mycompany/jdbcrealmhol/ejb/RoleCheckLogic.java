/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jdbcrealmhol.ejb;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;

/**
 *
 * @author matsumotoshigeharu
 */
@Stateless
public class RoleCheckLogic {

    @RolesAllowed("admin")
    public String executableByAdmin(){
        return "管理者による実行が可能なロジック";
    }

    @RolesAllowed("user")
    public String executableByUser(){
        return "ユーザーによる実行が可能なロジック";
    }
}
