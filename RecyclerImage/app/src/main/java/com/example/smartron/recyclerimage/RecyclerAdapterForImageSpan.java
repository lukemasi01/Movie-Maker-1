package com.example.smartron.recyclerimage;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RecyclerAdapterForImageSpan extends RecyclerView.Adapter<RecyclerAdapterForImageSpan.RecyclerViewHolder>{
    private ArrayList<String> images = new ArrayList<>();
    private ArrayList<String> imagesLen = new ArrayList<>();
    private final Context c;

    public RecyclerAdapterForImageSpan(Context c){
        this.c = c;
    }

    public void setData(ArrayList<String> images,ArrayList<String> imagesLen){
        this.images = images;
        this.imagesLen = imagesLen;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_span, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, int position) {
        if(position < images.size()) {
            Glide.with(c).load(Uri.parse(images.get(position))).into(holder.image);
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(c, R.array.imageLen, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            holder.spinner.setAdapter(adapter);
            holder.spinner.setSelection(Integer.parseInt(imagesLen.get(position)));
            holder.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                    imagesLen.set(holder.getAdapterPosition(),""+pos);
                    ImageSpan1 is1 = new ImageSpan1();
                    is1.update(holder.getAdapterPosition(),pos);
                    Log.d("Position : ",""+holder.getAdapterPosition()+" Pos : "+pos);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return images.size();
    }


    public static class RecyclerViewHolder extends RecyclerView.ViewHolder{

        final ImageView image;
        final Spinner spinner;

        public RecyclerViewHolder(View view){
            super(view);
            spinner = (Spinner) view.findViewById(R.id.spinner);
            image = (ImageView) view.findViewById(R.id.img);
        }
    }

}
