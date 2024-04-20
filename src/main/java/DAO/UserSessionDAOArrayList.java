package DAO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserSessionDAOArrayList implements UserSessionDAO {
    List<UserSession> list = new ArrayList<>();

    @Override
    public UserSession newSession(int user_id, String passHash, String ip) {
        UserSession session = new UserSession(user_id, passHash, ip);
        list.add(session);
        return session;
    }


    @Override
    public boolean isSession(String session_id) {
        UserSession session = getSession(session_id);
        if(session != null){
                return true;
            }
        return false;
    }

    @Override
    public boolean isSessionValidWithIP(String session_id, String ip) {
        UserSession session = getSession(session_id);
        if(session != null){
            if(session.getIp().equals(ip)){
                return true;
            }
        }
        return false;
    }

    @Override
    public UserSession deleteSession(String session_id) {
        UserSession session = getSession(session_id);
        if(session != null){
            list.remove(session);
            return session;
        }
        return null;
    }

    @Override
    public List<UserSession> deleteAll() {
        List temp = list;
        list = new ArrayList<>();
        return temp;
    }

    @Override
    public List<UserSession> deleteTimeOut(int timeOutSecundes) {
        List<UserSession> temp = new ArrayList<>();
        //”далим все сессии, которы последний раз был одновленны ƒќ этой даты
        Date timeOutDateToDelete = new Date(new Date().getTime() - timeOutSecundes * 1000);
        for(UserSession session : list){
            if(session.getUpdated().before(timeOutDateToDelete)){
                //все сессис которые мы должны удалить, ложим в список дл€ возвращени€
                temp.add(session);
            }
        }
        for(UserSession session : temp){
            //”дал€ем все ссессии которые мы положили в список дл€ удалени€
            list.remove(session);
        }
        return temp;
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public UserSession getSession(String session_id) {
        for(UserSession session: list){
            if(session.getSession_id().equals(session_id)){
                return session;
            }
        }
        return null;
    }
}
