package by.valuesoft;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class League {

    private String bk;
    private String time;
    private long timeMs;
    private String club1, club2;
    private String tournament;
    private String sport;

    public League(String bk, String time, String timeFormat, ArrayList<String> clubs, String tournament, String sport) throws ParseException {
        this.bk = bk;

        this.time = time;
        this.timeMs = convertToMilliseconds(time, timeFormat);

        this.club1 = clubs.get(0);
        this.club2 = clubs.get(1);

        this.tournament = tournament;
        this.sport = sport;
    }

    public String get

    public static long convertToMilliseconds(String time, String timeFormat) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(timeFormat);

        Date date = format.parse(time);
        return date.getTime();
    }
}
