package hr.lordsofsmell.parfume.dagger.modules;

import dagger.Module;
import dagger.Provides;
import hr.lordsofsmell.parfume.feature.perfumeProfile.IPerfumeProfile;
import hr.lordsofsmell.parfume.feature.perfumeProfile.presenter.PerfumeProfilePresenter;
import hr.lordsofsmell.parfume.feature.perfumeProfile.usecase.GetPerfumeUseCase;
import hr.lordsofsmell.parfume.feature.perfumeProfile.usecase.GetSimilarPerfumesUseCase;
import hr.lordsofsmell.parfume.feature.perfumelist.IPerfumeList;
import hr.lordsofsmell.parfume.feature.perfumelist.presenter.PerfumeListPresenter;
import hr.lordsofsmell.parfume.feature.perfumelist.usecase.ChangeFavoriteUseCase;
import hr.lordsofsmell.parfume.feature.perfumelist.usecase.ChangeOwnedUseCase;
import hr.lordsofsmell.parfume.feature.perfumelist.usecase.ChangeWishlistedUseCase;
import hr.lordsofsmell.parfume.feature.perfumelist.usecase.GetAllPerfumesUseCase;
import hr.lordsofsmell.parfume.feature.perfumelist.usecase.GetFavoritePerfumesUseCase;
import hr.lordsofsmell.parfume.feature.perfumelist.usecase.GetOwnedPerfumesUseCase;
import hr.lordsofsmell.parfume.feature.perfumelist.usecase.GetRecommendedPerfumesUseCase;
import hr.lordsofsmell.parfume.feature.perfumelist.usecase.GetWishlistedPerfumesUseCase;
import hr.lordsofsmell.parfume.feature.perfumelist.usecase.LogoutUseCase;

@Module
public class PerfumeModule {

    private final IPerfumeProfile.View view;

    public PerfumeModule(IPerfumeProfile.View view) {
        this.view = view;
    }

    @Provides
    public IPerfumeProfile.View provideView() {
        return view;
    }

    @Provides
    public IPerfumeProfile.Presenter providePresenter(PerfumeProfilePresenter presenter) {
        return presenter;
    }

    @Provides
    public IPerfumeProfile.GetPerfumeUseCase provideGetPerfumeUseCase(GetPerfumeUseCase useCase) {
        return useCase;
    }

    @Provides
    public IPerfumeProfile.GetSimilarPerfumesUseCase provideGetSimilarPerfumesUseCase(GetSimilarPerfumesUseCase useCase) {
        return useCase;
    }

    @Provides
    public IPerfumeList.ChangeLikedUseCase provideChangeLikedUseCase(ChangeFavoriteUseCase useCase) {
        return useCase;
    }

    @Provides
    public IPerfumeList.ChangeWishlistedUseCase provideChangeWishlistedUseCase(ChangeWishlistedUseCase useCase) {
        return useCase;
    }

    @Provides
    public IPerfumeList.ChangeOwnedUseCase provideChangeOwnedUseCase(ChangeOwnedUseCase useCase) {
        return useCase;
    }
}
