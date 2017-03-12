package com.nameless.service;

import java.util.List;

import com.nameless.dao.FakeUserData;
import com.nameless.dao.UserDAO;
import com.nameless.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
public class UserService
{

    @Autowired
    private UserDAO userDao;

    @Autowired
    private FakeUserData fakeUserData;

    private int perPage = 10;

    @Transactional
    public List<User> getAllUsers(int page) throws IllegalArgumentException
    {
        if (page < 1)
        {
            throw new IllegalArgumentException();
        }

        List<User> users = userDao.getAllUsers(startFrom(page), perPage);

        if (isPageOutOfRange(page))
        {
            throw new IllegalArgumentException();
        }

        return users;
    }

    @Transactional
    public List<User> filterUsers(String name, int page) throws IllegalArgumentException
    {
        if (page < 1)
        {
            throw new IllegalArgumentException();
        }

        List<User> users = userDao.filterUsers(name, startFrom(page), perPage);

        if (isPageOutOfRange(page))
        {
            throw new IllegalArgumentException();
        }

        return users;
    }

    public int getOverallUsers()
    {
        return userDao.getOverallUsers();
    }

    @Transactional
    public User getUser(int id)
    {
        return userDao.getUser(id);
    }

    @Transactional
    public void addUser(User user)
    {
        userDao.addUser(user);
    }

    @Transactional
    public void updateUser(User user)
    {
        userDao.updateUser(user);

    }

    @Transactional
    public void deleteUser(int id)
    {
        userDao.deleteUser(id);
    }

    @Transactional
    public void spawnUserData()
    {
        fakeUserData.spawnUserData();
    }

    public int getPerPage()
    {
        return perPage;
    }

    public void setPerPage(int perPage)
    {
        this.perPage = perPage;
    }

    private boolean isPageOutOfRange(int page)
    {
        if (page == 1)
            return false;

        int lastPage = userDao.getOverallUsers() / perPage;

        if (userDao.getOverallUsers() % perPage != 0)
            lastPage++;

        if (page > lastPage)
            return true;

        return false;
    }

    private int startFrom(int page)
    {
        return (page - 1) * perPage;
    }
}
