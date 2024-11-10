package org.example.swtlabormimobaleph;

public class Message {

    public String Reciever;
    public String Sender;
    public String Topic;
    public String Counter ="";
    public int test =0;

    public Message(String sender, String reciever, String topic) {
        Reciever = reciever;
        Sender = sender;
        Topic = topic;
        Counter = ""+test;
        test++;
    }
    public String getCounter() {
        return Counter;
    }

    public void setCounter(String counter) {
        this.Counter = counter;
    }


    public String getReciever() {
        return Reciever;
    }

    public void setReciever(String reciever) {
        Reciever = reciever;
    }

    public String getSender() {
        return Sender;
    }

    public void setSender(String sender) {
        Sender = sender;
    }

    public String getTopic() {
        return Topic;
    }

    public void setTopic(String topic) {
        Topic = topic;
    }

}
