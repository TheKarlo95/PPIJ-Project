package hr.lordsofsmell.parfume.domain.model.params;

import com.google.auto.value.AutoValue;

import hr.lordsofsmell.parfume.domain.model.request.LikedRequest;

@AutoValue
public abstract class LikedRequestParams {

    public static LikedRequestParams create(String token, Long userId, LikedRequest request) {
        return new AutoValue_LikedRequestParams(token, userId, request);
    }

    public abstract String token();

    public abstract Long userId();

    public abstract LikedRequest request();
}
