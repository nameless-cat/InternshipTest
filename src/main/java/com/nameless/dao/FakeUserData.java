package com.nameless.dao;

import com.nameless.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;

/**
 * Created by wwwtm on 09.03.2017.
 */
@Repository
public class FakeUserData
{
    @Autowired
    private SessionFactory sessionFactory;

    private static List<String> userNames;

    static
    {
        userNames = new ArrayList<>();

        userNames.add("Andrey");
        userNames.add("Michail");
        userNames.add("Valentina");
        userNames.add("Sergey");
        userNames.add("Petr");
        userNames.add("Alexandr");
        userNames.add("Olga");
        userNames.add("Victor");
        userNames.add("Zinaida");
        userNames.add("Vyacheslav");
        userNames.add("Vladimir");
        userNames.add("Stanislav");
        userNames.add("Maxim");
        userNames.add("Ludmila");
        userNames.add("Darya");
        userNames.add("Lyesa");
        userNames.add("Elena");
        userNames.add("Irina");
        userNames.add("Nina");
    }

    public void spawnUserData()
    {
        Session session = sessionFactory.getCurrentSession();
        Random r = new Random();

        for (int i = 0; i < 130; i++)
        {
            String name = userNames.get(r.nextInt(userNames.size()));
            User u = new User(name, r.nextInt(100));

            session.save(u);
        }
    }
}
