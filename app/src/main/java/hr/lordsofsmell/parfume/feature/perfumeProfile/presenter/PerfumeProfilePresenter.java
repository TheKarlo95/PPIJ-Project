package hr.lordsofsmell.parfume.feature.perfumeProfile.presenter;

import android.support.annotation.StringRes;

import java.util.List;

import javax.inject.Inject;

import hr.lordsofsmell.parfume.R;
import hr.lordsofsmell.parfume.domain.model.params.GetPerfumeProfileParams;
import hr.lordsofsmell.parfume.domain.model.params.LikedRequestParams;
import hr.lordsofsmell.parfume.domain.model.params.OwnedRequestParams;
import hr.lordsofsmell.parfume.domain.model.params.WishlistedRequestParams;
import hr.lordsofsmell.parfume.domain.model.request.FavoriteRequest;
import hr.lordsofsmell.parfume.domain.model.request.OwnedRequest;
import hr.lordsofsmell.parfume.domain.model.request.WishlistRequest;
import hr.lordsofsmell.parfume.domain.model.response.Parfume;
import hr.lordsofsmell.parfume.domain.model.response.PerfumeItem;
import hr.lordsofsmell.parfume.domain.model.response.User;
import hr.lordsofsmell.parfume.feature.core.observer.Observer;
import hr.lordsofsmell.parfume.feature.core.presenter.Presenter;
import hr.lordsofsmell.parfume.feature.perfumeProfile.IPerfumeProfile;
import hr.lordsofsmell.parfume.feature.perfumelist.IPerfumeList;
import hr.lordsofsmell.parfume.feature.perfumeProfile.IPerfumeProfile;
import hr.lordsofsmell.parfume.utils.PreferencesUtil;

/**
 * Created by tea03 on 5/16/2017.
 */

public class PerfumeProfilePresenter extends Presenter implements IPerfumeProfile.Presenter {


    private static final String TAG = "PerfumeProfile";
    private IPerfumeList.ChangeLikedUseCase changeFavoriteUseCase;
    private IPerfumeList.ChangeWishlistedUseCase changeWishlistedUseCase;
    private IPerfumeList.ChangeOwnedUseCase changeOwnedUseCase;
    private IPerfumeProfile.PerfumeProfileUseCase perfumeProfileUseCase;

    @Inject
    PerfumeProfilePresenter(IPerfumeProfile.View view,
                            IPerfumeList.ChangeLikedUseCase changeFavoriteUseCase,
                            IPerfumeList.ChangeWishlistedUseCase changeWishlistedUseCase,
                            IPerfumeList.ChangeOwnedUseCase changeOwnedUseCase,
                            IPerfumeProfile.PerfumeProfileUseCase perfumeProfileUseCase) {
        super(view);
        this.changeFavoriteUseCase = changeFavoriteUseCase;
        this.changeWishlistedUseCase = changeWishlistedUseCase;
        this.changeOwnedUseCase = changeOwnedUseCase;
        this.perfumeProfileUseCase = perfumeProfileUseCase;
    }

    @Override
    protected void cancel() {
        changeFavoriteUseCase.cancel();
        changeWishlistedUseCase.cancel();
        changeOwnedUseCase.cancel();
        perfumeProfileUseCase.cancel();
    }


    @Override
    public void getSimilarPerfumes(List<PerfumeItem> similarPerfumes) {

    }

    @Override
    public void presentsPerfumeProfile(GetPerfumeProfileParams params) {
        final IPerfumeProfile.View view = (IPerfumeProfile.View) getView();
        long perfumeId = params.perfumeId();
        perfumeProfileUseCase.execute(params, new Observer<Parfume>(view, "perfumeProfileUseCase", R.string.get_perfume_profile_error) {
            @Override
            public void onNext(Parfume value) {
                super.onNext(value);
                view.setPerfumeProfile(value);
            }
        });
    }

    @Override
    public void changeFavorite(final FavoriteRequest request) {
        final IPerfumeProfile.View view = (IPerfumeProfile.View) getView();
        view.showLoading();

        User user = PreferencesUtil.getUser();
        LikedRequestParams params = null;

        if (user != null) {
            params = LikedRequestParams.create(user.token(), user.id(), request);
        }
        changeFavoriteUseCase.execute(params,
                new Observer<Void>(view,
                        TAG + " ChangeFavoriteUseCase",
                        R.string.change_liked_error) {
                    @Override
                    public void onNext(Void value) {
                        super.onNext(value);
                    }

                    @Override
                    public void onComplete() {
                        super.onComplete();
                        view.favoriteChanged(request.parfumeId(), request.favorited());
                    }
                });
    }

    @Override
    public void changeWishlisted(final WishlistRequest request) {
        final IPerfumeProfile.View view = (IPerfumeProfile.View) getView();
        view.showLoading();

        User user = PreferencesUtil.getUser();
        WishlistedRequestParams params = null;

        if (user != null) {
            params = WishlistedRequestParams.create(user.token(), user.id(), request);
        }
        changeWishlistedUseCase.execute(params,
                new Observer<Void>(view,
                        TAG + " ChangeWishlistedUseCase",
                        R.string.change_wishlisted_error) {
                    @Override
                    public void onNext(Void value) {
                        super.onNext(value);
                    }

                    @Override
                    public void onComplete() {
                        super.onComplete();
                        view.wishlistedChanged(request.parfumeId(), request.wishlisted());
                    }
                });
    }

    @Override
    public void changeOwned(final OwnedRequest request) {
        final IPerfumeProfile.View view = (IPerfumeProfile.View) getView();
        view.showLoading();

        User user = PreferencesUtil.getUser();
        OwnedRequestParams params = null;

        if (user != null) {
            params = OwnedRequestParams.create(user.token(), user.id(), request);
        }
        changeOwnedUseCase.execute(params,
                new Observer<Void>(view,
                        TAG + " ChangeOwnedUseCase",
                        R.string.change_owned_error) {
                    @Override
                    public void onNext(Void value) {
                        super.onNext(value);
                    }

                    @Override
                    public void onComplete() {
                        super.onComplete();
                        view.ownedChanged(request.parfumeId(), request.owned());
                    }
                });
    }

    private Observer<List<PerfumeItem>> getListObserver(String tagAppend,
                                                        final boolean clearAdapter,
                                                        @StringRes int errorId) {
        final IPerfumeList.View view = (IPerfumeList.View) getView();
        return new Observer<List<PerfumeItem>>(view, TAG + " " + tagAppend, errorId) {
            @Override
            public void onNext(List<PerfumeItem> perfumes) {
                super.onNext(perfumes);
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
