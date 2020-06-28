package com.github.tigeorge.webpagesstat.services;

import com.github.tigeorge.webpagesstat.model.WebPage;
import com.github.tigeorge.webpagesstat.utils.SessionUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

/*
* Implementing CRUD Operations for the WebPage Class
*/
public class WebPageService extends SessionUtil implements Dao<WebPage> {

    /*
    * Saves an object to the database
    */
    @Override
    public void save(WebPage webPage) throws SQLException {

        openTransactionSession();

        Session session = getSession();
        session.save(webPage);

        closeTransactionSession();
    }

    /*
    * Gets a list of all objects from the database
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
    * Gets an object by ID
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
    * Updates an object in the database
    */
    @Override
    public void update(WebPage webPage) throws SQLException {
        openTransactionSession();

        Session session = getSession();
        session.update(webPage);

        closeTransactionSession();
    }

    /*
    * Deletes an object from the database
    */
    @Override
    public void delete(WebPage webPage) throws SQLException {
        openTransactionSession();

        Session session = getSession();
        session.remove(webPage);

        closeTransactionSession();
    }
}
