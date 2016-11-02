package com.leohulabb.module.testpicasso;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.commonutils.appupdata.manager.UpdateManager;
import com.leohulabb.R;
import com.leohulabb.utils.picassoUtils.PicassoImageLoader;
import com.leohulabb.utils.picassoUtils.transformation.GrayscaleTransformation;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Leo on 2016/7/22.
 */

public class TestPicassoItemAdapter extends RecyclerView.Adapter<TestPicassoItemAdapter.ViewHolder>
{
    private List<Type> mDataSet;
    private Context context;

    public enum Type {
        BLUR,
        CROPCIRCLE,
        CROPSQUARE,
        CROP,
        GRAYSCALE,
        ROUNDEDCORNERS
    }

    public TestPicassoItemAdapter(List<Type> mDataSet) {
        this.mDataSet = mDataSet;
    }

    @Override
    public TestPicassoItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image_test, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(TestPicassoItemAdapter.ViewHolder holder, int position) {

        switch (mDataSet.get(position))
        {
            case BLUR:
                PicassoImageLoader.getImageLoader().picassoBlurLoader(R.mipmap.bg_test, holder.image);
                break;
            case CROPCIRCLE:
                PicassoImageLoader.getImageLoader().picassoCircleLoader(R.mipmap.bg_test, holder.image);
                break;
            case CROPSQUARE:
                break;
            case CROP:
                PicassoImageLoader.getImageLoader().picassoCircleLoader(R.mipmap.bg_test + "", holder.image);
                break;
            case GRAYSCALE:
                Picasso.with(context).load(R.mipmap.bg_test).transform(new GrayscaleTransformation()).into(holder.image);
                break;
            case ROUNDEDCORNERS:
                PicassoImageLoader.getImageLoader().picassoRoundedLoader(R.mipmap.bg_test, holder.image);
                break;
            default:
                break;
        }

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new UpdateManager(context).checkUpdate(true);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;

        public ViewHolder(View view) {
            super(view);
            image = (ImageView) view.findViewById(R.id.image);
        }
    }
}
