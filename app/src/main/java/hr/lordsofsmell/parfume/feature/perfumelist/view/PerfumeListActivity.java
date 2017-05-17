package hr.lordsofsmell.parfume.feature.perfumelist.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import co.infinum.mjolnirrecyclerview.MjolnirRecyclerView;
import hr.lordsofsmell.parfume.R;
import hr.lordsofsmell.parfume.dagger.components.AppComponent;
import hr.lordsofsmell.parfume.dagger.modules.PerfumeListModule;
import hr.lordsofsmell.parfume.domain.model.request.FavoriteRequest;
import hr.lordsofsmell.parfume.domain.model.request.OwnedRequest;
import hr.lordsofsmell.parfume.domain.model.request.WishlistRequest;
import hr.lordsofsmell.parfume.domain.model.response.PerfumeItem;
import hr.lordsofsmell.parfume.feature.core.OnScrollToBottomListener;
import hr.lordsofsmell.parfume.feature.core.adapter.PerfumeAdapter;
import hr.lordsofsmell.parfume.feature.core.view.ActivityView;
import hr.lordsofsmell.parfume.feature.login.view.LoginActivity;
import hr.lordsofsmell.parfume.feature.perfume.view.PerfumeActivity;
import hr.lordsofsmell.parfume.feature.perfumelist.IPerfumeList;
import hr.lordsofsmell.parfume.feature.register.view.RegisterActivity;
import hr.lordsofsmell.parfume.utils.PreferencesUtil;

