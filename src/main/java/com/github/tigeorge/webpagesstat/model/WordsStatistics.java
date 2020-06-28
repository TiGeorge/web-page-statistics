package com.github.tigeorge.webpagesstat.model;

import javax.persistence.*;
import java.util.Map;

/*
* Stores information about word count statistics on a page.
*/
@Entity
@Table(name = "word_statistics")
public class WordsStatistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    private WebPage webPage;

    @ElementCollection
    @CollectionTable(name = "page_statistics_mapping",
            joinColumns = {@JoinColumn(name = "page_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "word")
    @Column(name = "quantity")
    private Map<String, Integer> statistics;

    public WordsStatistics() {
    }

    public WordsStatistics(WebPage page) {
        this.webPage = page;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public WebPage getWebPage() {
        return webPage;
    }

    public void setWebPage(WebPage webPage) {
        this.webPage = webPage;
    }

    public Map<String, Integer> getStatistics() {
        return statistics;
    }

    public void setStatistics(Map<String, Integer> statistics) {
        this.statistics = statistics;
    }
}
