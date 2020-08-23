package com.jalpa.cavista.ui;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jalpa.cavista.R;
import com.jalpa.cavista.model.ImagesResponse;

import java.util.ArrayList;

public class ImageGridAdapter extends RecyclerView.Adapter<ImageGridAdapter.ImageViewHolder> {

    private final ArrayList<ImagesResponse> dataset;

    public ImageGridAdapter(ArrayList<ImagesResponse> mList) {
        this.dataset = mList;
    }

    public void addItems(ArrayList<ImagesResponse> mList) {
        int lastIndex = this.dataset.size();
        this.dataset.addAll(mList);
        notifyItemRangeInserted(lastIndex, dataset.size());
    }


    @NonNull
    @Override
    public ImageGridAdapter.ImageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        View inflate = inflater.inflate(R.layout.griditem, viewGroup, false);
        ImageViewHolder imageViewHolder = new ImageViewHolder(inflate);
        return imageViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ImageGridAdapter.ImageViewHolder viewHolder, int i) {
        if (dataset.get(i) != null && dataset.get(i).images != null && dataset.get(i).images.size() > 0) {
            Log.e("url", dataset.get(i).images.get(0).link + "");
            Glide.with(viewHolder.itemView.getContext()).load(dataset.get(i).images.get(0).link).centerCrop().into(((ImageViewHolder) viewHolder).imageView);
        }
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    class ImageViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int adapterPosition = getAdapterPosition();
                    Log.e("adapterPositon", adapterPosition + "");
                    Intent intent = new Intent(view.getContext(), DetailsActivity.class);
                    intent.putExtra("imageObject", dataset.get(adapterPosition));
                    view.getContext().startActivity(intent);
                }
            });
        }
    }

}
