package IntegraLogger.Controller.Service;

import IntegraLogger.Controller.Repository.ItagValueRepository;
import IntegraLogger.Model.Tag.ItagDescription;
import IntegraLogger.Model.Tag.ItagValue;
import etherip.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ItagValueService extends ServiceBase<ItagValue, Long, ItagValueRepository> {

    private ItagConfigService configService = BeanUtil.getBean(ItagConfigService.class);

    private PlcService plcService = BeanUtil.getBean(PlcService.class);
    private Map<String, ItagValue> values = new HashMap<>(configService.countInt());

    private SimpleDateFormat hourFormat = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private SimpleDateFormat lastUpdate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    public ItagValueService(ItagValueRepository repository) {
        super(repository);
    }

    public ItagValue tagToItag(Tag tag) throws Exception {
        Date date = new Date();
        ItagValue value = new ItagValue();
        value.setName(tag.getName());
        value.setType(tag.getData().getType().name());
        value.setPlcSource(plcService.getById(tag.getSource()));
        value.setDate(dateFormat.format(date));
        value.setHour(hourFormat.format(date));
        value.setLastUpdate(date);
        if (tag.getData().isNumeric()) {
            switch (value.getType()) {
                case "BOOL":
                    if (tag.getData().getNumber(0).intValue() == 0) {
                        value.setValueBool(false);
                    } else {
                        value.setValueBool(true);
                    }
                    break;
                case "REAL":
                    value.setValueFloat(tag.getData().getNumber(0).floatValue());
                    break;
                default:
                    value.setValueInt(tag.getData().getNumber(0).intValue());
                    break;
            }
        } else {
            value.setValueString(tag.getData().getString());
        }

        return value;
    }

    public void checkAndSave(ItagValue value) {
        if (!value.equals(null)) {
            String key = value.getName() + value.getPlcSource().getId();
            if (values.containsKey(key)) {
                ItagValue itagValue = values.get(key);
                switch (itagValue.getType()) {
                    case "BOOL":
                        if (value.getValueBool() == itagValue.getValueBool()){
                            itagValue.setLastUpdate(value.getLastUpdate());
                            values.get(key).setLastUpdate(itagValue.getLastUpdate());
                            repository.setTimeUpdate(value.getLastUpdate(), itagValue.getId());
                        } else {
                            values.put(key, value);
                            repository.save(value);
                        }
                        break;
                    case "REAL":
                        //TODO implement especified rules to REAL and STRUCT strategy
                        values.put(key, value);
                        repository.save(value);
                        break;
                    case "STRUCT":
                        values.put(key, value);
                        repository.save(value);
                        break;
                    default:
                        values.put(key, value);
                        repository.save(value);
                        break;
                }
            }else{
                values.put(key, value);
                repository.save(value);
            }
        }
    }


}
