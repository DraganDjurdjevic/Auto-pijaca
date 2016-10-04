/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ita.model;

import javax.persistence.Column;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author Dragan
 */
public class UserModel {
    
    private int user_id;
    @Size(min = 5, max = 20)
    private String name;
    @Size(min = 5, max = 20)
    private String username;
    @Pattern(regexp=".+@.+\\.[a-z]+" ,message="Invalid email address!")
    private String email;
    @Size(min = 8, max = 20)
    private String password;
    @Column(name = "rola")
    private int rola;
    @Column(name = "slika")
    private String slika;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the user_id
     */
    public int getUser_id() {
        return user_id;
    }

    /**
     * @param user_id the user_id to set
     */
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    /**
     * @return the rola
     */
    public int getRola() {
        return rola;
    }

    /**
     * @param rola the rola to set
     */
    public void setRola(int rola) {
        this.rola = rola;
    }

    /**
     * @return the slika
     */
    public String getSlika() {
        return slika;
    }

    /**
     * @param slika the slika to set
     */
    public void setSlika(String slika) {
        this.slika = slika;
    }
    
    
    
}
