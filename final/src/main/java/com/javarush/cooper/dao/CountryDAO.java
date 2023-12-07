package com.javarush.cooper.dao;

import java.util.List;
import org.hibernate.query.Query;
import org.hibernate.SessionFactory;
import com.javarush.cooper.domain.Country;

public class CountryDAO {
    private final SessionFactory sessionFactory;

    public CountryDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Получает список всех стран
     * @return
     */
    public List<Country> getAll() {
        Query<Country> query = sessionFactory.getCurrentSession().createQuery("select c from Country c join fetch c.languages", Country.class);
        return query.list();
    }
}
