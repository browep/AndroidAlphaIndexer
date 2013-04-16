package com.github.browep.alphaindexer;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SectionIndexer;

import java.util.ArrayList;
import java.util.List;

public class Main extends Activity {

    public static final String TAG = Main.class.getCanonicalName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        ListView listView = (ListView) findViewById(R.id.list_view);

        List<String> items = new ArrayList<String>();

        for (char ch : SideSelector.ALPHABET) {
            for (int i = 1; i <= 50; i++) {
                items.add(String.valueOf(ch) + "-" + i);
            }
        }

        listView.setAdapter(new IndexingArrayAdapter(this, android.R.layout.simple_list_item_1, items));

        SideSelector sideSelector = (SideSelector) findViewById(R.id.side_selector);
        sideSelector.setListView(listView);

    }

    public class IndexingArrayAdapter extends ArrayAdapter<String> implements SectionIndexer {
        public IndexingArrayAdapter(Context context, int textViewResourceId, List<String> objects) {
            super(context, textViewResourceId, objects);
        }

        public Object[] getSections() {
            String[] chars = new String[SideSelector.ALPHABET.length];
            for (int i = 0; i < SideSelector.ALPHABET.length; i++) {
                chars[i] = String.valueOf(SideSelector.ALPHABET[i]);
            }

            return chars;
        }


        public int getPositionForSection(int i) {

            Log.d(TAG, "getPositionForSection " + i);
            return (int) (getCount() * ((float)i/(float)getSections().length));
        }

        public int getSectionForPosition(int i) {
            return 0;
        }
    }


}
