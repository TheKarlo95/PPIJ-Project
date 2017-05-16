package hr.lordsofsmell.parfume.domain.model.params;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;

import java.util.List;

import hr.lordsofsmell.parfume.domain.model.response.PerfumeItem;

@AutoValue
public abstract class GetSimilarPerfumesParams {

    public static GetSimilarPerfumesParams create(long perfumeId) {
        return new AutoValue_GetSimilarPerfumesParams(perfumeId);
    }

    public abstract long perfumeId();

}
