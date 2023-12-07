package com.javarush.cooper.dao;

import java.util.List;
import org.hibernate.query.Query;
import org.hibernate.SessionFactory;
import com.javarush.cooper.domain.City;

public class CityDAO {
    private final SessionFactory sessionFactory;

    public CityDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Получает объект по id
     * @param id - id объекта
     * @return
     */
    public City getById(Integer id) {
        Query<City> query = sessionFactory.getCurrentSession().createQuery("select c from City c join fetch c.country where c.id = :ID", City.class);
        query.setParameter("ID", id);
        return query.getSingleResult();
    }

    /**
     * Получает список объектов
     * @param offset - начальный id объекта
     * @param limit - количество элементов в выборке
     * @return
     */
    public List<City> getItems(int offset, int limit) {
        Query<City> query = sessionFactory.getCurrentSession().createQuery("select c from City c", City.class);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.list();
    }

    /**
     * Получает общее количество объектов в БД
     * @return
     */
    public int getTotalCount() {
        Query<Long> query = sessionFactory.getCurrentSession().createQuery("select count(c) from City c", Long.class);
        return Math.toIntExact(query.uniqueResult());
    }
}
