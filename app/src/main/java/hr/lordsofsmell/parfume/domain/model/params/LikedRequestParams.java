package hr.lordsofsmell.parfume.domain.model.params;

import com.google.auto.value.AutoValue;

import hr.lordsofsmell.parfume.domain.model.request.FavoriteRequest;

@AutoValue
public abstract class LikedRequestParams {

    public static LikedRequestParams create(String token, FavoriteRequest request) {
        return new AutoValue_LikedRequestParams(token, request);
    }

    public abstract String token();

    public abstract FavoriteRequest request();
}
