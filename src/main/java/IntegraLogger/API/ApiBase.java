//package IntegraLogger.API;
//
//import IntegraLogger.Controller.Service.ServiceBase;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.web.bind.annotation.*;
//
//import java.io.Serializable;
//import java.util.List;
//import java.util.Optional;
//
//
//@RequestMapping("/api")
//public abstract class ApiBase<T, ID extends Serializable> {
//
////    @Autowired
////    public ApiBase(ServiceBase serviceBase) {
////        this.serviceBase = serviceBase;
////    }
//
//
//    @Autowired
//    public ApiBase(ServiceBase serviceBase) {
//        this.serviceBase = serviceBase;
//    }
//
//    ServiceBase serviceBase;
//
//
//    @PutMapping
//    public T save(T object) {
//        return serviceBase.save(object);
//
//    }
//
//    @PostMapping
//    public Optional update(ID id, T object) {
//        return serviceBase.update(id, object);
//    }
//
//    @GetMapping
//    public List<T> getAll() {
//        return serviceBase.getAll();
//    }
//
//
//    @GetMapping("/{id}")
//    public T getOne(@PathVariable ID id) {
//        return serviceBase.getById(id);
//    }
//
//    @DeleteMapping("/{id}")
//    public void delete(@PathVariable ID id) {
//        serviceBase.deletePermanent(id);
//    }
//
//
//}
