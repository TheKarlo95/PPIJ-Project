package hr.lordsofsmell.parfume.domain.repository;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

import hr.lordsofsmell.parfume.domain.model.Gender;
import hr.lordsofsmell.parfume.domain.model.request.FavoriteRequest;
import hr.lordsofsmell.parfume.domain.model.request.LoginRequest;
import hr.lordsofsmell.parfume.domain.model.request.OwnedRequest;
import hr.lordsofsmell.parfume.domain.model.request.RegisterRequest;
import hr.lordsofsmell.parfume.domain.model.request.WishlistRequest;
import hr.lordsofsmell.parfume.domain.model.response.Parfume;
import hr.lordsofsmell.parfume.domain.model.response.PerfumeItem;
import hr.lordsofsmell.parfume.domain.model.response.User;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.functions.Action;

public class MockRepository implements IRepository {

    private static final int LOAD_NUMBER = 5;

    private final List<PerfumeItem> perfumes = new ArrayList<>(Arrays.asList(
            PerfumeItem.create(1L,
                    "http://www.chanel.com/en_US/fragrance-beauty/cms2export/Site1Files/P125420/S125530_XLARGE.jpg",
                    "Chanel",
                    "Nº 5",
                    "1921",
                    Gender.FEMALE,
                    false,
                    false,
                    false),
            PerfumeItem.create(2L,
                    "http://img.allw.mn/content/perfumes/2012/01/P0771_hero.jpg",
                    "Guerlain",
                    "Shalimar",
                    "1925",
                    Gender.FEMALE,
                    false,
                    false,
                    false),
            PerfumeItem.create(3L,
                    "http://3.bp.blogspot.com/-oTo2y8ZA81Q/U_UF5_DlluI/AAAAAAAAdbk/PbHEE_iL8L0/s1600/Elizabeth%2Band%2BJames%2BNirvana%2BBlack.jpg",
                    "Elizabeth and James",
                    "Nirvana Black",
                    "2013",
                    Gender.FEMALE,
                    false,
                    false,
                    false),
            PerfumeItem.create(4L,
                    "http://images.ulta.com/is/image/Ulta/2276635",
                    "Chloé",
                    "Rose",
                    "2008",
                    Gender.FEMALE,
                    false,
                    false,
                    false),
            PerfumeItem.create(5L,
                    "http://img.allw.mn/content/p1/cn/dd6gh_perfume_cosmetics_daisy_marc_jacobs.jpg",
                    "Marc Jacobs",
                    "Daisy",
                    "2007",
                    Gender.FEMALE,
                    false,
                    false,
                    false),
            PerfumeItem.create(6L,
                    "http://img.allw.mn/content/i4/yd/fbsu7_perfume_cosmetics_nail-polish_distilled-beverage_rosabotanica.jpg",
                    "BALENCIAGA",
                    "Rosabotanica",
                    "2013",
                    Gender.FEMALE,
                    false,
                    false,
                    false),
            PerfumeItem.create(7L,
                    "http://img.allw.mn/content/qf/ve/nh9nt_perfume_cosmetics_lotion_glass-bottle_flavor.jpg",
                    "Tom Ford",
                    "Velvet Orchid",
                    "2013",
                    Gender.FEMALE,
                    false,
                    false,
                    false),
            PerfumeItem.create(8L,
                    "http://img.allw.mn/content/perfumes/2012/01/5_classique-by-jean-paul-gaultier.jpg",
                    "Jean Paul Gaultier",
                    "Classique",
                    "2012",
                    Gender.FEMALE,
                    false,
                    false,
                    false),
            PerfumeItem.create(9L,
                    "http://img.allw.mn/content/rm/ss/g78f7_perfume_nail-polish_cosmetics_glass-bottle_bottle.jpg",
                    "Philosophy",
                    "Loveswept",
                    "2013",
                    Gender.FEMALE,
                    false,
                    false,
                    false),
            PerfumeItem.create(10L,
                    "http://img.allw.mn/content/perfumes/2012/01/8_youth-dew-by-estee-lauder.jpg",
                    "Estee Lauder",
                    "Youth Dew",
                    "1953",
                    Gender.FEMALE,
                    false,
                    false,
                    false)));

    @Override
    public Observable<User> login(@NonNull LoginRequest request) {
        return Observable.fromArray(User.create("1b234c567df890ff23453e67ac",
                request.username(),
                "example@example.com",
                "John",
                "Doe"));
    }

    @Override
    public Observable<User> register(@NonNull RegisterRequest request) {
        return Observable.fromArray(User.create("1b234c567df890ff23453e67ac",
                request.username(),
                request.email(),
                request.name(),
                request.surname()));
    }

    @Override
    public Observable<Parfume> getPerfumeProfile(@NonNull long perfumeId) {
        return Observable.empty();
    }

    @Override
    public Observable<List<PerfumeItem>> getSimilarParfumes(@NonNull long perfumeId) {
        return Observable.empty();
    }

    public Completable logout(@NonNull String token) {
        return Completable.complete();
    }

