package IntegraLogger.DTO;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
public class UsuarioDTO  {
    private Long id;
    private String name;

    public UsuarioDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
