package com.nameless.helpers;

import com.nameless.model.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by wwwtm on 10.03.2017.
 */
@Component
public class UserValidator implements Validator
{
    private static String namePattern = "[a-zA-Z0-9\\-]+";

    @Override
    public boolean supports(Class<?> aClass)
    {
        return User.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors)
    {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "age", "age.required");

        User u = (User) o;

        if (!u.getName().matches(namePattern))
            errors.rejectValue("name", "nameIllegalChars");
        if (1 > u.getAge() || u.getAge() > 120)
            errors.rejectValue("age", "ageIllegalValue");

    }

    // freaky workaround
    public static boolean isNameValid(String name)
    {
        return name.matches(namePattern);
    }
}
