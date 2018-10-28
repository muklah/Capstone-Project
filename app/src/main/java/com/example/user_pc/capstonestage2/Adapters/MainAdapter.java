package com.example.user_pc.capstonestage2.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user_pc.capstonestage2.Models.Schedule;
import com.example.user_pc.capstonestage2.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {

    List<Schedule> mSchedule;
    private int rowLayout;
    private Context mContext;

    private final MainAdapter.MainAdapterOnClickHandler mClickHandler;

    public interface MainAdapterOnClickHandler {
        void onClick(Schedule schedule, View view);
    }

    public MainAdapter(List<Schedule> schedule, int rowLayout, Context context, MainAdapterOnClickHandler clickHandler) {
        this.mSchedule = schedule;
        this.rowLayout = rowLayout;
        this.mContext = context;
        this.mClickHandler = clickHandler;
    }

    class MainViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final TextView title;
        public final ImageView imageUrl;
        public final TextView score;

        public MainViewHolder(View v) {
            super(v);
            title = (TextView) v.findViewById(R.id.title);
            imageUrl = (ImageView) v.findViewById(R.id.image_url);
            score = (TextView) v.findViewById(R.id.score);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            Schedule schedule = mSchedule.get(adapterPosition);
            mClickHandler.onClick(schedule, v);
        }
    }

    @Override
    public MainAdapter.MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new MainViewHolder(view);
    }


    @Override
    public void onBindViewHolder(MainViewHolder holder, final int position) {
        holder.title.setText(mSchedule.get(position).getTitle());
        Picasso.get().load(mSchedule.get(position).getImageUrl()).placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background).into(holder.imageUrl);
        holder.score.setText(String.valueOf(mSchedule.get(position).getScore()));
    }

    @Override
    public int getItemCount() {
        if (null == mSchedule)
            return 0;
        else {
            return mSchedule.size();
        }
    }

    public void setMainData(ArrayList<Schedule> scheduleData, Context context) {
        mSchedule = scheduleData;
        mContext = context;
        notifyDataSetChanged();
    }
}