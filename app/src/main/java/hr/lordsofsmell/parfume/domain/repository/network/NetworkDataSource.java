package hr.lordsofsmell.parfume.domain.repository.network;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

import hr.lordsofsmell.parfume.domain.model.request.FavoriteRequest;
import hr.lordsofsmell.parfume.domain.model.request.LoginRequest;
import hr.lordsofsmell.parfume.domain.model.request.OwnedRequest;
import hr.lordsofsmell.parfume.domain.model.request.RegisterRequest;
import hr.lordsofsmell.parfume.domain.model.request.WishlistRequest;
import hr.lordsofsmell.parfume.domain.model.response.PerfumeItem;
import hr.lordsofsmell.parfume.domain.model.response.User;
import hr.lordsofsmell.parfume.domain.repository.IRepository;
import io.reactivex.Observable;

public class NetworkDataSource implements IRepository {

    private ApiService service;

    public NetworkDataSource(ApiService service) {
        this.service = service;
    }

    @Override
    public Observable<User> login(@NonNull LoginRequest request) {
        return service.login(request);
    }

    @Override
    public Observable<User> register(@NonNull RegisterRequest request) {
        return service.register(request);
    }

    @Override
    public Observable<List<PerfumeItem>> getAllParfumes(@Nullable String token, int page) {
        return service.getAllParfumes(token, page);
    }

    @Override
    public Observable<List<PerfumeItem>> getLikedParfumes(@NonNull String token,
                                                          @NonNull Long userId,
                                                          int page) {
        return service.getLikedParfumes(token, userId, page);
    }

    @Override
    public Observable<List<PerfumeItem>> getWishlistedParfumes(@NonNull String token,
                                                               @NonNull Long userId,
                                                               int page) {
        return service.getWishlistedParfumes(token, userId, page);
    }

    @Override
    public Observable<List<PerfumeItem>> getOwnedParfumes(@NonNull String token,
                                                          @NonNull Long userId,
                                                          int page) {
        return service.getOwnedParfumes(token, userId, page);
    }

    @Override
    public Observable<Void> changeFavorite(@NonNull String token,
                                           @NonNull Long userId,
                                           @NonNull FavoriteRequest request) {
        return service.changeLiked(token, userId, request);
    }

    @Override
    public Observable<Void> changeWishlisted(@NonNull String token,
                                             @NonNull Long userId,
                                             @NonNull WishlistRequest request) {
        return service.changeWishlisted(token, userId, request);
    }

    @Override
    public Observable<Void> changeOwned(@NonNull String token,
                                        @NonNull Long userId,
                                        @NonNull OwnedRequest request) {
        return service.changeOwned(token, userId, request);
    }
}
