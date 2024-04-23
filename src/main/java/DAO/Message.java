//2024.04.20 mip24
package DAO;

import java.util.Date;
import java.util.Random;
import com.google.gson.Gson;

public class Message {
    private static long count = 0;
    private String message_id;
    private int user_id;
    private Date date;
    private Date edit;
    private boolean editFlag;
    private boolean privateFlag;
    private int for_user_id;
    private String topic;
    private String message;

    public Message(int user_id, String topic, String message) {
        this.user_id = user_id;
        this.topic = topic;
        this.message = message;
        this.date = new Date();
        this.editFlag = false;

        message_id = "MESSAGE_"
                + String.format("%020d", getMessagesCount())
                + "_" + date.getTime();
    }

    //Конструктор приватного сообщения
    public Message(int user_id, String topic, String message, int for_user_id) {
        this(user_id, topic, message);
        this.for_user_id = for_user_id;
        this.privateFlag = true;
    }

    public Message editMessage(String newTopic, String newMessage){
        topic = newTopic;
        message = newMessage;
        this.editFlag = true;
        this.edit = new Date();
        return this;
    }

    private long getMessagesCount(){
        count++;
        if(count >= Long.MAX_VALUE){
            count = 0;
        };
        return count;
    }

    public static long getCount() {
        return count;
    }

    public String getMessage_id() {
        return message_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public Date getDate() {
        return date;
    }

    public Date getEdit() {
        return edit;
    }

    public boolean isEditFlag() {
        return editFlag;
    }

    public boolean isPrivateFlag() {
        return privateFlag;
    }

    public int getFor_user_id() {
        return for_user_id;
    }

    public String getTopic() {
        return topic;
    }

    public String getMessage() {
        return message;
    }
    @Override
    public String toString(){
        String result = String.format("MSG id: %s from: %d %s%n",message_id, user_id, date);
        result += String.format("Edit: %b from: %d%n", editFlag, edit);
        result += String.format("Private: %b to: %d%n", privateFlag, for_user_id);
        result += String.format("Topic: %s%n", topic);
        result += String.format("%s%n", message);
        return result;
    }

    public String toJson(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
