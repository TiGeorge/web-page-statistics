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
 * Основной класс приложения
 */
public class WebPageStatApp {

    private static final Logger logger = Logger.getLogger(WebPageStatApp.class.getName());

    public static void main(String[] args) {

        PageController controller = new PageController();

        WebPageService webPageService = new WebPageService();
        WordsStatisticsServis wordsStatisticsServis = new WordsStatisticsServis();

        // Запрашиваем у пользователя URL
        URL url = controller.readUrl();

        if (url == null) {
            // если корректный URL не введен, завершаем выполнение.
            ConsoleView.writeMessage("URL not entered.");
            logger.log(Level.INFO, "URL not entered.");
            return;
        }

        // Создаем объект веб-страницы для последующего сохранения в БД
        WebPage webPage = new WebPage(url.toString());

        try {
            // Сохраняем веб-страницу в БД
            webPageService.save(webPage);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Cannot save web page to DB", e);
        }

        // Создаем файл в корне проекта
        String fileName = "page" + webPage.getId() + ".html";

        try {

            // Скачиваем веб-страницу в указанный файл
            File file = controller.downloadPage(url, fileName);

            // Обновляем веб-страницу в БД, добавляя информацию о скачанном файлу
            webPage.setFileName(fileName);
            webPageService.update(webPage);

            // Получаем результат подсчета слов в файле
            InputStream inputStream = new FileInputStream(file);
            Map<String, Integer> statistics = controller.countWords(inputStream, url.toString());

            // Создаем объект WordsStatistics для сохранения результата посчета слов в БД
            WordsStatistics wordsStatistics = new WordsStatistics(webPage);
            wordsStatistics.setStatistics(statistics);

            // Сохраняем результат в БД
            wordsStatisticsServis.save(wordsStatistics);

            // Выводим результат в консоль
            statistics.forEach((k, v) -> ConsoleView.writeMessage(k + " - " + v));

        } catch (IOException | SQLException e) {
            logger.log(Level.SEVERE, "Trouble with application", e);
        }

        HibernateUtil.shutdown();
    }
}
