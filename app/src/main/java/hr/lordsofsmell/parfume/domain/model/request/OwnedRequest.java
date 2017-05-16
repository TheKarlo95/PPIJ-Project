package hr.lordsofsmell.parfume.domain.model.request;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

@AutoValue
public abstract class OwnedRequest {

    public static OwnedRequest create(boolean owned, long perfumeId) {
        return new AutoValue_OwnedRequest(owned, perfumeId);
    }

    @SerializedName("owned")
    public abstract boolean owned();

    @SerializedName("parfumeId")
    public abstract long parfumeId();

    public static TypeAdapter<OwnedRequest> typeAdapter(Gson gson) {
        return new AutoValue_OwnedRequest.GsonTypeAdapter(gson);
    }
}
