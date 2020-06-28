package com.github.tigeorge.webpagesstat;

import com.github.tigeorge.webpagesstat.controller.PageController;
import com.github.tigeorge.webpagesstat.model.WebPage;
import com.github.tigeorge.webpagesstat.model.WordsStatistics;
import com.github.tigeorge.webpagesstat.services.WebPageService;
import com.github.tigeorge.webpagesstat.services.WordsStatisticsServis;
import com.github.tigeorge.webpagesstat.utils.HibernateUtil;
import com.github.tigeorge.webpagesstat.view.ConsoleView;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Application Main Class
 */
public class WebPageStatApp {

    private static final Logger logger = Logger.getLogger(WebPageStatApp.class.getName());

    public static void main(String[] args) {

        PageController controller = new PageController();

        WebPageService webPageService = new WebPageService();
        WordsStatisticsServis wordsStatisticsServis = new WordsStatisticsServis();

        // User URL request
        URL url = controller.readUrl();

        if (url == null) {
            // If the correct URL is not entered, complete the execution.
            ConsoleView.writeMessage("URL not entered.");
            logger.log(Level.INFO, "URL not entered.");
            return;
        }

        // Creating a web page object for saving to the database
        WebPage webPage = new WebPage(url.toString());

        try {
            // Save web page to database
            webPageService.save(webPage);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Cannot save web page to DB", e);
        }

        // Create a file in the root of the project
        String fileName = "page" + webPage.getId() + ".html";

        try {

            // Download the web page to the specified file
            File file = controller.downloadPage(url, fileName);

            // Updating a web page in the database, adding information about the downloaded file
            webPage.setFileName(fileName);
            webPageService.update(webPage);

            // Getting the result of counting words in a file
            InputStream inputStream = new FileInputStream(file);
            Map<String, Integer> statistics = controller.countWords(inputStream, url.toString());

            // Creating a WordsStatistics object to save the word count result in the database
            WordsStatistics wordsStatistics = new WordsStatistics(webPage);
            wordsStatistics.setStatistics(statistics);

            // Saving the result to the database
            wordsStatisticsServis.save(wordsStatistics);

            // Output to console
            statistics.forEach((k, v) -> ConsoleView.writeMessage(k + " - " + v));

        } catch (IOException | SQLException e) {
            logger.log(Level.SEVERE, "Trouble with application", e);
        }

        HibernateUtil.shutdown();
    }
}
