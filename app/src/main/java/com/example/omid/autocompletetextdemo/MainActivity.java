package com.example.omid.autocompletetextdemo;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private AutoCompleteTextView actv;
    private MultiAutoCompleteTextView mactv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<String> phnNam = new ArrayList<>();

        ContentResolver cr = getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,null,null,null,null);
        if (cur.getCount()>0) {
            while (cur.moveToNext()) {
                phnNam.add(cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)));
            }
        }
//        String [] countries = getResources().getStringArray(R.array.list_of_countries);
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,phnNam);

        actv = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView1);
        mactv = (MultiAutoCompleteTextView) findViewById(R.id.multiAutoCompleteTextView1);
        actv.setAdapter(adapter);
        mactv.setAdapter(adapter);
        mactv.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
    }
}
