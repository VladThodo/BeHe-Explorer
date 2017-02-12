/*
 Copyright 2016 Vlad Todosin
*/
package com.vlath.beheexplorer.utils;


import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBarActivity;
import android.util.TypedValue;

import com.vlath.beheexplorer.R;


public class ThemeUtils {
   private static ActionBarActivity THEME_ACTIVITY;
    public ThemeUtils(ActionBarActivity activity){
        THEME_ACTIVITY = activity;
    }
    private static boolean isBlackTheme = false;
    public  void setTheme() {
        PreferenceUtils utils = new PreferenceUtils(THEME_ACTIVITY);
        switch (utils.getTheme()) {
            case 1:
                THEME_ACTIVITY.setTheme(R.style.WhiteTheme);
                isBlackTheme = false;
                break;
            case 2:
                THEME_ACTIVITY.setTheme(R.style.GreyTheme);
                isBlackTheme = false;
                break;
            case 3:
                THEME_ACTIVITY.setTheme(R.style.RedTheme);
                isBlackTheme = false;
                break;
            case 4:
                THEME_ACTIVITY.setTheme(R.style.BlueTheme);
                isBlackTheme = false;
                break;
            case 5:
                THEME_ACTIVITY.setTheme(R.style.GreenTheme);
                isBlackTheme = false;
                break;
            case 6:
                THEME_ACTIVITY.setTheme(R.style.BlackTheme);
                isBlackTheme = true;
                break;
        }
    }
    public static boolean isBlack(){
        return  isBlackTheme;
    }
    public   void setIncognitoTheme(){
        THEME_ACTIVITY.getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#2F4F4F")));
    }

}
