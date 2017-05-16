package hr.lordsofsmell.parfume.domain.model.params;

import com.google.auto.value.AutoValue;

import hr.lordsofsmell.parfume.domain.model.request.FavoriteRequest;

@AutoValue
public abstract class FavoriteRequestParams {

    public static FavoriteRequestParams create(String token, FavoriteRequest request) {
        return new AutoValue_FavoriteRequestParams(token, request);
    }

    public abstract String token();

    public abstract FavoriteRequest request();
}
