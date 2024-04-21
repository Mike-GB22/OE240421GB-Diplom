package services;

import DAO.MessageRepository;
import DAO.MessageRepositoryCurrent;
import DAO.UserSessionRepository;

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
}
