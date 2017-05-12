package hr.lordsofsmell.parfume.domain.model.params;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class GetOwnedPerfumesParams {

    public static GetOwnedPerfumesParams create(long userId, int from, int numOfItems) {
        return new AutoValue_GetOwnedPerfumesParams(userId, from, numOfItems);
    }

    public abstract long userId();

    public abstract int from();

    public abstract int numOfItems();
}
