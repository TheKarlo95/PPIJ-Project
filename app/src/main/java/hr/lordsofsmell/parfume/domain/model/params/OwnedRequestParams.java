package hr.lordsofsmell.parfume.domain.model.params;

import com.google.auto.value.AutoValue;

import hr.lordsofsmell.parfume.domain.model.request.OwnedRequest;

@AutoValue
public abstract class OwnedRequestParams {

    public static OwnedRequestParams create(String token, OwnedRequest request) {
        return new AutoValue_OwnedRequestParams(token, request);
    }

    public abstract String token();

    public abstract OwnedRequest request();
}
