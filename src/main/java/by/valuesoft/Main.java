package by.valuesoft;

import by.valuesoft.betbonanza.BetBonanza;

public class Main {

    public static void main(String[] args) throws Exception {

        if (Consts.TASK.equalsIgnoreCase("BETBONANZA")) {
            new BetBonanza(Consts.URL).start();

        } else {
            throw new Exception("System env TASK should be configured, try TASK=BETBONANZA");
        }
    }

}
