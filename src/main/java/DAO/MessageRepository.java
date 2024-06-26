package DAO;

import java.util.List;

public interface MessageRepository {
    Message newMessage(int user_id, String topic, String message);
    void addMessage(Message message);
    Message newPrivateMessage(int user_id, String topic, String message, int for_user_id);
    Message getMessage(String message_id);
    List<Message> getMessages(String[] messages_id);
    List<Message> getAllMessages();
    List<Message> getAllMessagesFromUser(int user_id);
    Message deleteMessage(String message_id);
    List<Message> deleteMessages(String[] messages_id);
    List<Message> deleteAllMessages();
    List<Message> deleteAllMessagesFromUser(int user_id);
    Message editMessage(String message_id, String newTopic, String newMessage);
    int size();
    void print();

    List<Message> getLast100Messages();
    List<Message> getLastMessagesForm(String messageID);
}
