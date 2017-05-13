package hr.lordsofsmell.parfume.domain.model.request;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

@AutoValue
public abstract class FavoriteRequest {

    public static FavoriteRequest create(Boolean favorited, Long perfumeId) {
        return new AutoValue_FavoriteRequest(favorited, perfumeId);
    }

    @SerializedName("favorited")
    public abstract Boolean favorited();

    @SerializedName("parfumeId")
    public abstract Long parfumeId();

    public static TypeAdapter<FavoriteRequest> typeAdapter(Gson gson) {
        return new AutoValue_FavoriteRequest.GsonTypeAdapter(gson);
    }
}
