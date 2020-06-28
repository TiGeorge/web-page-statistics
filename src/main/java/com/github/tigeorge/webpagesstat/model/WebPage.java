package com.github.tigeorge.webpagesstat.model;

import javax.persistence.*;

/*
* Содержит информацию о веб-странице, сохраняется в БД
*/
@Entity
@Table(name = "web_page")
public class WebPage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "url")
    private String urlString;

    @Column(name = "file_name")
    private String fileName;

    public WebPage() {
    }

    public WebPage(String urlString) {
        this.urlString = urlString;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrlString() {
        return urlString;
    }

    public void setUrlString(String urlString) {
        this.urlString = urlString;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return "WebPage{" +
                "id=" + id +
                ", urlString='" + urlString + '\'' +
                ", fileName='" + fileName + '\'' +
                '}';
    }
}
