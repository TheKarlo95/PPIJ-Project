package hr.lordsofsmell.parfume.domain.model.params;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class GetAllPerfumesParams {

    public static GetAllPerfumesParams create(String token, int page) {
        return new AutoValue_GetAllPerfumesParams(token, page);
    }

    @Nullable
    public abstract String token();

    public abstract int page();
}
