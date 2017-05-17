package hr.lordsofsmell.parfume.feature.perfume.presenter;

import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;

import hr.lordsofsmell.parfume.R;
import hr.lordsofsmell.parfume.domain.model.params.FavoriteRequestParams;
import hr.lordsofsmell.parfume.domain.model.params.OwnedRequestParams;
import hr.lordsofsmell.parfume.domain.model.params.PerfumeParams;
import hr.lordsofsmell.parfume.domain.model.params.WishlistedRequestParams;
import hr.lordsofsmell.parfume.domain.model.request.FavoriteRequest;
import hr.lordsofsmell.parfume.domain.model.request.OwnedRequest;
import hr.lordsofsmell.parfume.domain.model.request.WishlistRequest;
import hr.lordsofsmell.parfume.domain.model.response.Perfume;
import hr.lordsofsmell.parfume.domain.model.response.PerfumeItem;
import hr.lordsofsmell.parfume.feature.core.observer.CompletableObserver;
import hr.lordsofsmell.parfume.feature.core.observer.Observer;
import hr.lordsofsmell.parfume.feature.core.presenter.Presenter;
import hr.lordsofsmell.parfume.feature.perfume.IPerfume;
import hr.lordsofsmell.parfume.feature.perfumelist.IPerfumeList;
import hr.lordsofsmell.parfume.utils.PreferencesUtil;
import retrofit2.Response;

public class PerfumePresenter extends Presenter implements IPerfume.Presenter {

    private static final String TAG = "PerfumeProfile";

    private IPerfume.GetPerfumeUseCase perfumeProfileUseCase;
    private IPerfume.GetSimilarPerfumesUseCase getSimilarPerfumesUseCase;
    private IPerfumeList.ChangeLikedUseCase changeFavoriteUseCase;
    private IPerfumeList.ChangeWishlistedUseCase changeWishlistedUseCase;
    private IPerfumeList.ChangeOwnedUseCase changeOwnedUseCase;

    @Inject
    PerfumePresenter(@NonNull IPerfume.View view,
                     IPerfume.GetPerfumeUseCase perfumeProfileUseCase,
                     IPerfume.GetSimilarPerfumesUseCase getSimilarPerfumesUseCase,
                     IPerfumeList.ChangeLikedUseCase changeFavoriteUseCase,
                     IPerfumeList.ChangeWishlistedUseCase changeWishlistedUseCase,
                     IPerfumeList.ChangeOwnedUseCase changeOwnedUseCase) {
        super(view);
        this.perfumeProfileUseCase = perfumeProfileUseCase;
        this.getSimilarPerfumesUseCase = getSimilarPerfumesUseCase;
        this.changeFavoriteUseCase = changeFavoriteUseCase;
        this.changeWishlistedUseCase = changeWishlistedUseCase;
        this.changeOwnedUseCase = changeOwnedUseCase;
    }

    @Override
    public void getSimilarPerfumes(PerfumeParams params) {
        final IPerfume.View view = (IPerfume.View) getView();
        view.showLoading();
        getSimilarPerfumesUseCase.execute(params,
                new Observer<Response<List<PerfumeItem>>>(view, TAG, R.string.get_similar_perfumes_error) {
                    @Override
                    public void onNext(Response<List<PerfumeItem>> response) {
                        super.onNext(response);
                        List<PerfumeItem> perfumes = response.body();
                        view.setSimilarPerfumes(perfumes);
                    }
                });
    }

    @Override
    public void getPerfumeProfile(PerfumeParams params) {
        final IPerfume.View view = (IPerfume.View) getView();
        view.showLoading();
        perfumeProfileUseCase.execute(params,
                new Observer<Perfume>(view, TAG, R.string.get_perfume_profile_error) {
                    @Override
                    public void onNext(Perfume value) {
                        super.onNext(value);
                        view.setPerfumeProfile(value);
                    }
                });
    }

    @Override
    public void changeFavorite(final FavoriteRequest request) {
        final IPerfume.View view = (IPerfume.View) getView();
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
        final IPerfume.View view = (IPerfume.View) getView();
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
        final IPerfume.View view = (IPerfume.View) getView();
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
        changeFavoriteUseCase.cancel();
        changeWishlistedUseCase.cancel();
        changeOwnedUseCase.cancel();
        perfumeProfileUseCase.cancel();
    }
}
