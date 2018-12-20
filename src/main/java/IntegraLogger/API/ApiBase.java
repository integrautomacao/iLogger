package IntegraLogger.API;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
