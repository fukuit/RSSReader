package jp.gr.java_conf.fukuit.rssreader;

import java.util.List;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class RSSListAdapter extends ArrayAdapter<RSSItem> {
	private LayoutInflater inflater;
	private TextView title_tv;
	private TextView desc_tv;
	private TextView date_tv;
	private TextView link_tv;

	public RSSListAdapter(Context context, List<RSSItem> objects) {
		super(context, 0, objects);
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;

		if (convertView == null) {
			view = inflater.inflate(R.layout.item_row, null);
		}
		RSSItem item = this.getItem(position);
		if (item != null) {
			title_tv = (TextView) view.findViewById(R.id.rss_item_title);
			title_tv.setText(item.getTitle());
			desc_tv = (TextView) view.findViewById(R.id.rss_item_desc);
			desc_tv.setText(item.getDescription());
			date_tv = (TextView) view.findViewById(R.id.rss_item_date);
			date_tv.setText(item.getDate());
			link_tv = (TextView) view.findViewById(R.id.rss_item_link);
			link_tv.setText(item.getLink());
		}
		return view;
	}
}
