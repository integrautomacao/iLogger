package IntegraLogger.Application.Listeners;

import etherip.Tag;
import etherip.TagListener;

public class TagLogger implements TagListener {
    @Override
    public synchronized void tagUpdate(Tag tag) {
        System.out.println("Tag Logger");
        System.out.println(tag.getData());
    }

    @Override
    public synchronized void tagError(Tag tag) {

    }
}
