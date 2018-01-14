package net.kaunghtetlin.brupple.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import net.kaunghtetlin.brupple.Data.VOs.PromotionVO;
import net.kaunghtetlin.brupple.R;
import net.kaunghtetlin.brupple.viewholders.GuideViewHolder;
import net.kaunghtetlin.brupple.viewholders.PromotionViewHolder;

/**
 * Created by Kaung Htet Lin on 1/10/2018.
 */

public class PromotionAdapter extends BaseRecyclerAdapter<PromotionViewHolder,PromotionVO> {

    public PromotionAdapter(Context context) {
        super(context);
    }

    @Override
    public PromotionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View promotionScreenView = mLayoutInflator.inflate(R.layout.view_item_promotions, parent, false);
        return new PromotionViewHolder(promotionScreenView);
    }

    @Override
    public void onBindViewHolder(PromotionViewHolder holder, int position) {
//        super.onBindViewHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        return 16;
    }
}
