package com.dudutech.duduslim.ui;


import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.KeyEvent;
import android.widget.Toast;


import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;
import com.dudutech.duduslim.R;
import com.dudutech.duduslim.ui.fragment.BaseFragment;
import com.dudutech.duduslim.ui.fragment.DrawerFragment;
import com.sherlock.navigationdrawer.compat.SherlockActionBarDrawerToggle;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends SherlockFragmentActivity {



    private SherlockActionBarDrawerToggle mDrawerToggle;
    @InjectView(R.id.drawer_layout)
   public DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        initActionBar();
        mDrawerToggle = new SherlockActionBarDrawerToggle(this, mDrawerLayout, R.drawable.ic_drawer, R.string.drawer_open, R.string.drawer_close);
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        replaceFragment(R.id.left_drawer,new DrawerFragment());

    }

    private void initActionBar() {

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected( item)) {
            return true;
        }
//        switch (item.getItemId()) {
//            case R.id.action_refresh:
//                mContentFragment.loadFirstAndScrollToTop();
//                return true;
//            case R.id.action_settings:
//                startActivity(new Intent(this, PreferenceActivity.class));
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
        return super.onOptionsItemSelected(item);
    }


    private long exitTime = 0;

    /***
     *
     *
     * 按两次返回键 退出程序
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {

            exitApp();

            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    private void exitApp() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            //图片缓存

            finish();
//			System.exit(0);
        }
    }


    protected void replaceFragment(int viewId, BaseFragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(viewId, fragment).commit();
    }
}
