package appsauceco.drinkingtime;

import android.content.Intent;
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

public class MainActivity extends FragmentActivity {
	
	// ListView list;
	ViewPager viewPager = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d("MAIN", "onCreate");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		viewPager = (ViewPager) findViewById(R.id.pager);
		FragmentManager fragmentManager = getSupportFragmentManager();
		viewPager.setAdapter(new MyAdapter(fragmentManager));

		// list = (ListView) findViewById(R.id.listView);
		// list.setAdapter(new rulesAdapter(this));

	}
	
	class MyAdapter extends FragmentPagerAdapter{

		public MyAdapter(FragmentManager fm) {			
			super(fm);
			Log.d("MAIN", "FragmentManger method");
			// TODO Auto-generated constructor stub
		}

		@Override
		public Fragment getItem(int i) {
			Fragment fragment = null;
			if (i == 0){
				fragment = new FragmentLevel1();
			} else if(i == 1){
				fragment = new FragmentLevel2();
			} else if(i == 2){
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
	    public CharSequence getPageTitle(int position) {
	        if(position==0)
	        {
	            return "Level 1";
	        }
	        if(position==1)
	        {
	            return "Level 2";
	        }
	        if(position==2)
	        {
	            return "Level 3";
	        }
	        return null;
	    }
		
	}

	@Override
	protected void onPause() {
		Log.d("MAIN", "onPause");
		super.onPause();
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
			appSauce(null);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@FromXML
	public void oneDrink(View ImageView) {
		Toast.makeText(this, R.string.drinkOnce, Toast.LENGTH_SHORT).show();
		Log.d("MAIN", "oneDrink");
	}

	@FromXML
	public void finishDrink(View ImageView) {
		Toast.makeText(this, R.string.finishYourDrink, Toast.LENGTH_SHORT)
				.show();
		Log.d("MAIN", "finishDrink");
	}

	public void appSauce(View v) {
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setData(Uri.parse("market://search?q=pub:App+Sauce+Co."));
		startActivity(i);
	}

	@Override
	protected void onDestroy() {
		Log.d("MAIN", "onDestroy");
		super.onDestroy();
	}
}
