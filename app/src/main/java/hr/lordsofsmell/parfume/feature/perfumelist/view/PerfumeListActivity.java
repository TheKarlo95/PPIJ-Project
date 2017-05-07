package hr.lordsofsmell.parfume.feature.perfumelist.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import hr.lordsofsmell.parfume.R;
import hr.lordsofsmell.parfume.dagger.components.AppComponent;
import hr.lordsofsmell.parfume.dagger.modules.PerfumeListModule;
import hr.lordsofsmell.parfume.domain.model.response.PerfumeItem;
import hr.lordsofsmell.parfume.feature.core.view.ActivityView;
import hr.lordsofsmell.parfume.feature.perfumelist.IPerfumeList;
import hr.lordsofsmell.parfume.feature.perfumelist.presenter.PerfumeListPresenter;

public class PerfumeListActivity extends ActivityView
        implements IPerfumeList.View,
        NavigationView.OnNavigationItemSelectedListener {

    private static final String LIST_TYPE_KEY = "list_type";

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @Inject
    IPerfumeList.Presenter presenter;
    private List<PerfumeItem> perfumes;

    public static Intent createIntent(Context context, int listType) {
        Intent intent = new Intent(context, PerfumeListActivity.class);

        intent.putExtra(LIST_TYPE_KEY, listType);

        return intent;
    }

    @Override
    public void addPerfumes(Collection<PerfumeItem> newPerfumes) {
        if (newPerfumes != null) {
            if (this.perfumes == null) {
                this.perfumes = new ArrayList<>(newPerfumes);
            } else {
                this.perfumes.addAll(newPerfumes);
            }
        }
    }

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
    }

    @Override
    protected void injectDependencies(@NonNull AppComponent appComponent) {
        appComponent.plus(new PerfumeListModule(this, getApplicationContext())).inject(this);
    }

    @Override
    public void onBackPressed() {
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
            Intent intent = PerfumeListActivity.createIntent(this, PerfumeListPresenter.LIKED_PERFUMES_LIST);
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
