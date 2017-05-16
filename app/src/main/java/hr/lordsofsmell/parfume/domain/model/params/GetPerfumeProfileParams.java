package hr.lordsofsmell.parfume.domain.model.params;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class GetPerfumeProfileParams {

    public static GetPerfumeProfileParams create(int from, int numOfItems) {
        return new AutoValue_GetPerfumeProfileParams(from, numOfItems);
    }
    @Nullable
    public abstract String token();

    public abstract long perfumeId();
}
