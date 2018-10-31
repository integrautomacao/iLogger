package IntegraLogger.Application.Listeners;

import etherip.Tag;
import etherip.TagListener;

public class TagPersist implements TagListener {
    @Override
    public synchronized void tagUpdate(Tag tag) {
        System.out.println("Persist -> "+tag.getData());

    }

    @Override
    public synchronized void tagError(Tag tag) {
        System.out.println("error Persist");
    }
}
