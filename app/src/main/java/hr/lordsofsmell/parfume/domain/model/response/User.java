package hr.lordsofsmell.parfume.domain.model.response;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

import hr.lordsofsmell.parfume.annotations.Redacted;
import hr.lordsofsmell.parfume.domain.model.Gender;

import static android.R.attr.password;

@AutoValue
public abstract class User implements Parcelable {

    public static User create(Long id,
                              String token,
                              String username,
                              String email,
                              String name,
                              String surname,
                              Gender gender) {
        return new AutoValue_User(id, token, username, email, name, surname, gender);
    }

    @SerializedName("id")
    public abstract Long id();

    @SerializedName("token")
    @Redacted
    public abstract String token();

    @SerializedName("username")
    public abstract String username();

    @SerializedName("email")
    public abstract String email();

    @SerializedName("name")
    public abstract String name();

    @SerializedName("surname")
    public abstract String surname();

    @SerializedName("gender")
    public abstract Gender gender();

    public static TypeAdapter<User> typeAdapter(Gson gson) {
        return new AutoValue_User.GsonTypeAdapter(gson);
    }
}
