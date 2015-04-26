package edu.auburn.eng.csse.comp3710.cma0036.droidclicker.viewUtils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import edu.auburn.eng.csse.comp3710.cma0036.droidclicker.R;

public class ListArrayAdpater extends ArrayAdapter<String> {
	private final Context context;
	private final String[] values;

	public ListArrayAdpater(Context context, String[] values) {
		super(context, R.layout.list_item_layout, values);
		this.context = context;
		this.values = values;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = null;

			/*rowView = inflater.inflate(R.layout.list_item_layout, parent,false);
			TextView txtTitle = (TextView) rowView.findViewById(R.id.title);
			TextView txtDescription = (TextView) rowView.findViewById(R.id.description);
			ImageView imgvIcon = (ImageView) rowView.findViewById(R.id.icon);
			txtTitle.setText(values[position]);
			String s = values[position];
*/

		return rowView;
	}
}
