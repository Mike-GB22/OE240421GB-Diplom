package services;

import DAO.User;
import DAO.UserSession;
import DAO.UserSessionRepository;
import DAO.UserSessionRepositoryCurrent;

public class UserSessionService {
    private static final int TIME_OUT_FOR_SESSION_SECONDS = 1 * 60 * 60;
    private static UserSessionService instance;
    private UserSessionRepository repository;

    //����� (������ ����) ��� �������������� ������� �� ���� �������� ������ � �������
    private DataServices dataServices(){
        return DataServices.getInstance();
    }

    //��������� �����������, ���� �� ��� �� ��� ������� ����� ��������� ������� �� ���
    private UserSessionService(){
        repository = new UserSessionRepositoryCurrent();
    }
    public static UserSessionService getInstance(){
        if(instance == null){
            instance = new UserSessionService();
        }
        return instance;
    }

    public UserSessionRepository getRepository(){
        return repository;
    }

    /**
     * �����������, ������� ����� ������ � ���������� �� ���� ����� � ������ ������
     * @param user_id
     * @param pass
     * @param ip
     * @return - ������������� ������, ��� ������ - null
     */
    public String authentication(int user_id, String pass, String ip){
        deleteTimeOutSession();
        boolean isLoginPassOk = dataServices().userService.isLoginPassOk(user_id, pass);
        if(isLoginPassOk){
            return newSession(user_id, pass, ip);
        }
        return null;
    }

    /**
     * ������� � ����������� ����� ������
     * @param user_id
     * @param pass
     * @param ip
     * @return
     */
    private String newSession(int user_id, String pass, String ip){
        String passHash = String.valueOf(pass.hashCode());
        UserSession userSession = repository.newSession(user_id, passHash, ip);
        return userSession.getSession_id();
    }

    /**
     * ��������� ���� �� �������������� ������ � ������
     * @param askedSID
     * @return
     */
    public String checkSID(String askedSID){
        if(repository.isSession(askedSID)){
            return askedSID;
        } else { return null; }
    }



    /**
     * �������� ������, ����� ������� ������ ��� ������������ � ���������
     */
    private void deleteTimeOutSession(){
        repository.deleteTimeOut(TIME_OUT_FOR_SESSION_SECONDS);
    }

    @Override
    public String toString(){
        return repository.toString();
    }
}