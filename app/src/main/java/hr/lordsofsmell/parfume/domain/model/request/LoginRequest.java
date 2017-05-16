package hr.lordsofsmell.parfume.domain.model.request;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

import hr.lordsofsmell.parfume.annotations.Redacted;

@AutoValue
public abstract class LoginRequest implements Parcelable {

    public static LoginRequest create(String username, String password) {
        return new AutoValue_LoginRequest(username, password);
    }

    @SerializedName("username")
    @Redacted
    public abstract String username();

    @SerializedName("hash")
    @Redacted
    public abstract String password();

    public static TypeAdapter<LoginRequest> typeAdapter(Gson gson) {
        return new AutoValue_LoginRequest.GsonTypeAdapter(gson);
    }
}
