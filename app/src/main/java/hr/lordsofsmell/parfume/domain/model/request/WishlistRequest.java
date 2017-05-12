package hr.lordsofsmell.parfume.domain.model.request;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

@AutoValue
public abstract class WishlistRequest {

    public static WishlistRequest create(Boolean liked, Long perfumeId) {
        return new AutoValue_WishlistRequest(liked, perfumeId);
    }

    @SerializedName("wishlisted")
    public abstract Boolean wishlisted();

    @SerializedName("parfumeId")
    public abstract Long parfumeId();

    public static TypeAdapter<WishlistRequest> typeAdapter(Gson gson) {
        return new AutoValue_WishlistRequest.GsonTypeAdapter(gson);
    }
}
