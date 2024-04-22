package DAO;

import java.util.List;

public class UserSessionRepositoryCurrent implements UserSessionRepository{
    UserSessionDAO dao;
    //DAO по умолчанию ArrayList
    public UserSessionRepositoryCurrent(){
        dao = new UserSessionDAOArrayList();
    }

    public UserSessionRepositoryCurrent(UserSessionDAO dao){
        this.dao = dao;
    }

    @Override
    public UserSession newSession(int user_id, String passHash, String ip) {
        return dao.newSession(user_id, passHash, ip);
    }

    public void addSession(UserSession userSession){
        dao.addSession(userSession);
    }

    @Override
    public UserSession getSession(String session_id) {
        return dao.getSession(session_id);
    }

    @Override
    public boolean isSessionValidWithIP(String session_id, String ip) {
        return dao.isSessionValidWithIP(session_id, ip);
    }

    @Override
    public boolean isSession(String session_id) {
        return dao.isSession(session_id);
    }

    @Override
    public UserSession deleteSession(String session_id) {
        return dao.deleteSession(session_id);
    }

    @Override
    public List<UserSession> deleteAll() {
        return dao.deleteAll();
    }

    @Override
    public List<UserSession> deleteTimeOut(int timeOutSecundes) {
        return dao.deleteTimeOut(timeOutSecundes);
    }

    @Override
    public int size() {
        return dao.size();
    }

    @Override
    public String toString(){
        return dao.toString();
    }
}
