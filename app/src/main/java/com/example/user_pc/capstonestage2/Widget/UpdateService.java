package com.example.user_pc.capstonestage2.Widget;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;

public class UpdateService extends IntentService {

    public static String SCHEDULES_LIST ="FROM_ACTIVITY_SCHEDULES_LIST";
    public static final String UPDATE_WIDGETS = "android.appwidget.action.APPWIDGET_UPDATE";

    public UpdateService() {
        super("UpdateService");
    }

    public static void startService(Context context, ArrayList<String> schedulesList) {
        Intent intent = new Intent(context, UpdateService.class);
        intent.putExtra(SCHEDULES_LIST, schedulesList);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            ArrayList<String> schedulesList = intent.getExtras().getStringArrayList(SCHEDULES_LIST);
            handleActionUpdateWidgets(schedulesList);

        }
    }

    private void handleActionUpdateWidgets(ArrayList<String> schedulesList) {
        Intent intent = new Intent(UPDATE_WIDGETS);
        intent.setAction(UPDATE_WIDGETS);
        intent.putExtra(SCHEDULES_LIST, schedulesList);
        sendBroadcast(intent);
    }

}
