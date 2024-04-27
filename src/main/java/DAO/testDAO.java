package DAO;

import services.DataServices;

import java.util.Random;

public class testDAO implements Runnable {
    //Äëÿ äîñòóïà ê ñåğâèñàì ğàáîòû ñ äàííûìè
    private DataServices dataServices;

    public testDAO(){}
    public testDAO(DataServices dataServices){
        this.dataServices = dataServices;
    }

    //Äëÿ ğó÷íîãî âûïîëíåíèÿ çàïîëíåíèÿ.
    public static void main(String[] args) {
        Thread testDAOthread = new Thread(new testDAO());
        testDAOthread.start();
    }

    @Override
    public void run() {
        System.out.println("ÑÎÇÄÀÍÈÅ ĞÅÏÎÇÈÒÎĞÈÅÂ È ÇÀÍÅÑÅÍÈÅ ÈÍÔÎĞÌÀÖÈÈ Â ÍÈÕ, ÄËß ÏĞÎÂÅÄÅÍÈß ÒÅÑÒÎÂÎÃÎ ÇÀÏÓÑÊÀ");
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
        System.out.println("SESSION:");
        System.out.println("----------------------------");
        UserSession ss1 = new UserSession(11111, "fff", "127.0.0.1");
        UserSession ss2 = new UserSession(22222, "fff", "192.168.0.1");

        System.out.println(ss1);
        System.out.println(ss2);
        System.out.println(ss1.toJson());
        System.out.println(ss2.toJson());

        System.out.println("---------------------------------");
        if(dataServices != null){
            System.out.println("Çàíîñèì âñå ñîçäàííûå îáúåêòû â ğåïîçèòîğèè: ");
            dataServices.userSessionService.getRepository().addSession(ss1);
            dataServices.userSessionService.getRepository().addSession(ss2);

            System.out.println("----------------------------");
            System.out.println("USERS:");
            System.out.println("----------------------------");
            User user1 = dataServices.userService.getRepository().newUser("Ivan", "Paroll1", true);
            User user2 = dataServices.userService.getRepository().newUser("Petr", "Paroll2", true);
            dataServices.userService.getRepository().newUser("User1", "pass" + new Random().nextInt());
            dataServices.userService.getRepository().newUser("User2", "pass" + new Random().nextInt());
            System.out.println(user1);
            System.out.println(user2);
            System.out.println(user1.toJson());
            System.out.println(user2.toJson());

            dataServices.messageService.getRepository().addMessage(msg);
            dataServices.messageService.getRepository().addMessage(msg2);

            dataServices.printSize();
        } else {
            System.out.println("ÑÎÇÄÀÍÍÛÅ İËÅÌÅÍÒÛ ÍÅ ÇÀÍÅÑÅÍÍÛ Â ĞÅÏÎÇÈÒÎĞÈÉ!!!");
        }
        System.out.println("---------------------------------");
    }
}
