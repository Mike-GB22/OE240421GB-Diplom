package services;

//����� ��� �������������� ������� �� ���� �������� ������ � �������
public class DataServices {
    private static DataServices instance;
    public UserService userService;
    public UserSessionService userSessionService ;
    public MessageService messageService;

    private DataServices(){
        userService = UserService.getInstance();
        userSessionService = UserSessionService.getInstance();
        messageService = MessageService.getInstance();
    }

    public static DataServices getInstance(){
        if(instance == null){
            instance = new DataServices();
            loadTestDAO();
        }
        return instance;
    }

    //�������� �������� ������
    public static void loadTestDAO(){
        new DAO.testDAO(instance).run();
    }

    public String size(){
        String result = "������� ������������ (� �������):";
        result += "\n - Size Users: " + instance.userService.getRepository().size();
        result += "\n - Size Sessions: " + instance.userSessionService.getRepository().size();
        result += "\n - Size Messages: " + instance.messageService.getRepository().size();
        return result;
    }

    public void printSize(){
        System.out.println(size());
    }

}
