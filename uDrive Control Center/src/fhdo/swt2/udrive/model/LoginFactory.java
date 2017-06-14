/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fhdo.swt2.udrive.model;

import fhdo.swt2.udrive.view.Login;

/**
 *
 * @author Marcel
 */
public class LoginFactory {
    
    Login login;
    
    public LoginFactory()
    {
        
    }
    
    public Login newInstance()
    {
        login = new Login();
        return login;
    }
}
