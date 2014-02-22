package appsauceco.drinkingtime;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

public class MainActivity extends Activity {
	private AdView adView;
	ListView list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		list = (ListView) findViewById(R.id.listView);
		list.setAdapter(new rulesAdapter(this));
		// still need to set list adapter

		adView = new AdView(this);
		adView.setAdUnitId("ca-app-pub-7649747947968832/4431607104");
		adView.setAdSize(AdSize.SMART_BANNER);

		// RelativeLayout layout2 =
		// (RelativeLayout)findViewById(R.id.mainLayout);
		LinearLayout layout = (LinearLayout) findViewById(R.id.adView);
		layout.addView(adView);
		AdRequest adRequest = new AdRequest.Builder().addTestDevice("6b0284de")
				.addTestDevice("c0808a004e5b92f").build();
		adView.loadAd(adRequest);

	}

	class SingleRow {
		String rule;

		// constructer
		SingleRow(String rule) {
			this.rule = rule;
		}
	}

	class rulesAdapter extends BaseAdapter {
		ArrayList<SingleRow> list;
		Context context;

		rulesAdapter(Context c) {
			list = new ArrayList<SingleRow>();
			context = c;
			Resources res = c.getResources();
			String[] rules = res.getStringArray(R.array.level1);
			
			for (int i = 0; i < 10; i++) {
				list.add(new SingleRow(rules[i]));
			}

		}

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Object getItem(int i) {
			return list.get(i);
		}

		@Override
		public long getItemId(int i) {
			return i;
		}

		@Override
		public View getView(int i, View view, ViewGroup viewGroup) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(context.LAYOUT_INFLATER_SERVICE);
			View row;
			if(i<8){
				row = inflater.inflate(R.layout.single_row, viewGroup, false);
			} else {
				row = inflater.inflate(R.layout.single_row_finish, viewGroup, false);
			}
			

			TextView rule = (TextView) row.findViewById(R.id.textView);

			SingleRow temp = list.get(i);
			rule.setText(temp.rule);

			return row;
		}

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
		Toast.makeText(this, R.string.finishYourDrink, Toast.LENGTH_SHORT)
				.show();
	}

	public void appSauce(View v) {
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setData(Uri.parse("market://search?q=pub:App+Sauce+Co."));
		startActivity(i);
	}

	@Override
	protected void onDestroy() {
		adView.destroy();
		super.onDestroy();
	}
}
