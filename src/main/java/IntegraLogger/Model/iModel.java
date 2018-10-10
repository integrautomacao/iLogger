package IntegraLogger.Model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public interface iModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id = 0L;

    public Long getId();
}
