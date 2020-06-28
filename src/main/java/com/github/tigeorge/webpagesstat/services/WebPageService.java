package com.github.tigeorge.webpagesstat.services;

import com.github.tigeorge.webpagesstat.model.WebPage;
import com.github.tigeorge.webpagesstat.utils.SessionUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

/*
* Реализация CRUD-операций для класса WebPage
*/
public class WebPageService extends SessionUtil implements Dao<WebPage> {

    /*
    * Сохраняет объект в БД
    */
    @Override
    public void save(WebPage webPage) throws SQLException {

        openTransactionSession();

        Session session = getSession();
        session.save(webPage);

        closeTransactionSession();
    }

    /*
    * Получает из БД список всех объектов
    */
    @Override
    public List<WebPage> getAll() throws SQLException {

        openTransactionSession();

        String sql = "select * from web_page";

        Session session = getSession();
        Query query = session.createNativeQuery(sql).addEntity(WebPage.class);
        List<WebPage> webPageList = query.list();

        closeTransactionSession();

        return webPageList;
    }

    /*
    * получает объект по ID
    */
    @Override
    public WebPage get(long id) throws SQLException {

        openTransactionSession();

        Session session = getSession();

        String sql = "select * from web_page where id = :id";
        Query query = session.createNativeQuery(sql).addEntity(WebPage.class);
        query.setParameter("id", id);

        WebPage webPage = (WebPage) query.getSingleResult();

        closeTransactionSession();

        return webPage;
    }

    /*
    * Обновляет объект в БД
    */
    @Override
    public void update(WebPage webPage) throws SQLException {
        openTransactionSession();

        Session session = getSession();
        session.update(webPage);

        closeTransactionSession();
    }

    /*
    * Удаляет объект из БД
    */
    @Override
    public void delete(WebPage webPage) throws SQLException {
        openTransactionSession();

        Session session = getSession();
        session.remove(webPage);

        closeTransactionSession();
    }
}
