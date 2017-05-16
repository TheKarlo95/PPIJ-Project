package hr.lordsofsmell.parfume.domain.model.request;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

@AutoValue
public abstract class WishlistRequest {

    public static WishlistRequest create(boolean wishlisted, long perfumeId) {
        return new AutoValue_WishlistRequest(wishlisted, perfumeId);
    }

    @SerializedName("wish")
    public abstract boolean wishlisted();

    @SerializedName("parfumeId")
    public abstract long parfumeId();

    public static TypeAdapter<WishlistRequest> typeAdapter(Gson gson) {
        return new AutoValue_WishlistRequest.GsonTypeAdapter(gson);
    }
}
