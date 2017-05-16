package hr.lordsofsmell.parfume.domain.model.params;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class GetPerfumeProfileParams {

    public static GetPerfumeProfileParams create( String token,long perfumeId) {
        return new AutoValue_GetPerfumeProfileParams(token, perfumeId);
    }
    @Nullable
    public abstract String token();

    public abstract long perfumeId();
}
