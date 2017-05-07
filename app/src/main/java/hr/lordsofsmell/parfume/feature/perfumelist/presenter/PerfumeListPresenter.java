package hr.lordsofsmell.parfume.feature.perfumelist.presenter;

import android.support.annotation.StringRes;

import java.util.List;

import javax.inject.Inject;

import hr.lordsofsmell.parfume.R;
import hr.lordsofsmell.parfume.domain.model.response.PerfumeItem;
import hr.lordsofsmell.parfume.feature.core.observer.Observer;
import hr.lordsofsmell.parfume.feature.core.presenter.Presenter;
import hr.lordsofsmell.parfume.feature.perfumelist.IPerfumeList;

/**
 * Created by thekarlo95 on 5/7/17.
 */

public class PerfumeListPresenter extends Presenter implements IPerfumeList.Presenter {

    public static final int ALL_PERFUMES_LIST = 0;
    public static final int LIKED_PERFUMES_LIST = 1;
    public static final int WISHLISTED_PERFUMES_LIST = 2;
    public static final int OWNED_PERFUMES_LIST = 3;

    private static final String TAG = "PerfumeList";

    private IPerfumeList.GetAllPerfumesUseCase getAllPerfumesUseCase;
    private IPerfumeList.GetLikedPerfumesUseCase getLikedPerfumesUseCase;
    private IPerfumeList.GetWishlistedPerfumesUseCase getWishlistedPerfumesUseCase;
    private IPerfumeList.GetOwnedPerfumesUseCase getOwnedPerfumesUseCase;

    @Inject
    public PerfumeListPresenter(IPerfumeList.View view,
                                IPerfumeList.GetAllPerfumesUseCase getAllPerfumesUseCase,
                                IPerfumeList.GetLikedPerfumesUseCase getLikedPerfumesUseCase,
                                IPerfumeList.GetWishlistedPerfumesUseCase getWishlistedPerfumesUseCase,
                                IPerfumeList.GetOwnedPerfumesUseCase getOwnedPerfumesUseCase) {
        super(view);
        this.getAllPerfumesUseCase = getAllPerfumesUseCase;
        this.getLikedPerfumesUseCase = getLikedPerfumesUseCase;
        this.getWishlistedPerfumesUseCase = getWishlistedPerfumesUseCase;
        this.getOwnedPerfumesUseCase = getOwnedPerfumesUseCase;
    }

    @Override
    public void loadPerfumes(int parfumeListType) {
        switch (parfumeListType) {
            case ALL_PERFUMES_LIST:
                getAllPerfumesUseCase.execute(null, getListObserver(R.string.get_all_perfumes_error));
            case LIKED_PERFUMES_LIST:
                getLikedPerfumesUseCase.execute(null, getListObserver(R.string.get_liked_perfumes_error));
            case WISHLISTED_PERFUMES_LIST:
                getWishlistedPerfumesUseCase.execute(null, getListObserver(R.string.get_wishlisted_perfumes_error));
            case OWNED_PERFUMES_LIST:
                getOwnedPerfumesUseCase.execute(null, getListObserver(R.string.get_owned_perfumes_error));
        }
    }

    private Observer<List<PerfumeItem>> getListObserver(@StringRes int errorId) {
        final IPerfumeList.View view = (IPerfumeList.View) getView();
        return new Observer<List<PerfumeItem>>(view, TAG, errorId) {
            @Override
            public void onNext(List<PerfumeItem> perfumes) {
                super.onNext(perfumes);
                view.addPerfumes(perfumes);
            }
        };
    }
}
