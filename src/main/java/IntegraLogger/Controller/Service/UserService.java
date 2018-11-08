package IntegraLogger.Controller.Service;

import IntegraLogger.Controller.Repository.UserRepository;
import IntegraLogger.Model.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class UserService extends ServiceBase<User, Long, UserRepository> {

    @Autowired
    public UserService(UserRepository repository) {
        super(repository);
    }
    public void metodo(){
        System.out.println("metodo");
    }



}
