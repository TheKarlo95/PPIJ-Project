package hr.lordsofsmell.parfume.domain.repository;

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
import hr.lordsofsmell.parfume.domain.repository.network.NetworkDataSource;
import hr.lordsofsmell.parfume.utils.ObservableUtils;
import io.reactivex.Observable;

public class Repository implements IRepository {


    private NetworkDataSource network;

    public Repository(NetworkDataSource network) {
        this.network = network;
    }

    @Override
    public Observable<User> login(@NonNull LoginRequest request) {
        return ObservableUtils.getFirstNonNull(network.login(request));
    }

    @Override
    public Observable<User> register(@NonNull RegisterRequest request) {
        return ObservableUtils.getFirstNonNull(network.register(request));
    }

    @Override
    public Observable<List<PerfumeItem>> getAllParfumes(@Nullable String token, int page) {
        return ObservableUtils.getFirstNonNull(network.getAllParfumes(token == null ? "" : token,
                page));
    }

    @Override
    public Observable<List<PerfumeItem>> getLikedParfumes(@NonNull String token,
                                                          @NonNull Long userId,
                                                          int page) {
        return ObservableUtils.getFirstNonNull(network.getLikedParfumes(token, userId, page));
    }

    @Override
    public Observable<List<PerfumeItem>> getWishlistedParfumes(@NonNull String token,
                                                               @NonNull Long userId,
                                                               int page) {
        return ObservableUtils.getFirstNonNull(network.getWishlistedParfumes(token, userId, page));
    }

    @Override
    public Observable<List<PerfumeItem>> getOwnedParfumes(@NonNull String token,
                                                          @NonNull Long userId,
                                                          int page) {
        return ObservableUtils.getFirstNonNull(network.getOwnedParfumes(token, userId, page));
    }

    @Override
    public Observable<Void> changeFavorite(@NonNull String token,
                                           @NonNull Long userId,
                                           @NonNull FavoriteRequest request) {
        return ObservableUtils.getFirstNonNull(network.changeFavorite(token, userId, request));
    }

    @Override
    public Observable<Void> changeWishlisted(@NonNull String token,
                                             @NonNull Long userId,
                                             @NonNull WishlistRequest request) {
        return ObservableUtils.getFirstNonNull(network.changeWishlisted(token, userId, request));
    }

    @Override
    public Observable<Void> changeOwned(@NonNull String token,
                                        @NonNull Long userId,
                                        @NonNull OwnedRequest request) {
        return ObservableUtils.getFirstNonNull(network.changeOwned(token, userId, request));
    }
}
