package hr.lordsofsmell.parfume.domain.model.response;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

@AutoValue
public abstract class PerfumeItem {

    public static PerfumeItem create(Long id,
                                     String image,
                                     String company,
                                     String model,
                                     String year,
                                     boolean liked,
                                     boolean owned,
                                     boolean wishlisted) {
        return new AutoValue_PerfumeItem(id, image, company, model, year, liked, owned, wishlisted);
    }

    @SerializedName("id")
    public abstract Long id();

    @SerializedName("image")
    public abstract String image();

    @SerializedName("company")
    public abstract String company();

    @SerializedName("model")
    public abstract String model();

    @SerializedName("year")
    public abstract String year();

    @SerializedName("liked")
    public abstract Boolean liked();

    @SerializedName("owned")
    public abstract Boolean owned();

    @SerializedName("wishlisted")
    public abstract Boolean wishlisted();

    public abstract PerfumeItem withLiked(Boolean liked);

    public abstract PerfumeItem withOwned(Boolean wishlisted);

    public abstract PerfumeItem withWishlisted(Boolean liked);

    public static TypeAdapter<PerfumeItem> typeAdapter(Gson gson) {
        return new AutoValue_PerfumeItem.GsonTypeAdapter(gson);
    }
}
