package hr.lordsofsmell.parfume.domain.model.request;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

import hr.lordsofsmell.parfume.annotations.Redacted;
import hr.lordsofsmell.parfume.domain.model.Gender;

@AutoValue
public abstract class RegisterRequest {

    public static RegisterRequest create(String username,
                                         String password,
                                         String email,
                                         String name,
                                         String surname,
                                         Gender gender) {
        return new AutoValue_RegisterRequest(username, password, email, name, surname, gender);
    }

    @SerializedName("username")
    public abstract String username();

    @SerializedName("password")
    @Redacted
    public abstract String password();

    @SerializedName("email")
    public abstract String email();

    @SerializedName("name")
    public abstract String name();

    @SerializedName("surname")
    public abstract String surname();

    @SerializedName("gender")
    public abstract Gender gender();

    public static TypeAdapter<RegisterRequest> typeAdapter(Gson gson) {
        return new AutoValue_RegisterRequest.GsonTypeAdapter(gson);
    }
}
