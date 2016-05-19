/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meatdesign.chatapp.service;

import java.util.List;

/**
 *
 * @author Martin
 */
public interface MessageService {
    public void addUser(String user);
    public List<String> getNames();
    public boolean inList(String name);
}
