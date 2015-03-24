package com.duron.recyclerdemo;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Explode;
import android.util.Pair;
import android.view.View;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;


public class MainActivity extends Activity  implements  ListClick{

    RecyclerView recyclerView;
    RecyclerAdapter myRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setExitTransition(new Explode());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        myRecyclerAdapter = new RecyclerAdapter(this, Arrays.asList(getData()), this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myRecyclerAdapter);
        recyclerView.addItemDecoration(new MyItemDecorator(this, MyItemDecorator.VERTICAL_LIST));

    }


    private CellContent[] getData(){
        String json = getJson();
        if(json != null){
            Gson gson = new Gson();
            SampleData sampleData = gson.fromJson(json, SampleData.class);
            if(sampleData != null){
                return sampleData.cellContents;
            }
        }
        return null;
    }

    private String getJson(){
        StringBuilder buf=new StringBuilder();
        try{
            InputStream json = getAssets().open("sample_json.json");
            BufferedReader in = new BufferedReader(new InputStreamReader(json, "UTF-8"));
            String str;

            while ((str=in.readLine()) != null) {
                buf.append(str);
            }

            in.close();
        }catch(Exception e){
            return null;
        }
        return buf.toString();
    }


    @Override
    public void onClick(int position, Object listObject, View row) {
        View image = row.findViewById(R.id.cell_imageview);
        View text = row.findViewById(R.id.cell_textview);
        ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(this,
                Pair.create(text, text.getTransitionName()),
                Pair.create(image, image.getTransitionName()));
        Intent intent = new Intent(this, DetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("cell_content", (CellContent)listObject);
        intent.putExtras(bundle);
        startActivity(intent, activityOptions.toBundle());
    }
}
