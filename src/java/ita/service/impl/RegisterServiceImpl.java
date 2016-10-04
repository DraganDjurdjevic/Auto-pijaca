/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ita.service.impl;

import ita.dao.OglasDao;
import ita.dao.RegisterDao;
import ita.domein.User;
import ita.model.UserModel;
import ita.service.RegisterService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Dragan
 */
@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private RegisterDao registerDao;

    
    @Override
    public void RegisterService(UserModel userModel) {
        User user = new User();
        user.setUser_id(userModel.getUser_id());
        user.setName(userModel.getName());
        user.setUserName(userModel.getUsername());
        user.setEmail(userModel.getEmail());
        user.setPassword(userModel.getPassword());
        user.setRola(1);
        user.setSlika("0");
        getRegisterDao().getRegister(user);
    }

    /**
     * @return the registerDao
     */
    public RegisterDao getRegisterDao() {
        return registerDao;
    }

    /**
     * @param registerDao the registerDao to set
     */
    public void setRegisterDao(RegisterDao registerDao) {
        this.registerDao = registerDao;
    }

    @Override
    public List<UserModel> getAllUsers() {
        List<UserModel> userModels = new ArrayList<>();
        List<User> users = getRegisterDao().getAllUsers();
        for (User user : users) {
            userModels.add(repack(user));
        }
        return userModels;
    }

    public UserModel repack(User user) {
        UserModel userModel = new UserModel();
        userModel.setUser_id(user.getUser_id());
        userModel.setName(user.getName());
        userModel.setUsername(user.getUserName());
        userModel.setEmail(user.getEmail());
        userModel.setPassword(user.getPassword());
        userModel.setRola(user.getRola());
        userModel.setSlika(user.getSlika());
        return userModel;
    }

    @Override
    public UserModel getUserById(Integer id) {
        User user = getRegisterDao().getUserById(id);
        UserModel userModel = repack(user);
        return userModel;
    }

    @Override
    public void EditUser(UserModel userModel) {
        User user = new User();
        user.setUser_id(userModel.getUser_id());
        user.setName(userModel.getName());
        user.setEmail(userModel.getEmail());
        user.setUserName(userModel.getUsername());
        user.setPassword(userModel.getPassword());
        user.setRola(userModel.getRola());
        user.setSlika(userModel.getSlika());
        getRegisterDao().updateUser(user);
    }

    @Override
    public void deleteUser(UserModel userModel) {
        User user = new User();
        user.setUser_id(userModel.getUser_id());
        user.setName(userModel.getName());
        user.setEmail(userModel.getEmail());
        user.setUserName(userModel.getUsername());
        user.setPassword(userModel.getPassword());
        user.setRola(userModel.getRola());
        user.setSlika(userModel.getSlika());
        getRegisterDao().deleteUser(user);
    }
}
