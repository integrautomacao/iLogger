package IntegraLogger.Controller.Service;

import IntegraLogger.Controller.Repository.UserRepository;
import IntegraLogger.DTO.UsuarioDTO;
import IntegraLogger.Model.User.Sector;
import IntegraLogger.Model.User.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class UserService extends ServiceBase<Usuario, Long, UserRepository> {

    @Autowired
    private SectorService sectorService;

    @Autowired
    public UserService(UserRepository repository) {
        super(repository);
    }

    @Override
    public Usuario getById(Long id) {
        return repository.getById(id);
    }

    public UsuarioDTO parseUsuarioDTO(Usuario usuario) {

        UsuarioDTO dto = constructUsuarioDTO(usuario);
        return dto;
    }

    private UsuarioDTO constructUsuarioDTO(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO(usuario.getName(), usuario.getEmail(), usuario.getPhoneNumber());
        if (usuario.getSector() != null) {

            dto.setSector(usuario.getSector().getId());
        }

        if (usuario.getId() != null) {
            dto.setId(usuario.getId());
        }
        return dto;
    }

    public Usuario login(String user, String pass) {
        Usuario usuario = repository.findByEmail(user);
        if (usuario != null && usuario.getPassword().equals(pass)) {
            return usuario;
        } else {
            return null;
        }
    }

    public List<UsuarioDTO> parseUsuarioDTO(List<Usuario> usuarios) {
        List<UsuarioDTO> toReturn = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            toReturn.add(constructUsuarioDTO(usuario));
        }
        return toReturn;
    }

    public Usuario saveDTO(UsuarioDTO usuarioDTO) {
        Usuario usuario;
        if (usuarioDTO.getId() != null) {
            //update
            usuario = repository.getById(usuarioDTO.getId());
            usuario = updateFromDTO(usuario, usuarioDTO);
        } else {

            usuario = parseUsuario(usuarioDTO);
        }
        return repository.save(usuario);
    }

    private Usuario updateFromDTO(Usuario usuario, UsuarioDTO dto) {
        usuario.setSector(sectorService.getById(dto.getSector()));
        usuario.setPhoneNumber(dto.getPhoneNumber());
        usuario.setName(dto.getName());
        usuario.setEmail(dto.getEmail());

        return usuario;
    }


    private Usuario parseUsuario(UsuarioDTO usuarioDTO) {
        Sector sector = sectorService.getById(usuarioDTO.getSector());
        Usuario usuario = new Usuario();
        usuario.setName(usuarioDTO.getName());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setPhoneNumber(usuarioDTO.getPhoneNumber());
        usuario.setSector(sector);
        usuario.setPassword(generatePassword());

        return usuario;
    }

    private String generatePassword() {

        String caracters = "qwertyuiopasdfghjklzxcvbnm7894561230";

        Random random = new Random();

        String keys = "";
        int index;
        for (int i = 0; i < 5; i++) {
            index = random.nextInt(caracters.length());
            keys += caracters.substring(index, index + 1);
        }
        return keys;
    }

    public boolean resetPassword(Long id) {

        try {

            Usuario usuario = repository.getById(id);
            usuario.setPassword(generatePassword());

            if (save(usuario).getId() != null) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }
}
