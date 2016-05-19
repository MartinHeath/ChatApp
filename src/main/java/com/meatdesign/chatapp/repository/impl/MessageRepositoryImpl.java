/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meatdesign.chatapp.repository.impl;

import com.meatdesign.chatapp.repository.MessageRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Martin
 */
@Repository("MessageRepository")
public class MessageRepositoryImpl implements MessageRepository{
    
    List<String> list = new ArrayList<String>();

    @Override
    public void addUser(String user) {
        list.add(user);
    }


    @Override
    public List<String> getUsers() {
        return list;
    }
    
}
