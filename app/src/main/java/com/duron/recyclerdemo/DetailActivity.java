package com.duron.recyclerdemo;

import android.app.Activity;
import android.os.Bundle;
import android.transition.Explode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


public class DetailActivity extends Activity {

    ImageView detailImage;
    TextView detailTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        detailImage =(ImageView)findViewById(R.id.cell_imageview);
        detailTextView = (TextView)findViewById(R.id.cell_textview);



        CellContent cellContent = (CellContent)getIntent().getSerializableExtra("cell_content");

        detailTextView.setText(cellContent.cellText);

        Picasso.with(this).load(cellContent.imageUrl).error(R.drawable.placeholder_demo).into(detailImage);

    }



}
