package net.kaunghtetlin.brupple.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.kaunghtetlin.brupple.R;
import net.kaunghtetlin.brupple.viewitems.HighlightImagesViewItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kaung Htet Lin on 1/9/2018.
 */

public class HighlightImagePagerAdapter extends PagerAdapter{

    private LayoutInflater mLayoutInflater;
    private List<String> mImages;

    public HighlightImagePagerAdapter(Context context) {
        super();
        mLayoutInflater = LayoutInflater.from(context);
        mImages = new ArrayList<>();

    }

    @Override
    public int getCount() {
//        return mImages.size();
        return 5;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == (View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        HighlightImagesViewItem itemView = (HighlightImagesViewItem) mLayoutInflater.inflate(R.layout.view_item_highlight_image, container, false);
//        itemView.setData(mImages.get(position));
        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    public void setImages(List<String> images) {
        mImages = images;
        notifyDataSetChanged();
    }
}
