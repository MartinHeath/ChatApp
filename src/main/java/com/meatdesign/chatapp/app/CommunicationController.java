/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meatdesign.chatapp.app;

import com.meatdesign.chatapp.domain.MessageTemplate;
import com.meatdesign.chatapp.domain.Communication;
import com.meatdesign.chatapp.service.impl.MessageServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CommunicationController {
    @Autowired 
    private SimpMessagingTemplate simpMessagingTemplate;
    MessageServiceImpl messageServ = new MessageServiceImpl();

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public MessageTemplate greeting(Communication message) throws Exception{
        if(!messageServ.inList(message.getSender())){
            messageServ.addUser(message.getSender());
        }
        return new MessageTemplate ((message.getCreatedOn() + " " + message.getSender() + ": " + message.getMsg()), message.getSender());
    }
    
    @RequestMapping(value={"/list"})
    @ResponseBody
    public List<String> list(){
        return messageServ.getNames();
    }
    
    @MessageMapping("/msg")
    public void message (Communication message) throws Exception{
        simpMessagingTemplate.convertAndSendToUser(message.getRecipient(), "/reply", new MessageTemplate ((message.getCreatedOn() + " " + message.getSender() + ": " + message.getMsg()), message.getSender()));
    }
    
}