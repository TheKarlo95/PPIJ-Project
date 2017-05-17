package hr.lordsofsmell.parfume.domain.repository.network;

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
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {

    @POST("Profile/Login")
    Observable<User> login(@Body LoginRequest request);

    @POST("Profile/Register")
    Observable<User> register(@Body RegisterRequest request);

    @GET("parfumes/Details/{perfumeId}")
    Observable<Perfume> getPerfumeProfile(@Header("X-Authorization") String token,
                                          @Path("perfumeId") long perfumeId);

    @GET("parfumes/Similar/{perfumeId}")
    Observable<Response<List<PerfumeItem>>> getSimilarPerfumes(@Header("X-Authorization") String token,
                                                     @Path("perfumeId") long perfumeId);

    @POST("Profile/Logout")
    Completable logout(@Header("X-Authorization") String token);

    @GET("parfumes/{page}")
    Observable<Response<List<PerfumeItem>>> getAllParfumes(@Header("X-Authorization") String token,
                                                           @Path("page") int page,
                                                           @Header("manufacturer") String company,
                                                           @Header("name") String name,
                                                           @Header("year") String year,
                                                           @Header("gender") String[] genders);

    @GET("parfumes/recommendations")
    Observable<Response<List<PerfumeItem>>> getRecommendedParfumes(@Header("X-Authorization") String token);


    @GET("parfumes/GetLiked/{page}")
    Observable<Response<List<PerfumeItem>>> getLikedParfumes(@Header("X-Authorization") String token,
                                                             @Path("page") int page);

    @GET("parfumes/GetWish/{page}")
    Observable<Response<List<PerfumeItem>>> getWishlistedParfumes(@Header("X-Authorization") String token,
                                                                  @Path("page") int page);

    @GET("parfumes/GetOwned/{page}")
    Observable<Response<List<PerfumeItem>>> getOwnedParfumes(@Header("X-Authorization") String token,
                                                             @Path("page") int page);

    @POST("Profile/AddToLikes")
    Completable changeLiked(@Header("X-Authorization") String token,
                            @Body FavoriteRequest request);

    @POST("Profile/AddToWishlist")
    Completable changeWishlisted(@Header("X-Authorization") String token,
                                 @Body WishlistRequest request);

    @POST("Profile/AddToOwned")
    Completable changeOwned(@Header("X-Authorization") String token,
                            @Body OwnedRequest request);
}
