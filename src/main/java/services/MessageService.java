package services;

import DAO.MessageRepository;
import DAO.MessageRepositoryCurrent;
import DAO.UserSessionRepository;

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
}
