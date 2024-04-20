package DAO;

import java.util.Date;
import java.util.Random;

public class UserSession {
    private static long count = 0;
    private String session_id;
    private int user_id;
    private String passHash;
    private String ip;
    private Date begone;
    private Date updated;

    // Создание новой сессии
    public UserSession(int user_id, String passHash, String ip) {
        this.user_id = user_id;
        this.passHash = passHash;
        this.ip = ip;
        begone = updated = new Date();

        session_id = "SID_" + begone.getTime() + "-" + (new Random().nextDouble()*1_000_000);
        sessionCountIncrement();
    }

    //При любом обращении к сессии обновляем ее, чтоб отслеживать те, у которых время жизни вышло
    public UserSession updateSession(){
        updated = new Date();
        return this;
    }

    public String getSession_id() {
        return session_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getPassHash() {
        return passHash;
    }

    public String getIp() {
        return ip;
    }

    public Date getBegone() {
        return begone;
    }

    public Date getUpdated() {
        return updated;
    }

    private void sessionCountIncrement(){
        count++;
        if(count >= Long.MAX_VALUE){
            count = 0;
        };
    }
}
