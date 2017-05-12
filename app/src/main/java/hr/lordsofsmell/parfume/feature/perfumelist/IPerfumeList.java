package hr.lordsofsmell.parfume.feature.perfumelist;

import java.util.Collection;
import java.util.List;

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
import hr.lordsofsmell.parfume.feature.core.ICore;

public interface IPerfumeList {

    interface View extends ICore.View {
        void addPerfumes(Collection<PerfumeItem> perfumes, boolean clearAdapter);

        void setRefreshing(boolean refreshing);

        boolean isRefreshing();

        void likeChanged(Long parfumeId);

        void wishlistedChanged(Long parfumeId);

        void ownedChanged(Long parfumeId);
    }

    interface Presenter extends ICore.Presenter {
        int getPerfumeListType();

        void setPerfumeListType(int perfumeListType);

        void loadPerfumes(boolean clearAdapter, boolean userSwipe);

        void changeLiked(LikedRequest request);

        void changeWishlisted(WishlistRequest request);

        void changeOwned(OwnedRequest request);
    }

    interface GetAllPerfumesUseCase extends ICore.Interactor<GetAllPerfumesParams, List<PerfumeItem>> {
    }

    interface GetLikedPerfumesUseCase extends ICore.Interactor<GetLikedPerfumesParams, List<PerfumeItem>> {
    }

    interface GetWishlistedPerfumesUseCase extends ICore.Interactor<GetWishlistedPerfumesParams, List<PerfumeItem>> {
    }

    interface GetOwnedPerfumesUseCase extends ICore.Interactor<GetOwnedPerfumesParams, List<PerfumeItem>> {
    }

    interface ChangeLikedUseCase extends ICore.Interactor<LikedRequestParams, Void> {
    }

    interface ChangeWishlistedUseCase extends ICore.Interactor<WishlistedRequestParams, Void> {
    }

    interface ChangeOwnedUseCase extends ICore.Interactor<OwnedRequestParams, Void> {
    }
}
