package com.example.user_pc.capstonestage2.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user_pc.capstonestage2.Database.Favorite;
import com.example.user_pc.capstonestage2.Database.Favorites;
import com.example.user_pc.capstonestage2.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder> {

    final private ItemClickListener mItemClickListener;
    private List<Favorites> mFavorites;
    private Context mContext;

    public FavoriteAdapter(Context context, ItemClickListener listener) {
        mContext = context;
        mItemClickListener = listener;
    }

    @Override
    public FavoriteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.favorite_list_item, parent, false);

        return new FavoriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FavoriteViewHolder holder, int position) {

        Favorites favorite = mFavorites.get(position);

        String mTitle = favorite.getTitle();
        String mImageUrl = favorite.getImageUrl();
        String mScore = favorite.getScore();

        holder.title.setText(mTitle);
        Picasso.get().load(mImageUrl).placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background).into(holder.imageUrl);
        holder.score.setText(mScore);

    }

    @Override
    public int getItemCount() {
        if (mFavorites == null) {
            return 0;
        }
        return mFavorites.size();
    }

    public List<Favorites> getTFavorites() {
        return mFavorites;
    }

    public void setFavorites(List<Favorites> favoriteEntries) {
        mFavorites = favoriteEntries;
        notifyDataSetChanged();
    }

    public interface ItemClickListener {
        void onItemClickListener(int itemId);
    }

    class FavoriteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title;
        ImageView imageUrl;
        TextView score;

        public FavoriteViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            imageUrl = itemView.findViewById(R.id.image_url);
            score = itemView.findViewById(R.id.score);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int elementId = mFavorites.get(getAdapterPosition()).getId();
            mItemClickListener.onItemClickListener(elementId);
        }
    }

}
