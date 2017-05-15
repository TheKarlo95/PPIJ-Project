package hr.lordsofsmell.parfume.domain.model.params;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class GetOwnedPerfumesParams {

    public static GetOwnedPerfumesParams create(String token, long userId, int page) {
        return new AutoValue_GetOwnedPerfumesParams(token, userId, page);
    }

    public abstract String token();

    public abstract long userId();

    public abstract int page();
}
