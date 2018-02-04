package com.mayhsupaing.news.activities;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ShareCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.Toast;

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
import com.mayhsupaing.news.dialogs.AddCommentDialog;
import com.mayhsupaing.news.dialogs.LikeUsersDialog;
import com.mayhsupaing.news.events.LoadedNewsEvent;
import com.mayhsupaing.news.network.HttpUrlConnectionDataAgent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import com.mayhsupaing.news.viewpods.AccountControlViewPod;



public class MainActivity extends BaseActivity implements NewsActionDelegate, BeforeLoginDelegate,
        LogInUserDelegate, RegisterUserDelegate {


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

    @BindView(R.id.vp_empty_view)
    RelativeLayout emptyViewPod;

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    private NewsAdapter mNewsAdapter;

 /*   private BeforeLogInUserViewPod vpBeforeLogInDelegate;*/

    private AccountControlViewPod vpAccountControl;

    private ProgressDialog mProgressDialog;

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

        vpAccountControl = (AccountControlViewPod) navigationView.getHeaderView(0);
        vpAccountControl.setDelegate((BeforeLoginDelegate) this);
        vpAccountControl.setDelegate((LogInUserDelegate) this);
        vpAccountControl.setDelegate((RegisterUserDelegate) this);

        //pull to refresh
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                NewsModel.getsObjInstance().loadNews();
            }
        });

        //soft gesture
        swipeRefreshLayout.setRefreshing(true);
        //call the method of singleton pattern
        NewsModel.getsObjInstance().loadNews();

        //hard gesture
        mProgressDialog=new ProgressDialog(this);
        mProgressDialog.setMessage("Please wait while data is loading");
        mProgressDialog.show();

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


    //Implicit Intent
    //Phone Call action
    @OnClick(R.id.fab)
    public void onTapFab(View view) {
       /* String numberToCall = "+959765640635"; //String data
        CallToNumber(numberToCall);
*/
        /*Snackbar.make(view, "Replace with your own action - ButterKnife", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();*/

        showConfirmDialog();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100) {
            //request call phone permission
            //length>0 user make at least one permission
            //grantResults[0] i.e only one permission

            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                String numberToCall = "+959765640635"; //String data
                CallToNumber(numberToCall);
            }
        }
    }

    private void CallToNumber(String numberToCall) {

        Uri numberToCallUri = Uri.parse("tel:" + numberToCall); //change as Uri.Unit resource identifier. protocol-tel
        Intent intentToCall = new Intent(Intent.ACTION_CALL, numberToCallUri);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.


            //Request permission
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    100);
            return;
        }
        startActivity(intentToCall);
    }

    private void showConfirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmation")
                .setCancelable(false)
                .setMessage(getResources().getString(R.string.msg_to_exit,
                        LogInUserModel.getsObjInstance(getApplicationContext()).getLoginUser().getName()))
                .setPositiveButton("Sure", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Snackbar.make(rvNews, "OK, Your will exit in hour", BaseTransientBottomBar.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "This is the right choice", Toast.LENGTH_SHORT).show();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onTapNewsItem(NewsVO tappedNews) {
        Intent intent = new Intent(getApplicationContext(), NewsDetailsActivity.class);
        intent.putExtra("news_id", tappedNews.getNewsId());
        startActivity(intent);
    }

    @Override
    public void onTapCommentButton() {
        AddCommentDialog dialog=new AddCommentDialog(this);
        dialog.show();
    }

    //Implicit intent
    //Shared Action
    @Override
    public void onTapSendToButton(NewsVO news) {
        /*Intent intent=new Intent(Intent.ACTION_SEND); */

        //how do you want to shared your content.
        Intent shareIntent = ShareCompat
                .IntentBuilder
                .from(this)
                .setType("text/plain")
                .setText(news.getBrief())
                .getIntent();

        //check if there is any app to handle. !=null at least there is one app.
        if (shareIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(shareIntent);
        } else {
            Snackbar.make(rvNews, "No app to handle shared action", BaseTransientBottomBar.LENGTH_INDEFINITE);
        }

    }

    @Override
    public void onTapFavouriteButton() {

    }

    @Override
    public void onTapLikeUser(NewsVO tappedNews) {
        LikeUsersDialog dialog=new LikeUsersDialog(this,tappedNews.getFavourites());

        dialog.show();
    }

    @Override
    public void onTapCommentUser(NewsVO tappedNews) {

    }

    @Override
    public void onTapSendToUser(NewsVO tappedNews) {

    }

    //get the data from network using eventbus.
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onNewsLoaded(LoadedNewsEvent event) {
        Log.d(MMNewsApp.LOG_TAG, "mmNewsLoaded" + event.getNewsList().size());

        swipeRefreshLayout.setRefreshing(false);
        mProgressDialog.dismiss(); //hard gesture

        if (!event.getNewsList().isEmpty()) {
            mNewsAdapter.setNews(event.getNewsList());
            emptyViewPod.setVisibility(View.GONE);
        } else {
            emptyViewPod.setVisibility(View.VISIBLE);
        }
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
    public void onTapLogOut() {
        LogInUserModel.getsObjInstance(getApplicationContext()).logOut();
    }

    @Override
    public void onTapLoginUser() {
        Intent intent=UserProfileActivity.newIntent(getApplicationContext());
        startActivity(intent);
    }

    public void onTapRegisterLogOut() {
        RegisterUserModel.getsObjInstance().logOut();
    }


}
