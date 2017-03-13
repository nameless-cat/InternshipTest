package com.nameless.helpers;

/**
 * Created by wwwtm on 10.03.2017.
 */
public class Validator
{
    public static boolean isValidName(String name)
    {
        if (name == null || "".equals(name))
            return false;

        return name.matches("[a-zA-Z0-9\\-]+");
    }

    public static boolean isValidAge(int age)
    {
        return (age < 1) ? false : true;
    }
}
