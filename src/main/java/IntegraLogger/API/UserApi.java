package IntegraLogger.API;

import IntegraLogger.API.ApiExceptions.UserExceptions;
import IntegraLogger.Controller.Service.UserService;
import IntegraLogger.DTO.UsuarioDTO;
import IntegraLogger.Model.User.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping("/user")
public class UserApi implements ApiBase<Usuario, Long> {

    @Autowired
    private UserService userService;

    @Override
    @PostMapping
    public Usuario save(@RequestBody Usuario o) {
        return userService.save(o);
    }

    @Override
    public Optional update(Long id, Usuario object) {
        return Optional.empty();
    }

    @Override
    @GetMapping
    public List<Usuario> getAll() {
        return userService.getAll();
    }

    @Override
    @GetMapping("/{id}")
    public Usuario getOne(@PathParam("id") Long id) {
        return userService.getById(id);
    }

    @Override
    public void delete(@PathParam("id") Long id) {
        userService.deletePermanent(id);
    }

    @GetMapping("/login")
    public ResponseEntity<UsuarioDTO> login(@RequestHeader String user, @RequestHeader String pass) {

        Usuario usuario = userService.login(user, pass);

        if (usuario == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            UsuarioDTO dto = new UsuarioDTO(usuario.getId(), usuario.getName());
            ResponseEntity<UsuarioDTO> usuarioResponseEntity = new ResponseEntity<>(dto,HttpStatus.OK);

            return usuarioResponseEntity;
        }
    }
}
