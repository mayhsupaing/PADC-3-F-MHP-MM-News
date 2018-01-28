package com.mayhsupaing.news.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.mayhsupaing.news.MMNewsApp;
import com.mayhsupaing.news.R;
import com.mayhsupaing.news.adapters.NewsAdapter;
import com.mayhsupaing.news.data.models.LogInUserModel;
import com.mayhsupaing.news.data.models.NewsModel;
import com.mayhsupaing.news.data.models.RegisterUserModel;
import com.mayhsupaing.news.data.vo.NewsVO;
import com.mayhsupaing.news.delegates.BeforeLoginDelegate;
import com.mayhsupaing.news.delegates.LogInUserDelegate;
import com.mayhsupaing.news.delegates.NewsActionDelegate;
import com.mayhsupaing.news.delegates.RegisterUserDelegate;
import com.mayhsupaing.news.events.LoadedNewsEvent;
import com.mayhsupaing.news.network.HttpUrlConnectionDataAgent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.mayhsupaing.news.viewpods.AccountControlViewPod;


public class MainActivity extends AppCompatActivity implements NewsActionDelegate, BeforeLoginDelegate,
        LogInUserDelegate,RegisterUserDelegate{


    @BindView(R.id.rv_news)
    RecyclerView rvNews;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    @BindView(R.id.navigation_view)
    NavigationView navigationView;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    private NewsAdapter mNewsAdapter;

 /*   private BeforeLogInUserViewPod vpBeforeLogInDelegate;*/

    private AccountControlViewPod vpAccountControl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this, this);

        setSupportActionBar(toolbar);

        mNewsAdapter = new NewsAdapter(this);

        LinearLayoutManager LinearLayoutManager = new LinearLayoutManager(getApplicationContext(),
                android.support.v7.widget.LinearLayoutManager.VERTICAL, false);
        rvNews.setLayoutManager(LinearLayoutManager);
        rvNews.setAdapter(mNewsAdapter);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.title_all_news);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() == R.id.menu_news_by_category) {
                    Intent intent = NewsByCategoryActivity.newIntent(getApplicationContext());
                    startActivity(intent);

                    drawerLayout.closeDrawer(GravityCompat.START); //close drawer
                }


                return false;
            }
        });

        vpAccountControl= (AccountControlViewPod) navigationView.getHeaderView(0);
        vpAccountControl.setDelegate((BeforeLoginDelegate)this);
        vpAccountControl.setDelegate((LogInUserDelegate) this);
        vpAccountControl.setDelegate((RegisterUserDelegate) this);

        //call the method of singleton pattern
        NewsModel.getsObjInstance().loadNews();

        HttpUrlConnectionDataAgent.getsObjInstance().loadNews();

       /* GridLayoutManager gridLayoutManager=new GridLayoutManager(getApplication(),2,rvNews.setLayoutManager());
        rvNews.setAdapter(mNewsAdapter);*/

    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START);
        }

        return super.onOptionsItemSelected(item);
    }


    @OnClick(R.id.fab)
    public void onTapFab(View view) {
        Snackbar.make(view, "Replace with your own action - ButterKnife", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }


    @Override
    public void onTapNewsItem(NewsVO tappedNews) {
        Intent intent = new Intent(getApplicationContext(), NewsDetailsActivity.class);
        intent.putExtra("news_id", tappedNews.getNewsId());
        startActivity(intent);
    }

    @Override
    public void onTapCommentButton() {

    }

    @Override
    public void onTapSendToButton() {

    }

    @Override
    public void onTapFavouriteButton() {

    }

    //get the data from network using eventbus.
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onNewsLoaded(LoadedNewsEvent event) {
        Log.d(MMNewsApp.LOG_TAG, "mmNewsLoaded" + event.getNewsList().size());
        mNewsAdapter.setNews(event.getNewsList());
    }


    @Override
    public void onTapToLogin() {
        Intent intent = AccountControlActivity.newIntentLogIn(getApplicationContext());
        startActivity(intent);
    }

    @Override
    public void onTapToRegister() {
        Intent intent = AccountControlActivity.newIntentRegister(getApplicationContext());
        startActivity(intent);
    }

    @Override
    public void onTapLogOut()
    {
        LogInUserModel.getsObjInstance(getApplicationContext()).logOut();
    }

    public void onTapRegisterLogOut(){
        RegisterUserModel.getsObjInstance().logOut();
    }


}
