package hr.lordsofsmell.parfume.domain.model.params;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class PerfumeParams {

    public static PerfumeParams create(@NonNull String token, long perfumeId) {
        return new AutoValue_PerfumeParams(token, perfumeId);
    }

    @Nullable
    public abstract String token();

    public abstract long perfumeId();
}
