package IntegraLogger.Controller.Service;

import IntegraLogger.Controller.Repository.UserRepository;
import IntegraLogger.Model.User.User;
import org.springframework.stereotype.Service;

@Service
public class UserService extends ServiceBase<User, Long, UserRepository> {

    public UserService(UserRepository repository) {
        super(repository);
    }
}
