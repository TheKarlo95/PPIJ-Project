package hr.lordsofsmell.parfume.feature.perfumelist.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import java.util.Collection;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import co.infinum.mjolnirrecyclerview.MjolnirRecyclerView;
import hr.lordsofsmell.parfume.R;
import hr.lordsofsmell.parfume.dagger.components.AppComponent;
import hr.lordsofsmell.parfume.dagger.modules.PerfumeListModule;
import hr.lordsofsmell.parfume.domain.model.request.LikedRequest;
import hr.lordsofsmell.parfume.domain.model.request.OwnedRequest;
import hr.lordsofsmell.parfume.domain.model.request.WishlistRequest;
import hr.lordsofsmell.parfume.domain.model.response.PerfumeItem;
import hr.lordsofsmell.parfume.feature.core.adapter.PerfumeAdapter;
import hr.lordsofsmell.parfume.feature.core.view.ActivityView;
import hr.lordsofsmell.parfume.feature.perfumelist.IPerfumeList;
import hr.lordsofsmell.parfume.feature.perfumelist.presenter.PerfumeListPresenter;

public class PerfumeListActivity extends ActivityView
        implements IPerfumeList.View,
        NavigationView.OnNavigationItemSelectedListener,
        PerfumeAdapter.OnPerfumeLikeClickListener,
        PerfumeAdapter.OnPerfumeWishlistClickListener,
        PerfumeAdapter.OnPerfumeOwnedClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @BindView(R.id.mrv_perfumes_list)
    MjolnirRecyclerView mrvPerfumesList;
    @BindView(R.id.empty_view_perfume_list)
    View emptyView;

    @Inject
    IPerfumeList.Presenter presenter;

    private PerfumeAdapter adapter;

    @Override
    public void addPerfumes(Collection<PerfumeItem> newPerfumes) {
        adapter.addAll(newPerfumes);
    }

    @Override
    public void likeChanged(Long parfumeId) {

    }

    @Override
    public void wishlistedChanged(Long parfumeId) {

    }

    @Override
    public void ownedChanged(Long parfumeId) {

    }

    @Override
    public void onLikeClick(View view, PerfumeItem perfume, int position) {
        presenter.changeLiked(LikedRequest.create(perfume.liked(), perfume.id()));
    }

    @Override
    public void onWishlistClick(View view, PerfumeItem perfume, int position) {
        presenter.changeWishlisted(WishlistRequest.create(perfume.liked(), perfume.id()));
    }

    @Override
    public void onOwnedClick(View view, PerfumeItem perfume, int position) {
        presenter.changeOwned(OwnedRequest.create(perfume.liked(), perfume.id()));
    }

//    void updateList() {
//        List<Item> newList = new ArrayList<>();
//        newList.add(new Item(1, "Car"));
//        newList.add(new Item(2, "Plane"));
//        newList.add(new Item(3, UUID.randomUUID().toString()));
//
//        adapter.update(newList, new ItemsDiffUtil(items, newList));
//    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_perfume_list;
    }

    @Override
    protected Unbinder bind() {
        return ButterKnife.bind(this);
    }

    @Override
    protected void init(Bundle savedInstanceState, Intent intent) {
        setSupportActionBar(toolbar);
        setPresenter(presenter);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        mrvPerfumesList.setLayoutManager(new LinearLayoutManager(this));
        mrvPerfumesList.setEmptyView(emptyView);

        adapter = new PerfumeAdapter(this, this, this, this);
        mrvPerfumesList.setAdapter(adapter);

        presenter.loadPerfumes(PerfumeListPresenter.ALL_PERFUMES_LIST);
    }

    @Override
    protected void injectDependencies(@NonNull AppComponent appComponent) {
        appComponent.plus(new PerfumeListModule(this)).inject(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        adapter.cancel();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
