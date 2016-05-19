/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meatdesign.chatapp.repository;

import java.util.List;

/**
 *
 * @author Martin
 */
public interface MessageRepository {
    public void addUser(String user);
    public List<String> getUsers ();
}
