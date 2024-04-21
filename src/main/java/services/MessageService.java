package services;

import DAO.MessageRepository;
import DAO.MessageRepositoryCurrent;

public class MessageService {
    MessageRepository repository = new MessageRepositoryCurrent();
}
