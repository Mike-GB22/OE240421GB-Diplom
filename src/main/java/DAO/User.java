package DAO;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;

public class User {
    private static int max_user_id = 0;
    private int user_id;
    private String name;
    //@Expose(serialize = false)
    private transient String pass;
    private boolean admin_flag;

    public User(String name, String pass){
        user_id = ++max_user_id;
        this.name = name;
        this.pass = pass;
        this.admin_flag = false;
    }
    public User(String name, String pass, boolean isAdminFlag){
        this(name, pass);
        this.admin_flag = isAdminFlag;
    }

    //������ ������ ������������, �������� ��� �������� ��� �� ���� ������
    public User(int userId, String name, String pass,boolean isAdmin){
        this.user_id = userId;
        this.name = name;
        this.pass = pass;
        this.admin_flag = isAdmin;
        if (user_id > max_user_id) {
            max_user_id = user_id;
        }
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

    public User setPass(String newPass){
        this.pass = newPass;
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

    public boolean checkPass(String passToCheck) {
        if (pass.equals(passToCheck)) return true;
        return false;
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

    public static int getMaxUserId(){
        return max_user_id;
    }
}
