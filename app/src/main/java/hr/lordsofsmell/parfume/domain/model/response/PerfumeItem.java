package hr.lordsofsmell.parfume.domain.model.response;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

import hr.lordsofsmell.parfume.domain.model.Gender;

@AutoValue
public abstract class PerfumeItem {

    public static PerfumeItem create(Long id,
                                     String image,
                                     String company,
                                     String model,
                                     String year,
                                     Gender gender,
                                     boolean favorited,
                                     boolean owned,
                                     boolean wishlisted) {
        return new AutoValue_PerfumeItem(id,
                image,
                company,
                model,
                year,
                gender,
                favorited,
                owned,
                wishlisted);
    }

    @SerializedName("id")
    public abstract Long id();

    @SerializedName("url")
    public abstract String image();

    @SerializedName("manufacturerName")
    public abstract String company();

    @SerializedName("name")
    public abstract String model();

    @SerializedName("year")
    public abstract String year();

    @SerializedName("gender")
    public abstract Gender gender();

    @SerializedName("liked")
    public abstract boolean favorited();

    @SerializedName("wishes")
    public abstract boolean wishlisted();

    @SerializedName("owned")
    public abstract boolean owned();

    public abstract PerfumeItem withFavorited(boolean favorited);

    public abstract PerfumeItem withWishlisted(boolean wishlisted);

    public abstract PerfumeItem withOwned(boolean owned);

    public static TypeAdapter<PerfumeItem> typeAdapter(Gson gson) {
        return new AutoValue_PerfumeItem.GsonTypeAdapter(gson);
    }
}
