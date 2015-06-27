package com.example.next.firsapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.next.firsapp.R;
import com.example.next.firsapp.model.Episode;
import com.example.next.firsapp.remote.service.OnClickEpisodeListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aluisio on 6/21/15.
 */
public class EpisodeListAdapter extends ArrayAdapter<Episode> {
    private List<Episode> episodes = new ArrayList<>();
    private OnClickEpisodeListener mListener;
    Context context;

    public EpisodeListAdapter(Context contextP, OnClickEpisodeListener mListenerP) {
        super(contextP, R.layout.episode_item);
        context = contextP;
        mListener = mListenerP;
    }

    public int getCount() { return episodes.size(); }

    public Episode getItem(int position) { return  episodes.get(position);}

    public long getItemId(int position) { return position; }

    public View getView(int position, View view, ViewGroup parent){
        ViewHolder holder;
        int type = getItemViewType(position);
        if (view == null) {
            int resource = R.layout.episode_item;
            view = LayoutInflater.from(parent.getContext()).
                    inflate(resource, parent, false);

            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }


        populateViewFromHolder(holder, position, type);
        return view;
    }

    public void populateViewFromHolder( final ViewHolder holder, int position, int type){
        final Episode episode = getItem(position);

        holder.getTitle().setText(episode.title().toString());
        holder.getNumber().setText(episode.number().toString());
        holder.setPosition(position);

        holder.getTitle().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getPosition();
                mListener.onClickEpisodeL(getItem(position));
            }
        });

    }

    public void updateEpisodes(List<Episode> episodeP) {
        episodes = episodeP;
        notifyDataSetChanged();
    }

    public class ViewHolder {
        private TextView title;
        private TextView number;
        private int position;

        public ViewHolder(View root) {
            title = (TextView) root.findViewById(R.id.TV_Episode_Item_Title);
            number = (TextView) root.findViewById(R.id.TV_Episode_Item_Number);
        }
        public TextView getTitle() {
            return title;
        }

        public TextView getNumber() {
            return number;
        }

        public void setPosition(int position) {
            this.position = position;
        }

        public int getPosition() {
            return position;
        }
    }
}


