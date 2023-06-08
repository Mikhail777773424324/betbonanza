package by.valuesoft;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class League {

    private String bk;
    private final String time;
    private final long timeMs;
    private final String club1;
    private final String club2;
    private final String tournament;
    private final String sport;

    public League(String bk, String time, String timeFormat, ArrayList<String> clubs, String tournament, String sport) throws ParseException {
        this.bk = bk;

        this.time = time;
        this.timeMs = convertToMilliseconds(time, timeFormat);

        this.club1 = clubs.get(0);
        this.club2 = clubs.get(1);

        this.tournament = tournament;
        this.sport = sport;
    }

    public String getTr() {
        StringBuilder sb = new StringBuilder();
        sb.append("<tr>");

        sb.append("<td data-date=\"").append(timeMs).append("\">").append(time).append("</td>");
        sb.append("<td>").append(club1).append(" - ").append(club2).append("</td>");
        sb.append("<td>").append(tournament).append("</td>");
        sb.append("<td>").append(sport).append("</td>");
        sb.append("</tr>");

        return sb.toString();
    }

    public static long convertToMilliseconds(String time, String timeFormat) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(timeFormat);

        Date date = format.parse(time);
        return date.getTime();
    }
}
