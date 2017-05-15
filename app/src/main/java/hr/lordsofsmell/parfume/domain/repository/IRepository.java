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
import io.reactivex.Observable;

public interface IRepository {

    Observable<User> login(@NonNull LoginRequest request);

    Observable<User> register(@NonNull RegisterRequest request);

    Observable<List<PerfumeItem>> getAllParfumes(@Nullable String token, int page);

    Observable<List<PerfumeItem>> getLikedParfumes(@NonNull String token,
                                                   @NonNull Long userId,
                                                   int page);

    Observable<List<PerfumeItem>> getWishlistedParfumes(@NonNull String token,
                                                        @NonNull Long userId,
                                                        int page);

    Observable<List<PerfumeItem>> getOwnedParfumes(@NonNull String token,
                                                   @NonNull Long userId,
                                                   int page);

    Observable<Void> changeFavorite(@NonNull String token,
                                    @NonNull Long userId,
                                    @NonNull FavoriteRequest request);

    Observable<Void> changeWishlisted(@NonNull String token,
                                      @NonNull Long userId,
                                      @NonNull WishlistRequest request);

    Observable<Void> changeOwned(@NonNull String token,
                                 @NonNull Long userId,
                                 @NonNull OwnedRequest request);
}
