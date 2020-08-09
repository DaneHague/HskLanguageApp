package com.dane.hsklanguageapplication;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;

import java.util.Dictionary;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.DictionaryViewHolder> {
    private List<DictionaryEntry> dicList;
    private OnHanziListener mOnHanziListener;
    public RecyclerAdapter(List<DictionaryEntry> dicList, OnHanziListener onHanziListener)
    {
        this.dicList = dicList;
        this.mOnHanziListener = onHanziListener;
    }

    @NonNull
    @Override
    public DictionaryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_item, parent, false);

        return new DictionaryViewHolder(view, mOnHanziListener);
    }

    @Override
    public void onBindViewHolder(@NonNull DictionaryViewHolder holder, int position) {

        DictionaryEntry dicEntry = dicList.get(position);
        holder.dicHanzi.setText(dicEntry.hanzi);
        holder.dicPinyin.setText(dicEntry.pinyin);
        holder.dicTranslations.setText(dicEntry.translations);

        holder.dicBtnSpeak.setBackgroundResource(R.drawable.volume_up_2);
    }

    @Override
    public int getItemCount() {
        return dicList.size();
    }

    public static class DictionaryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView dicHanzi;
        public TextView dicPinyin;
        public TextView dicTranslations;
        public Button dicBtnSpeak;
        OnHanziListener onHanziListener;
        public DictionaryViewHolder(@NonNull View itemView, OnHanziListener onHanziListener) {
            super(itemView);
            dicHanzi = itemView.findViewById(R.id.dicHanzi);
            dicPinyin = itemView.findViewById(R.id.dicPinyin);
            dicTranslations = itemView.findViewById(R.id.dicTranslations);
            dicBtnSpeak = itemView.findViewById(R.id.btnSpeak);
            this.onHanziListener = onHanziListener;

            dicBtnSpeak.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            try {
                onHanziListener.onHanziClick(getAdapterPosition());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public interface OnHanziListener {
        void onHanziClick(int position) throws JSONException;
        }
    }

