import DAO.testDAO;

import static java.lang.Thread.*;

public class ProgrammServer {
    public static void main(String[] args) {
        Server server = new Server(1400);
        System.out.println("--- server is ended --- ");
    }
}
