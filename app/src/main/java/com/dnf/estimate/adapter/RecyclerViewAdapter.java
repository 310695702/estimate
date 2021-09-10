package com.dnf.estimate.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class RecyclerViewAdapter<T extends ArrayList, R extends RecyclerView.ViewHolder> extends
        RecyclerView.Adapter<R> {
    private Context context;
    private T data;
    @LayoutRes
    private int layoutId;
    private String className;
    private R viewHolder;
    private onBindViewHolderListener onBindViewHolderListener;
    private onClickListener onClickListener;

    public interface onBindViewHolderListener<R> {
        void onBindViewHolder(R holder, Object o);
    }

    public interface onClickListener{
        void onClick(int position);
    }

    public void setOnClickListener(RecyclerViewAdapter.onClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void setOnBindViewHolderListener(RecyclerViewAdapter.onBindViewHolderListener onBindViewHolderListener) {
        this.onBindViewHolderListener = onBindViewHolderListener;
    }

    public RecyclerViewAdapter(Context context, T arrayList, int layoutId) {
        this.context = context;
        this.data = arrayList;
        this.layoutId = layoutId;
    }

    @NonNull
    @Override
    public R onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(layoutId, parent, false);
        if (!className.equals("")) {
            try {
                Class<? extends RecyclerView.Adapter> o = (Class<? extends RecyclerView.Adapter>) Class.forName(className);
                Constructor cons = o.getConstructor(View.class);
                viewHolder = (R) cons.newInstance(view);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return viewHolder;
    }

    public void setViewHolder(String className) {
        this.className = className;
    }

    public R getViewHolder() {
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull R holder, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickListener.onClick(position);
            }
        });
        onBindViewHolderListener.onBindViewHolder(holder, data.get(position));
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }
}
