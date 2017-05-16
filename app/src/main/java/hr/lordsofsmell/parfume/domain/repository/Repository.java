package hr.lordsofsmell.parfume.domain.repository;

import android.support.annotation.NonNull;

import java.util.List;

import hr.lordsofsmell.parfume.domain.model.request.FavoriteRequest;
import hr.lordsofsmell.parfume.domain.model.request.LoginRequest;
import hr.lordsofsmell.parfume.domain.model.request.OwnedRequest;
import hr.lordsofsmell.parfume.domain.model.request.RegisterRequest;
import hr.lordsofsmell.parfume.domain.model.request.WishlistRequest;
import hr.lordsofsmell.parfume.domain.model.response.Parfume;
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
    public Observable<Parfume> getPerfumeProfile(@NonNull long perfumeId) {
        return ObservableUtils.getFirstNonNull(network.getPerfumeProfile(perfumeId));
    }

    @Override
    public Observable<List<PerfumeItem>> getSimilarParfumes(@NonNull long perfumeId) {
        return ObservableUtils.getFirstNonNull(network.getSimilarParfumes(perfumeId));
    }

    @Override
    public Observable<List<PerfumeItem>> getAllParfumes(int from, int numOfItems) {
        return ObservableUtils.getFirstNonNull(network.getAllParfumes(from, numOfItems));
    }

    @Override
    public Observable<List<PerfumeItem>> getLikedParfumes(@NonNull Long userId,
                                                          int from,
                                                          int numOfItems) {
        return ObservableUtils.getFirstNonNull(network.getLikedParfumes(userId, from, numOfItems));
    }

    @Override
    public Observable<List<PerfumeItem>> getWishlistedParfumes(@NonNull Long userId,
                                                               int from,
                                                               int numOfItems) {
        return ObservableUtils.getFirstNonNull(network.getWishlistedParfumes(userId, from, numOfItems));
    }

    @Override
    public Observable<List<PerfumeItem>> getOwnedParfumes(@NonNull Long userId,
                                                          int from,
                                                          int numOfItems) {
        return ObservableUtils.getFirstNonNull(network.getOwnedParfumes(userId, from, numOfItems));
    }

    @Override
    public Observable<Void> changeFavorite(@NonNull Long userId, @NonNull FavoriteRequest request) {
        return ObservableUtils.getFirstNonNull(network.changeFavorite(userId, request));
    }

    @Override
    public Observable<Void> changeWishlisted(@NonNull Long userId,
                                             @NonNull WishlistRequest request) {
        return ObservableUtils.getFirstNonNull(network.changeWishlisted(userId, request));
    }

    @Override
    public Observable<Void> changeOwned(@NonNull Long userId, @NonNull OwnedRequest request) {
        return ObservableUtils.getFirstNonNull(network.changeOwned(userId, request));
    }
}
