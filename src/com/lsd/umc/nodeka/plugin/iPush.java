/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lsd.umc.nodeka.plugin;

import com.lsd.umc.script.ScriptInterface;
import com.lsd.umc.util.AnsiTable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.pushover.client.MessagePriority;
import net.pushover.client.PushoverClient;
import net.pushover.client.PushoverException;
import net.pushover.client.PushoverMessage;
import net.pushover.client.PushoverRestClient;

public class iPush {

    PushoverClient client = new PushoverRestClient();
    PushoverMessage.Builder build;
    ScriptInterface script;
    String applicationKey;
    String userKey = "";
    boolean created = false;
    //ArrayList<push> l;

    public iPush() {
        this.applicationKey = "aa9Q2UdCvoEejci2pcRknTbqY2timT";
        //this.l = new ArrayList<>();
    }

    public void init(ScriptInterface script) {
        this.script = script;
        this.build = PushoverMessage.builderWithApiToken(applicationKey);
        script.print(AnsiTable.getCode("yellow") + "Pushover Plugin loaded.\001");
        script.registerCommand("pushover", "com.lsd.umc.nodeka.plugin.iPush", "menu");
    }

    public String menu(String args) throws PushoverException {
        List<String> argArray = new ArrayList<>();
        Matcher m = Pattern.compile("([^\"]\\S*|\".+?\")\\s*").matcher(args);
        while (m.find()) {
            argArray.add(m.group(1).replace("\"", ""));
        }

        if (argArray.isEmpty() || argArray.size() > 5 || "".equals(argArray.get(0))) {
            script.capture(AnsiTable.getCode("light red") + "--------------------------------------------------------------------------\001");
            script.capture(AnsiTable.getCode("yellow") + "Syntax:\001");
            script.capture(AnsiTable.getCode("white") + " > #pushover " + AnsiTable.getCode("yellow") + "user" + AnsiTable.getCode("white") + " key\001");
            script.capture(AnsiTable.getCode("white") + " > #pushover " + AnsiTable.getCode("yellow") + "notify" + AnsiTable.getCode("white") + " \"message\" priority sound\001");
            script.capture(AnsiTable.getCode("white") + " > #pushover " + AnsiTable.getCode("yellow") + "test" + AnsiTable.getCode("white") + "\001");
            script.capture(AnsiTable.getCode("white") + " > #pushover " + AnsiTable.getCode("yellow") + "sounds" + AnsiTable.getCode("white") + "\001");
            script.capture(AnsiTable.getCode("light red") + "--------------------------------------------------------------------------\001");
            return "";
        }

        if ("test".equals(argArray.get(0))) {
            client.pushMessage(PushoverMessage.builderWithApiToken(applicationKey)
                    .setUserId(userKey)
                    .setMessage("This is a test of the Pushover broadcast system!")
                    .setPriority(MessagePriority.HIGH)
                    .setTitle("Nodeka iPush Notification System")
                    .setSound("tugboat")
                    .build());
        }
        
        /*
        if ("show".equals(argArray.get(0))) {
            script.capture("-------------------------------------------------------------------------------" + AnsiTable.ansiYellowHex);
            l.stream().forEach((p) -> {
                script.capture("Message: \"" + p.getMessage() + "\" Priority: \"" + p.getPriority().toLowerCase() + "\" Sound: \"" + p.getSound() + "\"");
            });
        }

        if ("delete".equals(argArray.get(0))) {
            for (int i = 0; i < l.size(); i++) {
                Matcher del = Pattern.compile(l.get(i).getMessage()).matcher(argArray.get(1));
                try {
                    if (del.find()) {
                        l.remove(i);
                    }
                } catch (Exception e) {
                    script.capture("iPush: Error deleting pushing action");
                }
            }
        }
        */

        if ("notify".equals(argArray.get(0))) {
            try {
                //l.add(new push(argArray.get(1), argArray.get(2).toUpperCase(), argArray.get(3)));
                if (userKey.length() > 20)
                {
                client.pushMessage(PushoverMessage.builderWithApiToken(applicationKey)
                        .setUserId(userKey)
                        .setMessage(argArray.get(1))
                        .setPriority(MessagePriority.valueOf(argArray.get(2).toUpperCase()))
                        .setTitle("iPush: Nodeka Alert")
                        .setSound(argArray.get(3))
                        .build());
                }
                else
                {
                    script.capture(AnsiTable.getCode("light red") + "iPush: No user key supplied!");
                }
            } catch (Exception e) {
                script.capture(AnsiTable.getCode("light red") + "iPush: Error creating notification!");
            }
        }

        if ("userKey".equals(argArray.get(0)) && null != argArray.get(1)) {
            userKey = argArray.get(1);
        }

        return "";
    }
}
