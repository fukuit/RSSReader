package jp.gr.java_conf.fukuit.rssreader;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import android.app.ListActivity;
import android.os.Bundle;
import android.util.Xml;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RSSReader extends ListActivity implements OnClickListener {
	RSSListAdapter adapter;
	ArrayList<RSSItem> items;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		items = new ArrayList<RSSItem>();
		adapter = new RSSListAdapter(this, items);

		setListAdapter(adapter);

		Button btn = (Button) findViewById(R.id.exec_btn);
		btn.setOnClickListener(this);
	}

	public void onClick(View v) {
		EditText url_et = (EditText) findViewById(R.id.url_et);
		String url = url_et.getText().toString();
		if (url.length() != 0) {
			getRSS(url);
		}
	}

	private void readRssItem(InputStream is) {
		XmlPullParser parser = Xml.newPullParser();
		try {
			parser.setInput(is, null);
			int eventType = parser.getEventType();
			RSSItem item = null;
			while (eventType != XmlPullParser.END_DOCUMENT) {
				String tag = null;
				switch (eventType) {
				case XmlPullParser.START_TAG:
					tag = parser.getName();
					if (tag.equals("item")) {
						item = new RSSItem();
					} else if (item != null) {
						if (tag.equals("title")) {
							if (item.getTitle().equals("")) {
								item.setTitle(parser.nextText());
							}
						} else if (tag.equals("link")) {
							if (item.getLink().equals("")) {
								item.setLink(parser.nextText());
							}
						} else if (tag.equals("description")) {
							if (item.getDescription().equals("")) {
								item.setDescription(parser.nextText());
							}
						} else if (tag.equals("date")) {
							if (item.getDate().equals("")) {
								item.setDate(parser.nextText());
							}
						}
					}
					break;
				case XmlPullParser.END_TAG:
					tag = parser.getName();
					if (tag.equals("item")) {
						items.add(item);
					}
					break;
				}
				eventType = parser.next();
			}
		} catch (Exception e) {

		} finally {

		}
	}

	private void getRSS(String url_in) {
		try {
			URL url = new URL(url_in);
			InputStream is = url.openConnection().getInputStream();
			readRssItem(is);
		} catch (Exception e) {
			Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
		} finally {
			setListAdapter(adapter);
		}
	}
}