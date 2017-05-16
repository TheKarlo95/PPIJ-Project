package hr.lordsofsmell.parfume.feature.perfumeProfile;

import java.util.Collection;
import java.util.List;

import hr.lordsofsmell.parfume.domain.model.params.GetAllPerfumesParams;
import hr.lordsofsmell.parfume.domain.model.params.GetPerfumeProfileParams;
import hr.lordsofsmell.parfume.domain.model.params.LikedRequestParams;
import hr.lordsofsmell.parfume.domain.model.params.OwnedRequestParams;
import hr.lordsofsmell.parfume.domain.model.params.WishlistedRequestParams;
import hr.lordsofsmell.parfume.domain.model.request.FavoriteRequest;
import hr.lordsofsmell.parfume.domain.model.request.OwnedRequest;
import hr.lordsofsmell.parfume.domain.model.request.WishlistRequest;
import hr.lordsofsmell.parfume.domain.model.response.Parfume;
import hr.lordsofsmell.parfume.domain.model.response.PerfumeItem;
import hr.lordsofsmell.parfume.feature.core.ICore;

/**
 * Created by tea03 on 5/16/2017.
 */

public interface IPerfumeProfile {
    interface View extends ICore.View {

        void setPerfumeProfile(Parfume perfume);

        void addSimilarPerfumes(Collection<PerfumeItem> perfumes, boolean clearAdapter);

        void favoriteChanged(long perfumeId, boolean isChecked);

        void wishlistedChanged(long perfumeId, boolean isChecked);

        void ownedChanged(long perfumeId, boolean isChecked);
    }
    interface Presenter extends ICore.Presenter {

        void getSimilarPerfumes (List<PerfumeItem> similarPerfumes);

        void presentsPerfumeProfile(GetPerfumeProfileParams params);

        void changeFavorite(FavoriteRequest request);

        void changeWishlisted(WishlistRequest request);

        void changeOwned(OwnedRequest request);
    }
    interface PerfumeProfileUseCase extends ICore.Interactor<GetPerfumeProfileParams, Parfume> {
    }

}
