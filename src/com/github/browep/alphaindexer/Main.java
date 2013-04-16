package com.github.browep.alphaindexer;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SectionIndexer;

import java.util.ArrayList;
import java.util.List;

public class Main extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        ListView listView = (ListView) findViewById(R.id.list_view);

//        SideSelector sideSelector = (SideSelector) findViewById(R.id.side_selector);
//        sideSelector.setListView(listView);

        List<String> items = new ArrayList<String>();

        for(char ch : SideSelector.ALPHABET){
            for(int i=1; i<=50;i++){
                items.add(String.valueOf(ch) + "-" +  i);
            }
        }

        listView.setAdapter(new IndexingArrayAdapter(this, android.R.layout.simple_list_item_1, items));

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
            return 0;
        }

        public int getSectionForPosition(int i) {
            return 0;
        }
    }


}
