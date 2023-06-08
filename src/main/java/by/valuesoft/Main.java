package by.valuesoft;

import by.valuesoft.betbonanza.BetBonanza;

public class Main {

    public static void main(String[] args) throws Exception {

        if (Consts.TASK.equalsIgnoreCase("BETBONANZA")) {
            Data.storeAsHTML(
                    new BetBonanza(Consts.URL)
                            .parse()
            );

        } else {
            throw new Exception("System.env 'TASK' should be configured, try TASK=BETBONANZA");
        }
    }

}
