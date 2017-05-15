package hr.lordsofsmell.parfume.domain.model.params;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class GetLikedPerfumesParams {

    public static GetLikedPerfumesParams create(String token, long userId, int page) {
        return new AutoValue_GetLikedPerfumesParams(token, userId, page);
    }

    public abstract String token();

    public abstract long userId();

    public abstract int page();
}
