package appsauceco.drinkingtime;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

//import com.google.android.gms.ads.AdRequest;
//import com.google.android.gms.ads.AdView;

public class MainActivity extends FragmentActivity {

	ViewPager viewPager = null;
//	private AdView adView;
//	private AdRequest adRequest;
	boolean hasTwoPanes;
	public static int ruleToDisplay;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (isXLargeScreen(getApplicationContext())) {
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		}

		hasTwoPanes = getResources().getBoolean(R.bool.has_two_panes);

		viewPager = (ViewPager) findViewById(R.id.pager);
		FragmentManager fragmentManager = getSupportFragmentManager();
		viewPager.setAdapter(new MyAdapter(fragmentManager));

//		adView = (AdView) this.findViewById(R.id.adView);
//		adRequest = new AdRequest.Builder()
//				.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
//				.addTestDevice("6b0284de").addTestDevice("c0808a004e5b92f")
//				.build();
//		adView.loadAd(adRequest);
		
		// comment out to allow ads to appear:
		//adView.setVisibility(View.GONE);

	}

	public static boolean isXLargeScreen(Context context) {
		return (context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_XLARGE;
	}

	class MyAdapter extends FragmentPagerAdapter {

		public MyAdapter(FragmentManager fm) {
			super(fm);
			Log.d("MAIN", "FragmentManger method");
		}

		@Override
		public Fragment getItem(int i) {			
			Fragment fragment = null;
			if (i == 0) {
				ruleToDisplay = i;
				fragment = new FragmentLevel1();
			} else if (i == 1) {
				fragment = new FragmentLevel2();
			} else if (i == 2) {
				fragment = new FragmentLevel3();
			}
			Log.d("MAIN", "about to return getItem method");
			return fragment;
		}

		@Override
		public int getCount() {
			return 3;
		}

		@Override
		public float getPageWidth(int position) {
			// returns 0.5f to show two panes
			if (hasTwoPanes == true) {
				return (0.5f);
			} else {
				return (1f);
			}
		}

		@Override
		public CharSequence getPageTitle(int position) {
			if (hasTwoPanes == true) {
				if (position == 0) {
					return "Level 1 and 2";
				}
				if (position == 1) {
					return "Level 2 and 3";
				}
				if (position == 2) {
					return null;
				}
			} else {
				if (position == 0) {
					return "Level 1";
				}
				if (position == 1) {
					return "Level 2";
				}
				if (position == 2) {
					return "Level 3";
				}
			}
			return null;
		}

	}

	@Override
	protected void onPause() {
		Log.d("MAIN", "onPause");
//		adView.pause();
		super.onPause();
	}

	@Override
	protected void onResume() {
		Log.d("MAIN", "onResume");
		super.onResume();
//		adView.resume();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		Log.d("MAIN", "onCreatedOptionsMenu");
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.more_apps:
			Intent i = new Intent(Intent.ACTION_VIEW);
			i.setData(Uri.parse("market://search?q=pub:App+Sauce+Co."));
			startActivity(i);
			return true;
		case R.id.switchToCharacters:
			Log.d("MAIN", "swtichToCharacters");
			Intent intent = new Intent(this, CharaMainActivity.class);
			startActivity(intent);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	public void oneDrink(View ImageView) {
		Toast.makeText(this, R.string.drinkOnce, Toast.LENGTH_SHORT).show();
		Log.d("MAIN", "oneDrink");
	}

	public void finishDrink(View ImageView) {
		Toast.makeText(this, R.string.finishYourDrink, Toast.LENGTH_SHORT)
				.show();
		Log.d("MAIN", "finishDrink");
	}

	@Override
	protected void onDestroy() {
		Log.d("MAIN", "onDestroy");
//		adView.destroy();
		super.onDestroy();
	}
}
