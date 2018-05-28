package com.gmail.goodonline;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Message implements Serializable {

   private  static final long serialVersionUID=1L;
    private String from;
    private String to;
    private Date date;


    public Message(String text, Date date, String sender) {
    }

    public Message(String from, String to, Date date) {
        this.from = from;
        this.to = to;
        this.date = date;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(from, message.from) &&
                Objects.equals(to, message.to) &&
                Objects.equals(date, message.date);
    }

    @Override
    public int hashCode() {

        return Objects.hash(from, to, date);
    }

    @Override
    public String toString() {
        return "Message{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", date=" + date +
                '}';
    }
}
