//2024.04.20 mip24
package DAO;

import java.util.Date;
import java.util.Random;

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

        message_id = "MESSAGE_" + date.getTime() + "-" + (new Random().nextDouble()*1_000_000);
        messagesCountIncrement();
    }

    //Конструктор приватного сообщения
    public Message(int user_id, String topic, String message, int for_user_id) {
        this(user_id, topic, message);
        this.for_user_id = for_user_id;
        this.privateFlag = true;
    }

    public Message editMessage(String newMessage){
        message = newMessage;
        this.editFlag = true;
        this.edit = new Date();
        return this;
    }

    private void messagesCountIncrement(){
        count++;
        if(count >= Long.MAX_VALUE){
            count = 0;
        };
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
}
