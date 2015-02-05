/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lsd.umc.nodeka.plugin;

/**
 *
 * @author Leviticus
 */
class push {
    
    private String message;
    private String sound;
    private String priority;
    
    public push(String message, String priority, String sound) {
        this.message = message;
        this.sound = sound;
        this.priority = priority;
    }

    public push() {
        this.message = "";
        this.sound = "none";
        this.priority = "-1";
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
}
