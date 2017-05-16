package hr.lordsofsmell.parfume.domain.model.params;

import com.google.auto.value.AutoValue;

import hr.lordsofsmell.parfume.domain.model.request.WishlistRequest;

@AutoValue
public abstract class WishlistedRequestParams {

    public static WishlistedRequestParams create(String token, WishlistRequest request) {
        return new AutoValue_WishlistedRequestParams(token, request);
    }

    public abstract String token();

    public abstract WishlistRequest request();
}
