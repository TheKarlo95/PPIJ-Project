package hr.lordsofsmell.parfume.domain.repository;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

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
import io.reactivex.Completable;
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

    public Completable logout(@NonNull String token) {
        return network.logout(token);
    }

    @Override
    public Observable<List<PerfumeItem>> getAllParfumes(@Nullable String token,
                                                        int page,
                                                        @Nullable String company,
                                                        @Nullable String model,
                                                        @Nullable String year) {
        return ObservableUtils.getFirstNonNull(
                network.getAllParfumes(token, page, company, model, year));
    }

    @Override
    public Observable<List<PerfumeItem>> getRecommendedParfumes(@Nullable String token, int page) {
        return ObservableUtils.getFirstNonNull(network.getRecommendedParfumes(token, page));
    }

    @Override
    public Observable<List<PerfumeItem>> getLikedParfumes(@NonNull String token, int page) {
        return ObservableUtils.getFirstNonNull(network.getLikedParfumes(token, page));
    }

    @Override
    public Observable<List<PerfumeItem>> getWishlistedParfumes(@NonNull String token, int page) {
        return ObservableUtils.getFirstNonNull(network.getWishlistedParfumes(token, page));
    }

    @Override
    public Observable<List<PerfumeItem>> getOwnedParfumes(@NonNull String token, int page) {
        return ObservableUtils.getFirstNonNull(network.getOwnedParfumes(token, page));
    }

    @Override
    public Completable changeFavorite(@NonNull String token,
                                           @NonNull FavoriteRequest request) {
        return network.changeFavorite(token, request);
    }

    @Override
    public Completable changeWishlisted(@NonNull String token,
                                             @NonNull WishlistRequest request) {
        return network.changeWishlisted(token, request);
    }

    @Override
    public Completable changeOwned(@NonNull String token,
                                        @NonNull OwnedRequest request) {
        return network.changeOwned(token, request);
    }
}
