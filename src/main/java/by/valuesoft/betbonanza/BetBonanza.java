package by.valuesoft.betbonanza;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class BetBonanza {

    private String url;

    public BetBonanza(String url) {
        this.url = url;
    }

    public void start() throws IOException {
        Document doc = Jsoup.connect(url).get();
        System.out.println(doc.title());
    }
}
