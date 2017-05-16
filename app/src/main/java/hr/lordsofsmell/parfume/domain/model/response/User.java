package hr.lordsofsmell.parfume.domain.model.response;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

import hr.lordsofsmell.parfume.annotations.Redacted;

@AutoValue
public abstract class User implements Parcelable {

    public static User create(String token,
                              String username,
                              String email,
                              String name,
                              String surname) {
        return new AutoValue_User(token, username, email, name, surname);
    }

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

    public static TypeAdapter<User> typeAdapter(Gson gson) {
        return new AutoValue_User.GsonTypeAdapter(gson);
    }
}
