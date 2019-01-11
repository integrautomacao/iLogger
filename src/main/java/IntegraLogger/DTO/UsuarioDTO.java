package IntegraLogger.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
public class UsuarioDTO  {
    private Long id;
    private String name;
    private String email;
    private Long phoneNumber;
    private Long sectorId;


    public UsuarioDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public UsuarioDTO() {
    }

    public UsuarioDTO(String name, String email, Long phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getSector() {
        return sectorId;
    }

    public void setSector(Long sectorId) {
        this.sectorId = sectorId;
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
