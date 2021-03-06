package hr.lordsofsmell.parfume.domain.model.params;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class GetAllPerfumesParams {

    public static GetAllPerfumesParams create(String token,
                                              int page,
                                              String company,
                                              String model,
                                              String year) {
        if (TextUtils.isEmpty(token)) {
            token = null;
        }
        if (TextUtils.isEmpty(company)) {
            company = null;
        }
        if (TextUtils.isEmpty(model)) {
            model = null;
        }
        if (TextUtils.isEmpty(year)) {
            year = null;
        }
        return new AutoValue_GetAllPerfumesParams(token, page, company, model, year);
    }

    @Nullable
    public abstract String token();

    public abstract int page();

    @Nullable
    public abstract String company();

    @Nullable
    public abstract String model();

    @Nullable
    public abstract String year();
}
