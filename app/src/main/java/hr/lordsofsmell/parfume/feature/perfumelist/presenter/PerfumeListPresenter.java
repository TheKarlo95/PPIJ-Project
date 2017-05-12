package hr.lordsofsmell.parfume.feature.perfumelist.presenter;

import android.support.annotation.StringRes;

import java.util.List;

import javax.inject.Inject;

import hr.lordsofsmell.parfume.R;
import hr.lordsofsmell.parfume.domain.model.params.GetAllPerfumesParams;
import hr.lordsofsmell.parfume.domain.model.params.GetLikedPerfumesParams;
import hr.lordsofsmell.parfume.domain.model.params.GetOwnedPerfumesParams;
import hr.lordsofsmell.parfume.domain.model.params.GetWishlistedPerfumesParams;
import hr.lordsofsmell.parfume.domain.model.params.LikedRequestParams;
import hr.lordsofsmell.parfume.domain.model.params.OwnedRequestParams;
import hr.lordsofsmell.parfume.domain.model.params.WishlistedRequestParams;
import hr.lordsofsmell.parfume.domain.model.request.LikedRequest;
import hr.lordsofsmell.parfume.domain.model.request.OwnedRequest;
import hr.lordsofsmell.parfume.domain.model.request.WishlistRequest;
import hr.lordsofsmell.parfume.domain.model.response.PerfumeItem;
import hr.lordsofsmell.parfume.domain.model.response.User;
import hr.lordsofsmell.parfume.feature.core.observer.Observer;
import hr.lordsofsmell.parfume.feature.core.presenter.Presenter;
import hr.lordsofsmell.parfume.feature.perfumelist.IPerfumeList;
import hr.lordsofsmell.parfume.utils.PreferencesUtil;

public class PerfumeListPresenter extends Presenter implements IPerfumeList.Presenter {

    public static final int ALL_PERFUMES_LIST = 0;
    public static final int LIKED_PERFUMES_LIST = 1;
    public static final int WISHLISTED_PERFUMES_LIST = 2;
    public static final int OWNED_PERFUMES_LIST = 3;

    private static final String TAG = "PerfumeList";
    private static final int LOAD_ITEMS = 5;

    private IPerfumeList.GetAllPerfumesUseCase getAllPerfumesUseCase;
    private IPerfumeList.GetLikedPerfumesUseCase getLikedPerfumesUseCase;
    private IPerfumeList.GetWishlistedPerfumesUseCase getWishlistedPerfumesUseCase;
    private IPerfumeList.GetOwnedPerfumesUseCase getOwnedPerfumesUseCase;
    private IPerfumeList.ChangeLikedUseCase changeLikedUseCase;
    private IPerfumeList.ChangeWishlistedUseCase changeWishlistedUseCase;
    private IPerfumeList.ChangeOwnedUseCase changeOwnedUseCase;

    private int perfumeListType;
    private int lastPerfumeIndex;
    private boolean reachedLastPerfume;

    @Inject
    PerfumeListPresenter(IPerfumeList.View view,
                         IPerfumeList.GetAllPerfumesUseCase getAllPerfumesUseCase,
                         IPerfumeList.GetLikedPerfumesUseCase getLikedPerfumesUseCase,
                         IPerfumeList.GetWishlistedPerfumesUseCase getWishlistedPerfumesUseCase,
                         IPerfumeList.GetOwnedPerfumesUseCase getOwnedPerfumesUseCase,
                         IPerfumeList.ChangeLikedUseCase changeLikedUseCase,
                         IPerfumeList.ChangeWishlistedUseCase changeWishlistedUseCase,
                         IPerfumeList.ChangeOwnedUseCase changeOwnedUseCase) {
        super(view);
        this.getAllPerfumesUseCase = getAllPerfumesUseCase;
        this.getLikedPerfumesUseCase = getLikedPerfumesUseCase;
        this.getWishlistedPerfumesUseCase = getWishlistedPerfumesUseCase;
        this.getOwnedPerfumesUseCase = getOwnedPerfumesUseCase;
        this.changeLikedUseCase = changeLikedUseCase;
        this.changeWishlistedUseCase = changeWishlistedUseCase;
        this.changeOwnedUseCase = changeOwnedUseCase;

        perfumeListType = 0;
        lastPerfumeIndex = 1;
        reachedLastPerfume = false;
    }

    @Override
    public void setPerfumeListType(int perfumeListType) {
        this.perfumeListType = perfumeListType;
    }

