package com.dudutech.duduslim.view;

import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;

import com.dudutech.duduslim.utils.MyUtils;

public class ArcsProgress extends View {

	private Paint mArcPaint;
	private Paint mArcBGPaint;
	private Paint mTextBigPaint;
	private Paint mTextSmallPaint;

	private float mCurrAngle=90 ;
	private RectF mOval;
	private float mSweep = 20;
	private int mSpeedMax = 200;
	private int mThreshold = 100;
	private int mIncSpeedValue = 80;
	private int mCurrentSpeedValue = 20;
	private float mCenterX;
	private float mCenterY;
	private float mSpeedArcWidth=30;
	private final int TEXT_COLOR = Color.GREEN;
	private final float SPEED_VALUE_INC = 2;
	
	private int value;
	
	
	
	private String mCenterbigStr;
	private float mBigTextHeight;
	private float mBigTextWidth;
	private float mSmallTextSize;
	private String mCenterSmallStr;
	private float mSmallTextHeight;
	private float mSmallTextWidth;
	private float mBigTextSize;
	
	private float textMagin = MyUtils.dip2px(getContext(), 20);;;
	

	public ArcsProgress(Context context) {
		super(context,null);
		// TODO Auto-generated constructor stub
		mArcPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		
	}

	public ArcsProgress(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public ArcsProgress(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub

		
		  mCenterbigStr=String.valueOf(value);
		  mCenterSmallStr="";
		
		mArcPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mArcPaint.setStyle(Paint.Style.STROKE);
		mArcPaint.setStrokeWidth(mSpeedArcWidth);
		// mPaint.setStrokeCap(Paint.Cap.ROUND);
		  mArcPaint.setColor(Color.parseColor("#80fe80"));
//		mArcPaint.setColor(Color.BLUE);
		
		BlurMaskFilter mBlur = new BlurMaskFilter(8, BlurMaskFilter.Blur.INNER);
		mArcPaint.setMaskFilter(mBlur);

		mArcBGPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mArcBGPaint.setStyle(Paint.Style.STROKE);
		mArcBGPaint.setStrokeWidth(mSpeedArcWidth + 8);
//		mArcBGPaint.setColor(Color.RED);
		   mArcBGPaint.setColor(Color.parseColor("#667177"));

		BlurMaskFilter mBGBlur = new BlurMaskFilter(8,
				BlurMaskFilter.Blur.INNER);
		mArcBGPaint.setMaskFilter(mBGBlur);
		
		//
		mTextBigPaint=new Paint();
		mTextBigPaint.setAntiAlias(true);
		//mTextBigPaint.setTextSize(200);
		mTextBigPaint.setTextAlign(Paint.Align.CENTER);
		mTextBigPaint.setStyle(Paint.Style.FILL);
		mTextBigPaint.setColor(TEXT_COLOR);
	
		mTextSmallPaint=new Paint();
		mTextSmallPaint.setAntiAlias(true);
		//mTextBigPaint.setTextSize(200);
		mTextSmallPaint.setTextAlign(Paint.Align.CENTER);
		mTextSmallPaint.setStyle(Paint.Style.FILL);
		mTextSmallPaint.setColor(TEXT_COLOR);
		
	}

	@Override
	protected void onSizeChanged(int w, int h, int ow, int oh) {
		super.onSizeChanged(w, h, ow, oh);
		Log.i("onSizeChanged w", w + "");
		Log.i("onSizeChanged h", h + "");
		
		mCenterX = w * 0.5f; // remember the center of the screen
		mCenterY =h* 0.5f;
		
		mBigTextSize=w/5*2;
		
		mOval = new RectF(0+mSpeedArcWidth, 0+mSpeedArcWidth,w-mSpeedArcWidth
				,  h-mSpeedArcWidth);
		
		 Rect r = new Rect();
		 mTextBigPaint.setTextSize(mBigTextSize);
		 mTextBigPaint.getTextBounds(mCenterbigStr,0,mCenterbigStr.length(),r);
		 mBigTextHeight=r.height();
		 mBigTextWidth=r.width();
		 
		 
		 mSmallTextSize=mBigTextSize/5*2;
		 Rect r2 = new Rect();
		 mTextSmallPaint.setTextSize( mSmallTextSize);
		 mTextSmallPaint.getTextBounds(mCenterSmallStr, 0, mCenterSmallStr.length(), r2);
		 mSmallTextHeight=r2.height();
		 mSmallTextWidth=r2.width();
		 
		 
		 
//		   RectF oval=new RectF();                     //RectF对象  
//		    oval.left=100;                              //左边  
//		    oval.top=100;                                   //上边  
//		    oval.right=400;                             //右边  
//		    oval.bottom=400; 
//		    
//		    mOval=oval;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		drawSpeed(canvas);
		calcSpeed();
	}

	private void drawSpeed(Canvas canvas) {
		canvas.drawArc(mOval, 135, 270, false, mArcBGPaint);

		mSweep = (float) mIncSpeedValue / mSpeedMax * 180;
//		if (mIncSpeedValue > mThreshold) {
//			mArcPaint.setColor(0xFFFF0000);
//		} else {
//			mArcPaint.setColor(0xFF00B0F0);
//		}

		float percent=(float)value/100;
		mCurrAngle=percent*270;
		canvas.drawArc(mOval, 135, mCurrAngle, false, mArcPaint);
		
		canvas.drawText(mCenterbigStr, mCenterX-mSmallTextWidth/2, mCenterY+mBigTextHeight/2, mTextBigPaint);
		canvas.drawText(mCenterSmallStr, mCenterX+mSmallTextWidth/2+textMagin, mCenterY+mBigTextHeight/2, mTextSmallPaint);
	}

	private void calcSpeed() {
		if (mIncSpeedValue < mCurrentSpeedValue) {
			mIncSpeedValue += SPEED_VALUE_INC;
			if (mIncSpeedValue > mCurrentSpeedValue) {
				mIncSpeedValue = mCurrentSpeedValue;
			}
			invalidate();
		} else if (mIncSpeedValue > mCurrentSpeedValue) {
			mIncSpeedValue -= SPEED_VALUE_INC;
			if (mIncSpeedValue < mCurrentSpeedValue) {
				mIncSpeedValue = mCurrentSpeedValue;
			}
			invalidate();
		}
	}
	
	
	 /* Here we define our nested custom animation */
	  public class OpenPacman extends Animation {
	    float mStartAngle;
	    float mSweepAngle;

	    public OpenPacman (int startAngle, int sweepAngle, long duration) {
	      mStartAngle = startAngle;
	      mSweepAngle = sweepAngle;
	      setDuration(duration);
	      setInterpolator(new LinearInterpolator());
	    }

	    @Override
	    protected void applyTransformation(float interpolatedTime, Transformation t) {
	      float currAngle = mStartAngle + ((mSweepAngle - mStartAngle) * interpolatedTime);
	      ArcsProgress.this.mCurrAngle = -currAngle; //negative for counterclockwise animation.
	    }
	  }


	public String getmCenterbigStr() {
		return mCenterbigStr;
	}

	public void setmCenterbigStr(String mCenterbigStr) {
		this.mCenterbigStr = mCenterbigStr;
	}

	public String getmCenterSmallStr() {
		return mCenterSmallStr;
	}

	public void setmCenterSmallStr(String mCenterSmallStr) {
		this.mCenterSmallStr = mCenterSmallStr;
	}
	
	public void setValue(int value)
	{
		this.value=value;
		mCenterbigStr=String.valueOf(value);
//		invalidate();
	}
	  
	  

}
