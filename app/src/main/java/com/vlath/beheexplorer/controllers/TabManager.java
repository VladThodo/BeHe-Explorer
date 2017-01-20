/*
   Copyright 2016 Vlad Todosin
*/

package com.vlath.beheexplorer.controllers;



import android.content.Context;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.vlath.beheexplorer.view.BeHeView;


import java.util.ArrayList;
import java.util.List;

public class TabManager {
   private static List<BeHeView> mViewsList = new ArrayList<BeHeView>();
   private static PreferenceManager MANAGER;
   static BeHeView currentTab;
   private static NavigationView VIEW;
   public static void addTab(BeHeView view){
       mViewsList.add(view);
   }
   @Nullable
    public static List<BeHeView> getList(){
        return mViewsList;
    }

    public static void removeTab(BeHeView view){
        int index = mViewsList.indexOf(view);
        if(index != 0){
          mViewsList.remove(view);
        }
        else {
            BeHeView behe = mViewsList.get(index + 1);
            mViewsList.set(0,behe);
            mViewsList.remove(index + 1);
            mViewsList.remove(view);
            setCurrentTab(behe);
        }
    }
    public static BeHeView getCurrentTab(){
        if(currentTab != null) {
            return currentTab;
        }
        else{
            return mViewsList.get(0);
        }
    }
    public static void setNavigationView(NavigationView view){
        VIEW = view;
    }
    public static void updateTabView(){
        VIEW.getMenu().clear();
        for(int i = 0;i < mViewsList.size();i++) {
            BeHeView  view = mViewsList.get(i);
            VIEW.getMenu().add(view.getTitle());
            if(view == TabManager.currentTab){
                VIEW.getMenu().getItem(i).setChecked(true);
            }
        }
        for(int i = 0;i< VIEW.getMenu().size();i++){
            ColorGenerator gen = ColorGenerator.MATERIAL;
            int col = gen.getRandomColor();
            TextDrawable drawable = TextDrawable.builder().buildRound(String.valueOf(i + 1),col);
            VIEW.getMenu().getItem(i).setIcon(drawable);
        }
    }
    public static void setCurrentTab(BeHeView view){
        for(BeHeView behe : getList()){
            behe.setIsCurrentTab(false);
        }
        view.setIsCurrentTab(true);
        currentTab = view;
    }
    public static BeHeView getTabByTitle(String title){
        for(BeHeView view : getList()){
            String web = view.getTitle();
            if(web.matches(title)){
                return view;
            }
            else{
                return null;
            }
        }
        return null;
    }
    public static BeHeView getTabAtPosition(MenuItem menuItem){
        List<MenuItem> items = new ArrayList<>();
        Menu menu = VIEW.getMenu();
        for(int i = 0; i < menu.size();i++){
            MenuItem item = menu.getItem(i);
            items.add(item);
        }
        int index = items.indexOf(menuItem);
        BeHeView view = getList().get(index);
        return view;
    }
    public static void removeAllTabs(){
       mViewsList.clear();
    }
    public static void resetAll(ActionBarActivity act, ProgressBar pBar, boolean pvt, EditText txt){
          for(BeHeView view : mViewsList){
              view.setNewParams(txt,pBar,act,pvt);
          }
    }
    public static void stopPlayback(){
        for(BeHeView view : mViewsList){
            view.onPause();
        }
    }
   public static void resume(){
       for(BeHeView view : mViewsList){
           view.onResume();
       }
   }
    public static String getSearchEngine(Context cnt) {
        String searchEngine;
        searchEngine = MANAGER.getDefaultSharedPreferences(cnt).getString("search","1");
        int e = Integer.parseInt(searchEngine);
        switch (e) {
            case 1:
                String google = "https://www.google.com/search?q=";
                return google;
            case 2:
                String bing = "http://www.bing.com/search?q=";
                return bing;
            case 3:
                String yahoo = "https://search.yahoo.com/search?p=";
                return yahoo;
            case 4:
                String duck = "https://duckduckgo.com/?q=";
                return duck;
            case 5:
                String ask = "http://www.ask.com/web?q=";
                return ask;

            case 6:
                String wow = "http://www.wow.com/search?s_it=search-thp&v_t=na&q=";
                return wow;
            default:
                String goole = "https://www.google.com/search?q=";
                return goole;
        }
    }
    public static void deleteAllHistory(){
        for(BeHeView view : mViewsList){
            view.clearHistory();
        }
    }
}

