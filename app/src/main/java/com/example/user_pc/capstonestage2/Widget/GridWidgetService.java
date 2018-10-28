package com.example.user_pc.capstonestage2.Widget;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.user_pc.capstonestage2.R;

import java.util.List;

import static com.example.user_pc.capstonestage2.Widget.WidgetProvider.scheduleList;

public class GridWidgetService extends RemoteViewsService {
    List<String> remoteSchedulesList;

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new GridRemoteViewsFactory(this.getApplicationContext(),intent);
    }


    class GridRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

        Context mContext = null;

        public GridRemoteViewsFactory(Context context,Intent intent) {
            mContext = context;

        }

        @Override
        public void onCreate() {
        }

        @Override
        public void onDataSetChanged() {
            remoteSchedulesList = scheduleList;
        }

        @Override
        public void onDestroy() {

        }

        @Override
        public int getCount() {
            if (remoteSchedulesList == null) {
                return 0;
            }
            return remoteSchedulesList.size();
        }

        @Override
        public RemoteViews getViewAt(int position) {

            RemoteViews views = new RemoteViews(mContext.getPackageName(), R.layout.grid_widget_item);

            views.setTextViewText(R.id.widget_text_view, remoteSchedulesList.get(position));

            Intent intent = new Intent();
            views.setOnClickFillInIntent(R.id.widget_text_view, intent);

            return views;
        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

    }

}

