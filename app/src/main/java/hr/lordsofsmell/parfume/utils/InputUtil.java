package hr.lordsofsmell.parfume.utils;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.widget.EditText;

import org.apache.commons.validator.routines.EmailValidator;

public class InputUtil {

    @Nullable
    public static String getUsernameText(@NonNull TextInputLayout tilUsername) {
        String username = InputUtil.get(tilUsername);

        if (username == null) {
            tilUsername.setErrorEnabled(true);
            tilUsername.setError("Username field cannot be empty");
        } else if (!UserUtils.isValidUsername(username)) {
            tilUsername.setErrorEnabled(true);
            tilUsername.setError("Invalid username");
            username = null;
        } else {
            tilUsername.setError(null);
            tilUsername.setErrorEnabled(false);
        }

        return username;
    }

    @Nullable
    public static String getPasswordText(@NonNull TextInputLayout tilPassword) {
        String password = InputUtil.get(tilPassword);

        if (password == null) {
            tilPassword.setErrorEnabled(true);
            tilPassword.setError("Password field cannot be empty");
        } else if (!UserUtils.isValidPassword(password)) {
            tilPassword.setErrorEnabled(true);
            tilPassword.setError("Invalid password");
            password = null;
        } else {
            tilPassword.setError(null);
            tilPassword.setErrorEnabled(false);
        }

        return password;
    }

    @Nullable
    public static String getPasswordConfiramtionText(@NonNull TextInputLayout tilPassword) {
        String password = InputUtil.get(tilPassword);

        if (password == null) {
            tilPassword.setErrorEnabled(true);
            tilPassword.setError("Password confirmation field cannot be empty");
        } else if (!UserUtils.isValidPassword(password)) {
            tilPassword.setErrorEnabled(true);
            tilPassword.setError("Invalid password confirmation");
            password = null;
        } else {
            tilPassword.setError(null);
            tilPassword.setErrorEnabled(false);
        }

        return password;
    }

    @Nullable
    public static String getEmailText(@NonNull TextInputLayout tilEmail) {
        String email = InputUtil.get(tilEmail);

        if (email == null) {
            tilEmail.setErrorEnabled(true);
            tilEmail.setError("Email field cannot be empty");
        } else if (!EmailValidator.getInstance().isValid(email)) {
            tilEmail.setErrorEnabled(true);
            tilEmail.setError("Invalid email");
            email = null;
        } else {
            tilEmail.setError(null);
            tilEmail.setErrorEnabled(false);
        }

        return email;
    }

    @Nullable
    public static String getNameText(@NonNull TextInputLayout tilName) {
        String name = InputUtil.get(tilName);

        if (name == null) {
            tilName.setErrorEnabled(true);
            tilName.setError("Name field cannot be empty");
        } else {
            tilName.setError(null);
            tilName.setErrorEnabled(false);
        }

        return name;
    }

    @Nullable
    public static String getSurnameText(@NonNull TextInputLayout tilSurname) {
        String surname = InputUtil.get(tilSurname);

        if (surname == null) {
            tilSurname.setErrorEnabled(true);
            tilSurname.setError("Surname field cannot be empty");
        } else {
            tilSurname.setError(null);
            tilSurname.setErrorEnabled(false);
        }

        return surname;
    }

    @Nullable
    private static String get(@NonNull TextInputLayout textInputLayout) {
        EditText editText = textInputLayout.getEditText();
        String text = null;

        if (editText != null) {
            text = editText.getText().toString().trim();

            if (text.isEmpty()) {
                text = null;
            }
        }

        return text;
    }
}