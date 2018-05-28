package com.gmail.goodonline;

import java.io.IOException;

public class ListenerThread implements  Runnable {
    private MessageProvider messageProvider;
    private MessangeRender messangeRender;

    public ListenerThread(MessageProvider messageProvider, MessangeRender messangeRender) {
        this.messageProvider = messageProvider;
        this.messangeRender = messangeRender;
    }
  public  void run(){
        Thread  th = Thread.currentThread();
        try{

            for(;!th.isInterrupted();){

                Message message = messageProvider.readMessange();
                messangeRender.renderMessange(message);
            }

        }catch (IOException e){
            System.out.println(e);


        }
        System.out.println("ListenerThread shutdown");


  }
}
