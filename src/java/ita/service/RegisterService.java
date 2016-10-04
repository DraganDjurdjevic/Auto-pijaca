/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ita.service;

import ita.domein.User;
import ita.model.UserModel;
import java.util.List;



/**
 *
 * @author Dragan
 */
public interface RegisterService {
    
    public void RegisterService(UserModel userModel);
    public List<UserModel> getAllUsers();
    public UserModel getUserById(Integer id);
    public void EditUser(UserModel userModel);
    public void deleteUser(UserModel userModel);
        
}
