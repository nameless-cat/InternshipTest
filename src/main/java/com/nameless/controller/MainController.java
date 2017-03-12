package com.nameless.controller;


import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nameless.helpers.Validator;
import com.nameless.model.User;
import com.nameless.service.Filter;
import com.nameless.service.UserService;
import com.nameless.helpers.TagNavigatorBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController
{

    @Autowired
    UserService userService;


    private String notFoundRedirect = "redirect:/userNotFound";
    private String wrongNameRedirect = "redirect:/wrongUserName";
    private String defaultRedirect = "redirect:/getAllUsers";

    @RequestMapping(value = "/getAllUsers", method = RequestMethod.GET, headers = "Accept=application/json")
    public String getUsers(Model model)
    {
        return "redirect:/getAllUsers/1";
    }

    @RequestMapping(value = "/getAllUsers/{page}", method = RequestMethod.GET, headers = "Accept=application/json")
    public String getUsers(@PathVariable int page, Model model)
    {
        List<User> listOfUsers = new ArrayList<>();

        try
        {
            listOfUsers = userService.getAllUsers(page);

        } catch (IllegalArgumentException e)
        {
            return "redirect:/404";
        }

        buildModel(model, listOfUsers, page, "/getAllUsers");

        return "userDetails";
    }

    @RequestMapping(value = "/getUser/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    public User getUserById(@PathVariable int id)
    {
        return userService.getUser(id);
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST, headers = "Accept=application/x-www-form-urlencoded")
    public String addUser(@ModelAttribute("user") User user, Model model)
    {
        if (!Validator.isValidName(user.getName()))
            return wrongNameRedirect;

        if (user.getId() == 0)
        {
            userService.addUser(user);
        } else
        {
            userService.updateUser(user);
        }

        return "redirect:/getAllUsers";
    }

    @RequestMapping(value = "/updateUser/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    public String updateUser(@PathVariable("id") int id, Model model)
    {
        model.addAttribute("user", this.userService.getUser(id));
        model.addAttribute("listOfUsers", this.userService.getAllUsers(1));
        return "userDetails";
    }

    @RequestMapping(value = "/deleteUser/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    public String deleteUser(@PathVariable("id") int id)
    {
        userService.deleteUser(id);
        return "redirect:/getAllUsers";

    }

    @RequestMapping(value = "/spawnUserTestData", method = RequestMethod.GET, headers = "Accept=application/json")
    public String spawnUserData()
    {
        userService.spawnUserData();
        return "redirect:/getAllUsers";
    }

    @RequestMapping(value = "/filter", method = RequestMethod.POST, headers = "Accept=application/json")
    public String filterUser(@RequestParam("filter") String filter, Model model)
    {
        if (!Validator.isValidName(filter))
            return wrongNameRedirect;

        return String.format("redirect:/filter/%s/1", filter);
    }

    @RequestMapping(value = "/filter/{userName}/{page}", method = RequestMethod.GET, headers = "Accept=application/json")
    public String filterUser(@PathVariable("userName") String name, @PathVariable("page") int page, Model model)
    {
        if (name == null || "".equals(name))
            return defaultRedirect;

        if (!Validator.isValidName(name))
            return wrongNameRedirect;

        List<User> listOfUsers = new ArrayList<>();
        try
        {
            listOfUsers = userService.filterUsers(name, page);

        } catch (IllegalArgumentException e)
        {
            return "redirect:/404";
        }

        if (listOfUsers.size() == 0)
            return notFoundRedirect;

        String linkBase = "/filter/" + name;
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("filter", name);
        buildModel(model, listOfUsers, page, linkBase, attributes);

        return "userDetails";
    }

    @RequestMapping(value = "/userNotFound", method = RequestMethod.GET)
    public String userNotFound()
    {
        return "userNotFound";
    }

    @RequestMapping(value = "/wrongUserName", method = RequestMethod.GET)
    public String wrongUserName()
    {
        return "wrongUserName";
    }

    private void setNameError(Model model)
    {
        model.addAttribute("error", "Wrong user name");
    }

    private void buildModel(Model model, List<User> listOfUsers, int page, String linkBase)
    {
        buildModel(model, listOfUsers, page, linkBase, new HashMap<String, Object>());
    }

    private void buildModel(Model model, List<User> listOfUsers, int page, String linkBase, Map<String, Object> attributes)
    {
        model.addAttribute("user", new User());
        model.addAttribute("listOfUsers", listOfUsers);
        TagNavigatorBuilder tnb = new TagNavigatorBuilder(page,
                userService.getPerPage(),
                userService.getOverallUsers(),
                linkBase);
        model.addAttribute("navigator", tnb.build());

        if (attributes.size() > 0)
        {
            for (Map.Entry<String, Object> pair : attributes.entrySet())
                model.addAttribute(pair.getKey(), pair.getValue());
        }
    }

}