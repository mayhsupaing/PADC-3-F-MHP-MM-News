package com.mayhsupaing.news.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.SpeechRecognizer;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;


import com.mayhsupaing.news.R;
import com.mayhsupaing.news.adapters.NewsByCategoryAdapter;
import com.mayhsupaing.news.adapters.SportNewsAdapter;
import com.mayhsupaing.news.fragments.InternationalNewsFragment;
import com.mayhsupaing.news.fragments.NewsByCategoryFragment;
import com.mayhsupaing.news.fragments.SportNewsFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Lenovo on 1/7/2018.
 */

public class NewsByCategoryActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.vp_news_by_category)
    ViewPager vpNewsByCategory;

    @BindView(R.id.tb_news_by_category)
    TabLayout tbNewsByCategory;

    private NewsByCategoryAdapter mNewsByCategoryAdapter;

    //static factory method.
    public static Intent newIntent(Context context){
        Intent intent=new Intent(context,NewsByCategoryActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_by_category);

        ButterKnife.bind(this,this);

        setSupportActionBar(toolbar);

        //Back Button
        if(getSupportActionBar()!=null) {
            getSupportActionBar().setTitle(R.string.title_news_by_category);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_24dp);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        mNewsByCategoryAdapter=new NewsByCategoryAdapter(getSupportFragmentManager());
        vpNewsByCategory.setAdapter(mNewsByCategoryAdapter);


        mNewsByCategoryAdapter.addTab("Local News",new NewsByCategoryFragment());
        mNewsByCategoryAdapter.addTab("International News",new InternationalNewsFragment());
        mNewsByCategoryAdapter.addTab("Sports News",new SportNewsFragment());

        tbNewsByCategory.setupWithViewPager(vpNewsByCategory);

        vpNewsByCategory.setOffscreenPageLimit(mNewsByCategoryAdapter.getCount());

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home)// checked if we clicked home button
        {
            onBackPressed(); //call previous screen
        }
        return super.onOptionsItemSelected(item);
    }
}
