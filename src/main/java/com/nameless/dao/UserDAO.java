package com.nameless.dao;

import java.util.Collections;
import java.util.List;

import com.nameless.model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO
{

    @Autowired
    private SessionFactory sessionFactory;

    private int overallUsers = 0;

    public void setSessionFactory(SessionFactory sf)
    {
        sessionFactory = sf;
    }

    public List<User> getAllUsers(int from, int perPage)
    {
        Session session = sessionFactory.getCurrentSession();
        //noinspection JpaQlInspection
        Query q = session.createQuery("FROM User");
        overallUsers = q.list().size();
        q.setFirstResult(from);
        q.setMaxResults(perPage != 0 ? perPage : 5);

        return q.list();
    }

    public List<User> filterUsers(String name, int from, int perPage)
    {
        if (name == null || "".equals(name))
            return Collections.emptyList();

        Session session = sessionFactory.getCurrentSession();
        //noinspection JpaQlInspection
        Query q = session.createQuery("FROM User WHERE name = :name");
        q.setParameter("name", name);
        overallUsers = q.list().size();
        q.setFirstResult(from);
        q.setMaxResults(perPage != 0 ? perPage : 5);

        return q.list();
    }

    public User getUser(int id)
    {
        Session session = sessionFactory.getCurrentSession();
        User user = (User) session.get(User.class, new Integer(id));

        return user;
    }

    public User addUser(User user)
    {
        Session session = sessionFactory.getCurrentSession();
        session.persist(user);

        return user;
    }

    public void updateUser(User user)
    {
        Session session = sessionFactory.getCurrentSession();
        session.update(user);
    }

    public void deleteUser(int id)
    {
        Session session = sessionFactory.getCurrentSession();
        User p = (User) session.load(User.class, new Integer(id));
        if (null != p)
        {
            session.delete(p);
        }
    }

    public int getOverallUsers()
    {
        return overallUsers;
    }
}
