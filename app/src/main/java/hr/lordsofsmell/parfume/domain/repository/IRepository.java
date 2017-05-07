package hr.lordsofsmell.parfume.domain.repository;

import android.support.annotation.NonNull;

import java.util.List;

import hr.lordsofsmell.parfume.domain.model.request.LikedRequest;
import hr.lordsofsmell.parfume.domain.model.request.LoginRequest;
import hr.lordsofsmell.parfume.domain.model.request.OwnedRequest;
import hr.lordsofsmell.parfume.domain.model.request.RegisterRequest;
import hr.lordsofsmell.parfume.domain.model.request.WishlistRequest;
import hr.lordsofsmell.parfume.domain.model.response.PerfumeItem;
import hr.lordsofsmell.parfume.domain.model.response.User;
import io.reactivex.Observable;

public interface IRepository {

    Observable<User> login(@NonNull LoginRequest request);

    Observable<User> register(@NonNull RegisterRequest request);

    Observable<List<PerfumeItem>> getAllParfumes();

    Observable<List<PerfumeItem>> getLikedParfumes(@NonNull Long userId);

    Observable<List<PerfumeItem>> getWishlistedParfumes(@NonNull Long userId);

    Observable<List<PerfumeItem>> getOwnedParfumes(@NonNull Long userId);

    Observable<Void> changeLiked(@NonNull Long userId, @NonNull LikedRequest request);

    Observable<Void> changeWishlisted(@NonNull Long userId, @NonNull WishlistRequest request);

    Observable<Void> changeOwned(@NonNull Long userId, @NonNull OwnedRequest request);
}