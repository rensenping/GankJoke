package com.rsp.gankjoke.ui;


import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.rsp.gankjoke.App;
import com.rsp.gankjoke.R;
import com.rsp.gankjoke.di.component.DaggerMainComponent;
import com.rsp.gankjoke.entity.gank.GankItem;
import com.rsp.gankjoke.presenent.MainPresenent;
import com.rsp.gankjoke.presenent.contract.MainPresenentContract;
import com.rsp.gankjoke.ui.base.BaseActivity;
import com.rsp.gankjoke.ui.base.BaseFragment;
import com.rsp.gankjoke.ui.gank.fragment.GankMainFragment;
import com.rsp.gankjoke.ui.joke.fragment.JokeMainFragment;
import com.rsp.gankjoke.ui.news.fragment.NewsMainFragment;

import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity<MainPresenent> implements MainPresenentContract.View {
    ActionBarDrawerToggle mDrawerToggle;
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.content)
    FrameLayout content;
    @BindView(R.id.navigation)
    NavigationView navigation;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    private GankMainFragment gankMainFragment;
    private NewsMainFragment newsMainFragment;
    private JokeMainFragment jokeMainFragment;
    private MenuItem lastMenuItem;
    private BaseFragment currentFragment;
    private ImageView drawer_img;


    @Override
    protected void initEvent() {
        setToolBar(toolBar,"干货");
        mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolBar, R.string.open, R.string.close);
        mDrawerToggle.syncState();
        drawerLayout.addDrawerListener(mDrawerToggle);
        View headerView = navigation.getHeaderView(0);
        drawer_img = (ImageView) headerView.findViewById(R.id.drawer_img);
        lastMenuItem = navigation.getMenu().findItem(R.id.drawer_gank);
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected( MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.drawer_gank:
                        if (currentFragment != gankMainFragment) {
                           switchFragment(gankMainFragment);
                        }
                        break;
                    case R.id.drawer_news:
                        if (currentFragment != newsMainFragment) {
                            switchFragment(newsMainFragment);
                        }
                        break;
                    case R.id.drawer_joke:
                        if (currentFragment != jokeMainFragment) {
                            switchFragment(jokeMainFragment);
                        }
                        break;
                }

                if (lastMenuItem != null) {
                    lastMenuItem.setChecked(false);
                }
                lastMenuItem = item;
                drawerLayout.closeDrawers();
                return true;
            }
        });
        gankMainFragment = new GankMainFragment();
        newsMainFragment = new NewsMainFragment();
        jokeMainFragment = new JokeMainFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.content, gankMainFragment).commit();
        currentFragment = gankMainFragment;
        mPresenent.loadingMeizi(1,1);
    }

    private void switchFragment(BaseFragment baseFragment) {
        if (baseFragment.isAdded()) {
            getSupportFragmentManager().beginTransaction().hide(currentFragment).show(baseFragment).commit();
        } else {
            getSupportFragmentManager().beginTransaction().hide(currentFragment).add(R.id.content,baseFragment).commit();
        }
        currentFragment = baseFragment;
        toolBar.setTitle(currentFragment.getTitle());
    }

    @Override
    protected void inject() {
        DaggerMainComponent.builder().appComponent(App.getAppComponent()).build().inject(this);
    }

    @Override
    public void startLoading() {

    }

    @Override
    public void endLoading() {

    }

    @Override
    public void loadingError(String msg) {

    }

    @Override
    protected int getLayouId() {
        return R.layout.activity_main;
    }

    @Override
    public void startLoadingMeizi(List<GankItem> items) {
        Glide.with(this).load(items.get(0).url).into(drawer_img);
    }
}
