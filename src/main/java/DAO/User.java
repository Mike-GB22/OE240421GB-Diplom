package DAO;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;

public class User {
    private static int max_user_id = 0;
    private int user_id;
    private String name;
    @Expose(serialize = false)
    private String pass;
    private boolean admin_flag;

    public User(String name, String pass){
        user_id = ++max_user_id;
        this.name = name;
        this.pass = pass;
        this.admin_flag = false;
    }

    public String getName(){
        return name;
    }

    public boolean isAdmin(){
        return admin_flag;
    }

    public User setName(String newName){
        this.name = newName;
        return this;
    }

    public User setAdminFlag(boolean admin_flag){
        this.admin_flag = admin_flag;
        return this;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getPass() {
        return pass;
    }

    public boolean isAdmin_flag() {
        return admin_flag;
    }
    @Override
    public String toString(){
        String result = String.format("USER id: %d, name: %s, pass: %s, admin flag: %b"
                , user_id, name, pass, admin_flag);
        return result;
    }

    public String toJson(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
