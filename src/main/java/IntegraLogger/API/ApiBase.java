package IntegraLogger.API;

import IntegraLogger.Controller.Service.ServiceBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;


@RequestMapping("/api")
public interface ApiBase<T, ID extends Serializable> {


    public T save(T o);

    public Optional update(ID id, T object);

    public List<T> getAll();

    public T getOne(@PathVariable ID id);

    public void delete(@PathVariable ID id);


}
