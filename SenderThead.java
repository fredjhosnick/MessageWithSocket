package com.gmail.goodonline;

import javax.xml.crypto.Data;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

public class SenderThead implements  Runnable {

    private MessageProvider messageProvider;
    private String sender;

    public SenderThead(MessageProvider messageProvider, String sender) {
        super();
        this.messageProvider = messageProvider;
        this.sender = sender;


    }

    public SenderThead() {
    }

    public void setMessageProvider(MessageProvider messageProvider) {
        this.messageProvider = messageProvider;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    @Override
    public void run() {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            Thread thS = Thread.currentThread();
            for (; !thS.isInterrupted(); ) {
                String text = br.readLine();
                Message message = new Message(text, new Date(), sender);
                messageProvider.sendMessage(message);
            }


        } catch (IOException e) {
            System.out.println(e);
        }
        System.out.println("SenderThread shutdown");
    }
}





