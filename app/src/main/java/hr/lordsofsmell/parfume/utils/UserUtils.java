package hr.lordsofsmell.parfume.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.validator.routines.EmailValidator;

import java.util.regex.Pattern;

/**
 * Created by thekarlo95 on 5/7/17.
 */

public class UserUtils {

    private static final String USERNAME_PATTERN = "[a-zA-Z0-9-_]{4,}";
    private static final String PASSWORD_PATTERN = "[a-zA-Z0-9-_]{6,}";

    private UserUtils() {
    }

    public static boolean isValidUsername(String username) {
        return username.matches(USERNAME_PATTERN);
    }

    public static boolean isValidPassword(String password) {
        return password.matches(PASSWORD_PATTERN);
    }

    public static boolean isValidEmail(String email) {
        return EmailValidator.getInstance().isValid(email);
    }

    public static String hashPassword(String password) {
        return new String(DigestUtils.sha(password));
    }
}
