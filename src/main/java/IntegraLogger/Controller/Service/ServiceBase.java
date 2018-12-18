package IntegraLogger.Controller.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Service
public abstract class ServiceBase<T, ID extends Serializable, R extends JpaRepository<T, ID>> {
    protected final R repository;

    @Autowired
    public ServiceBase(R repository) {
        this.repository = repository;
    }

    public T save(T entity) {
        return repository.save(entity);
    }

    public void deletePermanent(ID id) {
        repository.deleteById(id);
    }

    public List<T> getAll() {
        return repository.findAll();
    }

    public T getById(ID id) {
        return repository.getOne(id);
    }

    public boolean hasData() {
        return repository.count() > 0;
    }

}
