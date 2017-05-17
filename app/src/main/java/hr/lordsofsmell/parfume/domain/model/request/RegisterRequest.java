package hr.lordsofsmell.parfume.domain.model.request;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

import hr.lordsofsmell.parfume.annotations.Redacted;

@AutoValue
public abstract class RegisterRequest {

    public static RegisterRequest create(String username,
                                         String password,
                                         String email,
                                         String name,
                                         String surname) {
        return new AutoValue_RegisterRequest(username, password, email, name, surname);
    }

    @SerializedName("username")
    public abstract String username();

    @SerializedName("hash")
    @Redacted
    public abstract String password();

    @SerializedName("mail")
    public abstract String email();

    @SerializedName("ime")
    public abstract String name();

    @SerializedName("prezime")
    public abstract String surname();

    public static TypeAdapter<RegisterRequest> typeAdapter(Gson gson) {
        return new AutoValue_RegisterRequest.GsonTypeAdapter(gson);
    }
}
