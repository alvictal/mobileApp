package com.example.next.firsapp.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.next.firsapp.R;
import com.example.next.firsapp.database.dbflow.FavoriteEntitydb;
import com.example.next.firsapp.remote.service.OnClickFavoriteListener;

/**
 * Created by movile on 05/07/15.
 */
public class FavoriteAdapter extends CursorAdapter{
    private Context mContext;
    private OnClickFavoriteListener mListener;

    public FavoriteAdapter(Context context, Cursor cursor, OnClickFavoriteListener listener ) {
        super(context, cursor, 0);
        mContext = context;
        mListener = listener;
    }

    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.favorite_show_item, parent, false);

        ViewHolder holder = new ViewHolder(view);
        view.setTag(holder);

        return view;
    }

    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        final FavoriteEntitydb entity = new FavoriteEntitydb();
        entity.fromCursor(cursor);

        viewHolder.name().setText(entity.title());
        viewHolder.root().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.clickFavorite(entity.slug());
            }
        });
    }

    public void updateFavorite(Cursor cursor) {
        swapCursor(cursor);
    }

    public static class ViewHolder {
        private View mRoot;
        private TextView mTitle;

        public ViewHolder(View root) {
            mRoot = root;
            mTitle = (TextView) root.findViewById(R.id.TV_Favorite_Show_Name);
        }

        public TextView name(){
            return  mTitle;
        }

        public View root(){
            return mRoot;
        }

    }
}
