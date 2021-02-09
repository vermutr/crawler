import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

public class Main {
    private static final String EMAIL_PATTERN =
        "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    public static void main(String[] args) throws Exception {
        List<String> refList = new ArrayList<>();
        String regex1 = "https://zakupki.gov.ru/.*";
        String regex2 = ".*common-info.html\\?regNumber=.*";
        String regex3 = "https://zakupki.gov.ru/epz/order/notice//*";
        for (int i = 1; i <= 100; i++) {
            String myStr="https://zakupki.gov.ru/epz/order/extendedsearch/results.html?morphology=on&pageNumber=" + i + "&sortDirection=false&recordsPerPage=_10&showLotsInfoHidden=false&sortBy=UPDATE_DATE&fz44=on&fz223=on&af=on&ca=on&pc=on&pa=on&priceContractAdvantages44IdNameHidden=%7B%7D&priceContractAdvantages94IdNameHidden=%7B%7D&currencyIdGeneral=-1&customerPlace=5277327&delKladrIds=5277327&selectedSubjectsIdNameHidden=%7B%7D&OrderPlacementSmallBusinessSubject=on&OrderPlacementRnpData=on&OrderPlacementExecutionRequirement=on&orderPlacement94_0=0&orderPlacement94_1=0&orderPlacement94_2=0&contractPriceCurrencyId=-1&budgetLevelIdNameHidden=%7B%7D&nonBudgetTypesIdNameHidden=%7B%7D";
            Document doc = Jsoup.connect(myStr).get();
            Elements element = doc.select("a");
            for (Element els : element) {
                String str = els.attr("abs:href");
                if ((Pattern.matches(regex1,str) && Pattern.matches(regex2,str)) || (Pattern.matches(regex3,str) && Pattern.matches(regex2,str))) {
                    refList.add(str);
                }
            }

            for (String ptr : refList) {
                System.out.println(ptr);
            }
        }
        Set<String> mails=new HashSet<>();
        for(String link: refList){
            Document document = Jsoup.connect(link).get();
            Elements elements = document.getElementsMatchingText(EMAIL_PATTERN);
            for(Element element: elements){
                String s=element.text();
                mails.add(s);
            }

        }

        for(String mail: mails){
            System.out.println(mail);
        }

    }
}
