package com.example.fragmenttabhostviewpager;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.ImageView;

public class MainActivity extends FragmentActivity {
	private RadioGroup rg;
	private RadioButton firstBtn;
	private RadioButton secondBtn;
	private RadioButton thirdBtn;
	private FragmentTabHost mFragmentTabhost;
	public static final String SHOW_OF_FIRST_TAG = "first";
	public static final String SHOW_OF_SECOND_TAG = "second";
	public static final String SHOW_OF_THIRD_TAG = "third";

	private int SCREEN_WIDTH;

	private float currentX;// 当前X坐标
	private float preX;// 前一操作的X坐标
	private ImageView mRedlineIV;

	private List<Fragment> list = new ArrayList<Fragment>();
	private ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		SCREEN_WIDTH = metrics.widthPixels;
		setContentView(R.layout.activity_main);
		Log.i("TAG", "0000");
		mFragmentTabhost = (FragmentTabHost) findViewById(android.R.id.tabhost);
		rg = (RadioGroup) findViewById(R.id.tab_rg_menu);
		firstBtn = (RadioButton) findViewById(R.id.tab_rb_1);
		secondBtn = (RadioButton) findViewById(R.id.tab_rb_2);
		thirdBtn = (RadioButton) findViewById(R.id.tab_rb_3);
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mRedlineIV = (ImageView) findViewById(R.id.tab_menu_red_line);
		
		LinearLayout.LayoutParams parmas = new LinearLayout.LayoutParams(SCREEN_WIDTH/3, LinearLayout.LayoutParams.WRAP_CONTENT);
		mRedlineIV.setLayoutParams(parmas);
		
		mFragmentTabhost.setup(this, getSupportFragmentManager(), R.id.pager);

		TabSpec tabSpec0 = mFragmentTabhost.newTabSpec(SHOW_OF_FIRST_TAG)
				.setIndicator("0");
		TabSpec tabSpec1 = mFragmentTabhost.newTabSpec(SHOW_OF_SECOND_TAG)
				.setIndicator("1");
		TabSpec tabSpec2 = mFragmentTabhost.newTabSpec(SHOW_OF_THIRD_TAG)
				.setIndicator("2");

		mFragmentTabhost.addTab(tabSpec0, Fragment1.class, null);
		mFragmentTabhost.addTab(tabSpec1, Fragment2.class, null);
		mFragmentTabhost.addTab(tabSpec2, Fragment3.class, null);

		rg.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				switch (checkedId) {
				case R.id.tab_rb_1:
					preX = currentX;
					currentX = 0;
					mFragmentTabhost.setCurrentTabByTag(SHOW_OF_FIRST_TAG);
					break;
				case R.id.tab_rb_2:
					preX = currentX;
					currentX = SCREEN_WIDTH * 1 / 3;
					mFragmentTabhost.setCurrentTabByTag(SHOW_OF_SECOND_TAG);
					break;
				case R.id.tab_rb_3:
					preX = currentX;
					currentX = SCREEN_WIDTH * 2 / 3;
					mFragmentTabhost.setCurrentTabByTag(SHOW_OF_THIRD_TAG);
					break;

				default:
					break;
				}
				Animation translateAnimation = new TranslateAnimation(preX,
						currentX, 0, 0);
				translateAnimation.setFillAfter(true);
				translateAnimation.setDuration(400);
				mRedlineIV.setAnimation(translateAnimation);
			}
		});

		mFragmentTabhost.setOnTabChangedListener(new OnTabChangeListener() {

			@Override
			public void onTabChanged(String tabId) {
				// TODO Auto-generated method stub
				int position = mFragmentTabhost.getCurrentTab();
				mViewPager.setCurrentItem(position);
			}
		});

		mFragmentTabhost.setCurrentTab(0);

		Fragment1 p1 = new Fragment1();
		Fragment2 p2 = new Fragment2();
		Fragment3 p3 = new Fragment3();
		list.add(p1);
		list.add(p2);
		list.add(p3);
		mViewPager.setAdapter(new MenuAdapter(getSupportFragmentManager()));
		mViewPager.setOnPageChangeListener(new ViewPagerListener());
	}

	class MenuAdapter extends FragmentPagerAdapter {

		public MenuAdapter(FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}

		@Override
		public Fragment getItem(int arg0) {
			return list.get(arg0);
		}

		@Override
		public int getCount() {
			return list.size();
		}

	}

	class ViewPagerListener implements OnPageChangeListener {
		@Override
		public void onPageScrollStateChanged(int arg0) {

		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {

		}

		@Override
		public void onPageSelected(int index) {
			if (index == 0) {
				firstBtn.setChecked(true);
			} else if (index == 1) {
				secondBtn.setChecked(true);
			} else if (index == 2) {
				thirdBtn.setChecked(true);
			}
			mFragmentTabhost.setCurrentTab(index);
		}
	}
}
