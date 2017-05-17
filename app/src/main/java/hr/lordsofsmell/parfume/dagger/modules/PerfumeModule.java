package hr.lordsofsmell.parfume.dagger.modules;

import dagger.Module;
import dagger.Provides;
import hr.lordsofsmell.parfume.feature.perfume.IPerfume;
import hr.lordsofsmell.parfume.feature.perfume.presenter.PerfumePresenter;
import hr.lordsofsmell.parfume.feature.perfume.usecase.GetPerfumeUseCase;
import hr.lordsofsmell.parfume.feature.perfume.usecase.GetSimilarPerfumesUseCase;
import hr.lordsofsmell.parfume.feature.perfumelist.IPerfumeList;
import hr.lordsofsmell.parfume.feature.perfumelist.usecase.ChangeFavoriteUseCase;
import hr.lordsofsmell.parfume.feature.perfumelist.usecase.ChangeOwnedUseCase;
import hr.lordsofsmell.parfume.feature.perfumelist.usecase.ChangeWishlistedUseCase;

@Module
public class PerfumeModule {

    private final IPerfume.View view;

    public PerfumeModule(IPerfume.View view) {
        this.view = view;
    }

    @Provides
    IPerfume.View provideView() {
        return view;
    }

    @Provides
    IPerfume.Presenter providePresenter(PerfumePresenter presenter) {
        return presenter;
    }

    @Provides
    IPerfume.GetPerfumeUseCase provideGetPerfumeUseCase(GetPerfumeUseCase useCase) {
        return useCase;
    }

    @Provides
    IPerfume.GetSimilarPerfumesUseCase provideGetSimilarPerfumesUseCase(GetSimilarPerfumesUseCase useCase) {
        return useCase;
    }

    @Provides
    IPerfumeList.ChangeLikedUseCase provideChangeLikedUseCase(ChangeFavoriteUseCase useCase) {
        return useCase;
    }

    @Provides
    IPerfumeList.ChangeWishlistedUseCase provideChangeWishlistedUseCase(ChangeWishlistedUseCase useCase) {
        return useCase;
    }

    @Provides
    IPerfumeList.ChangeOwnedUseCase provideChangeOwnedUseCase(ChangeOwnedUseCase useCase) {
        return useCase;
    }
}
