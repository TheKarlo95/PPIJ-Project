package hr.lordsofsmell.parfume.domain.model.response;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

@AutoValue
public abstract class Note implements Parcelable {

    public static Note create(long id, String name, int level) {
        return new AutoValue_Note(id, name, level);
    }

    @SerializedName("id")
    public abstract long id();

    @SerializedName("name")
    public abstract String name();

    @SerializedName("level")
    public abstract int level();

    public static TypeAdapter<Note> typeAdapter(Gson gson) {
        return new AutoValue_Note.GsonTypeAdapter(gson);
    }
}
