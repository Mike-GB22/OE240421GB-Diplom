package DAO;

public class testDAO implements Runnable {
    public static void main(String[] args) {
        System.out.println("----------------------------");
        System.out.println("MESSAGES:");
        System.out.println("----------------------------");
        Message msg = new Message(111, "asfsadfsaf", "msg1");
        Message msg2 = new Message(111, "asfsadfsaf", "msg2", 222);

        System.out.println(msg);
        System.out.println(msg2);
        System.out.println(msg.toJson());
        System.out.println(msg2.toJson());

        System.out.println("----------------------------");
        System.out.println("USERS:");
        System.out.println("----------------------------");
        User user1 = new User("Ivan", "Paroll1");
        User user2 = new User("Petr", "Paroll2");

        System.out.println(user1);
        System.out.println(user2);
        System.out.println(user1.toJson());
        System.out.println(user2.toJson());

        System.out.println("----------------------------");
        System.out.println("SESSION:");
        System.out.println("----------------------------");
        UserSession ss1 = new UserSession(11111, "fff", "127.0.0.1");
        UserSession ss2 = new UserSession(22222, "fff", "192.168.0.1");

        System.out.println(ss1);
        System.out.println(ss2);
        System.out.println(ss1.toJson());
        System.out.println(ss2.toJson());

    }

    @Override
    public void run() {

    }
}
