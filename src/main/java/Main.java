import config.ApiConfiguration;
import service.LinkGetter;
import service.MailGetter;

import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        ApiConfiguration apiConfiguration = new ApiConfiguration();
        LinkGetter linkGetter = apiConfiguration.getLink();
        List<String> linksList = linkGetter.getLinksList();
        MailGetter mailGetter = apiConfiguration.getMail(linksList);
        mailGetter.getMails();
    }
}