public class PerfumeListActivity extends ActivityView
        implements IPerfumeList.View,
        NavigationView.OnNavigationItemSelectedListener,
        PerfumeAdapter.OnPerfumeImageClickListener,
        PerfumeAdapter.OnPerfumeFavoriteClickListener,
        PerfumeAdapter.OnPerfumeWishlistClickListener,
        PerfumeAdapter.OnPerfumeOwnedClickListener {

    private static final String TAG = "PerfumeList";

    public static final String EXTRA_LIST_TYPE = "list_type";
    public static final int LIST_TYPE_ERROR = -1;
    public static final int LIST_TYPE_ALL_PERFUMES = 0;
    public static final int LIST_TYPE_FAVORITES = 1;
    public static final int LIST_TYPE_WISHLIST = 2;
    public static final int LIST_TYPE_OWNED = 3;
    public static final int LIST_TYPE_RECOMMENDED = 4;

    public static final String EXTRA_ACTION = "action_extra";
    public static final int ACTION_ERROR = -1;
    public static final int ACTION_FAVORITE = 0;
    public static final int ACTION_WISHLIST = 1;
    public static final int ACTION_OWNED = 2;

    public static final String EXTRA_POSITION = "action_position";
    public static final int POSITION_ERROR = -1;

    public static final String EXTRA_SEARCH_COMPANY = "search_company";
    public static final String EXTRA_SEARCH_MODEL = "search_model";
    public static final String EXTRA_SEARCH_YEAR = "search_year";
    public static final String EXTRA_SEARCH_GENDERS = "search_genders";

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.drawer_layout) DrawerLayout drawer;
    @BindView(R.id.nav_view) NavigationView navigationView;

    @BindView(R.id.tv_company) TextView tvCompany;
    @BindView(R.id.tv_model) TextView tvModel;
    @BindView(R.id.tv_year) TextView tvYear;
    @BindView(R.id.tv_gender) TextView tvGender;
    @BindView(R.id.srl_perfumes_list) SwipeRefreshLayout srlPerfumesList;
    @BindView(R.id.mrv_perfumes_list) MjolnirRecyclerView mrvPerfumesList;
    @BindView(R.id.empty_view_perfume_list) View emptyView;
    @BindView(R.id.ll_search_options) LinearLayout llSearchOptions;

    @Inject
    IPerfumeList.Presenter presenter;

    private PerfumeAdapter adapter;

    public static Intent createIntent(Context context, int listType) {
        Intent intent = new Intent(context, PerfumeListActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        if (listType != LIST_TYPE_ERROR) {
            intent.putExtra(EXTRA_LIST_TYPE, listType);
        }
        return intent;
    }

    public static Intent createIntent(Context context, int listType, int action, int position) {
        Intent intent = createIntent(context, listType);
        if (action != ACTION_ERROR) {
            intent.putExtra(EXTRA_ACTION, action);
            intent.putExtra(EXTRA_POSITION, position);
        }
        return intent;
    }

    @Override
    public void logoutSuccesful() {
        PreferencesUtil.removeUser();

        // recreate activity
        Intent intent = getIntent();
        intent.removeExtra(EXTRA_LIST_TYPE);
        intent.removeExtra(EXTRA_ACTION);
        intent.removeExtra(EXTRA_POSITION);

        finish();
        startActivity(intent);
    }

    @Override
    public void addPerfumes(Collection<PerfumeItem> newPerfumes, boolean clearAdapter) {
        if (clearAdapter) {
            adapter.clear();
        }
        if (newPerfumes != null && !newPerfumes.isEmpty()) {
            adapter.addAll(newPerfumes);
        }

        doAction(getIntent());
    }

    @Override
    public void search(@Nullable String company,
                       @Nullable String model,
                       @Nullable String year,
                       @Nullable ArrayList<String> genders) {
        setParameterText(company, model, year, genders);

        Intent intent = getIntent();
        intent.putExtra(EXTRA_SEARCH_COMPANY, company);
        intent.putExtra(EXTRA_SEARCH_MODEL, model);
        intent.putExtra(EXTRA_SEARCH_YEAR, year);
        intent.putExtra(EXTRA_SEARCH_GENDERS, genders);
        intent.putStringArrayListExtra(EXTRA_SEARCH_GENDERS, genders);

        presenter.loadPerfumes(true, company, model, year, genders);
    }

    @Override
    public void setRefreshing(boolean refreshing) {
        srlPerfumesList.setRefreshing(refreshing);
    }

    @Override
    public boolean isRefreshing() {
        return srlPerfumesList.isRefreshing();
    }

    @Override
    public void favoriteChanged(long perfumeId, boolean isChecked) {
        int index = adapter.getIndexById(perfumeId);

        if (!isChecked && getListType(getIntent()) == LIST_TYPE_FAVORITES) {
            adapter.remove(index);
        } else {
            PerfumeItem perfume = adapter.get(index).withFavorited(isChecked);
            adapter.update(perfume);
        }
    }

    @Override
    public void wishlistedChanged(long perfumeId, boolean isChecked) {
        int index = adapter.getIndexById(perfumeId);

        if (!isChecked && getListType(getIntent()) == LIST_TYPE_WISHLIST) {
            adapter.remove(index);
        } else {
            PerfumeItem perfume = adapter.get(index).withWishlisted(isChecked);
            adapter.update(perfume);
        }
    }

    @Override
    public void ownedChanged(long perfumeId, boolean isChecked) {
        int index = adapter.getIndexById(perfumeId);

        if (!isChecked && getListType(getIntent()) == LIST_TYPE_OWNED) {
            adapter.remove(index);
        } else {
            PerfumeItem perfume = adapter.get(index).withOwned(isChecked);
            adapter.update(perfume);
        }
    }

    @Override
    public void onImageClick(View view, PerfumeItem perfume, int position) {
        hideLoading();
        startActivity(PerfumeActivity.createIntent(this, perfume));
    }

    @Override
    public void onFavoriteClick(View view, PerfumeItem perfume, int position, boolean enabled) {
        if (enabled) {
            presenter.changeFavorite(FavoriteRequest.create(!perfume.favorited(),
                    perfume.id()));
        } else {
            startActivity(LoginActivity.createIntent(this,
                    presenter.getListType(),
                    ACTION_FAVORITE,
                    position));
        }
    }

    @Override
    public void onWishlistClick(View view, PerfumeItem perfume, int position, boolean enabled) {
        if (enabled) {
            presenter.changeWishlisted(WishlistRequest.create(!perfume.wishlisted(),
                    perfume.id()));
        } else {
            startActivity(LoginActivity.createIntent(this,
                    presenter.getListType(),
                    ACTION_WISHLIST,
                    position));
        }
    }

    @Override
    public void onOwnedClick(View view, PerfumeItem perfume, int position, boolean enabled) {
        if (enabled) {
            presenter.changeOwned(OwnedRequest.create(!perfume.owned(),
                    perfume.id()));
        } else {
            startActivity(LoginActivity.createIntent(this,
                    presenter.getListType(),
                    ACTION_OWNED,
                    position));
        }
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_perfume_list;
    }

    @Override
    @NonNull
    protected String getLogTag() {
        return TAG;
    }

    @Override
    protected Unbinder bind() {
        return ButterKnife.bind(this);
    }

    @Override
    protected void init(Bundle savedInstanceState, final Intent intent) {
        setSupportActionBar(toolbar);
        setPresenter(presenter);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                drawer,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        mrvPerfumesList.setLayoutManager(new LinearLayoutManager(this));
        mrvPerfumesList.setEmptyView(emptyView);

        adapter = new PerfumeAdapter(this, this, this, this, this);
        mrvPerfumesList.setAdapter(adapter);
        mrvPerfumesList.addOnScrollListener(new OnScrollToBottomListener() {
            @Override
            public void onScrollToBottom(RecyclerView recyclerView, int dx, int dy) {
                if (intent != null) {
                    String company = intent.getStringExtra(EXTRA_SEARCH_COMPANY);
                    String model = intent.getStringExtra(EXTRA_SEARCH_MODEL);
                    String year = intent.getStringExtra(EXTRA_SEARCH_YEAR);
                    ArrayList<String> genders = intent.getStringArrayListExtra(EXTRA_SEARCH_GENDERS);

                    presenter.loadPerfumes(false, company, model, year, genders);
                } else {
                    presenter.loadPerfumes(false);
                }
            }
        });

        srlPerfumesList.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.i(TAG, "onRefresh called from SwipeRefreshLayout");
                presenter.loadPerfumes(true);
            }
        });

        int listType = getListType(intent);

        presenter.init(listType);
        setMenu(listType);
        setActivityTitle(listType);
        setSearchVisibility(listType);
        setParameterText(intent);

        presenter.loadPerfumes(false);
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
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_login) {
            if (PreferencesUtil.isLoggedIn()) {
                showLogoutDialog(R.string.logout_dialog_message_login,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                PreferencesUtil.removeUser();
                                startActivity(LoginActivity.createIntent(PerfumeListActivity.this,
                                        presenter.getListType()));
                            }
                        },
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
            } else {
                startActivity(LoginActivity.createIntent(this, presenter.getListType()));
            }
        } else if (id == R.id.nav_register) {
            if (PreferencesUtil.isLoggedIn()) {
                showLogoutDialog(R.string.register_dialog_message,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                PreferencesUtil.removeUser();
                                startActivity(RegisterActivity.createIntent(PerfumeListActivity.this,
                                        presenter.getListType()));
                            }
                        },
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
            } else {
                startActivity(RegisterActivity.createIntent(this, presenter.getListType()));
            }
        } else if (id == R.id.nav_all_perfumes) {
            startActivity(PerfumeListActivity.createIntent(this, LIST_TYPE_ALL_PERFUMES));
        } else if (id == R.id.nav_recommended) {
            if (PreferencesUtil.isLoggedIn()) {
                startActivity(PerfumeListActivity.createIntent(this, LIST_TYPE_RECOMMENDED));
            } else {
                startActivity(LoginActivity.createIntent(this, LIST_TYPE_RECOMMENDED));
            }
        } else if (id == R.id.nav_favorites) {
            if (PreferencesUtil.isLoggedIn()) {
                startActivity(PerfumeListActivity.createIntent(this, LIST_TYPE_FAVORITES));
            } else {
                startActivity(LoginActivity.createIntent(this, LIST_TYPE_FAVORITES));
            }
        } else if (id == R.id.nav_wishlist) {
            if (PreferencesUtil.isLoggedIn()) {
                startActivity(PerfumeListActivity.createIntent(this, LIST_TYPE_WISHLIST));
            } else {
                startActivity(LoginActivity.createIntent(this, LIST_TYPE_WISHLIST));
            }
        } else if (id == R.id.nav_owned) {
            if (PreferencesUtil.isLoggedIn()) {
                startActivity(PerfumeListActivity.createIntent(this, LIST_TYPE_OWNED));
            } else {
                startActivity(LoginActivity.createIntent(this, LIST_TYPE_OWNED));
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        if (!PreferencesUtil.isLoggedIn()) {
            MenuItem logoutMenuItem = menu.findItem(R.id.action_logout);
            logoutMenuItem.setVisible(false);
        }

        if (getListType(getIntent()) != LIST_TYPE_ALL_PERFUMES) {
            MenuItem searchMenuItem = menu.findItem(R.id.action_search);
            searchMenuItem.setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_logout) {
            showLogoutDialog(R.string.logout_dialog_message,
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            presenter.logout();
                        }
                    }, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
            return true;
        }
        if (id == R.id.action_search) {
            new SearchDialog().show(getSupportFragmentManager(), "search_dialog");
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private int getListType(@Nullable Intent intent) {
        int listType;

        if (intent == null) {
            listType = LIST_TYPE_ALL_PERFUMES;
        } else {
            listType = intent.getIntExtra(EXTRA_LIST_TYPE, LIST_TYPE_ALL_PERFUMES);
        }

        return listType;
    }

    private void showLogoutDialog(@StringRes int message,
                                  @NonNull DialogInterface.OnClickListener positiveListener,
                                  @NonNull DialogInterface.OnClickListener negativeListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.logout_dialog_title);
        builder.setMessage(message);
        builder.setPositiveButton(R.string.logout_ok_button, positiveListener);
        builder.setNegativeButton(R.string.logout_cancel_button, negativeListener);
        builder.show();
    }

    private void setMenu(int listType) {
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.inflateMenu(R.menu.all_perfumes_drawer);
        switch (listType) {
            case LIST_TYPE_ALL_PERFUMES:
                navigationView.getMenu().findItem(R.id.nav_all_perfumes).setVisible(false);
                break;
            case LIST_TYPE_FAVORITES:
                navigationView.getMenu().findItem(R.id.nav_favorites).setVisible(false);
                break;
            case LIST_TYPE_WISHLIST:
                navigationView.getMenu().findItem(R.id.nav_wishlist).setVisible(false);
                break;
            case LIST_TYPE_OWNED:
                navigationView.getMenu().findItem(R.id.nav_owned).setVisible(false);
                break;
            case LIST_TYPE_RECOMMENDED:
                navigationView.getMenu().findItem(R.id.nav_recommended).setVisible(false);
                break;
        }
    }

    private void setActivityTitle(int listType) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            switch (listType) {
                case LIST_TYPE_ALL_PERFUMES:
                    actionBar.setTitle(R.string.all_perfumes_title);
                    break;
                case LIST_TYPE_FAVORITES:
                    actionBar.setTitle(R.string.favorite_perfumes_title);
                    break;
                case LIST_TYPE_WISHLIST:
                    actionBar.setTitle(R.string.wishlist_perfumes_title);
                    break;
                case LIST_TYPE_OWNED:
                    actionBar.setTitle(R.string.owned_perfumes_title);
                    break;
                case LIST_TYPE_RECOMMENDED:
                    actionBar.setTitle(R.string.recommended_perfumes_title);
                    break;
            }
        }
    }

    private void setSearchVisibility(int listType) {
        if (listType == LIST_TYPE_ALL_PERFUMES) {
            llSearchOptions.setVisibility(View.VISIBLE);
        } else {
            llSearchOptions.setVisibility(View.GONE);
        }
    }

    private void doAction(@Nullable Intent intent) {
        if (intent != null) {
            int action = intent.getIntExtra(EXTRA_ACTION, ACTION_ERROR);
            int position = intent.getIntExtra(EXTRA_POSITION, POSITION_ERROR);

            if (action != ACTION_ERROR && position != POSITION_ERROR) {
                PerfumeItem perfume = adapter.get(position);
                switch (action) {
                    case ACTION_FAVORITE:
                        presenter.changeFavorite(FavoriteRequest.create(!perfume.favorited(),
                                perfume.id()));
                        break;
                    case ACTION_WISHLIST:
                        presenter.changeWishlisted(WishlistRequest.create(!perfume.wishlisted(),
                                perfume.id()));
                        break;
                    case ACTION_OWNED:
                        presenter.changeOwned(OwnedRequest.create(!perfume.owned(),
                                perfume.id()));
                        break;
                }
            }
        }
    }

    public void setParameterText(Intent intent) {
        String company = "";
        String model = "";
        String year = "";
        ArrayList<String> genders = null;

        Bundle extras = intent.getExtras();
        if (extras != null) {
            company = extras.getString(EXTRA_SEARCH_COMPANY, "");
            model = extras.getString(EXTRA_SEARCH_MODEL, "");
            year = extras.getString(EXTRA_SEARCH_YEAR, "");
            genders = extras.getStringArrayList(EXTRA_SEARCH_GENDERS);
        }

        setParameterText(company, model, year, genders);
    }

    public void setParameterText(@Nullable String company,
                                 @Nullable String model,
                                 @Nullable String year,
                                 @Nullable List<String> genders) {
        String notSpecifiedText = getString(R.string.search_dialog_not_specified);
        String allGenders = getString(R.string.search_dialog_all_genders);

        String genderString = join(genders, ", ");

        tvCompany.setText(TextUtils.isEmpty(company) ? notSpecifiedText : company);
        tvModel.setText(TextUtils.isEmpty(model) ? notSpecifiedText : model);
        tvYear.setText(TextUtils.isEmpty(year) ? notSpecifiedText : year);
        tvGender.setText(TextUtils.isEmpty(genderString) ? allGenders : genderString);
    }

    private static String join(@Nullable List<String> strings, @Nullable String separator) {
        String output = null;

        if (strings != null && !strings.isEmpty()) {
            StringBuilder sb = new StringBuilder(strings.size() * 5);

            sb.append(" ");
            for (int i = 0, size = strings.size(); i < size; i++) {
                String str = strings.get(i);
                sb.append(str);
                if (separator != null && i < size - 1) {
                    sb.append(separator);
                }
            }

            output = sb.toString();
        }

        return output;
    }
}
