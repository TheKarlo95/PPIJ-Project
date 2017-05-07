package hr.lordsofsmell.parfume.domain.repository;

import android.support.annotation.NonNull;

import java.util.List;

import hr.lordsofsmell.parfume.domain.model.Gender;
import hr.lordsofsmell.parfume.domain.model.request.LikedRequest;
import hr.lordsofsmell.parfume.domain.model.request.LoginRequest;
import hr.lordsofsmell.parfume.domain.model.request.OwnedRequest;
import hr.lordsofsmell.parfume.domain.model.request.RegisterRequest;
import hr.lordsofsmell.parfume.domain.model.request.WishlistRequest;
import hr.lordsofsmell.parfume.domain.model.response.PerfumeItem;
import hr.lordsofsmell.parfume.domain.model.response.User;
import hr.lordsofsmell.parfume.domain.repository.network.NetworkDataSource;
import hr.lordsofsmell.parfume.utils.ObservableUtils;
import io.reactivex.Observable;

public class MockRepository implements IRepository {

    @Override
    public Observable<User> login(@NonNull LoginRequest request) {
        return Observable.fromArray(User.create(1L,
                "dsasdsadasas",
                request.username(),
                "example@example.com",
                "ivan",
                "ivic",
                Gender.MALE));
    }

    @Override
    public Observable<User> register(@NonNull RegisterRequest request) {
        return null;
    }

    @Override
    public Observable<List<PerfumeItem>> getAllParfumes() {
        return null;
    }

    @Override
    public Observable<List<PerfumeItem>> getLikedParfumes(@NonNull Long userId) {
        return null;
    }

    @Override
    public Observable<List<PerfumeItem>> getWishlistedParfumes(@NonNull Long userId) {
        return null;
    }

    @Override
    public Observable<List<PerfumeItem>> getOwnedParfumes(@NonNull Long userId) {
        return null;
    }

    @Override
    public Observable<Void> changeLiked(@NonNull Long userId, @NonNull LikedRequest request) {
        return null;
    }

    @Override
    public Observable<Void> changeWishlisted(@NonNull Long userId, @NonNull WishlistRequest request) {
        return null;
    }

    @Override
    public Observable<Void> changeOwned(@NonNull Long userId, @NonNull OwnedRequest request) {
        return null;
    }
}
