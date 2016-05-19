/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meatdesign.chatapp.service.impl;

import com.meatdesign.chatapp.repository.MessageRepository;
import com.meatdesign.chatapp.repository.impl.MessageRepositoryImpl;
import com.meatdesign.chatapp.service.MessageService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Martin
 */
@Service("MessageService")
public class MessageServiceImpl implements MessageService{
    
    @Autowired
    MessageRepositoryImpl messageRepo = new MessageRepositoryImpl();
    
    @Override
    public void addUser(String username){
        messageRepo.addUser(username);
    }

    @Override
    public List<String> getNames() {
        return messageRepo.getUsers();
    }

    @Override
    public boolean inList(String name) {
        List<String> list = messageRepo.getUsers();
        return list.contains(name);
    }
}
