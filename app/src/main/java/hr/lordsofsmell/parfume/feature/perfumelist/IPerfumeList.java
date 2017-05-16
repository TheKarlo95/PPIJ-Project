package hr.lordsofsmell.parfume.feature.perfumelist;

import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import hr.lordsofsmell.parfume.domain.model.params.GetAllPerfumesParams;
import hr.lordsofsmell.parfume.domain.model.params.FavoriteRequestParams;
import hr.lordsofsmell.parfume.domain.model.params.OwnedRequestParams;
import hr.lordsofsmell.parfume.domain.model.params.PerfumesListParams;
import hr.lordsofsmell.parfume.domain.model.params.WishlistedRequestParams;
import hr.lordsofsmell.parfume.domain.model.request.FavoriteRequest;
import hr.lordsofsmell.parfume.domain.model.request.OwnedRequest;
import hr.lordsofsmell.parfume.domain.model.request.WishlistRequest;
import hr.lordsofsmell.parfume.domain.model.response.PerfumeItem;
import hr.lordsofsmell.parfume.feature.core.ICore;

public interface IPerfumeList {

    interface View extends ICore.View {
        void logoutSuccesful();

        void addPerfumes(Collection<PerfumeItem> perfumes, boolean clearAdapter);

        void search(@Nullable String company,
                    @Nullable String model,
                    @Nullable String year,
                    @Nullable ArrayList<String> genders);

        void setRefreshing(boolean refreshing);

        boolean isRefreshing();

        void favoriteChanged(long perfumeId, boolean isChecked);

        void wishlistedChanged(long perfumeId, boolean isChecked);

        void ownedChanged(long perfumeId, boolean isChecked);
    }

    interface Presenter extends ICore.Presenter {
        void logout();

        int getListType();

        void init(int perfumeListType);

        void loadPerfumes(boolean clearAdapter,
                          String company,
                          String model,
                          String year,
                          List<String> genders);

        void loadPerfumes(boolean clearAdapter);

        void changeFavorite(FavoriteRequest request);

        void changeWishlisted(WishlistRequest request);

        void changeOwned(OwnedRequest request);
    }

    interface LogoutUseCase extends ICore.CompletableInteractor<String> {
    }

    interface GetAllPerfumesUseCase extends ICore.Interactor<GetAllPerfumesParams, List<PerfumeItem>> {
    }

    interface GetRecommendedPerfumesUseCase extends ICore.Interactor<PerfumesListParams, List<PerfumeItem>> {
    }

    interface GetLikedPerfumesUseCase extends ICore.Interactor<PerfumesListParams, List<PerfumeItem>> {
    }

    interface GetWishlistedPerfumesUseCase extends ICore.Interactor<PerfumesListParams, List<PerfumeItem>> {
    }

    interface GetOwnedPerfumesUseCase extends ICore.Interactor<PerfumesListParams, List<PerfumeItem>> {
    }

    interface ChangeLikedUseCase extends ICore.CompletableInteractor<FavoriteRequestParams> {
    }

    interface ChangeWishlistedUseCase extends ICore.CompletableInteractor<WishlistedRequestParams> {
    }

    interface ChangeOwnedUseCase extends ICore.CompletableInteractor<OwnedRequestParams> {
    }
}
