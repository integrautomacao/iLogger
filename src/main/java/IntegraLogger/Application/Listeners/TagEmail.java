package IntegraLogger.Application.Listeners;

import IntegraLogger.Application.ThreadPool;
import IntegraLogger.Controller.Service.ItagValueService;
import IntegraLogger.Controller.Service.PlcService;
import IntegraLogger.Controller.Service.testeService;
import etherip.Tag;
import etherip.TagListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
public class TagEmail implements TagListener {

    @Autowired
    PlcService plcService;

    @Autowired
    ItagValueService itagValueService;

    @Autowired
    ThreadPool pool;

    @Autowired
    IntegraLogger.Controller.Service.testeService testeService;

    @Override
    public synchronized void tagUpdate(Tag tag) {

        System.out.println("Email Sender -> Value: " + tag.getData());
        testeService.teste();
        plcService.test();
    }

    @Override
    public synchronized void tagError(Tag tag) {
        System.out.println("error Email");
    }
}
