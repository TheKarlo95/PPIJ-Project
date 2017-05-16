package hr.lordsofsmell.parfume.domain.repository.network;

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
import hr.lordsofsmell.parfume.domain.repository.IRepository;
import io.reactivex.Completable;
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
    public Observable<Parfume> getPerfumeProfile(@NonNull long perfumeId) {
        return service.getPerfumeProfile(perfumeId);
    }

    @Override
    public Observable<List<PerfumeItem>> getSimilarParfumes(@NonNull long perfumeId) {
        return service.getSimilarPerfumes(perfumeId);
    }

    public Completable logout(@NonNull String token) {
        return service.logout(token);
    }

    @Override
    public Observable<List<PerfumeItem>> getAllParfumes(@Nullable String token,
                                                        int page,
                                                        @Nullable String company,
                                                        @Nullable String model,
                                                        @Nullable String year) {
        return service.getAllParfumes(token, page, company, model, year);
    }

    @Override
    public Observable<List<PerfumeItem>> getRecommendedParfumes(@Nullable String token, int page) {
        return service.getRecommendedParfumes(token, page);
    }

    @Override
    public Observable<List<PerfumeItem>> getLikedParfumes(@NonNull String token, int page) {
        return service.getLikedParfumes(token, page);
    }

    @Override
    public Observable<List<PerfumeItem>> getWishlistedParfumes(@NonNull String token, int page) {
        return service.getWishlistedParfumes(token, page);
    }

    @Override
    public Observable<List<PerfumeItem>> getOwnedParfumes(@NonNull String token, int page) {
        return service.getOwnedParfumes(token, page);
    }

    @Override
    public Completable changeFavorite(@NonNull String token,
                                      @NonNull FavoriteRequest request) {
        return service.changeLiked(token, request);
    }

    @Override
    public Completable changeWishlisted(@NonNull String token,
                                        @NonNull WishlistRequest request) {
        return service.changeWishlisted(token, request);
    }

    @Override
    public Completable changeOwned(@NonNull String token,
                                   @NonNull OwnedRequest request) {
        return service.changeOwned(token, request);
    }
}