    @Override
    public Observable<List<PerfumeItem>> getAllParfumes(@Nullable String token,
                                                        final int page,
                                                        @Nullable final String company,
                                                        @Nullable final String model,
                                                        @Nullable final String year) {
        return Observable.fromCallable(new Callable<List<PerfumeItem>>() {
            @Override
            public List<PerfumeItem> call() throws Exception {
                List<PerfumeItem> filtered = filter(perfumes, new Predicate<PerfumeItem>() {
                    @Override
                    public boolean test(PerfumeItem perfumeItem) {
                        boolean test = true;
                        if (!TextUtils.isEmpty(company)) {
                            test = company.equals(perfumeItem.company());
                        }
                        if (!TextUtils.isEmpty(model)) {
                            test = test && model.equals(perfumeItem.model());
                        }
                        if (!TextUtils.isEmpty(year)) {
                            test = test && year.equals(perfumeItem.year());
                        }
                        return test;
                    }
                });

                return subList(filtered, page, LOAD_NUMBER);
            }
        });
    }

    @Override
    public Observable<List<PerfumeItem>> getRecommendedParfumes(@Nullable String token, int page) {
        return Observable.empty();
    }

    @Override
    public Observable<List<PerfumeItem>> getLikedParfumes(@NonNull String token, final int page) {
        return Observable.fromCallable(new Callable<List<PerfumeItem>>() {
            @Override
            public List<PerfumeItem> call() throws Exception {
                List<PerfumeItem> liked = new ArrayList<>(perfumes.size() / 2);
                for (PerfumeItem perfume : perfumes) {
                    if (perfume.favorited()) {
                        liked.add(perfume);
                    }
                }

                return subList(liked, page, LOAD_NUMBER);
            }
        });
    }

    @Override
    public Observable<List<PerfumeItem>> getWishlistedParfumes(@NonNull String token,
                                                               final int page) {
        return Observable.fromCallable(new Callable<List<PerfumeItem>>() {
            @Override
            public List<PerfumeItem> call() throws Exception {
                List<PerfumeItem> wishlisted = new ArrayList<>(perfumes.size() / 2);
                for (PerfumeItem perfume : perfumes) {
                    if (perfume.wishlisted()) {
                        wishlisted.add(perfume);
                    }
                }

                return subList(wishlisted, page, LOAD_NUMBER);
            }
        });
    }

    @Override
    public Observable<List<PerfumeItem>> getOwnedParfumes(@NonNull String token, final int page) {
        return Observable.fromCallable(new Callable<List<PerfumeItem>>() {
            @Override
            public List<PerfumeItem> call() throws Exception {
                List<PerfumeItem> owned = new ArrayList<>(perfumes.size() / 2);
                for (PerfumeItem perfume : perfumes) {
                    if (perfume.owned()) {
                        owned.add(perfume);
                    }
                }

                return subList(owned, page, LOAD_NUMBER);
            }
        });
    }

    @Override
    public Completable changeFavorite(@NonNull String token,
                                      @NonNull final FavoriteRequest request) {
        return Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                for (int i = 0, max = perfumes.size(); i < max; i++) {
                    PerfumeItem perfume = perfumes.get(i);

                    if (perfume.id().equals(request.parfumeId())) {
                        PerfumeItem changedPerfume = perfume.withFavorited(request.favorited());
                        perfumes.remove(i);
                        perfumes.add(i, changedPerfume);
                        break;
                    }
                }
            }
        });
    }

    @Override
    public Completable changeWishlisted(@NonNull String token,
                                        @NonNull final WishlistRequest request) {
        return Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                for (int i = 0, max = perfumes.size(); i < max; i++) {
                    PerfumeItem perfume = perfumes.get(i);

                    if (perfume.id().equals(request.parfumeId())) {
                        PerfumeItem changedPerfume = perfume.withWishlisted(request.wishlisted());
                        perfumes.remove(i);
                        perfumes.add(i, changedPerfume);
                        break;
                    }
                }
            }
        });
    }

    @Override
    public Completable changeOwned(@NonNull String token,
                                   @NonNull final OwnedRequest request) {
        return Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                for (int i = 0, max = perfumes.size(); i < max; i++) {
                    PerfumeItem perfume = perfumes.get(i);

                    if (perfume.id().equals(request.parfumeId())) {
                        PerfumeItem changedPerfume = perfume.withOwned(request.owned());
                        perfumes.remove(i);
                        perfumes.add(i, changedPerfume);
                        break;
                    }
                }
            }
        });
    }

    private static <T> List<T> filter(@NonNull List<T> list, Predicate<T> predicate) {
        ArrayList<T> filtered = new ArrayList<>(list.size() / 2);

        for (T t : list) {
            if (predicate.test(t)) {
                filtered.add(t);
            }
        }

        return filtered;
    }

    private static <T> List<T> subList(@NonNull List<T> list, int page, int loadPerPage) {
        int from = (page - 1) * loadPerPage;
        int to = from + loadPerPage;

        if (to > list.size()) {
            to = list.size();
        }

        if (from > list.size()) {
            return Collections.emptyList();
        } else {
            return list.subList(from, to);
        }
    }
}
