package hr.lordsofsmell.parfume.domain.repository.network;

import android.support.annotation.NonNull;

import java.util.List;

import hr.lordsofsmell.parfume.domain.model.request.LikedRequest;
import hr.lordsofsmell.parfume.domain.model.request.LoginRequest;
import hr.lordsofsmell.parfume.domain.model.request.OwnedRequest;
import hr.lordsofsmell.parfume.domain.model.request.RegisterRequest;
import hr.lordsofsmell.parfume.domain.model.request.WishlistRequest;
import hr.lordsofsmell.parfume.domain.model.response.PerfumeItem;
import hr.lordsofsmell.parfume.domain.model.response.User;
import hr.lordsofsmell.parfume.domain.repository.IRepository;
import hr.lordsofsmell.parfume.utils.PreferencesUtil;
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
    public Observable<List<PerfumeItem>> getAllParfumes(int from, int numOfItems) {
        return service.getAllParfumes(from, from + numOfItems - 1);
    }

    @Override
    public Observable<List<PerfumeItem>> getLikedParfumes(@NonNull Long userId,
                                                          int from,
                                                          int numOfItems) {
        return service.getLikedParfumes(PreferencesUtil.getToken(),
                userId,
                from,
                from + numOfItems - 1);
    }

    @Override
    public Observable<List<PerfumeItem>> getWishlistedParfumes(@NonNull Long userId,
                                                               int from,
                                                               int numOfItems) {
        return service.getWishlistedParfumes(PreferencesUtil.getToken(),
                userId,
                from,
                from + numOfItems - 1);
    }

    @Override
    public Observable<List<PerfumeItem>> getOwnedParfumes(@NonNull Long userId,
                                                          int from,
                                                          int numOfItems) {
        return service.getOwnedParfumes(PreferencesUtil.getToken(),
                userId,
                from,
                from + numOfItems - 1);
    }

    @Override
    public Observable<Void> changeLiked(@NonNull Long userId, @NonNull LikedRequest request) {
        return service.changeLiked(PreferencesUtil.getToken(), userId, request);
    }

    @Override
    public Observable<Void> changeWishlisted(@NonNull Long userId,
                                             @NonNull WishlistRequest request) {
        return service.changeWishlisted(PreferencesUtil.getToken(), userId, request);
    }

    @Override
    public Observable<Void> changeOwned(@NonNull Long userId, @NonNull OwnedRequest request) {
        return service.changeOwned(PreferencesUtil.getToken(), userId, request);
    }
}
