package DAO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;

class MessageTest {
    int userId = 1;
    int userId2 = 2;
    String topic = "Thema";
    String textMsg = "Message";

    Message message;
    Message messageToEdit;
    @BeforeEach
    void setUp() {
        message = new Message(userId, topic, textMsg, userId2);
        messageToEdit  = new Message(userId, topic, textMsg, userId2);
    }

    @Test
    void editMessage() {
        String topicNew = "Thema";
        String textMsgNew = "Message";
        messageToEdit.editMessage(topicNew, textMsgNew);
        assertThat(messageToEdit.getTopic())
                .isEqualTo(topicNew);
        assertThat(messageToEdit.getMessage())
                .isEqualTo(textMsgNew);
    }

    @Test
    void getCount() {
        assertThat(message.getCount())
                .isEqualTo(message.getCount());
    }

    @Test
    void getMessage_id() {
        assertThat(message.getMessage_id())
                .isEqualTo(message.getMessage_id());
    }

    @Test
    void getUser_id() {
        assertThat(message.getUser_id())
                .isEqualTo(userId);
    }

    @Test
    void getDate() {
        assertThat(message.getDate())
                .isEqualTo(message.getDate());
    }

    @Test
    void getEdit() {
        assertThat(message.getEdit())
                .isEqualTo(message.getEdit());
    }

    @Test
    void isEditFlag() {
        assertThat(message.isEditFlag())
                .isEqualTo(message.isEditFlag());
    }

    @Test
    void isPrivateFlag() {
        assertThat(message.isPrivateFlag())
                .isEqualTo(true);
    }

    @Test
    void getFor_user_id() {
        assertThat(message.getFor_user_id())
                .isEqualTo(userId2);
    }

    @Test
    void getTopic() {
        assertThat(message.getTopic())
                .isEqualTo(topic);
    }

    @Test
    void getMessage() {
        assertThat(message.getMessage())
                .isEqualTo(message.getMessage());
    }

    @Test
    void testToString() {
        assertThat(message.toString())
                .isEqualTo(message.toString());
    }

    @Test
    void toJson() {
        assertThat(message.toJson())
                .isEqualTo(message.toJson());
    }
}