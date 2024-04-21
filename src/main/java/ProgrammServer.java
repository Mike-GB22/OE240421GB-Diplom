import DAO.testDAO;

import static java.lang.Thread.*;

public class ProgrammServer {
    public static void main(String[] args) {
        Thread testDAOthread = new Thread(new testDAO());
        testDAOthread.start();

        try {
            sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Server server = new Server(1400);
        System.out.println("--- server is ended --- ");
    }
}
