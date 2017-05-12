package hr.lordsofsmell.parfume.domain.model.params;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class GetAllPerfumesParams {

    public static GetAllPerfumesParams create(int from, int numOfItems) {
        return new AutoValue_GetAllPerfumesParams(from, numOfItems);
    }

    public abstract int from();

    public abstract int numOfItems();
}
