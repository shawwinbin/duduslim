package com.dudutech.duduslim.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import com.dudutech.duduslim.R;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;


/**    
 *     
 * 项目名称：duduslim
 * 类名称：BaseSwipeBackActivity    
 * 类描述：    活动返回ACTIVity基类
 * 创建人：shaw  
 * 创建时间：2013-10-11 下午2:26:07    
 * 修改人：shaw  
 * 修改时间：2013-10-11 下午2:26:07    
 * 修改备注：    
 * @version     
 *     
 */
@SuppressLint("NewApi")
public class BaseSwipeBackActivity extends SwipeBackActivity {

	//滑动返回
	private SwipeBackLayout mSwipeBackLayout;
	private ImageButton btn_back;
	private TextView tv_title;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//设置滑动返回	
				mSwipeBackLayout = getSwipeBackLayout();
			//边缘滑动返回	
		         mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
		         
		        mSwipeBackLayout.setSwipeListener(new SwipeBackLayout.SwipeListener() {
	            @Override
	            public void onScrollStateChange(int state, float scrollPercent) {
	
	            }
	
	            @Override
	            public void onEdgeTouch(int edgeFlag) {
	              //  vibrate(VIBRATE_DURATION);
	            }
	
	            @Override
	            public void onScrollOverThreshold() {
	               // vibrate(VIBRATE_DURATION);
	            }
	        });
		        //init actionbar
//		       getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
//
//				getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.toolbar_bg));


//                getSupportActionBar().setCustomView(R.layout.abs_layout);
               // getSupportActionBar().setIcon(R.drawable.btn_back_selector);

//                getSupportActionBar().setDisplayShowHomeEnabled(true);
//
//                getSupportActionBar().setIcon(null);
//
//
//               getSupportActionBar().setDisplayHomeAsUpEnabled(true);



//				btn_back=(ImageButton) getSupportActionBar().getCustomView().findViewById(R.id.btn_back);
//				tv_title=(TextView) getSupportActionBar().getCustomView().findViewById(R.id.tv_title);
//				btn_back.setVisibility(View.VISIBLE);
//				btn_back.setOnClickListener(new OnClickListener() {
//
//					@Override
//					public void onClick(View v) {
//						// TODO Auto-generated method stub
//					//	finish();
//						onBackPressed();
//
//					}
//				});
				
				//友盟页面统计如果，统计activity
			//	MobclickAgent.openActivityDurationTrack(true);
		
	}


//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        switch (item.getItemId()) {
//            case R.id.homeAsUp:
//
//               onBackPressed();
//
//
//
//                return true;
//            default:
//        return super.onOptionsItemSelected(item);
//    }
//    }

    protected void setBarTitle(int id) {
	tv_title.setText(id);	
	}
	protected void setBarTitle(String title) {	
		tv_title.setText(title);	
		}


	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
//		MobclickAgent.onResume(this);
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
//		MobclickAgent.onPause(this);
		
	}


}
