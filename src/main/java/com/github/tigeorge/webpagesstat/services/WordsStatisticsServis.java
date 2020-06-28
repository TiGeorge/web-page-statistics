package com.github.tigeorge.webpagesstat.services;

import com.github.tigeorge.webpagesstat.model.WordsStatistics;
import com.github.tigeorge.webpagesstat.utils.SessionUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

/*
 * Реализация CRUD-операций для класса WordsStatistics
 */
public class WordsStatisticsServis extends SessionUtil implements Dao<WordsStatistics> {

    /*
     * Сохраняет объект в БД
     */
    @Override
    public void save(WordsStatistics wordsStatistics) throws SQLException {

        openTransactionSession();

        Session session = getSession();
        session.save(wordsStatistics);

        closeTransactionSession();
    }

    /*
     * Получает из БД список всех объектов
     */
    @Override
    public List<WordsStatistics> getAll() throws SQLException {

        openTransactionSession();

        String sql = "select * from word_statistics";

        Session session = getSession();
        Query query = session.createNativeQuery(sql).addEntity(WordsStatistics.class);
        List<WordsStatistics> wordsStatisticsList = query.list();

        closeTransactionSession();

        return wordsStatisticsList;
    }

    /*
     * получает объект по ID
     */
    @Override
    public WordsStatistics get(long id) throws SQLException {

        openTransactionSession();

        Session session = getSession();

        String sql = "select * from word_statistics where id = :id";
        Query query = session.createNativeQuery(sql).addEntity(WordsStatistics.class);
        query.setParameter("id", id);

        WordsStatistics wordsStatistics = (WordsStatistics) query.getSingleResult();

        closeTransactionSession();

        return wordsStatistics;
    }

    /*
     * Обновляет объект в БД
     */
    @Override
    public void update(WordsStatistics wordsStatistics) throws SQLException {
        openTransactionSession();

        Session session = getSession();
        session.update(wordsStatistics);

        closeTransactionSession();
    }

    /*
     * Удаляет объект из БД
     */
    @Override
    public void delete(WordsStatistics wordsStatistics) throws SQLException {
        openTransactionSession();

        Session session = getSession();
        session.remove(wordsStatistics);

        closeTransactionSession();
    }
}
