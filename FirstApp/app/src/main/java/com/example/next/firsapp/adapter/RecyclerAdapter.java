package com.example.next.firsapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.next.firsapp.R;
import com.example.next.firsapp.model.Episode;
import com.example.next.firsapp.model.Images;
import com.example.next.firsapp.model.Season;
import com.example.next.firsapp.remote.service.OnClickSeasonListener;

import java.util.List;

/**
 * Created by movile on 27/06/15.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private Context mContext;
    private List<Season> mSeasons;
    private OnClickSeasonListener mListener;
    private int mLayout;

    public RecyclerAdapter(Context context, int layout, OnClickSeasonListener onClickSeasonListener) {
        mContext  = context;
        mLayout = layout;
        mListener = onClickSeasonListener;
    }

    public void updateContents(List<Season> seasons) {
        mSeasons = seasons;
        notifyDataSetChanged();
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(mLayout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Season content = mSeasons.get(position);
        String screenshotUrl = content.images().poster().get(Images.ImageSize.FULL);

        Glide.with(mContext).load(screenshotUrl).
                placeholder(R.drawable.highlight_placeholder).
                centerCrop().
                into( holder.seasonImage());

        holder.seasonNumber().setText("Season " + content.number().toString());
        holder.episodeNumbers().setText(content.episodeCount().toString() + " Episodes");
        holder.setPosition(position);
        holder.seasonImage().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getPositionp();
                mListener.onClickSeason(getItem(position));
            }
        });
    }

    public Season getItem(int position) { return  mSeasons.get(position);}

    @Override
    public int getItemCount() {
        return mSeasons != null ? mSeasons.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView  mSeasonView;
        private TextView mSeasonText;
        private TextView mEpisodeText;
        private View mRoot;
        private int position;

        public ViewHolder(View view) {
            super(view);

            mRoot = view;
            mSeasonView = (ImageView) mRoot.findViewById(R.id.IV_Show_Season);
            mSeasonText = (TextView) mRoot.findViewById(R.id.TV_Show_Season_Number);
            mEpisodeText = (TextView) mRoot.findViewById(R.id.TV_Show_Episode_Number);
        }

        public View root() {return  mRoot;}

        public ImageView seasonImage() {return mSeasonView;}

        public TextView seasonNumber() {return mSeasonText;}

        public TextView episodeNumbers() {return  mEpisodeText;}

        public void setPosition(int position){ this.position = position;}

        public int getPositionp() {return  position;}
    }
}