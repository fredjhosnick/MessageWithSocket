package com.gmail.goodonline;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.sql.SQLOutput;
import java.util.Date;

public class Main {

    public static void main(String[] args) {

Client client = new Client();
client.start();

        Message message = new Message("Kiev", "London", new Date());


        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Message.class,new MessageService());


        Gson gson = gsonBuilder.create();
        String result = gson.toJson(message);
        System.out.println(result);
        Message message1= gson.fromJson(result,Message.class);
        System.out.println(message1);


    }
}