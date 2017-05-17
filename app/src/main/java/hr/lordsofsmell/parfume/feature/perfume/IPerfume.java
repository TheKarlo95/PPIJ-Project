package hr.lordsofsmell.parfume.feature.perfume;

import java.util.Collection;
import java.util.List;

import hr.lordsofsmell.parfume.domain.model.params.PerfumeParams;
import hr.lordsofsmell.parfume.domain.model.request.FavoriteRequest;
import hr.lordsofsmell.parfume.domain.model.request.OwnedRequest;
import hr.lordsofsmell.parfume.domain.model.request.WishlistRequest;
import hr.lordsofsmell.parfume.domain.model.response.Perfume;
import hr.lordsofsmell.parfume.domain.model.response.PerfumeItem;
import hr.lordsofsmell.parfume.feature.core.ICore;
import retrofit2.Response;

public interface IPerfume {

    interface View extends ICore.View {

        void setPerfumeProfile(Perfume perfume);

        void setSimilarPerfumes(Collection<PerfumeItem> perfumes);

        void favoriteChanged(long perfumeId, boolean isChecked);

        void wishlistedChanged(long perfumeId, boolean isChecked);

        void ownedChanged(long perfumeId, boolean isChecked);
    }

    interface Presenter extends ICore.Presenter {

        void getSimilarPerfumes(PerfumeParams params);

        void getPerfumeProfile(PerfumeParams params);

        void changeFavorite(FavoriteRequest request);

        void changeWishlisted(WishlistRequest request);

        void changeOwned(OwnedRequest request);
    }

    interface GetPerfumeUseCase extends ICore.Interactor<PerfumeParams, Perfume> {
    }

    interface GetSimilarPerfumesUseCase extends ICore.Interactor<PerfumeParams, Response<List<PerfumeItem>>> {
    }
}
