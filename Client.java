package com.gmail.goodonline;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    private String nickName;
    private Socket socet;
    private String serverIP;
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private MessageProvider messageProvider = null;
    private MessangeRender messageRender = null;
    private Thread listenerThread;
    private Thread senderThread;

    public Client() {
        super();
    }

    private boolean init() {
        for (boolean correct = false; correct != true; ) {
            try {
                System.out.println("Please input Server IP");
                this.serverIP = br.readLine();
                this.socet = new Socket(serverIP, 20000);
                System.out.println("Input your nickName");
                this.nickName = br.readLine();
                correct = true;
            } catch (UnknownHostException e) {
                System.out.println("Unable to connect. Try another IP");
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        System.out.println("Initialization start");
        MessageSocket messageSocket = new
                MessageSocket();
        MessageService messageService = new
                MessageService();
        System.out.println("Set Provider and Render Implementation");
        try {
            messageSocket.setSocet(this.socet);
            this.messageProvider = messageSocket;
            this.messageRender = (MessangeRender) messageService;
        } catch (IOException e) {
            System.out.println(e);
            return false;
        }
        this.listenerThread = new Thread(new ListenerThread(this.messageProvider,
                this.messageRender));
        this.senderThread = new Thread(new SenderThead(messageProvider, nickName));
        System.out.println("Initialization end");
        return true;
    }

    public void start() {
        boolean startInit = this.init();
        if (startInit == false) {
            System.out.println("Initialization failed ....");
            return;
        }
        System.out.println("Start chat. Type text and press Enter");
        listenerThread.start();
        senderThread.start();
        for (; listenerThread.isAlive() && senderThread.isAlive(); ) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }
}
