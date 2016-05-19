/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meatdesign.chatapp.domain;

import java.util.Date;

/**
 *
 * @author Martin
 */
public class Communication{

    private String sender;
    private String recipient;
    private String msg;
    private Date createdOn;

    public String getSender() {
        return sender;
    }
    /*public void setSender(String sndr){
        this.sender=sndr;
    }*/
    public String getRecipient(){
        return this.recipient;
    }
    /*
    public void setRecipient(String rcpnt){
        this.recipient = rcpnt;
    }*/
    
    public String getMsg(){
        return this.msg;
    }
    /*public void setMsg(String mes){
        this.msg = mes;
    }*/
    public Date getCreatedOn(){
        return this.createdOn;
    }
    /*
    public void setCreatedOn(Date date){
        this.createdOn = date;
    }*/

}
