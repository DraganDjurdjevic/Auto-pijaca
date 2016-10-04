/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ita.dao;

import ita.domein.User;
import java.util.List;

/**
 *
 * @author Dragan
 */
public interface RegisterDao {

    public void getRegister(User user);

    public List<User> getAllUsers();

    public User getUserById(Integer id);

    public void updateUser(User user);
    
    public void deleteUser(User user);
}
