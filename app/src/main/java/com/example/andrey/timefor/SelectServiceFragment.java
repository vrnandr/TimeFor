package com.example.andrey.timefor;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;


/**
 * Created by vrnandr on 26.08.18.
 */

public class SelectServiceFragment extends ListFragment implements LoaderManager.LoaderCallbacks<Cursor> {
    private CursorAdapter adapter;

    public interface OnServiceClickListener{
        void onServiceClick(String service);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        adapter = new SimpleCursorAdapter(getActivity(),
                android.R.layout.simple_list_item_1,
                null,
                new String[]{"Service"},
                new int[]{android.R.id.text1},
                0);
        setListAdapter(adapter);

        getLoaderManager().initLoader(MyCursorLoader.SERVICES,null, this);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        OnServiceClickListener onServiceClickListener = (OnServiceClickListener) getActivity();
        onServiceClickListener.onServiceClick(((TextView)v).getText().toString());

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new MyCursorLoader(getActivity(),MyCursorLoader.SERVICES,null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.swapCursor(null);
    }


}
