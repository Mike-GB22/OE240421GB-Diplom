package DAO;

import java.util.List;

public interface UserSessionRepository {
    UserSession newSession(int user_id, String passHash, String ip);
    UserSession getSession(String session_id);
    boolean isSessionValidWithIP(String session_id, String ip);
    boolean isSession(String session_id);
    UserSession deleteSession(String session_id);
    List<UserSession> deleteAll();
    List<UserSession> deleteTimeOut(int timeOutSecundes);
    int size();
}