package hr.lordsofsmell.parfume.feature.perfumelist.presenter;

import android.support.annotation.StringRes;

import java.util.List;

import javax.inject.Inject;

import hr.lordsofsmell.parfume.R;
import hr.lordsofsmell.parfume.domain.model.params.FavoriteRequestParams;
import hr.lordsofsmell.parfume.domain.model.params.GetAllPerfumesParams;
import hr.lordsofsmell.parfume.domain.model.params.OwnedRequestParams;
import hr.lordsofsmell.parfume.domain.model.params.PerfumesListParams;
import hr.lordsofsmell.parfume.domain.model.params.WishlistedRequestParams;
import hr.lordsofsmell.parfume.domain.model.request.FavoriteRequest;
import hr.lordsofsmell.parfume.domain.model.request.OwnedRequest;
import hr.lordsofsmell.parfume.domain.model.request.WishlistRequest;
import hr.lordsofsmell.parfume.domain.model.response.PerfumeItem;
import hr.lordsofsmell.parfume.domain.model.response.User;
import hr.lordsofsmell.parfume.feature.core.observer.CompletableObserver;
import hr.lordsofsmell.parfume.feature.core.observer.Observer;
import hr.lordsofsmell.parfume.feature.core.presenter.Presenter;
import hr.lordsofsmell.parfume.feature.perfumelist.IPerfumeList;
import hr.lordsofsmell.parfume.feature.perfumelist.view.PerfumeListActivity;
import hr.lordsofsmell.parfume.utils.PreferencesUtil;

public class PerfumeListPresenter extends Presenter implements IPerfumeList.Presenter {

    private static final String TAG = "PerfumeList";

    private final IPerfumeList.LogoutUseCase logoutUseCase;
    private IPerfumeList.GetAllPerfumesUseCase getAllPerfumesUseCase;
    private IPerfumeList.GetRecommendedPerfumesUseCase getRecommendedPerfumesUseCase;
    private IPerfumeList.GetLikedPerfumesUseCase getLikedPerfumesUseCase;
    private IPerfumeList.GetWishlistedPerfumesUseCase getWishlistedPerfumesUseCase;
    private IPerfumeList.GetOwnedPerfumesUseCase getOwnedPerfumesUseCase;
    private IPerfumeList.ChangeLikedUseCase changeFavoriteUseCase;
    private IPerfumeList.ChangeWishlistedUseCase changeWishlistedUseCase;
    private IPerfumeList.ChangeOwnedUseCase changeOwnedUseCase;

    private int listType;
    private int lastPage;
    private boolean reachedLastPerfume;

    @Inject
    PerfumeListPresenter(IPerfumeList.View view,
                         IPerfumeList.LogoutUseCase logoutUseCase,
                         IPerfumeList.GetAllPerfumesUseCase getAllPerfumesUseCase,
                         IPerfumeList.GetRecommendedPerfumesUseCase getRecommendedPerfumesUseCase,
                         IPerfumeList.GetLikedPerfumesUseCase getLikedPerfumesUseCase,
                         IPerfumeList.GetWishlistedPerfumesUseCase getWishlistedPerfumesUseCase,
                         IPerfumeList.GetOwnedPerfumesUseCase getOwnedPerfumesUseCase,
                         IPerfumeList.ChangeLikedUseCase changeFavoriteUseCase,
                         IPerfumeList.ChangeWishlistedUseCase changeWishlistedUseCase,
                         IPerfumeList.ChangeOwnedUseCase changeOwnedUseCase) {
        super(view);
        this.logoutUseCase = logoutUseCase;
        this.getAllPerfumesUseCase = getAllPerfumesUseCase;
        this.getRecommendedPerfumesUseCase = getRecommendedPerfumesUseCase;
        this.getLikedPerfumesUseCase = getLikedPerfumesUseCase;
        this.getWishlistedPerfumesUseCase = getWishlistedPerfumesUseCase;
        this.getOwnedPerfumesUseCase = getOwnedPerfumesUseCase;
        this.changeFavoriteUseCase = changeFavoriteUseCase;
        this.changeWishlistedUseCase = changeWishlistedUseCase;
        this.changeOwnedUseCase = changeOwnedUseCase;

        listType = 0;
        lastPage = 1;
        reachedLastPerfume = false;
    }


    public void logout() {
        String token = PreferencesUtil.getToken();
        final IPerfumeList.View view = (IPerfumeList.View) getView();
        logoutUseCase.execute(token, new CompletableObserver(view, TAG, R.string.logout_error));
    }

    @Override
    public int getListType() {
        return listType;
    }

