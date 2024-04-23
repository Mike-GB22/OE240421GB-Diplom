package services;

import DAO.Message;
import DAO.MessageRepository;
import DAO.MessageRepositoryCurrent;

import java.util.List;

public class MessageService {
    private static MessageService instance;
    private MessageRepository repository;
    //ћетод (замена полю) дл€ единообразного доступа ко всем сервисам работы с данными
    private DataServices dataServices(){
        return DataServices.getInstance();
    }



    //приватный конструктор, чтоб ни кто не мог создать новый экземлп€р объекта из вне
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

    public Message getMessages(String message_id) {
        return repository.getMessage(message_id);
    }

    public List<Message> getMessages(String[] message_ids) {
        return repository.getMessages(message_ids);
    }

    public List<Message> getAllMessages() {
        return repository.getAllMessages();
    }
    public List<Message> getLast100Messages(){
        return repository.getLast100Messages();
    }
    public Message deleteMessage(String message_id) {
        return repository.deleteMessage(message_id);
    }

    public Message editMessage(String message_id, String newTopic, String newMessage) {
        return repository.editMessage(message_id, newTopic, newMessage);
    }

    public void print(){
        repository.print();
    }
}
