package services;

import DAO.UserSessionRepository;
import DAO.UserSessionRepositoryCurrent;

public class UserSessionService {
    UserSessionRepository repository = new UserSessionRepositoryCurrent();
}
