package DAO;

import java.sql.SQLException;

public class testUserDAODB {
    public static void main(String[] args) {
        UserDAODB db = new UserDAODB();
        //db.initialisationDBForUserDAODB();
        //System.out.println("---" + db.getMaxUserID() );
        User user = db.newUser("Sidorov", "Paroll3");
        System.out.println(db.getUser(1));
        System.out.println(db.getUser(1).isAdmin());
        System.out.println(db.getUser(3));
        System.out.println(db.deleteUser(3));
        System.out.println(db.getUser(3));
        System.out.println(db.getUsersID());
        System.out.println(db.getAllUsers());
        System.out.println(db.setAdminFlag(5,true));
    }
}
