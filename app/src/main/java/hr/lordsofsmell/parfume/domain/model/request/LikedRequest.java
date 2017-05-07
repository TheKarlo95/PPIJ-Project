package hr.lordsofsmell.parfume.domain.model.request;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

@AutoValue
public abstract class LikedRequest {

    public static LikedRequest create(Boolean liked) {
        return new AutoValue_LikedRequest(liked);
    }

    @SerializedName("liked")
    public abstract Boolean liked();

    public static TypeAdapter<LikedRequest> typeAdapter(Gson gson) {
        return new AutoValue_LikedRequest.GsonTypeAdapter(gson);
    }
}
