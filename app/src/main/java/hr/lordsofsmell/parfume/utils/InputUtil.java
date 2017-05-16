package hr.lordsofsmell.parfume.utils;

import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.widget.EditText;

import org.apache.commons.validator.routines.EmailValidator;

public class InputUtil {

    public static final String EMPTY = "";

    @NonNull
    public static String getUsernameText(@NonNull TextInputLayout tilUsername) {
        String username = InputUtil.get(tilUsername);

        if (username.isEmpty()) {
            tilUsername.setErrorEnabled(true);
            tilUsername.setError("Username field cannot be empty");
        } else if (!UserUtils.isValidUsername(username)) {
            tilUsername.setErrorEnabled(true);
            tilUsername.setError("Invalid username");
            username = EMPTY;
        } else {
            tilUsername.setError(null);
            tilUsername.setErrorEnabled(false);
        }

        return username;
    }

    @NonNull
    public static String getPasswordText(@NonNull TextInputLayout tilPassword) {
        String password = InputUtil.get(tilPassword);

        if (password.isEmpty()) {
            tilPassword.setErrorEnabled(true);
            tilPassword.setError("Password field cannot be empty");
        } else if (!UserUtils.isValidPassword(password)) {
            tilPassword.setErrorEnabled(true);
            tilPassword.setError("Invalid password");
            password = EMPTY;
        } else {
            tilPassword.setError(null);
            tilPassword.setErrorEnabled(false);
        }

        return password;
    }

    @NonNull
    public static String getPasswordConfiramtionText(@NonNull TextInputLayout tilPassword) {
        String password = InputUtil.get(tilPassword);

        if (password.isEmpty()) {
            tilPassword.setErrorEnabled(true);
            tilPassword.setError("Password confirmation field cannot be empty");
        } else if (!UserUtils.isValidPassword(password)) {
            tilPassword.setErrorEnabled(true);
            tilPassword.setError("Invalid password confirmation");
            password = EMPTY;
        } else {
            tilPassword.setError(null);
            tilPassword.setErrorEnabled(false);
        }

        return password;
    }

    @NonNull
    public static String getEmailText(@NonNull TextInputLayout tilEmail) {
        String email = InputUtil.get(tilEmail);

        if (email.isEmpty()) {
            tilEmail.setErrorEnabled(true);
            tilEmail.setError("Email field cannot be empty");
        } else if (!EmailValidator.getInstance().isValid(email)) {
            tilEmail.setErrorEnabled(true);
            tilEmail.setError("Invalid email");
            email = EMPTY;
        } else {
            tilEmail.setError(null);
            tilEmail.setErrorEnabled(false);
        }

        return email;
    }

    @NonNull
    public static String getNameText(@NonNull TextInputLayout tilName) {
        String name = InputUtil.get(tilName);

        if (name.isEmpty()) {
            tilName.setErrorEnabled(true);
            tilName.setError("Name field cannot be empty");
        } else {
            tilName.setError(null);
            tilName.setErrorEnabled(false);
        }

        return name;
    }

    @NonNull
    public static String getSurnameText(@NonNull TextInputLayout tilSurname) {
        String surname = InputUtil.get(tilSurname);

        if (surname.isEmpty()) {
            tilSurname.setErrorEnabled(true);
            tilSurname.setError("Surname field cannot be empty");
        } else {
            tilSurname.setError(null);
            tilSurname.setErrorEnabled(false);
        }

        return surname;
    }

    @NonNull
    private static String get(@NonNull TextInputLayout textInputLayout) {
        EditText editText = textInputLayout.getEditText();
        String text;

        if (editText != null) {
            text = editText.getText().toString().trim();
        } else {
            text = EMPTY;
        }

        return text;
    }
}