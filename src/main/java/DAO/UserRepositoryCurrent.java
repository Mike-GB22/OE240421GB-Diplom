package DAO;

import java.util.List;

public class UserRepositoryCurrent implements UserRepository{
    UserDAO dao;
    //DAO по умолчанию ArrayList
    public UserRepositoryCurrent(){
        //Хранилище в Коллекции
        dao = new UserDAOArrayList();
        //Хранилище в Базе данных
        //dao = new UserDAODB();
    }

    public UserRepositoryCurrent(UserDAO dao){
        this.dao = dao;
    }

    @Override
    public User newUser(String name, String pass) {
        return dao.newUser(name, pass);
    }

    @Override
    public void addUser(User user) {
        dao.addUser(user);
    }

    @Override
    public User getUser(int user_id) {
        return dao.getUser(user_id);
    }

    @Override
    public boolean isUser(int user_id) {
        return dao.isUser(user_id);
    }

    @Override
    public boolean isUserAdmin(int user_id) {
        return dao.isUserAdmin(user_id);
    }

    @Override
    public User deleteUser(int user_id) {
        return dao.deleteUser(user_id);
    }

    @Override
    public List<User> getAllUsers() {
        return dao.getAllUsers();
    }

    @Override
    public List<User> getAllAdminUsers() {
        return dao.getAllAdminUsers();
    }

    @Override
    public User setAdminFlag(int user_id, boolean admin_flag) {
        return dao.setAdminFlag(user_id, admin_flag);
    }

    @Override
    public User renameUser(int user_id, String newName) {
        return dao.renameUser(user_id, newName);
    }

    @Override
    public User newPass(int user_id, String newPass) {
        return dao.newPass(user_id, newPass);
    }

    @Override
    public int size() {
        return dao.size();
    }

    @Override
    public void print() {
        dao.print();
    }
}