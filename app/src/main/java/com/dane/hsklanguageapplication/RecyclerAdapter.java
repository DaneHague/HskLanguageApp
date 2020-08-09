package com.dane.hsklanguageapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Dictionary;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.DictionaryViewHolder> {
    private List<DictionaryEntry> dicList;

    public RecyclerAdapter(List<DictionaryEntry> dicList){
        this.dicList = dicList;
    }

    @NonNull
    @Override
    public DictionaryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_item, parent, false);

        return new DictionaryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DictionaryViewHolder holder, int position) {

        DictionaryEntry dicEntry = dicList.get(position);
        holder.dicHanzi.setText(dicEntry.hanzi);
        holder.dicPinyin.setText(dicEntry.pinyin);
        holder.dicTranslations.setText(dicEntry.translations);
    }

    @Override
    public int getItemCount() {
        return dicList.size();
    }

    public static class DictionaryViewHolder extends RecyclerView.ViewHolder{

        public TextView dicHanzi;
        public TextView dicPinyin;
        public TextView dicTranslations;
        public DictionaryViewHolder(@NonNull View itemView) {
            super(itemView);
            dicHanzi = itemView.findViewById(R.id.dicHanzi);
            dicPinyin = itemView.findViewById(R.id.dicPinyin);
            dicTranslations = itemView.findViewById(R.id.dicTranslations);
        }
    }
}
