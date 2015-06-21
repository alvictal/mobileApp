package com.example.next.firsapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;

import com.example.next.firsapp.R;
import com.example.next.firsapp.model.Episode;

import java.util.List;

/**
 * Created by aluisio on 6/21/15.
 */
public class EpisodeListAdapter extends ArrayAdapter<Episode> {
    private List<Episode> episodes;

    public EpisodeListAdapter(Context contextP){}

    public int getCount() { return episodes.size(); }

    public Episode getItem(int position) { return  episodes.get(position);}

    public long getItemId(int position) { return position; }

    public View getView(int position, View view, ViewGroup parent){
        ViewHolder holder;
        int type = getItemViewType(position);
        if (view == null) {
            int resoource = R.layout.episode_item;
            view = LayoutInflater.from(parent.getContext()).
                    inflate(resoource, parent, false);

            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        
        return view;
    }

    public static class ViewHolder {
        private View mView;
        public ViewHolder(View root) {
            mView = root.findViewById(R.id.LV_Season_Details_Episodes);
        }
        public View view() {
            return mView;
        }
    }

}
