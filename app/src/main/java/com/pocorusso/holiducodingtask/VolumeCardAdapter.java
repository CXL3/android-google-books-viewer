package com.pocorusso.holiducodingtask;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;


/**
 * Adapter to be used in recycler view
 */
public class VolumeCardAdapter extends RecyclerView.Adapter<VolumeCardAdapter.ViewHolder> {

    private Context mContext;

    //list of volumes
    private List<Volume> mVolumes;

    public VolumeCardAdapter(Context context, List<Volume> volumes) {
        super();
        mContext = context;
        mVolumes = volumes;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_volume, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Volume volume = mVolumes.get(position);

        ImageLoader imageLoader = VolumeVolleyRequest.getInstance(mContext).getImageLoader();
        imageLoader.get(volume.getImageUrl(), ImageLoader.getImageListener(holder.mImageView, R.mipmap.ic_launcher, android.R.drawable.ic_dialog_alert));
        holder.mImageView.setImageUrl(volume.getImageUrl(), imageLoader);
        holder.mTextViewTitle.setText(volume.getTitle());
    }

    @Override
    public int getItemCount() {
        return mVolumes.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        protected NetworkImageView mImageView;
        protected TextView mTextViewTitle;

        protected ViewHolder(View itemView) {
            super(itemView);
            mImageView = (NetworkImageView) itemView.findViewById(R.id.volume_cover);
            mTextViewTitle = (TextView) itemView.findViewById(R.id.volume_title);

        }

    }
}
