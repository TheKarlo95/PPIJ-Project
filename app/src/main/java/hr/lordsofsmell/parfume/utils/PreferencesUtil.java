package hr.lordsofsmell.parfume.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import hr.lordsofsmell.parfume.AndroidApplication;
import hr.lordsofsmell.parfume.BuildConfig;
import hr.lordsofsmell.parfume.domain.model.Gender;
import hr.lordsofsmell.parfume.domain.model.response.User;

public class PreferencesUtil {

    private static final String USER_PREFERENCES_KEY = BuildConfig.APPLICATION_ID + ".UserPrefs";

    private static final String USER_ID = "id";
    private static final String USER_TOKEN = "token";
    private static final String USER_USERNAME = "username";
    private static final String USER_EMAIL = "email";
    private static final String USER_NAME = "name";
    private static final String USER_SURNAME = "surname";
    private static final String USER_GENDER = "gender";

    private PreferencesUtil() {
    }

    public static void persistUser(@NonNull User user) {
        AndroidApplication.getContext()
                .getSharedPreferences(USER_PREFERENCES_KEY, Context.MODE_PRIVATE)
                .edit()
                .putLong(USER_ID, user.id())
                .putString(USER_TOKEN, user.token())
                .putString(USER_USERNAME, user.username())
                .putString(USER_EMAIL, user.email())
                .putString(USER_NAME, user.name())
                .putString(USER_SURNAME, user.surname())
                .putString(USER_GENDER, user.gender().toString())
                .apply();
    }

    public static User getUser() {
        SharedPreferences prefs = AndroidApplication.getContext()
                .getSharedPreferences(USER_PREFERENCES_KEY, Context.MODE_PRIVATE);
        User user = null;

        Long id = prefs.getLong(USER_ID, -1);
        if (id != -1) {
            String token = prefs.getString(USER_TOKEN, null);
            String username = prefs.getString(USER_TOKEN, null);
            String email = prefs.getString(USER_TOKEN, null);
            String name = prefs.getString(USER_TOKEN, null);
            String surname = prefs.getString(USER_TOKEN, null);
            Gender gender = Gender.fromString(prefs.getString(USER_GENDER, null));

            user = User.create(id, token, username, email, name, surname, gender);
        }

        return user;
    }

    public static String getToken() {
        return AndroidApplication.getContext()
                .getSharedPreferences(USER_PREFERENCES_KEY, Context.MODE_PRIVATE)
                .getString(USER_TOKEN, null);
    }

    public static boolean isLoggedIn() {
        return getToken() != null;
    }
}
