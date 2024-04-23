package services;

import DAO.Message;
import DAO.MessageRepository;
import DAO.MessageRepositoryCurrent;
import DAO.UserSessionRepository;

import java.util.List;

public class MessageService {
    private static MessageService instance;
    private MessageRepository repository;
    //����� (������ ����) ��� �������������� ������� �� ���� �������� ������ � �������
    private DataServices dataServices(){
        return DataServices.getInstance();
    }



    //��������� �����������, ���� �� ��� �� ��� ������� ����� ��������� ������� �� ���
    private MessageService(){
        repository = new MessageRepositoryCurrent();
    }
    public static MessageService getInstance(){
        if(instance == null){
            instance = new MessageService();
        }
        return instance;
    }

    public MessageRepository getRepository(){
        return repository;
    }



    public Message newMessage(int user_id, String topic, String message) {
        return repository.newMessage(user_id, topic, message);
    }

    public Message newPrivateMessage(int user_id, String topic, String message, int for_user_id) {
        return repository.newPrivateMessage(user_id, topic, message, for_user_id);
    }

    public Message getMessage(String message_id) {
        return repository.getMessage(message_id);
    }

    public List<Message> getMessages(String[] messages_id) {
        return repository.getMessages(messages_id);
    }

    public List<Message> getAllMessages() {
        return repository.getAllMessages();
    }

    public Message deleteMessage(String message_id) {
        return repository.deleteMessage(message_id);
    }

    public Message editMessage(String message_id, String newMessage) {
        return repository.editMessage(message_id, newMessage);
    }
}
