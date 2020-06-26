package com.github.tigeorge.webpagestat.model;

public interface WebPageService<T> {

    T getStatistics(WebPage webPage);

}
