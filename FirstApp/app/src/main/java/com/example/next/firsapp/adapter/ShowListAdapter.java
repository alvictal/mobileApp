package com.example.next.firsapp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.next.firsapp.R;
import com.example.next.firsapp.model.Images;
import com.example.next.firsapp.model.Show;
import com.example.next.firsapp.remote.service.OnClickShowListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by movile on 27/06/15.
 */
public class ShowListAdapter extends ArrayAdapter<Show> {
    private List<Show> mShow  = new ArrayList<>();
    private OnClickShowListener mListener;
    private Context mContext;

    public ShowListAdapter(Context context, OnClickShowListener onClickShowListener){
        super(context, R.layout.show_item);

        mContext = context;
        mListener = onClickShowListener;
    }

    public int getCount(){return mShow.size();}

    public Show getItem(int position) {return mShow.get(position);}

    public int getItemCount() {return mShow!= null ? mShow.size() : 0;}

    public View getView(int position, View view, ViewGroup parent){
        ViewHolder holder;
        int type = getItemViewType(position);
        if (view == null) {
            int resource = R.layout.show_item;
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
        final Show show = getItem(position);

        Glide.with(mContext).load(show.images().poster().get(Images.ImageSize.THUMB)).
                placeholder(R.drawable.highlight_placeholder).
                centerCrop().
                into((holder.getShowImage()));

        holder.setPosition(position);

        holder.getShowImage().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getPosition();
                mListener.onClickShow(getItem(position));
            }
        });

    }

    public void updateShow(List<Show> showP) {
        mShow = showP;
        notifyDataSetChanged();
    }

    public class ViewHolder {
        private ImageView showImage;
        private int position;

        public ViewHolder(View root) {
            showImage = (ImageView) root.findViewById(R.id.IV_Show_Main_Image);
        }
        public ImageView getShowImage() {
            return showImage;
        }

        public void setPosition(int position) {
            this.position = position;
        }

        public int getPosition() {
            return position;
        }
    }

}
