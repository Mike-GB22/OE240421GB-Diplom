package DAO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    String name = "TestName";
    String pass = "TestPass";
    User user;
    @BeforeEach
    void setUp() {
        user = new User(name, pass);
    }

    @Test
    void getUser_id() {
        assertThat(user.getUser_id()).isEqualTo(user.getMaxUserId());
    }

    @Test
    void getName() {
        assertThat(user.getName()).isEqualTo(name);
    }

    @Test
    void isAdmin() {
        assertThat(user.isAdmin()).isEqualTo(false);
    }

    @Test
    void setName() {
        String newName = "otherName";
        user.setName(newName);
        assertThat(user.getName()).isEqualTo(newName);
    }

    @Test
    void setAdminFlag() {
        user.setAdminFlag(true);
        assertThat(user.isAdmin()).isEqualTo(true);
        user.setAdminFlag(false);
    }

    @Test
    void isAdmin_flag() {
        assertThat(user.isAdmin()).isEqualTo(false);
    }

    @Test
    void getPass() {
        assertThat(user.getPass()).isEqualTo(pass);
    }

    @Test
    void checkPassTrue() {
        assertThat(user.checkPass(pass)).isEqualTo(true);
    }

    @Test
    void checkPassFalse() {
        assertThat(user.checkPass(pass+"1")).isEqualTo(false);
    }

    @Test
    void testToString() {
        assertThat(user.toString())
                .isEqualTo(user.toString());
    }

    @Test
    void toJson() {
        assertThat(user.toJson())
                .isEqualTo(user.toJson());
    }
}