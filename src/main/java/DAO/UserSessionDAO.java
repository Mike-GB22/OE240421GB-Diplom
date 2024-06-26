package DAO;

import java.util.List;

public interface UserSessionDAO {
    UserSession newSession(int user_id, String passHash, String ip);
    void addSession(UserSession userSession);
    UserSession getSession(String session_id);
    boolean isSessionValidWithIP(String session_id, String ip);
    boolean isSession(String session_id);
    UserSession deleteSession(String session_id);
    List<UserSession> deleteAll();
    List<UserSession> deleteTimeOut(int timeOutSecundes);
    int size();
    String toString();
    void print();
}
