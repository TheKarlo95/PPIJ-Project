package hr.lordsofsmell.parfume.feature.perfumelist;

import java.util.Collection;
import java.util.List;

import hr.lordsofsmell.parfume.domain.model.response.PerfumeItem;
import hr.lordsofsmell.parfume.feature.core.ICore;

/**
 * Created by thekarlo95 on 5/7/17.
 */

public interface IPerfumeList {

    interface View extends ICore.View {
        void addPerfumes(Collection<PerfumeItem> perfumes);
    }

    interface Presenter extends ICore.Presenter {
        void loadPerfumes(int parfumeListType);
    }

    interface GetAllPerfumesUseCase extends ICore.Interactor<Void, List<PerfumeItem>> {
    }

    interface GetLikedPerfumesUseCase extends ICore.Interactor<Long, List<PerfumeItem>> {
    }

    interface GetWishlistedPerfumesUseCase extends ICore.Interactor<Long, List<PerfumeItem>> {
    }

    interface GetOwnedPerfumesUseCase extends ICore.Interactor<Long, List<PerfumeItem>> {
    }
}
