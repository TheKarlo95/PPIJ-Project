package hr.lordsofsmell.parfume.domain.model.request;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

@AutoValue
public abstract class OwnedRequest {

    public static OwnedRequest create(Boolean liked, Long perfumeId) {
        return new AutoValue_OwnedRequest(liked, perfumeId);
    }

    @SerializedName("owned")
    public abstract Boolean owned();

    @SerializedName("parfumeId")
    public abstract Long parfumeId();

    public static TypeAdapter<OwnedRequest> typeAdapter(Gson gson) {
        return new AutoValue_OwnedRequest.GsonTypeAdapter(gson);
    }
}
