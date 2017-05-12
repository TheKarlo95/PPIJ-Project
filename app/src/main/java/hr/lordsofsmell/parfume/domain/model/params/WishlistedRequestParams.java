package hr.lordsofsmell.parfume.domain.model.params;

import com.google.auto.value.AutoValue;

import hr.lordsofsmell.parfume.domain.model.request.LikedRequest;
import hr.lordsofsmell.parfume.domain.model.request.WishlistRequest;

@AutoValue
public abstract class WishlistedRequestParams {

    public static WishlistedRequestParams create(String token, Long userId, WishlistRequest request) {
        return new AutoValue_WishlistedRequestParams(token, userId, request);
    }

    public abstract String token();

    public abstract Long userId();

    public abstract WishlistRequest request();
}
