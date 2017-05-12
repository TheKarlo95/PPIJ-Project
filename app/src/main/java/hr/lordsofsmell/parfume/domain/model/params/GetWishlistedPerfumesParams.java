package hr.lordsofsmell.parfume.domain.model.params;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class GetWishlistedPerfumesParams {

    public static GetWishlistedPerfumesParams create(long userId, int from, int numOfItems) {
        return new AutoValue_GetWishlistedPerfumesParams(userId, from, numOfItems);
    }

    public abstract long userId();

    public abstract int from();

    public abstract int numOfItems();
}
