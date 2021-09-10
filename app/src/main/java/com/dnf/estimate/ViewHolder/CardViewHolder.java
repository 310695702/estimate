package com.dnf.estimate.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dnf.estimate.R;

public class CardViewHolder extends RecyclerView.ViewHolder {
    public ImageView card_imageView;
    public TextView card_textView;
    public CardViewHolder(@NonNull View itemView) {
        super(itemView);
        card_imageView = itemView.findViewById(R.id.card_imageView);
        card_textView = itemView.findViewById(R.id.card_textView);
    }
}
