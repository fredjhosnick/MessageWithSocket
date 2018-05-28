package com.gmail.goodonline;


import com.google.gson.*;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public   class MessageService  implements JsonSerializer<Message>,JsonDeserializer<Message> {

    SimpleDateFormat sdf = new SimpleDateFormat("dd:MM:yyyy-hh:mm");
@Override
 public Message deserialize(JsonElement arg0, Type arg1, JsonDeserializationContext arg2)throws
         JsonParseException{

     JsonObject jsonObject= arg0.getAsJsonObject();
     String from=jsonObject.get("from").getAsString();
     String to = jsonObject.get("to").getAsString();
     Date date = new Date();
     try {

         date=sdf.parse(jsonObject.get("date").getAsString());
     }catch (ParseException e){

         e.printStackTrace();
     }
     return  new Message(from,to,date);



 }


    @Override
    public JsonElement serialize(Message arg0, Type arg1, JsonSerializationContext arg2) {

    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("from",arg0.getFrom());
    jsonObject.addProperty("to",arg0.getTo());
    jsonObject.addProperty("date",sdf.format(arg0.getDate()));
        return jsonObject;
    }
}
