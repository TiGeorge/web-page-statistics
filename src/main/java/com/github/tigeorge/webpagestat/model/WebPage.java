package com.github.tigeorge.webpagestat.model;

public class WebPage {

    private int id;
    private String urlString;
    private String fileName;

    public WebPage(String urlString, String fileName) {
        this.urlString = urlString;
        this.fileName = fileName;
    }

    public int getId() {
        return id;
    }

    public String getUrlString() {
        return urlString;
    }

    public String getFileName() {
        return fileName;
    }
}
