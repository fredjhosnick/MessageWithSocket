package com.gmail.goodonline;

import com.sun.xml.internal.ws.wsdl.writer.document.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class MessageSocket implements MessageProvider {

    private Socket socet;
    private ObjectInputStream ois=null;
    private ObjectOutputStream oos=null;

    public MessageSocket(Socket socet) {
        this.socet = socet;
    }

    public MessageSocket() {
    }

    public Socket getSocet() {
        return socet;
    }

    public void setSocet(Socket socet) throws IOException {
        this.socet = socet;
        oos =  new ObjectOutputStream(socet.getOutputStream());
        ois= new ObjectInputStream(socet.getInputStream());

    }
    @Override
    public void sendMessage(com.gmail.goodonline.Message message) throws IOException {
        try {
            oos.writeObject(message);
        }catch (IOException e){
            closeStream();

            throw e;
        }

    }



    @Override
    public com.gmail.goodonline.Message readMessange() throws IOException {
        try {

            return (com.gmail.goodonline.Message) ois.readObject();

        } catch (ClassNotFoundException e) {

            return null;
        } catch (IOException e) {
            throw e;
        }
    }

    private  final void closeStream(){

        try {
            oos.close();
            ois.close();
        }catch (IOException e){

            e.printStackTrace();
        }
    }
}
