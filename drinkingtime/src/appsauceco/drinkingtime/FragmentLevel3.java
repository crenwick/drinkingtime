package appsauceco.drinkingtime;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class FragmentLevel3 extends Fragment {
	ListView list;

	@Override
	public void onAttach(Activity activity) {
		Log.d("FRAG", "onAttach");
		super.onAttach(activity);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		Log.d("FRAG", "onCreate");
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.d("FRAG", "onCreateView");
		super.onCreateView(inflater, container, savedInstanceState);
		View view;
		view = inflater.inflate(R.layout.list_rules, container, false);
		list = (ListView) view.findViewById(R.id.listView);
		list.setAdapter(new rulesAdapter(getActivity()));

		return view;
	}

	class SingleRow {

		String rule;

		SingleRow(String rule) {
			this.rule = rule;
			Log.d("FRAG", "SingleRow");
		}
	}

	class rulesAdapter extends BaseAdapter {
		ArrayList<SingleRow> list;
		Context context;

		rulesAdapter(Context c) {
			list = new ArrayList<SingleRow>();
			context = c;
			Resources res = c.getResources();
			String[] rules = res.getStringArray(R.array.level3);

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
			Log.d("FRAG", "inside getView method");
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(context.LAYOUT_INFLATER_SERVICE);
			View row;
			if (i < 8) {
				row = inflater.inflate(R.layout.single_row, viewGroup, false);
			} else {
				row = inflater.inflate(R.layout.single_row_finish, viewGroup,
						false);
			}

			TextView rule = (TextView) row.findViewById(R.id.textView);

			SingleRow temp = list.get(i);
			rule.setText(temp.rule);
			Log.d("FRAG", "About to return BaseAdapter method");

			return row;
		}

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		Log.d("FRAG", "onActivityCreated");
		super.onActivityCreated(savedInstanceState);

	}

	@Override
	public void onStart() {
		Log.d("FRAG", "onStart");
		super.onStart();

	}

	@Override
	public void onResume() {
		Log.d("FRAG", "onResume");
		super.onResume();

	}

	@Override
	public void onPause() {
		Log.d("FRAG", "onPause");
		super.onPause();

	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);

	}

	@Override
	public void onStop() {
		super.onStop();
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();

	}

	@Override
	public void onDestroy() {
		super.onDestroy();

	}

	@Override
	public void onDetach() {
		super.onDetach();

	}

}
