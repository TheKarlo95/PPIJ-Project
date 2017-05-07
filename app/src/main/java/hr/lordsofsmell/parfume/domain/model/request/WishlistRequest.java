package hr.lordsofsmell.parfume.domain.model.request;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

@AutoValue
public abstract class WishlistRequest {

    public static WishlistRequest create(Boolean liked) {
        return new AutoValue_WishlistRequest(liked);
    }

    @SerializedName("wishlisted")
    public abstract Boolean wishlisted();

    public static TypeAdapter<WishlistRequest> typeAdapter(Gson gson) {
        return new AutoValue_WishlistRequest.GsonTypeAdapter(gson);
    }
}
