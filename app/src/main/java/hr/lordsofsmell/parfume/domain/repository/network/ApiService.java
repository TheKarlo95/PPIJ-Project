package hr.lordsofsmell.parfume.domain.repository.network;

import java.util.List;

import hr.lordsofsmell.parfume.domain.model.request.FavoriteRequest;
import hr.lordsofsmell.parfume.domain.model.request.LoginRequest;
import hr.lordsofsmell.parfume.domain.model.request.OwnedRequest;
import hr.lordsofsmell.parfume.domain.model.request.RegisterRequest;
import hr.lordsofsmell.parfume.domain.model.request.WishlistRequest;
import hr.lordsofsmell.parfume.domain.model.response.PerfumeItem;
import hr.lordsofsmell.parfume.domain.model.response.User;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {

    // TODO add real URLs when possible

    @POST("users/login")
    Observable<User> login(@Body LoginRequest request);

    @POST("users")
    Observable<User> register(@Body RegisterRequest request);

    @GET("parfumes/{from}/{to}")
    Observable<List<PerfumeItem>> getAllParfumes(@Path("from") int from, @Path("to") int to);

    @GET("users/{id}/favorited/{from}/{to}")
    Observable<List<PerfumeItem>> getLikedParfumes(@Header("X-Authorization") String token,
                                                   @Path("id") long userId,
                                                   @Path("from") int from,
                                                   @Path("to") int to);

    @GET("users/{id}/wishlist/{from}/{to}")
    Observable<List<PerfumeItem>> getWishlistedParfumes(@Header("X-Authorization") String token,
                                                        @Path("id") long userId,
                                                        @Path("from") int from,
                                                        @Path("to") int to);

    @GET("users/{id}/owned/{from}/{to}")
    Observable<List<PerfumeItem>> getOwnedParfumes(@Header("X-Authorization") String token,
                                                   @Path("id") long userId,
                                                   @Path("from") int from,
                                                   @Path("to") int to);

    @POST("users/{id}/favorited")
    Observable<Void> changeLiked(@Header("X-Authorization") String token,
                                 @Path("id") Long id,
                                 @Body FavoriteRequest request);

    @POST("users/{id}/wishlist")
    Observable<Void> changeWishlisted(@Header("X-Authorization") String token,
                                      @Path("id") Long id,
                                      @Body WishlistRequest request);

    @POST("users/{id}/owned")
    Observable<Void> changeOwned(@Header("X-Authorization") String token,
                                 @Path("id") Long id,
                                 @Body OwnedRequest request);
}
