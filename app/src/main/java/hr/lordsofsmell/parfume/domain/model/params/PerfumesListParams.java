package hr.lordsofsmell.parfume.domain.model.params;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class PerfumesListParams {

    public static PerfumesListParams create(@NonNull String token, int page) {
        return new AutoValue_PerfumesListParams(token, page);
    }

    public abstract String token();

    public abstract int page();
}
