package hr.lordsofsmell.parfume.dagger.modules;

import android.content.Context;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import hr.lordsofsmell.parfume.feature.perfumelist.IPerfumeList;
import hr.lordsofsmell.parfume.feature.perfumelist.presenter.PerfumeListPresenter;
import hr.lordsofsmell.parfume.feature.perfumelist.usecase.GetAllPerfumesUseCase;
import hr.lordsofsmell.parfume.feature.perfumelist.usecase.GetLikedPerfumesUseCase;
import hr.lordsofsmell.parfume.feature.perfumelist.usecase.GetOwnedPerfumesUseCase;
import hr.lordsofsmell.parfume.feature.perfumelist.usecase.GetWishlistedPerfumesUseCase;

@Module
public class PerfumeListModule {

    private final IPerfumeList.View view;
    private final Context applicationContext;

    public PerfumeListModule(IPerfumeList.View view, Context applicationContext) {
        this.view = view;
        this.applicationContext = applicationContext;
    }

    @Provides
    public IPerfumeList.View provideView() {
        return view;
    }

    @Provides
    public Context provideApplicationContext() {
        return applicationContext;
    }

    @Provides
    public IPerfumeList.Presenter providePresenter(PerfumeListPresenter presenter) {
        return presenter;
    }

    @Provides
    public IPerfumeList.GetAllPerfumesUseCase provideGetAllPerfumesUseCase(GetAllPerfumesUseCase useCase) {
        return useCase;
    }

    @Provides
    public IPerfumeList.GetLikedPerfumesUseCase provideGetLikedPerfumesUseCase(GetLikedPerfumesUseCase useCase) {
        return useCase;
    }

    @Provides
    public IPerfumeList.GetWishlistedPerfumesUseCase provideGetWishlistedPerfumesUseCase(GetWishlistedPerfumesUseCase useCase) {
        return useCase;
    }

    @Provides
    public IPerfumeList.GetOwnedPerfumesUseCase provideGetOwnedPerfumesUseCase(GetOwnedPerfumesUseCase useCase) {
        return useCase;
    }
}
