package com.github.tigeorge.webpagesstat.controller;

import com.github.tigeorge.webpagesstat.view.ConsoleView;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/*
* Contains methods of the main functions of the application
*/
public class PageController {

    private static final Logger logger = Logger.getLogger(PageController.class.getName());

    /*
    * Asks the user for the URL of the web page.
    * If the URL is entered incorrectly, offers to repeat the entry.
    */
    public URL readUrl() {

        URL url = null;
        while (true) {

            ConsoleView.writeMessage("Enter web page URL or \"exit\" for exit:");
            String input = ConsoleView.readString();

            if ("exit".equals(input)) {
                break;
            }

            try {
                url = new URL(input);
                break;
            } catch (MalformedURLException e) {
                ConsoleView.writeMessage("The URL is not in a valid form. Try again...");

                logger.log(Level.INFO, "The URL is not in a valid form.", e);
            }
        }

        return url;
    }

    /*
    * Saves the specified web page to the specified file
    */
    public File downloadPage(URL url, String fileName) throws IOException {

        File file = new File(fileName);

        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        ) {
            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Cannot download page.", e);
        }
        return file;
    }

    /*
    * Counts the words in the transmitted stream. Returns a map of results.
    */
    public Map<String, Integer> countWords(InputStream input, String baseUri) throws IOException {

        Document doc = Jsoup.parse(input, "UTF-8", baseUri);
        Map<String, Integer> map = doc.getAllElements().stream()
                .filter(element -> !"noscript".equals(element.tagName()))
                .map(element -> element.ownText().toUpperCase())
                .map(s -> s.split("[.,!?:;()\"\\[\\]\\s]"))
                .flatMap(Arrays::stream)
                .filter(s -> !s.isBlank())
                .collect(Collectors.toMap(s -> s, s -> 1, Integer::sum));

        return map;
    }
}
