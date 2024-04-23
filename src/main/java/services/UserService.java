package services;

import DAO.UserRepository;
import DAO.UserRepositoryCurrent;

public class UserService {
    private static UserService instance;
    private UserRepository repository;

    //Метод (замена полю) для единообразного доступа ко всем сервисам работы с данными
    private DataServices dataServices(){
        return DataServices.getInstance();
    }


    //приватный конструктор, чтоб ни кто не мог создать новый экземлпяр объекта из вне
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
     * Проверяем логин (идентификатор пользоваля) и пароль
     * @param user_id
     * @param pass
     * @return - правда если верно, ложь в противном случае
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