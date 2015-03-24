package com.duron.recyclerdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jacobduron on 2/24/15.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>{


    private List<CellContent> cellContents = new ArrayList<>();
    private Context mContext;
    private ListClick mClick;

    public RecyclerAdapter(Context context, List<CellContent> cellContentList, ListClick listClick){
        this.mContext = context;
        this.cellContents = cellContentList;
        this.mClick = listClick;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View cell = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.cell_recycler_view, viewGroup, false);
        MyViewHolder myViewHolder = new MyViewHolder(cell);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder myViewHolder, final int i) {
        final CellContent cellContent = cellContents.get(i);
        myViewHolder.itemText.setText(cellContent.cellText);
        myViewHolder.itemImage.setImageResource(0);

        Picasso.with(mContext).load(cellContent.imageUrl).error(R.drawable.placeholder_demo).into(myViewHolder.itemImage);


        myViewHolder.cellContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClick.onClick(i, cellContent, myViewHolder.cellContainer);

            }
        });
    }

    @Override
    public int getItemCount() {
        return cellContents.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        View cellContainer;
        TextView itemText;
        ImageView itemImage;

        MyViewHolder(View itemView) {
           super(itemView);
            cellContainer = itemView.findViewById(R.id.cell_container);
            itemText = (TextView) itemView.findViewById(R.id.cell_textview);
            itemImage = (ImageView)itemView.findViewById(R.id.cell_imageview);
       }
   }
}
