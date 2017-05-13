package hr.lordsofsmell.parfume.domain.model.response;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@AutoValue
public abstract class Parfume implements Parcelable {

    public static Parfume create(Long id,
                                 String image,
                                 String company,
                                 String model,
                                 String year,
                                 boolean liked,
                                 boolean owned,
                                 boolean wishlisted,
                                 String description,
                                 List<PerfumeItem> similarParfumes) {
        return new AutoValue_Parfume(id,
                image,
                company,
                model,
                year,
                liked,
                owned,
                wishlisted,
                description,
                similarParfumes);
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

    @SerializedName("favorited")
    public abstract Boolean liked();

    @SerializedName("owned")
    public abstract Boolean owned();

    @SerializedName("wishlisted")
    public abstract Boolean wishlisted();

    @SerializedName("description")
    public abstract String description();

    @SerializedName("similarParfumes")
    public abstract List<PerfumeItem> similarParfumes();

    public static TypeAdapter<Parfume> typeAdapter(Gson gson) {
        return new AutoValue_Parfume.GsonTypeAdapter(gson);
    }

    abstract Parfume withLiked(Boolean liked);

    abstract Parfume withOwned(Boolean owned);

    abstract Parfume withWishlisted(Boolean wishlisted);
}
