package services;

import DAO.UserRepository;
import DAO.UserRepositoryCurrent;

public class UserService {
    private static UserService instance;
    private UserRepository repository;

    //����� (������ ����) ��� �������������� ������� �� ���� �������� ������ � �������
    private DataServices dataServices(){
        return DataServices.getInstance();
    }


    //��������� �����������, ���� �� ��� �� ��� ������� ����� ��������� ������� �� ���
    private UserService(){
        repository = new UserRepositoryCurrent();

    }
    public static UserService getInstance(){
        if(instance == null){
            instance = new UserService();
        }
        return instance;
    }

    public UserRepository getRepository(){
        return repository;
    }
    public int size(){
        return repository.size();
    }

    /**
     * ��������� ����� (������������� ����������) � ������
     * @param user_id
     * @param pass
     * @return - ������ ���� �����, ���� � ��������� ������
     */
    public boolean isLoginPassOk(int user_id, String pass){
        if(repository.isUser(user_id)){
            return repository.getUser(user_id).checkPass(pass);
        }
        return false;
    }

    public void print(){
        repository.print();
    }
}