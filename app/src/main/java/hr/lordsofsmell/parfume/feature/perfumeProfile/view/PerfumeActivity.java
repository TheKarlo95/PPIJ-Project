package hr.lordsofsmell.parfume.feature.perfumeProfile.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collection;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import co.infinum.mjolnirrecyclerview.MjolnirRecyclerView;
import hr.lordsofsmell.parfume.R;
import hr.lordsofsmell.parfume.dagger.components.AppComponent;
import hr.lordsofsmell.parfume.dagger.modules.PerfumeModule;
import hr.lordsofsmell.parfume.domain.model.Gender;
import hr.lordsofsmell.parfume.domain.model.params.PerfumeParams;
import hr.lordsofsmell.parfume.domain.model.request.FavoriteRequest;
import hr.lordsofsmell.parfume.domain.model.request.OwnedRequest;
import hr.lordsofsmell.parfume.domain.model.request.WishlistRequest;
import hr.lordsofsmell.parfume.domain.model.response.Perfume;
import hr.lordsofsmell.parfume.domain.model.response.PerfumeItem;
import hr.lordsofsmell.parfume.feature.core.adapter.PerfumeAdapter;
import hr.lordsofsmell.parfume.feature.core.view.ActivityView;
import hr.lordsofsmell.parfume.feature.perfumeProfile.IPerfumeProfile;
import hr.lordsofsmell.parfume.utils.ImageUtils;
import hr.lordsofsmell.parfume.utils.PreferencesUtil;

public class PerfumeActivity extends ActivityView
        implements IPerfumeProfile.View,
        PerfumeAdapter.OnPerfumeImageClickListener,
        PerfumeAdapter.OnPerfumeFavoriteClickListener,
        PerfumeAdapter.OnPerfumeWishlistClickListener,
        PerfumeAdapter.OnPerfumeOwnedClickListener {

    private static final String TAG = "Perfume";

    private static final String EXTRA_PERFUME_ITEM = "perfume_item";

    @BindView(R.id.iv_perfume_image) ImageView ivImage;
    @BindView(R.id.iv_perfume_gender) ImageView ivGender;
    @BindView(R.id.tv_perfume_company) TextView tvCompany;
    @BindView(R.id.tv_perfume_model) TextView tvModel;
    @BindView(R.id.tv_perfume_year) TextView tvYear;
    @BindView(R.id.tv_perfume_description) TextView tvDescription;
    @BindView(R.id.mrv_perfumes_list) MjolnirRecyclerView mrvSimilarPerfumes;

    @Inject
    IPerfumeProfile.Presenter presenter;

    private PerfumeAdapter adapter;

    public static Intent createIntent(Context context, PerfumeItem perfume) {
        Intent intent = new Intent(context, PerfumeActivity.class);
        intent.putExtra(EXTRA_PERFUME_ITEM, perfume);
        return intent;
    }

    @Override
    public void setPerfumeProfile(final Perfume perfume) {
        ImageUtils.loadImage(this, ivImage, perfume.image());
        setGenderIcon(perfume.gender());

        tvCompany.setText(perfume.company());
        tvModel.setText(perfume.model());
        tvYear.setText(perfume.year());
        tvDescription.setText(perfume.description());

        String token = PreferencesUtil.getToken();
        long perfumeId = perfume.id();
        presenter.getSimilarPerfumes(PerfumeParams.create(token, perfumeId));
    }

    @Override
    public void setSimilarPerfumes(Collection<PerfumeItem> perfumes) {
        adapter.clear();
        adapter.addAll(perfumes);
    }

    @Override
    public void favoriteChanged(long perfumeId, boolean isChecked) {
        int index = adapter.getIndexById(perfumeId);

        PerfumeItem perfume = adapter.get(index).withFavorited(isChecked);
        adapter.update(perfume);
    }

    @Override
    public void wishlistedChanged(long perfumeId, boolean isChecked) {
        int index = adapter.getIndexById(perfumeId);

        PerfumeItem perfume = adapter.get(index).withWishlisted(isChecked);
        adapter.update(perfume);
    }

    @Override
    public void ownedChanged(long perfumeId, boolean isChecked) {
        int index = adapter.getIndexById(perfumeId);

        PerfumeItem perfume = adapter.get(index).withOwned(isChecked);
        adapter.update(perfume);
    }

    @Override
    public void onImageClick(View view, PerfumeItem perfume, int position) {
        startActivity(PerfumeActivity.createIntent(this, perfume));
    }

    @Override
    public void onFavoriteClick(View view, PerfumeItem perfume, int position, boolean enabled) {
        if (enabled) {
            presenter.changeFavorite(FavoriteRequest.create(!perfume.favorited(), perfume.id()));
        }
    }

    @Override
    public void onWishlistClick(View view, PerfumeItem perfume, int position, boolean enabled) {
        if (enabled) {
            presenter.changeWishlisted(WishlistRequest.create(!perfume.wishlisted(), perfume.id()));
        }
    }

    @Override
    public void onOwnedClick(View view, PerfumeItem perfume, int position, boolean enabled) {
        if (enabled) {
            presenter.changeOwned(OwnedRequest.create(!perfume.owned(), perfume.id()));
        }
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_perfume;
    }

    @NonNull
    @Override
    protected String getLogTag() {
        return TAG;
    }

    @Override
    protected Unbinder bind() {
        return ButterKnife.bind(this);
    }

    @Override
    protected void init(Bundle savedInstanceState, Intent intent) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        adapter = new PerfumeAdapter(this, this, this, this, this);
        mrvSimilarPerfumes.setLayoutManager(new LinearLayoutManager(this));
        mrvSimilarPerfumes.setAdapter(adapter);

        if (intent != null) {
            String token = PreferencesUtil.getToken();
            PerfumeItem perfume = intent.getParcelableExtra(EXTRA_PERFUME_ITEM);

            if (perfume != null) {
                setTitle(getString(R.string.perfume_profile_title,
                        perfume.model(),
                        perfume.company()));

                presenter.getPerfumeProfile(PerfumeParams.create(token, perfume.id()));
            }
        }
    }

    @Override
    protected void injectDependencies(@NonNull AppComponent appComponent) {
        appComponent.plus(new PerfumeModule(this)).inject(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    void setGenderIcon(Gender gender) {
        switch (gender) {
            case MALE:
                ivGender.setImageResource(R.drawable.ic_gender_male_on);
                break;
            case FEMALE:
                ivGender.setImageResource(R.drawable.ic_gender_female_on);
                break;
            case UNISEX:
                ivGender.setImageResource(R.drawable.ic_gender_unisex_on);
                break;
        }
    }
}
