package hr.lordsofsmell.parfume.domain.model.response;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

import hr.lordsofsmell.parfume.domain.model.Gender;

@AutoValue
public abstract class Perfume implements Parcelable {

    public static Perfume create(Long id,
                                 String image,
                                 String company,
                                 String model,
                                 String year,
                                 Gender gender,
                                 boolean liked,
                                 boolean owned,
                                 boolean wishlisted,
                                 String description) {
        return new AutoValue_Perfume(id,
                image,
                company,
                model,
                year,
                gender,
                liked,
                owned,
                wishlisted,
                description);
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
    public abstract Boolean favorited();

    @SerializedName("wishes")
    public abstract Boolean wishlisted();

    @SerializedName("owned")
    public abstract Boolean owned();

    @SerializedName("details")
    public abstract String description();

    public static TypeAdapter<Perfume> typeAdapter(Gson gson) {
        return new AutoValue_Perfume.GsonTypeAdapter(gson);
    }
}
