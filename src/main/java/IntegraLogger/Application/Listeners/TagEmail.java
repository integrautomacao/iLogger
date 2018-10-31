package IntegraLogger.Application.Listeners;

import etherip.Tag;
import etherip.TagListener;

public class TagEmail implements TagListener {
    @Override
    public synchronized void tagUpdate(Tag tag) {
        System.out.println();
        System.out.println("Email Sender -> " + tag.getData());
    }

    @Override
    public synchronized void tagError(Tag tag) {
        System.out.println("error Email");
    }
}
