package com.example.ag6505.extendsview;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {
    private String[] content = {"Detta", "är", "innehållet", "i", "en", "ListView", "rad7", "rad8"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeComponents();
    }

    private void initializeComponents() {
        ListView lvTrl = (ListView) findViewById(R.id.lvTrl);
        lvTrl.setAdapter(new ArrayAdapter<String>(this,R.layout.trl_row,content));
        lvTrl.setOnItemClickListener(new ListViewListener());
    }
//        lvTrl.setAdapter(new ArrayAdapter<String>(this,R.layout.trl_row,content));
//        lvTrl.setAdapter(new TRLAdapter(this,content));

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class ListViewListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if(view instanceof TRLTextView) {
                TextView tv = (TextView) view;
                Log.d("OICL", content[position] + " - " + tv.getText());
            } else if(view instanceof LinearLayout) {
                Log.d("OICL", content[position]);
            }
        }
    }
}