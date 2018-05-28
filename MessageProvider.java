package com.gmail.goodonline;

import java.io.IOException;

public interface MessageProvider {
    public  void  sendMessage(Message message)throws IOException;

     public  Message readMessange() throws  IOException;

}
