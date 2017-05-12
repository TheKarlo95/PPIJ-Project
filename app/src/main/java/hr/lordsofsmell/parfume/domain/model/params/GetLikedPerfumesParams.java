package hr.lordsofsmell.parfume.domain.model.params;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class GetLikedPerfumesParams {

    public static GetLikedPerfumesParams create(long userId, int from, int numOfItems) {
        return new AutoValue_GetLikedPerfumesParams(userId, from, numOfItems);
    }

    public abstract long userId();

    public abstract int from();

    public abstract int numOfItems();
}
