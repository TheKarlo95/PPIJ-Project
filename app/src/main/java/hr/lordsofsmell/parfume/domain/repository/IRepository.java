package hr.lordsofsmell.parfume.domain.repository;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

import hr.lordsofsmell.parfume.domain.model.request.FavoriteRequest;
import hr.lordsofsmell.parfume.domain.model.request.LoginRequest;
import hr.lordsofsmell.parfume.domain.model.request.OwnedRequest;
import hr.lordsofsmell.parfume.domain.model.request.RegisterRequest;
import hr.lordsofsmell.parfume.domain.model.request.WishlistRequest;
import hr.lordsofsmell.parfume.domain.model.response.Perfume;
import hr.lordsofsmell.parfume.domain.model.response.PerfumeItem;
import hr.lordsofsmell.parfume.domain.model.response.User;
import io.reactivex.Completable;
import io.reactivex.Observable;
import retrofit2.Response;

public interface IRepository {

    Observable<User> login(@NonNull LoginRequest request);

    Observable<User> register(@NonNull RegisterRequest request);

    Completable logout(@NonNull String token);

    Observable<Perfume> getPerfumeProfile(@NonNull String token, long perfumeId);

    Observable<Response<List<PerfumeItem>>> getSimilarParfumes(@Nullable String token, long perfumeId);

    Observable<Response<List<PerfumeItem>>> getAllParfumes(@Nullable String token,
                                                           int page,
                                                           @Nullable String company,
                                                           @Nullable String model,
                                                           @Nullable String year,
                                                           @Nullable String[] genders);

    Observable<Response<List<PerfumeItem>>> getRecommendedParfumes(@Nullable String token);

    Observable<Response<List<PerfumeItem>>> getLikedParfumes(@NonNull String token, int page);

    Observable<Response<List<PerfumeItem>>> getWishlistedParfumes(@NonNull String token, int page);

    Observable<Response<List<PerfumeItem>>> getOwnedParfumes(@NonNull String token, int page);

    Completable changeFavorite(@NonNull String token, @NonNull FavoriteRequest request);

    Completable changeWishlisted(@NonNull String token, @NonNull WishlistRequest request);

    Completable changeOwned(@NonNull String token, @NonNull OwnedRequest request);
}
