package IntegraLogger.Controller.Service;

import IntegraLogger.Controller.Repository.UserRepository;
import IntegraLogger.Model.User.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService extends ServiceBase<Usuario, Long, UserRepository> {

    @Autowired
    public UserService(UserRepository repository) {
        super(repository);
    }
    public void metodo(){
        System.out.println("metodo");
    }


    public Usuario login(String user, String pass) {
       Usuario usuario = repository.findByEmail(user);
       if (usuario != null && usuario.getPassword().equals(pass)){
           return usuario;
       } else {
           return null;
       }
    }
}
