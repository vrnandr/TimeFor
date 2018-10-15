package com.example.andrey.timefor;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.SimpleCursorTreeAdapter;
import android.widget.TextView;

/**
 * Created by vrnandr on 26.08.18.
 */

public class MainFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>, OnHostActivityEventListener{
    public final static String KEY = "date";

    private final static String TAG = "My";

    SimpleCursorTreeAdapter adapter;

    @Override
    public void onUpdateData() {
        getLoaderManager().getLoader(MyCursorLoader.DAYS).forceLoad();
    }

    public interface OnMainFragmentEventListener {
        void onFABClick();
        void onChildItemClick(int id);
    }

    OnMainFragmentEventListener eventListener;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        eventListener = (OnMainFragmentEventListener) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.main_fragment,container, false);

        v.findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventListener.onFABClick();
            }
        });


        adapter = new SimpleCursorTreeAdapter(
                getActivity(),
                null,
                // вывод группы переопределен в getGroupView
                0,
                null,
                null,

                R.layout.main_fragment_listview_child_item,
                new String[]{"work", "time"},
                new int[] {R.id.child_item_service,R.id.child_item_time}
        ) {
            @Override
            protected Cursor getChildrenCursor(Cursor groupCursor) {
                int id = groupCursor.getPosition();
                CharSequence date = groupCursor.getString(groupCursor.getColumnIndex("date"));
                Bundle bundle = new Bundle();
                bundle.putCharSequence(KEY, date);
                Loader<Cursor> myCursorLoader = getLoaderManager().getLoader(id);
                if (myCursorLoader!= null && !myCursorLoader.isReset()){
                    getLoaderManager().restartLoader(id,bundle,MainFragment.this);
                } else
                    getLoaderManager().initLoader(id,bundle,MainFragment.this);
                return null;
            }

            // для лучшего вида даты в списке (дд.мм.гггг)
            @Override
            public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

                if (convertView == null)
                    convertView = getLayoutInflater().inflate(R.layout.main_fragment_listview_group_item, parent, false);

                TextView tvDate = convertView.findViewById(R.id.group_item_date);
                TextView tvTime = convertView.findViewById(R.id.group_item_sum);
                // берем дату с базы в формате гггг-мм-дд
                String date = getGroup(groupPosition).getString(getGroup(groupPosition).getColumnIndex("date"));
                //конвертируем ее к виду дд.мм.гггг
                String fDate = date.substring(8,10)+"."+date.substring(5,7)+"."+date.substring(0,4);
                tvDate.setText(fDate);
                tvTime.setText(getGroup(groupPosition).getString(getGroup(groupPosition).getColumnIndex("sum")));


                return convertView;
            }
        };

        ExpandableListView expandableListView = v.findViewById(R.id.main_fragment_listview);
        expandableListView.setAdapter(adapter);
        expandableListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                //parent.setItemChecked(childPosition, true);
                Log.d(TAG, "onChildClick: "+id);
                return true;
            }
        });
        expandableListView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            int id=0;

            @Override
            public boolean onCreateActionMode(android.view.ActionMode mode, Menu menu) {
                MenuInflater inflater = mode.getMenuInflater();
                inflater.inflate(R.menu.main_fragment_contex_menu, menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(android.view.ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(android.view.ActionMode mode, MenuItem item) {
                switch (item.getItemId()){
                    case R.id.item_menu_delete:
                        eventListener.onChildItemClick(id);
                        mode.finish();
                        return true;
                    default:
                        return false;
                }
            }

            @Override
            public void onDestroyActionMode(android.view.ActionMode mode) {

            }

            @Override
            public void onItemCheckedStateChanged(android.view.ActionMode mode, int position, long i, boolean checked) {
                id = ExpandableListView.getPackedPositionChild(i);
            }


        });

        getLoaderManager().initLoader(MyCursorLoader.DAYS,null,this);

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        getLoaderManager().getLoader(MyCursorLoader.DAYS).forceLoad();
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new MyCursorLoader(getActivity(),id,args);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        int id = loader.getId();
        Log.d(TAG, "onLoadFinished: id ="+id);
        if (id==MyCursorLoader.DAYS) adapter.changeCursor(data);
        if (id>=0) {
            try{ //todo ниебу откуда ошибка при добавлении первой записи какой-либо даты
                adapter.setChildrenCursor(id,data);
            } catch (NullPointerException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.changeCursor(null);
    }

}
