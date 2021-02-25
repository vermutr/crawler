package config;

import service.LinkGetter;
import service.MailGetter;

import java.util.List;

public class ApiConfiguration {

    public LinkGetter getLink(){
        return new LinkGetter();
    }

    public MailGetter getMail(final List<String> linksList){
        return new MailGetter(linksList);
    }

}
