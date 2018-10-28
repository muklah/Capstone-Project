package com.example.user_pc.capstonestage2.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user_pc.capstonestage2.Models.Search;
import com.example.user_pc.capstonestage2.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.RecyclerViewHolder> {

    ArrayList<Search> mSearch;
    private Context context;
    private final SearchAdapter.SearchAdapterOnClickHandler mClickHandler;

    public interface SearchAdapterOnClickHandler {
        void onClick(Search search);
    }

    public SearchAdapter(SearchAdapterOnClickHandler clickHandler) {
        mClickHandler = clickHandler;
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final ImageView imageUrl;
        public final TextView title;

        public RecyclerViewHolder(View view) {
            super(view);
            imageUrl = (ImageView) itemView.findViewById(R.id.image_url);
            title = (TextView)itemView.findViewById(R.id.title);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            Search search = mSearch.get(adapterPosition);
            mClickHandler.onClick(search);
        }
    }

    @Override
    public SearchAdapter.RecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.search_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchAdapter.RecyclerViewHolder holder, int position) {
        Picasso.get().load(mSearch.get(position).getImageUrl()).placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background).into(holder.imageUrl);
        holder.title.setText(String.valueOf(mSearch.get(position).getTitle()));
    }

    @Override
    public int getItemCount() {
        if (null == mSearch)
            return 0;
        else {
            return mSearch.size();
        }
    }

    public void setSearchData(ArrayList<Search> searchData) {
        mSearch = searchData;
        notifyDataSetChanged();
    }

}
