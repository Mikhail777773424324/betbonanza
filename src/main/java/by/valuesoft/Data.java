package by.valuesoft;

import java.io.*;
import java.util.ArrayList;

public class Data {

    public static void storeAsHTML(ArrayList<League> leagues, String bk) throws Exception {
        if (leagues == null)
            throw new Exception("Failed to parse data " + Consts.TASK);

        StringBuilder data = new StringBuilder();
        leagues.forEach(league -> data.append(league.getTr()));


        String fileTemplate = "html" + File.separator + "templates" + File.separator + bk.toLowerCase() + ".html";
        String fileOutput = "html" + File.separator + "output" + File.separator + bk.toLowerCase() + ".html";

        // Read the template file
        StringBuilder contentBuilder = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(fileTemplate))) {
            String line;
            while ((line = br.readLine()) != null) {
                contentBuilder.append(line);
                contentBuilder.append(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String template = contentBuilder.toString();

        String htmlContent = template.replace("{{table_data}}", data);

        // Write the generated HTML content to the output file
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileOutput))) {
            bw.write(htmlContent);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("HTML page generated successfully!");
    }
}
