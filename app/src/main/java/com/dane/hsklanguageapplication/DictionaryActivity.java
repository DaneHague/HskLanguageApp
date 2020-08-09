package com.dane.hsklanguageapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DictionaryActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<DictionaryEntry> dicList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary);
        recyclerView = findViewById(R.id.home_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        try {
            prepareTheList();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RecyclerAdapter adapter = new RecyclerAdapter(dicList);
        recyclerView.setAdapter(adapter);

    }
    private void prepareTheList() throws JSONException {

        JSONArray arr = selectHanZi();

        for(int i = 0; i < arr.length(); i++){
            DictionaryEntry dicEntry = new DictionaryEntry(arr.getJSONObject(i).getString("hanzi"),
                    arr.getJSONObject(i).getString("pinyin"),
                    arr.getJSONObject(i).getString("translations").replaceAll("\\[|]|\"","").replace(",", ", "));
            dicList.add(dicEntry);
        }

    }

    public String readJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("hsk6.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public JSONArray selectHanZi() throws JSONException {
        JSONArray jsA = new JSONArray(readJSONFromAsset());
        JSONArray selectedHanZi = new JSONArray();

        for (int i = 0; i < jsA.length(); i++){
            JSONObject tmp = new JSONObject();

            tmp.put("hanzi",jsA.getJSONObject(i).optString("hanzi"));
            tmp.put("pinyin", jsA.getJSONObject(i).optString("pinyin"));
            tmp.put( "translations", jsA.getJSONObject(i).optString("translations"));
            selectedHanZi.put(i, tmp);
        }
        return selectedHanZi ;
    }


}