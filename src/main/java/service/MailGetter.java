package service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.naming.LimitExceededException;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MailGetter {
    private final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                    "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private final List<String> linksList;

    public MailGetter(final List<String> linksList) {
        this.linksList = linksList;
    }

    public void getMails(){
        final Set<String> mails=new HashSet<>();
        for(String link: linksList){
            try {
                Document document = Jsoup.connect(link).get();
                Elements elements = document.getElementsMatchingText(EMAIL_PATTERN);
                for(Element element: elements){
                    String s=element.text();
                    mails.add(s);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        mails.forEach(System.out::println);
    }


}
