package hr.lordsofsmell.parfume.domain.repository;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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

    private final List<PerfumeItem> perfumes = Arrays.asList(
            PerfumeItem.create(1L,
                    "http://www.chanel.com/en_US/fragrance-beauty/cms2export/Site1Files/P125420/S125530_XLARGE.jpg",
                    "Chanel",
                    "Nº 5",
                    "1921",
                    false,
                    false,
                    false),
            PerfumeItem.create(2L,
                    "http://img.allw.mn/content/perfumes/2012/01/P0771_hero.jpg",
                    "Guerlain",
                    "Shalimar",
                    "1925",
                    false,
                    false,
                    false),
            PerfumeItem.create(3L,
                    "http://3.bp.blogspot.com/-oTo2y8ZA81Q/U_UF5_DlluI/AAAAAAAAdbk/PbHEE_iL8L0/s1600/Elizabeth%2Band%2BJames%2BNirvana%2BBlack.jpg",
                    "Elizabeth and James",
                    "Nirvana Black",
                    "2013",
                    false,
                    false,
                    false),
            PerfumeItem.create(4L,
                    "http://images.ulta.com/is/image/Ulta/2276635",
                    "Chloé",
                    "Rose",
                    "2008",
                    false,
                    false,
                    false),
            PerfumeItem.create(5L,
                    "http://img.allw.mn/content/p1/cn/dd6gh_perfume_cosmetics_daisy_marc_jacobs.jpg",
                    "Marc Jacobs",
                    "Daisy",
                    "2007",
                    false,
                    false,
                    false),
            PerfumeItem.create(6L,
                    "http://img.allw.mn/content/i4/yd/fbsu7_perfume_cosmetics_nail-polish_distilled-beverage_rosabotanica.jpg",
                    "BALENCIAGA",
                    "Rosabotanica",
                    "2013",
                    false,
                    false,
                    false),
            PerfumeItem.create(7L,
                    "http://img.allw.mn/content/qf/ve/nh9nt_perfume_cosmetics_lotion_glass-bottle_flavor.jpg",
                    "Tom Ford",
                    "Velvet Orchid",
                    "2013",
                    false,
                    false,
                    false),
            PerfumeItem.create(8L,
                    "http://img.allw.mn/content/perfumes/2012/01/5_classique-by-jean-paul-gaultier.jpg",
                    "Jean Paul Gaultier",
                    "Classique",
                    "2012",
                    false,
                    false,
                    false),
            PerfumeItem.create(9L,
                    "http://img.allw.mn/content/rm/ss/g78f7_perfume_nail-polish_cosmetics_glass-bottle_bottle.jpg",
                    "Philosophy",
                    "Loveswept",
                    "2013",
                    false,
                    false,
                    false),
            PerfumeItem.create(10L,
                    "http://img.allw.mn/content/perfumes/2012/01/8_youth-dew-by-estee-lauder.jpg",
                    "Estee Lauder",
                    "Youth Dew",
                    "1953",
                    false,
                    false,
                    false));

    @Override
    public Observable<User> login(@NonNull LoginRequest request) {
        return null;
    }

    @Override
    public Observable<User> register(@NonNull RegisterRequest request) {
        return null;
    }

    @Override
    public Observable<List<PerfumeItem>> getAllParfumes(final int from, final int numOfItems) {
        return Observable.fromCallable(new Callable<List<PerfumeItem>>() {
            @Override
            public List<PerfumeItem> call() throws Exception {
                if (from > perfumes.size()) {
                    return Collections.emptyList();
                } else {
                    return perfumes.subList(from - 1, from + numOfItems - 1);
                }
            }
        });
    }

    @Override
    public Observable<List<PerfumeItem>> getLikedParfumes(@NonNull Long userId,
                                                          final int from,
                                                          final int numOfItems) {
        return Observable.fromCallable(new Callable<List<PerfumeItem>>() {
            @Override
            public List<PerfumeItem> call() throws Exception {
                if (from > perfumes.size()) {
                    return Collections.emptyList();
                } else {
                    List<PerfumeItem> liked = new ArrayList<>(perfumes.size() / 2);
                    for (PerfumeItem perfume : perfumes) {
                        if (perfume.liked()) {
                            liked.add(perfume);
                        }
                    }
                    return liked.subList(from - 1, from + numOfItems - 1);
                }
            }
        });
    }

    @Override
    public Observable<List<PerfumeItem>> getWishlistedParfumes(@NonNull Long userId,
                                                               final int from,
                                                               final int numOfItems) {
        return Observable.fromCallable(new Callable<List<PerfumeItem>>() {
            @Override
            public List<PerfumeItem> call() throws Exception {
                if (from > perfumes.size()) {
                    return Collections.emptyList();
                } else {
                    List<PerfumeItem> wishlisted = new ArrayList<>(perfumes.size() / 2);
                    for (PerfumeItem perfume : perfumes) {
                        if (perfume.wishlisted()) {
                            wishlisted.add(perfume);
                        }
                    }
                    return wishlisted.subList(from - 1, from + numOfItems - 1);
                }
            }
        });
    }

    @Override
    public Observable<List<PerfumeItem>> getOwnedParfumes(@NonNull Long userId,
                                                          final int from,
                                                          final int numOfItems) {
        return Observable.fromCallable(new Callable<List<PerfumeItem>>() {
            @Override
            public List<PerfumeItem> call() throws Exception {
                if (from > perfumes.size()) {
                    return Collections.emptyList();
                } else {
                    List<PerfumeItem> owned = new ArrayList<>(perfumes.size() / 2);
                    for (PerfumeItem perfume : perfumes) {
                        if (perfume.owned()) {
                            owned.add(perfume);
                        }
                    }
                    return owned.subList(from - 1, from + numOfItems - 1);
                }
            }
        });
    }

    @Override
    public Observable<Void> changeLiked(@NonNull Long userId, @NonNull final LikedRequest request) {
        return Observable.fromCallable(new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                for (int i = 0, max = perfumes.size(); i < max; i++) {
                    PerfumeItem perfume = perfumes.get(i);

                    if (perfume.id().equals(request.parfumeId())) {
                        PerfumeItem changedPerfume = perfume.withLiked(request.liked());
                        perfumes.remove(i);
                        perfumes.add(i, changedPerfume);
                    }
                }
                return null;
            }
        });
    }

    @Override
    public Observable<Void> changeWishlisted(@NonNull Long userId,
                                             @NonNull final WishlistRequest request) {
        return Observable.fromCallable(new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                for (int i = 0, max = perfumes.size(); i < max; i++) {
                    PerfumeItem perfume = perfumes.get(i);

                    if (perfume.id().equals(request.parfumeId())) {
                        PerfumeItem changedPerfume = perfume.withWishlisted(request.wishlisted());
                        perfumes.remove(i);
                        perfumes.add(i, changedPerfume);
                    }
                }
                return null;
            }
        });
    }

    @Override
    public Observable<Void> changeOwned(@NonNull Long userId, @NonNull final OwnedRequest request) {
        return Observable.fromCallable(new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                for (int i = 0, max = perfumes.size(); i < max; i++) {
                    PerfumeItem perfume = perfumes.get(i);

                    if (perfume.id().equals(request.parfumeId())) {
                        PerfumeItem changedPerfume = perfume.withOwned(request.owned());
                        perfumes.remove(i);
                        perfumes.add(i, changedPerfume);
                    }
                }
                return null;
            }
        });
    }
}
