package DAO;

import java.util.ArrayList;
import java.util.List;

public class UserDAOArrayList implements UserDAO{
    List<User> list = new ArrayList<>();

    @Override
    public User newUser(String name, String pass) {
        User user = new User(name, pass);
        list.add(user);
        return user;
    }

    @Override
    public void addUser(User user) {
        list.add(user);
    }

    @Override
    public User getUser(int user_id) {
        for(User user : list){
            if(user.getUser_id() == user_id){
                return user;
            }
        }
        return null;
    }

    @Override
    public boolean isUser(int user_id) {
        User user = getUser(user_id);
        if(user != null){
            return true;
        }
        return false;
    }

    @Override
    public boolean isUserAdmin(int user_id) {
        User user = getUser(user_id);
        if(user != null
                && user.isAdmin()){
            return true;
        }
        return false;
    }

    @Override
    public User deleteUser(int user_id) {
        User user = getUser(user_id);
        if(user != null){
            list.remove(user);
            return user;
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return list;
    }

    @Override
    public List<User> getAllAdminUsers() {
        List<User> listAdminUsers = new ArrayList<>();
        for(User user : list){
            if(user.isAdmin()){
                listAdminUsers.add(user);
            }
        }
        return listAdminUsers;
    }

    @Override
    public User setAdminFlag(int user_id, boolean admin_flag) {
        User user = getUser(user_id);
        if(user != null){
            user.setAdminFlag(admin_flag);
            return user;}
        return null;
    }

    @Override
    public User renameUser(int user_id, String newName){
        User user = getUser(user_id);
        if(user != null){
            user.setName(newName);
            return user;}
        return null;
    }

    @Override
    public User newPass(int user_id, String newPass){
        User user = getUser(user_id);
        if(user != null){
            user.setPass(newPass);
            return user;}
        return null;
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public  void print(){
        for(User user : list){
            System.out.println(user);
        }
    }
}
