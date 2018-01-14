package net.kaunghtetlin.brupple.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import net.kaunghtetlin.brupple.Data.VOs.GuideVO;
import net.kaunghtetlin.brupple.R;
import net.kaunghtetlin.brupple.viewholders.GuideViewHolder;

/**
 * Created by Kaung Htet Lin on 1/10/2018.
 */

public class GuideAdapter extends BaseRecyclerAdapter<GuideViewHolder,GuideVO> {

    public GuideAdapter(Context context) {
        super(context);
    }

    @Override
    public GuideViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View guideScreenView = mLayoutInflator.inflate(R.layout.view_item_guides, parent, false);
        return new GuideViewHolder(guideScreenView);
    }

    @Override
    public void onBindViewHolder(GuideViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 16;
    }
}
