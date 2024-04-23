package services;

import DAO.testDAO;

// ласс дл€ единообразного доступа ко всем сервисам работы с данными
public class DataServices {
    private static DataServices instance;
    public UserService userService;
    public UserSessionService userSessionService ;
    public MessageService messageService;

    private DataServices(){
        userService = UserService.getInstance();
        userSessionService = UserSessionService.getInstance();
        messageService = MessageService.getInstance();

        fillTestingDataInDAO();
   }

    public static DataServices getInstance(){
        if(instance == null){
            instance = new DataServices();
            loadTestDAO();
        }
        return instance;
    }

    //«агрузка тестовых данных
    public static void loadTestDAO(){
        new DAO.testDAO(instance).run();
    }

    public String size(){
        String result = "–азмеры репозиториев (в запис€х):";
        result += "\n - Size Users: " + instance.userService.getRepository().size();
        result += "\n - Size Sessions: " + instance.userSessionService.getRepository().size();
        result += "\n - Size Messages: " + instance.messageService.getRepository().size();
        return result;
    }

    public void printSize(){
        System.out.println(size());
    }

    public void printUserRepository(){
        instance.userService.getRepository().print();
    }

    public void fillTestingDataInDAO(){
        Thread testDAOthread = new Thread(new testDAO(instance));
        testDAOthread.start();
    }
}
