package com.tnb.f3r10.tutorinhome.tutorlist.ui.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.tnb.f3r10.tutorinhome.R;
import com.tnb.f3r10.tutorinhome.domain.Util;
import com.tnb.f3r10.tutorinhome.entities.Tutor;
import com.tnb.f3r10.tutorinhome.lib.base.ImageLoader;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by f3r10 on 13/7/16.
 */
public class TutorListAdapter extends RecyclerView.Adapter<TutorListAdapter.ViewHolder> {


    private List<Tutor> tutorList;
    private ImageLoader imageLoader;
    private OnItemClickListener onItemClickListener;
    private Util util;

    public TutorListAdapter(List<Tutor> tutorList, ImageLoader imageLoader, OnItemClickListener onItemClickListener, Util util) {
        this.tutorList = tutorList;
        this.imageLoader = imageLoader;
        this.onItemClickListener = onItemClickListener;
        this.util = util;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_tutor, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Tutor tutor = tutorList.get(position);
        holder.setClickListener(tutor, onItemClickListener);
        String institution = "Escuela Politecnica Nacional";
        String price = "10 USD";
        Float rating = tutor.getRatings().getNumber();
        String status = "offline";
        int color = Color.RED;
        imageLoader.load(holder.imgTutor, util.getAvatarUrl(tutor.getPicture_uri()));
        holder.editTxtNameTutor.setText(tutor.getNickName());
        holder.ratingBar.setRating(rating);
        if(tutor.getEducationTutor().size() > 0){
            institution = tutor.educationTutor.get(0).getInstitution();
        }
        holder.editTxtEducation.setText(institution);

        holder.editTxtPrice.setText(Integer.toString(tutor.getPrice()) + " USD");
        holder.txtStatus.setText(status);
        holder.txtStatus.setTextColor(color);
    }

    public void setTutors(List<Tutor> tutors) {
        this.tutorList = tutors;
        notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        return tutorList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.imgTutor)
        ImageView imgTutor;
        @Bind(R.id.editTxtNameTutor)
        TextView editTxtNameTutor;
        @Bind(R.id.ratingBar)
        RatingBar ratingBar;
        @Bind(R.id.editTxtEducation)
        TextView editTxtEducation;
        @Bind(R.id.editTxtPrice)
        TextView editTxtPrice;
        @Bind(R.id.txtStatus)
        TextView txtStatus;

        private View view;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.view = itemView;
        }

        private void setClickListener(final Tutor tutor, final OnItemClickListener listener) {
            view.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    listener.onItemClick(tutor);
                }
            });

            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    listener.onItemLongClick(tutor);
                    return false;
                }
            });
        }
    }
}