    @Override
    public void loadPerfumes(boolean clearAfter, boolean userSwipe) {
        final IPerfumeList.View view = (IPerfumeList.View) getView();
        if (userSwipe || (!reachedLastPerfume && !view.isRefreshing())) {
            view.setRefreshing(true);

            if (clearAfter) {
                lastPerfumeIndex = 1;
                reachedLastPerfume = false;
            }

            int userId = PreferencesUtil.getUserId();

            switch (perfumeListType) {
                case ALL_PERFUMES_LIST:
                    GetAllPerfumesParams allParams = GetAllPerfumesParams.create(lastPerfumeIndex,
                            LOAD_ITEMS);
                    getAllPerfumesUseCase.execute(allParams,
                            getListObserver(userSwipe, R.string.get_all_perfumes_error));
                    break;
                case LIKED_PERFUMES_LIST:
                    GetLikedPerfumesParams likedParams = GetLikedPerfumesParams.create(userId,
                            lastPerfumeIndex,
                            LOAD_ITEMS);
                    getLikedPerfumesUseCase.execute(likedParams,
                            getListObserver(userSwipe, R.string.get_liked_perfumes_error));
                    break;
                case WISHLISTED_PERFUMES_LIST:
                    GetWishlistedPerfumesParams wishlistedParams = GetWishlistedPerfumesParams.create(
                            userId,
                            lastPerfumeIndex,
                            LOAD_ITEMS);
                    getWishlistedPerfumesUseCase.execute(wishlistedParams,
                            getListObserver(userSwipe, R.string.get_wishlisted_perfumes_error));
                    break;
                case OWNED_PERFUMES_LIST:
                    GetOwnedPerfumesParams ownedParams = GetOwnedPerfumesParams.create(userId,
                            lastPerfumeIndex,
                            LOAD_ITEMS);
                    getOwnedPerfumesUseCase.execute(ownedParams,
                            getListObserver(userSwipe, R.string.get_owned_perfumes_error));
                    break;
            }
        }
    }

    @Override
    public void changeLiked(final LikedRequest request) {
        final IPerfumeList.View view = (IPerfumeList.View) getView();
        view.showLoading();

        User user = PreferencesUtil.getUser();
        LikedRequestParams params = null;

        if (user != null) {
            params = LikedRequestParams.create(user.token(), user.id(), request);
        }
        changeLikedUseCase.execute(params,
                new Observer<Void>(view, TAG, R.string.change_liked_error) {
                    @Override
                    public void onNext(Void value) {
                        super.onNext(value);
                        view.likeChanged(request.parfumeId());
                    }
                });
    }

    @Override
    public void changeWishlisted(final WishlistRequest request) {
        final IPerfumeList.View view = (IPerfumeList.View) getView();
        view.showLoading();

        User user = PreferencesUtil.getUser();
        WishlistedRequestParams params = null;

        if (user != null) {
            params = WishlistedRequestParams.create(user.token(), user.id(), request);
        }
        changeWishlistedUseCase.execute(params,
                new Observer<Void>(view, TAG, R.string.change_wishlisted_error) {
                    @Override
                    public void onNext(Void value) {
                        super.onNext(value);
                        view.wishlistedChanged(request.parfumeId());
                    }
                });
    }

    @Override
    public void changeOwned(final OwnedRequest request) {
        final IPerfumeList.View view = (IPerfumeList.View) getView();
        view.showLoading();

        User user = PreferencesUtil.getUser();
        OwnedRequestParams params = null;

        if (user != null) {
            params = OwnedRequestParams.create(user.token(), user.id(), request);
        }
        changeOwnedUseCase.execute(params,
                new Observer<Void>(view, TAG, R.string.change_owned_error) {
                    @Override
                    public void onNext(Void value) {
                        super.onNext(value);
                        view.ownedChanged(request.parfumeId());
                    }
                });
    }

    @Override
    protected void cancel() {
        getAllPerfumesUseCase.cancel();
        getLikedPerfumesUseCase.cancel();
        getWishlistedPerfumesUseCase.cancel();
        getOwnedPerfumesUseCase.cancel();
        changeLikedUseCase.cancel();
        changeWishlistedUseCase.cancel();
        changeOwnedUseCase.cancel();
    }

    private Observer<List<PerfumeItem>> getListObserver(final boolean clearAdapter,
                                                        @StringRes int errorId) {
        final IPerfumeList.View view = (IPerfumeList.View) getView();
        return new Observer<List<PerfumeItem>>(view, TAG, errorId) {
            @Override
            public void onNext(List<PerfumeItem> perfumes) {
                super.onNext(perfumes);

                lastPerfumeIndex += perfumes.size();
                reachedLastPerfume = perfumes.size() < LOAD_ITEMS;

                view.addPerfumes(perfumes, clearAdapter);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                view.setRefreshing(false);
            }

            @Override
            public void onComplete() {
                super.onComplete();
                view.setRefreshing(false);
            }
        };
    }
}
