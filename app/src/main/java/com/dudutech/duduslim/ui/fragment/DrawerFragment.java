package com.dudutech.duduslim.ui.fragment;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.dudutech.duduslim.R;
import com.dudutech.duduslim.ui.MainActivity;

import butterknife.ButterKnife;


/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 *
 */
public class DrawerFragment extends BaseFragment {




    private MainActivity mActivity;
    private RelativeLayout []  menus;

    public DrawerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mActivity = (MainActivity) getActivity();
        View contentView = inflater.inflate(R.layout.fragment_drawer, null);

        menus=new RelativeLayout[5];

        menus[0]= (RelativeLayout)contentView . findViewById(R.id.rl_main);
        menus[1]= (RelativeLayout)contentView.findViewById(R.id.rl_record);
        menus[2]= (RelativeLayout)contentView.findViewById(R.id.rl_daily);
        menus[3]= (RelativeLayout) contentView.findViewById(R.id.rl_train);
        menus[4]= (RelativeLayout) contentView.findViewById(R.id.rl_tips);

        for (int i=0;i<menus.length;i++)
        {
            menus[i].setOnClickListener(new MenuOnclickListenner());
        }
        menus[0].performClick();

        return contentView;
    }

    public class MenuOnclickListenner implements View.OnClickListener{
        @Override
        public void onClick(View view) {

            Fragment fragment=null;
            FragmentManager fragmentManager = getFragmentManager();
            switch (view.getId())
            {
                case R.id.rl_main:
                    fragment   = (MainFragment) fragmentManager.findFragmentByTag("1");

                    if(fragment == null)
                    {
                        fragment = new MainFragment();
                    }
                    fragmentManager.beginTransaction().replace(R.id.fl_content, fragment, "1").addToBackStack(null).commit();
                    mActivity.setTitle("嘟嘟减肥");

                    break;
                case R.id.rl_record :
                    fragment   = (RecordFragment) fragmentManager .findFragmentByTag("2");

                    if(fragment == null)
                    {
                        fragment = new RecordFragment();
                    }
                    fragmentManager.beginTransaction().replace(R.id.fl_content,fragment, "2").addToBackStack(null).commit();
                    mActivity.setTitle("体重记录");
                    break;
                case R.id.rl_daily :

                    fragment   = (DailytFragment) fragmentManager.findFragmentByTag("3");

                    if(fragment == null)
                    {
                        fragment = new DailytFragment();
                    }
                    fragmentManager .beginTransaction().replace(R.id.fl_content,fragment, "3").addToBackStack(null).commit();
                    mActivity.setTitle("减肥日记");


                    break;

                case R.id.rl_train :
                    fragment   = (TrainFragment) fragmentManager   .findFragmentByTag("4");

                    if(fragment == null)
                    {
                        fragment = new TrainFragment();
                    }
                    fragmentManager.beginTransaction().replace(R.id.fl_content, fragment, "4").addToBackStack(null).commit();

                    mActivity. setTitle("训练计划");


                    break;
                case R.id.rl_tips :
                    fragment   = (TipsFragment) fragmentManager   .findFragmentByTag("5");

                    if(fragment == null)
                    {
                        fragment = new TipsFragment();
                    }
                    fragmentManager.beginTransaction().replace(R.id.fl_content, fragment, "5").addToBackStack(null).commit();

                    mActivity. setTitle("小贴士");

                    break;


                default:

            }


           mActivity. mDrawerLayout.closeDrawer(GravityCompat.START);
            toggle(view.getId());

        }


    }

    public void toggle(int viewId) {
        for (RelativeLayout menu : menus) {
            if (menu.getId() != viewId) {
                //    menu.setBackground(getResources().getDrawable(R.drawable.bg_menu));
                menu.setBackgroundResource(R.drawable.bg_menu);
            }
            else
                // menu.setBackground(getResources().getDrawable(R.drawable.bg_menu_hover));
                menu.setBackgroundResource(R.drawable.bg_menu_hover);
        }
    }




}