    @Override
    public void init(int listType) {
        this.listType = listType;
        lastPage = 1;
        reachedLastPerfume = false;
    }

    @Override
    public void loadPerfumes(boolean clearAfter,
                             String company,
                             String model,
                             String year,
                             List<String> genders) {
        final IPerfumeList.View view = (IPerfumeList.View) getView();
        if (clearAfter || (!reachedLastPerfume && !view.isRefreshing())) {
            view.setRefreshing(true);

            if (clearAfter) {
                lastPage = 1;
                reachedLastPerfume = false;
            }

            String token = PreferencesUtil.getToken();
            PerfumesListParams params = PerfumesListParams.create(token, lastPage);

            switch (listType) {
                case PerfumeListActivity.LIST_TYPE_ALL_PERFUMES:
                    GetAllPerfumesParams allParams = GetAllPerfumesParams.create(token,
                            lastPage,
                            company,
                            model,
                            year);
                    getAllPerfumesUseCase.execute(allParams, getListObserver(clearAfter,
                            R.string.get_all_perfumes_error));
                    break;
                case PerfumeListActivity.LIST_TYPE_FAVORITES:
                    getLikedPerfumesUseCase.execute(params, getListObserver(clearAfter,
                            R.string.get_liked_perfumes_error));
                    break;
                case PerfumeListActivity.LIST_TYPE_WISHLIST:
                    getWishlistedPerfumesUseCase.execute(params, getListObserver(clearAfter,
                            R.string.get_wishlisted_perfumes_error));
                    break;
                case PerfumeListActivity.LIST_TYPE_OWNED:
                    getOwnedPerfumesUseCase.execute(params, getListObserver(clearAfter,
                            R.string.get_owned_perfumes_error));
                    break;
                case PerfumeListActivity.LIST_TYPE_RECOMMENDED:
                    getRecommendedPerfumesUseCase.execute(params, getListObserver(clearAfter,
                            R.string.get_owned_perfumes_error));
                    break;
            }
        }
    }

    @Override
    public void loadPerfumes(boolean clearAdapter) {
        loadPerfumes(clearAdapter, null, null, null, null);
    }

    @Override
    public void changeFavorite(final FavoriteRequest request) {
        final IPerfumeList.View view = (IPerfumeList.View) getView();
        view.showLoading();

        String token = PreferencesUtil.getToken();
        FavoriteRequestParams params = null;

        if (token != null) {
            params = FavoriteRequestParams.create(token, request);
        }

        changeFavoriteUseCase.execute(params,
                new CompletableObserver(view, TAG, R.string.change_liked_error) {
                    @Override
                    public void onComplete() {
                        super.onComplete();
                        view.favoriteChanged(request.parfumeId(), request.favorited());
                    }
                });
    }

    @Override
    public void changeWishlisted(final WishlistRequest request) {
        final IPerfumeList.View view = (IPerfumeList.View) getView();
        view.showLoading();

        String token = PreferencesUtil.getToken();
        WishlistedRequestParams params = null;

        if (token != null) {
            params = WishlistedRequestParams.create(token, request);
        }

        changeWishlistedUseCase.execute(params,
                new CompletableObserver(view, TAG, R.string.change_wishlisted_error) {
                    @Override
                    public void onComplete() {
                        super.onComplete();
                        view.wishlistedChanged(request.parfumeId(), request.wishlisted());
                    }
                });
    }

    @Override
    public void changeOwned(final OwnedRequest request) {
        final IPerfumeList.View view = (IPerfumeList.View) getView();
        view.showLoading();

        String token = PreferencesUtil.getToken();
        OwnedRequestParams params = null;

        if (token != null) {
            params = OwnedRequestParams.create(token, request);
        }

        changeOwnedUseCase.execute(params,
                new CompletableObserver(view, TAG, R.string.change_owned_error) {
                    @Override
                    public void onComplete() {
                        super.onComplete();
                        view.ownedChanged(request.parfumeId(), request.owned());
                    }
                });
    }

    @Override
    protected void cancel() {
        logoutUseCase.cancel();
        getAllPerfumesUseCase.cancel();
        getRecommendedPerfumesUseCase.cancel();
        getLikedPerfumesUseCase.cancel();
        getWishlistedPerfumesUseCase.cancel();
        getOwnedPerfumesUseCase.cancel();
        changeFavoriteUseCase.cancel();
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

                lastPage++;
                reachedLastPerfume = perfumes.size() < 10
                        || listType == PerfumeListActivity.LIST_TYPE_RECOMMENDED;

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
