/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meatdesign.chatapp.domain;

/**
 *
 * @author Martin
 */
public class MessageTemplate {
    private String content;
    private String recipient;

    public MessageTemplate(String content, String recipient) {
        this.content = content;
        this.recipient = recipient;
    }

    public String getContent() {
        return content;
    }
    public String getRecipient() {
        return recipient;
    }
}
