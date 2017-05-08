package hr.lordsofsmell.parfume.feature.perfumelist;

import java.util.Collection;
import java.util.List;

import hr.lordsofsmell.parfume.domain.model.request.LikedRequest;
import hr.lordsofsmell.parfume.domain.model.request.OwnedRequest;
import hr.lordsofsmell.parfume.domain.model.request.WishlistRequest;
import hr.lordsofsmell.parfume.domain.model.response.PerfumeItem;
import hr.lordsofsmell.parfume.feature.core.ICore;

public interface IPerfumeList {

    interface View extends ICore.View {
        void addPerfumes(Collection<PerfumeItem> perfumes);

        void likeChanged(Long parfumeId);

        void wishlistedChanged(Long parfumeId);

        void ownedChanged(Long parfumeId);
    }

    interface Presenter extends ICore.Presenter {
        void loadPerfumes(int parfumeListType);

        void changeLiked(LikedRequest request);

        void changeWishlisted(WishlistRequest request);

        void changeOwned(OwnedRequest request);
    }

    interface GetAllPerfumesUseCase extends ICore.Interactor<Void, List<PerfumeItem>> {
    }

    interface GetLikedPerfumesUseCase extends ICore.Interactor<Long, List<PerfumeItem>> {
    }

    interface GetWishlistedPerfumesUseCase extends ICore.Interactor<Long, List<PerfumeItem>> {
    }

    interface GetOwnedPerfumesUseCase extends ICore.Interactor<Long, List<PerfumeItem>> {
    }

    interface ChangeLikedUseCase extends ICore.Interactor<hr.lordsofsmell.parfume.feature.perfumelist.usecase.ChangeLikedUseCase.Params, Void> {
    }

    interface ChangeWishlistedUseCase extends ICore.Interactor<hr.lordsofsmell.parfume.feature.perfumelist.usecase.ChangeWishlistedUseCase.Params, Void> {
    }

    interface ChangeOwnedUseCase extends ICore.Interactor<hr.lordsofsmell.parfume.feature.perfumelist.usecase.ChangeOwnedUseCase.Params, Void> {
    }
}
