package com.example.andrey.timefor;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.takisoft.fix.support.v7.preference.PreferenceFragmentCompat;

/**
 * Created by vrnandr on 25.09.18.
 */

public class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {

    public static final String ENABLE_COLORING = "enable_coloring";
    public static final String TIME_NORM = "time_norm";
    public static final String NEED_TIME_PERCENTAGE = "need_time_percentage";

    interface OnSettingsFragmentEventListener{
        void onStopSettingsFragment();
    }

    OnSettingsFragmentEventListener eventListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        eventListener = (OnSettingsFragmentEventListener) getActivity();
    }

    @Override
    public void onCreatePreferencesFix(@Nullable Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences,rootKey);

    }

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
        updateSummary();
        ((HostActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((HostActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);


    }

    private void updateSummary() {
        findPreference(TIME_NORM).setSummary(getPreferenceScreen().getSharedPreferences().getString(TIME_NORM,""));
        findPreference(NEED_TIME_PERCENTAGE).setSummary(getPreferenceScreen().getSharedPreferences().getString(NEED_TIME_PERCENTAGE,""));
    }

    @Override
    public void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
        ((HostActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ((HostActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(false);
        //уведомим активити, что фрагмент завершает работу, чтобы активити активировала кнопку настроек в меню
        eventListener.onStopSettingsFragment();
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals(TIME_NORM)||key.equals(NEED_TIME_PERCENTAGE))
            updateSummary();
        if (key.equals(ENABLE_COLORING)||key.equals(TIME_NORM)||key.equals(NEED_TIME_PERCENTAGE))
            if(getActivity()!=null)
                getActivity().invalidateOptionsMenu();

    }
}
