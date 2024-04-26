package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAODB  implements UserDAO{
    private static final String URL = "jdbc:mysql://localhost:3306";
    private static final String USER = "root";
    private static final String PASSWORD = "admin";
    private static final String SCHEMA = "`diplom`";
    private static final String TABLE = "`users`";
    private static final String SCHEMA_TABLE = SCHEMA+"."+TABLE;


    //List<User> list = new ArrayList<>();
    //Иниацилизация БД и заполнение 2мя ползователями
    public void initialisationDBForUserDAODB() {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)){
            Statement statement = con.createStatement();
            statement.execute("DROP SCHEMA " + SCHEMA);
            statement.execute("CREATE SCHEMA " + SCHEMA);
            statement.execute("CREATE TABLE " + SCHEMA_TABLE + " (" +
                    "`user_id` INT NOT NULL, " +
                    "`name` VARCHAR (100) NULL," +
                    "`pass` VARCHAR (100) NULL, " +
                    "`admin_flag` BOOLEAN," +
                    "PRIMARY KEY(`user_id`));");
            statement.execute("INSERT INTO " + SCHEMA_TABLE +
                    "(`user_id`, `name`, `pass`, `admin_flag`) " +
                    "VALUES (1, 'Ivanov', 'Paroll1', TRUE);");
            statement.execute("INSERT INTO " + SCHEMA_TABLE +
                    "(`user_id`, `name`, `pass`, `admin_flag`) " +
                    "VALUES (2, 'Petrov', 'Paroll2', TRUE);");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public int getMaxUserID() {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)){
            Statement statement = con.createStatement();
            String request = "SELECT MAX(`user_id`) FROM " + SCHEMA_TABLE + ";";
            ResultSet resultSet = statement.executeQuery(request);
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Integer> getUsersID() {
        List<Integer> listUsersID = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)){
            Statement statement = con.createStatement();
            String request = "SELECT `user_id` FROM " + SCHEMA_TABLE + ";";
            ResultSet resultSet = statement.executeQuery(request);
            while (resultSet.next()){
                listUsersID.add(resultSet.getInt("user_id"));
            }
            return listUsersID;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void makeSQLRequest(String request){
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)){
            Statement statement = con.createStatement();
            statement.execute(request);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User newUser(String name, String pass) {
        int user_id = getMaxUserID() + 1;
        User user = new User(user_id, name, pass, false);
        addUser(user);
        return user;
    }

    @Override
    public void addUser(User user) {
        String statement = "INSERT INTO " + SCHEMA_TABLE +
                "(`user_id`, `name`, `pass`, `admin_flag`) ";
        String values = String.format("VALUES (%d, '%s', '%s', %b);"
                , user.getUser_id()
                , user.getName()
                , user.getPass()
                , user.isAdmin()
                );
        makeSQLRequest(statement + values);
    }

    @Override
    public User getUser(int user_id) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)){
            Statement statement = con.createStatement();
            String request = "SELECT `user_id`, `name`, `pass`, `admin_flag` FROM "
                    + SCHEMA_TABLE
                    + "WHERE `user_id` = " + user_id + ";";
            ResultSet resultSet = statement.executeQuery(request);
            if(resultSet.next() == false) return null;
            int newUser_id = resultSet.getInt("user_id");
            String name =  resultSet.getString("name");
            String pass =  resultSet.getString("pass");
            boolean isAdmin = resultSet.getBoolean("admin_flag");
            return new User(user_id, name, pass, isAdmin);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
        String request = "DELETE FROM " + SCHEMA_TABLE +
                "WHERE `user_id` = " + user_id + ";";
        makeSQLRequest(request);
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> listUsers = new ArrayList<>();
        List<Integer> listUsersID = getUsersID();
        for(Integer userID : listUsersID){
            User user = getUser(userID);
            listUsers.add(user);
        }
        return listUsers;
    }

    @Override
    public List<User> getAllAdminUsers() {
        List<User> users = getAllUsers();
        List<User> listAdminUsers = new ArrayList<>();
        for(User user : users){
            if(user.isAdmin()) listAdminUsers.add(user);
        }
        return listAdminUsers;
    }

    @Override
    public User setAdminFlag(int user_id, boolean admin_flag) {
        String request = "UPDATE " + SCHEMA_TABLE
                + "SET `admin_flag` = " + admin_flag + " "
                + "WHERE `user_id` = " + user_id + ";";
        makeSQLRequest(request);
        User user = getUser(user_id);
        return user;
    }

    @Override
    public User renameUser(int user_id, String newName){
        String request = "UPDATE " + SCHEMA_TABLE
                + "SET `name` = '" + newName + "' "
                + "WHERE `user_id` = " + user_id + ";";
        makeSQLRequest(request);
        User user = getUser(user_id);
        return user;
    }

    @Override
    public User newPass(int user_id, String newPass){
        String request = "UPDATE " + SCHEMA_TABLE
                + "SET `pass` = '" + newPass + "' "
                + "WHERE `user_id` = " + user_id + ";";
        makeSQLRequest(request);
        User user = getUser(user_id);
        return user;
    }


    @Override
    public int size() {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)){
            Statement statement = con.createStatement();
            String request = "SELECT COUNT(`user_id`) FROM " + SCHEMA_TABLE + ";";
            ResultSet resultSet = statement.executeQuery(request);
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public  void print(){
        for(User user : getAllUsers()){
            System.out.println(user);
        }
    }
}
