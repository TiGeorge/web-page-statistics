package com.github.tigeorge.webpagestat;

import com.github.tigeorge.webpagestat.controller.WebPageController;
import com.github.tigeorge.webpagestat.model.WebPage;
import com.github.tigeorge.webpagestat.model.WebPageService;
import com.github.tigeorge.webpagestat.model.WebPageServiceImpl;

import java.io.IOException;
import java.util.Map;

public class WebPageStatApp {
    public static void main(String[] args) {

        WebPageService<Map<String, Integer>> webPageService = new WebPageServiceImpl();
        WebPageController controller = new WebPageController(webPageService);
        String urlString = controller.readUrlString();
        WebPage webPage = null;
        String fileName = "page.html";
        try {
            webPage = controller.downloadPage(urlString, fileName);
            controller.showStatistics(webPage);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
