package hr.lordsofsmell.parfume.dagger.modules;

import dagger.Module;
import dagger.Provides;
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
public class PerfumeListModule {

    private final IPerfumeList.View view;

    public PerfumeListModule(IPerfumeList.View view) {
        this.view = view;
    }

    @Provides
    IPerfumeList.View provideView() {
        return view;
    }

    @Provides
    IPerfumeList.Presenter providePresenter(PerfumeListPresenter presenter) {
        return presenter;
    }

    @Provides
    IPerfumeList.LogoutUseCase provideLogoutUseCase(LogoutUseCase useCase) {
        return useCase;
    }

    @Provides
    IPerfumeList.GetAllPerfumesUseCase provideGetAllPerfumesUseCase(GetAllPerfumesUseCase useCase) {
        return useCase;
    }

    @Provides
    IPerfumeList.GetRecommendedPerfumesUseCase provideGetRecommendedPerfumesUseCase(
            GetRecommendedPerfumesUseCase useCase) {
        return useCase;
    }

    @Provides
    IPerfumeList.GetLikedPerfumesUseCase provideGetLikedPerfumesUseCase(
            GetFavoritePerfumesUseCase useCase) {
        return useCase;
    }

    @Provides
    IPerfumeList.GetWishlistedPerfumesUseCase provideGetWishlistedPerfumesUseCase(
            GetWishlistedPerfumesUseCase useCase) {
        return useCase;
    }

    @Provides
    IPerfumeList.GetOwnedPerfumesUseCase provideGetOwnedPerfumesUseCase(
            GetOwnedPerfumesUseCase useCase) {
        return useCase;
    }

    @Provides
    IPerfumeList.ChangeLikedUseCase provideChangeLikedUseCase(ChangeFavoriteUseCase useCase) {
        return useCase;
    }

    @Provides
    IPerfumeList.ChangeWishlistedUseCase provideChangeWishlistedUseCase(
            ChangeWishlistedUseCase useCase) {
        return useCase;
    }

    @Provides
    IPerfumeList.ChangeOwnedUseCase provideChangeOwnedUseCase(ChangeOwnedUseCase useCase) {
        return useCase;
    }
}
