package hr.lordsofsmell.parfume.domain.repository;

import android.support.annotation.NonNull;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;

import hr.lordsofsmell.parfume.domain.model.request.LikedRequest;
import hr.lordsofsmell.parfume.domain.model.request.LoginRequest;
import hr.lordsofsmell.parfume.domain.model.request.OwnedRequest;
import hr.lordsofsmell.parfume.domain.model.request.RegisterRequest;
import hr.lordsofsmell.parfume.domain.model.request.WishlistRequest;
import hr.lordsofsmell.parfume.domain.model.response.PerfumeItem;
import hr.lordsofsmell.parfume.domain.model.response.User;
import io.reactivex.Observable;

public class MockRepository implements IRepository {
    @Override
    public Observable<User> login(@NonNull LoginRequest request) {
        return null;
    }

    @Override
    public Observable<User> register(@NonNull RegisterRequest request) {
        return null;
    }

    @Override
    public Observable<List<PerfumeItem>> getAllParfumes() {
        return Observable.fromCallable(new Callable<List<PerfumeItem>>() {
            @Override
            public List<PerfumeItem> call() throws Exception {
                return Arrays.asList(PerfumeItem.create(1L, "http://www.chanel.com/en_US/fragrance-beauty/cms2export/Site1Files/P125420/S125530_XLARGE.jpg", "Chanel", "Nº 5", "1921", false, false, false),
                        PerfumeItem.create(2L, "http://3.bp.blogspot.com/-oTo2y8ZA81Q/U_UF5_DlluI/AAAAAAAAdbk/PbHEE_iL8L0/s1600/Elizabeth%2Band%2BJames%2BNirvana%2BBlack.jpg", "Elizabeth and James", "Nirvana Black", "2013", false, false, false),
                        PerfumeItem.create(3L, "http://images.ulta.com/is/image/Ulta/2276635", "Chloé", "Rose", "2008", false, false, false));
            }
        });
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
