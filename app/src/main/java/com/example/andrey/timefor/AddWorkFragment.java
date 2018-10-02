package com.example.andrey.timefor;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by vrnandr on 27.08.18.
 */

public class AddWorkFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>, AddWorkFragmentCursorAdapter.OnCheckBoxClickListener{

    public static final String KEY = "SERVICE";
    SimpleCursorAdapter adapter;

    Set<Integer> idWorksToAdd = new HashSet<>();


    public interface OnAddWorkButtonClickListener{
        void onAddWorkButtonClick(Set<Integer> idWorksToAdd);
        void onChangeTimeInMenu(Integer time);
    }
    private  OnAddWorkButtonClickListener onAddWorkButtonClickListener;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.add_work_fragment,container,false);

        ListView lv = v.findViewById(R.id.add_work_fragment_listview);
        adapter = new AddWorkFragmentCursorAdapter(getActivity(),
                R.layout.add_work_fragment_listview_item,
                null,
                new String[]{"ShortDesc","TimeNorm"},
                new int[]{R.id.add_work_fragment_listview_item_description, R.id.add_work_fragment_listview_item_time},
                this);

        lv.setAdapter(adapter);

        v.findViewById(R.id.button_add_work).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddWorkButtonClickListener.onAddWorkButtonClick(idWorksToAdd);
            }
        });

        onAddWorkButtonClickListener = (OnAddWorkButtonClickListener) getActivity();

        return v;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getLoaderManager().initLoader(MyCursorLoader.WORKS,null,this);
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new MyCursorLoader(getActivity(), MyCursorLoader.WORKS,getArguments());
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.swapCursor(null);
    }

    @Override
    public void onCheckBoxClick(HashMap<Integer,Integer> data) {

        idWorksToAdd.clear();
        idWorksToAdd.addAll(data.keySet());

        Integer time =0;
        for(Integer t:data.values()){
            time+=t;
        }
        onAddWorkButtonClickListener.onChangeTimeInMenu(time);

    }
}
