package com.pharos.a1_homework_5;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> implements PopupMenu.OnMenuItemClickListener {

    public List<Title> list;
    private Context context;
    ItemClickListener listener;
    int pos;

    public MainAdapter(List<Title> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public void setElement(Title title, int position) {
        list.set(position, title);
        this.pos = position;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false);

        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        holder.onBind(list.get(position));
        Log.d("Adapter", "onBindViewHolder" + position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.popupEd:

                return true;

            case R.id.popupDel:
                list.remove(pos);
                notifyDataSetChanged();
                return true;

            default:
                return false;
        }
    }

    public class MainViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView txtView;
        public Title title;
        public TextView txtViewDate;
        ImageButton imageBtnPopup;

        public MainViewHolder(@NonNull View itemView) {
            super(itemView);


            itemView.setOnClickListener(this);


            Log.d("Adapter", "onCreateViewHolder");
            txtView = itemView.findViewById(R.id.textView);
            txtViewDate = itemView.findViewById(R.id.textViewDate);
            imageBtnPopup = itemView.findViewById(R.id.imageButtonPopup);
            imageBtnPopup.setOnClickListener(v -> showPopup(v));
        }

        private void onBind(Title title) {
            this.title = title;
            txtView.setText(title.name);
            txtViewDate.setText(title.date);
        }

        @Override
        public void onClick(View v) {
            if (listener != null) {
                listener.onItemClick(title, getAdapterPosition());
            }
        }
    }

    public void setOnClick(ItemClickListener mListener) {
        this.listener = mListener;
    }

    public interface ItemClickListener {
        void onItemClick(Title title, int pos);
    }


    private void showPopup(View view) {
        PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
        popupMenu.inflate(R.menu.popup);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.show();
    }
}

