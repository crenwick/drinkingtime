package appsauceco.drinkingtime;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

public class MainActivity extends Activity {
	private AdView adView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		
		adView = new AdView(this);
		adView.setAdUnitId("ca-app-pub-7649747947968832/4431607104");
		adView.setAdSize(AdSize.SMART_BANNER);
		
		//RelativeLayout layout2 = (RelativeLayout)findViewById(R.id.mainLayout);
		LinearLayout layout = (LinearLayout)findViewById(R.id.adView);
		layout.addView(adView);
		AdRequest adRequest = new AdRequest.Builder()
		.addTestDevice("6b0284de")
		.addTestDevice("c0808a004e5b92f")
		.build();
		adView.loadAd(adRequest);
		
	}
	
	@Override
	protected void onPause() {
		adView.pause();
		super.onPause();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		adView.resume();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
	}

	@FromXML
	public void finishDrink(View ImageView) {
		Toast.makeText(this, R.string.finishYourDrink, Toast.LENGTH_SHORT).show();
	}

	public void appSauce(View v) {
		String url = "https://play.google.com/store/apps/developer?id=SideStreet,+Inc.";
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setData(Uri.parse(url));
		startActivity(i);
	}
	
	@Override
	protected void onDestroy() {
		adView.destroy();
		super.onDestroy();
	}
}
