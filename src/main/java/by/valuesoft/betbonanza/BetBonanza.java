package by.valuesoft.betbonanza;

import by.valuesoft.League;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class BetBonanza {

    private static String BK = "BetBonanza";

    private String url;
    private ArrayList<League> leagues;

    public BetBonanza(String url) {
        this.url = url;
        this.leagues = new ArrayList<>();
    }

    public ArrayList<League> parse() throws Exception {

        // ***************** GET DATA && SELECT TABLES
        Document doc = Jsoup.connect(url).get();
        Elements tables = doc.select("table[class='league']");

        tables.forEach(league -> {

            // ***************** GET TIME
            String timeFormat = "dd MMM HH:mm";
            String time = league.select("td[class=time]")
                    .text().replaceFirst("^\\S+\\s*", "").trim();

            // ***************** GET CLUBS
            ArrayList<String> clubs = new ArrayList<>();
            league.select("td[class=clubs]").select("p").forEach(element -> clubs.add(element.text()));


            // ***************** GET TOURNAMENT AND SPORT TYPES
            String[] parts = league.select("td[class=meta]").text().split("\\s*/\\s*");
            String tournament = parts[2].trim();
            String sport = parts[0].trim();


            // ***************** SAVE LEAGUE
            try {
                League mLeague = new League(BK, time, timeFormat, clubs, tournament, sport);
                leagues.add(mLeague);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        });

        return leagues;
    }

}
