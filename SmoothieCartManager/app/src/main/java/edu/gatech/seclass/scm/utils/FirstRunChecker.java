package edu.gatech.seclass.scm.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.util.Log;

import edu.gatech.seclass.scm.sqlite.DBHelper;

/**
 * Created by shivendrasrivastava on 10/22/15.
 */
public class FirstRunChecker {

    private static DBUtils dbUtils;

    /**
     *
     * @param context
     */
    public static void checkFirstRun(Context context) {

        final String PREFS_NAME = "app_prefs";
        final String PREF_VERSION_CODE_KEY = "version_code";
        final int DOESNOT_EXIST = -1;
        int currentVersionCode = 0;
        DBHelper db = new DBHelper(context);
        dbUtils = (DBUtils)ObjectFactory.getInstanceOf(AppConstants.DBUtils);
        try {
            currentVersionCode = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("SmoothieCartManager :::", "First Run Check failing due to:-  " + e);
            return;
        }

        SharedPreferences preferences = context.getSharedPreferences(
                PREFS_NAME, Context.MODE_PRIVATE);
        int savedVersionCode = preferences.getInt(PREF_VERSION_CODE_KEY, DOESNOT_EXIST);

        Log.w("SmoothieCartManager :::", "Calling DBHelper to create DB and Sample data");
        db.createDatabase();

        if (currentVersionCode == savedVersionCode) {
            //can implement stuff that we want to run every time app is opened.
        } else if (savedVersionCode == DOESNOT_EXIST) {
            //First run detected
            Log.w("SmoothieCartManager :::", "Calling DBHelper to create DB and Sample data");
            db.createDatabase();
        } else if (currentVersionCode > savedVersionCode) {
            //Upgrade detected.
        }

        dbUtils.setDatabase(db.getWritableDatabase());

        preferences.edit().putInt(PREF_VERSION_CODE_KEY, currentVersionCode).commit();

    }


}
