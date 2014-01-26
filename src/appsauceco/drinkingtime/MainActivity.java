package appsauceco.drinkingtime;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
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
		Toast.makeText(this, "Drink once!", Toast.LENGTH_LONG).show();
	}
	
	@FromXML
	public void finishDrink(View ImageView) {
		Toast.makeText(this, "Finish your drink!", Toast.LENGTH_LONG).show();
	}
	
	public void appSauce(View v) {
		String url = "https://play.google.com/store/apps/developer?id=SideStreet,+Inc.";
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setData(Uri.parse(url));
		startActivity(i);
	}

}
