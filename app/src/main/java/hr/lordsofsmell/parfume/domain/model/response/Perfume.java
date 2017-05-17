package hr.lordsofsmell.parfume.domain.model.response;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.List;

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
                                 String description,
                                 List<Note> notes){
        return new AutoValue_Perfume(id,
                image,
                company,
                model,
                year,
                gender,
                liked,
                owned,
                wishlisted,
                description,
                notes);
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

    @SerializedName("details")
    public abstract String description();

    @SerializedName("notes")
    public abstract List<Note> notes();

    public static TypeAdapter<Perfume> typeAdapter(Gson gson) {
        return new AutoValue_Perfume.GsonTypeAdapter(gson);
    }
}
