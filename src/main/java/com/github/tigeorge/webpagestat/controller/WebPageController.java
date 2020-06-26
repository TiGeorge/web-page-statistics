package com.github.tigeorge.webpagestat.controller;

import com.github.tigeorge.webpagestat.model.WebPage;
import com.github.tigeorge.webpagestat.model.WebPageService;
import com.github.tigeorge.webpagestat.view.ConsoleView;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.*;
import java.net.URL;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class WebPageController {

    private final WebPageService<Map<String, Integer>> webPageService;

    public WebPageController(WebPageService<Map<String, Integer>> webPageService) {
        this.webPageService = webPageService;
    }

    public void showStatistics(WebPage webPage) throws IOException {

        File input = new File(webPage.getFileName());
        Document doc = Jsoup.parse(input, "UTF-8");

        Map<String, Integer> map = doc.getAllElements().stream()
                .filter(element -> !"noscript".equals(element.tagName()))
                .map(element -> element.ownText().toUpperCase())
                .map(s -> s.split("[.,!?:;()\"\\[\\]\\s]"))
                .flatMap(Arrays::stream)
                .filter(s -> !s.isBlank())
                .collect(Collectors.toMap(s -> s, s -> 1, Integer::sum));
        map.forEach((key, value) -> System.out.println(key + " - " + value));

    }

        public String readUrlString() {
        ConsoleView.writeMessage("Insert web page URL:");
        return ConsoleView.readString();
    }

    public WebPage downloadPage(String urlString, String fileName) throws IOException {

        URL url = new URL(urlString);

        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
                BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        ) {
            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
            }
        }
        return new WebPage(urlString, fileName);
    }
}
