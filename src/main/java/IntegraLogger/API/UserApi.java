package IntegraLogger.API;

import IntegraLogger.Application.AppConstants;
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
@CrossOrigin(origins = AppConstants.FRONT_URL)
@RequestMapping("/user")
public class UserApi implements ApiBase<Usuario, Long> {

    @Autowired
    private UserService userService;

    @Override
    @PostMapping("/fat")
    public Usuario save(@RequestBody Usuario o) {
        return userService.save(o);
    }

    @PostMapping
    public ResponseEntity<Usuario> saveDTO(@RequestBody UsuarioDTO usuarioDTO) {
        Usuario usuario = userService.saveDTO(usuarioDTO);
        if (usuario.getId() != null) {

            return new ResponseEntity<>(usuario, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public Optional update(Long id, Usuario object) {
        return Optional.empty();
    }

    @GetMapping("/fat")
    @Override
    public List<Usuario> getAll() {
        return userService.getAll();
    }

    @GetMapping
    public List<UsuarioDTO> getAllUsersDTO() {
        return userService.parseUsuarioDTO(userService.getAll());
    }

    @Override
    @GetMapping("/fat/{id}")
    public Usuario getOne(@PathParam("id") Long id) {
        Usuario usuario = userService.getById(id);
        return usuario;
    }

    @GetMapping("/{id}")
    public UsuarioDTO getOneDTO(@PathVariable("id") Long id) {
        return userService.parseUsuarioDTO(userService.getById(id));
    }

    @GetMapping("/reset")
    public ResponseEntity<Boolean> resetPassword(@RequestHeader Long id) {
        if (userService.resetPassword(id)) {
            //send email
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @DeleteMapping("/{id}")
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
            ResponseEntity<UsuarioDTO> usuarioResponseEntity = new ResponseEntity<>(dto, HttpStatus.OK);

            return usuarioResponseEntity;
        }
    }
}
