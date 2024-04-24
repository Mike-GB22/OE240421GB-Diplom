package DAO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UserSessionTest {

    int userId=1;
    String passHash = "" + "Pass".hashCode();
    String ip = "127.0.0.1";
    UserSession userSession;

    @BeforeEach
    void setUp() {
        userSession = new UserSession(userId, passHash, ip);
    }

    @Test
    void updateSession() {
        Date alteDate = userSession.getUpdated();
        userSession.updateSession();
        assertThat(userSession.getUpdated())
                .isNotEqualTo(alteDate);
    }

    @Test
    void getSession_id() {
    }

    @Test
    void getUser_id() {
        assertThat(userSession.getUser_id())
                .isEqualTo(userId);
    }

        @Test
    void getPassHash() {
        assertThat(userSession.getPassHash())
                .isEqualTo(passHash);
    }

    @Test
    void getIp() {
        assertThat(userSession.getIp())
                .isEqualTo(ip);
    }

    @Test
    void getBegone() {
        assertThat(userSession.getBegone())
                .isEqualTo(userSession.getBegone());
    }

    @Test
    void getUpdated() {
        assertThat(userSession.getUpdated())
                .isEqualTo(userSession.getUpdated());
    }

    @Test
    void testToString() {
        assertThat(userSession.toString())
                .isEqualTo(userSession.toString());
    }

    @Test
    void toJson() {
        assertThat(userSession.toJson())
                .isEqualTo(userSession.toJson());
    }
}