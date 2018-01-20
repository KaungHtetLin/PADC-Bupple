package net.kaunghtetlin.brupple.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import net.kaunghtetlin.brupple.data.vos.PromotionsVO;
import net.kaunghtetlin.brupple.R;
import net.kaunghtetlin.brupple.viewholders.PromotionsViewHolder;

/**
 * Created by Kaung Htet Lin on 1/10/2018.
 */

public class PromotionsAdapter extends BaseRecyclerAdapter<PromotionsViewHolder, PromotionsVO> {

    public PromotionsAdapter(Context context) {
        super(context);
    }

    @Override
    public PromotionsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View promotionScreenView = mLayoutInflator.inflate(R.layout.view_item_promotions, parent, false);
        return new PromotionsViewHolder(promotionScreenView);
    }
}
