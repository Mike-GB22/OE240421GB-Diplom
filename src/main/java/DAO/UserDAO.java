package DAO;

import java.util.List;

public interface UserDAO {
    User newUser(String name, String pass);
    User getUser(int user_id);
    boolean isUser(int user_id);
    boolean isUserAdmin(int user_id);
    User deleteUser(int user_id);
    List<User> getAllUsers();
    List<User> getAllAdminUsers();
    User setAdminFlag(int user_id, boolean admin_flag);
    User renameUser(int user_id, String newName);

}
