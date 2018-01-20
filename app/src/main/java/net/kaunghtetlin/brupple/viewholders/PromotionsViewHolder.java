package net.kaunghtetlin.brupple.viewholders;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import net.kaunghtetlin.brupple.BurppleApp;
import net.kaunghtetlin.brupple.R;
import net.kaunghtetlin.brupple.data.vos.PromotionsVO;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Kaung Htet Lin on 1/10/2018.
 */

public class PromotionsViewHolder extends BaseViewHolder<PromotionsVO>{

    @BindView(R.id.iv_promotions)
    ImageView ivPromotions;

    @BindView(R.id.tv_promotions_title)
    TextView tvPromotionsTitle;

    @BindView(R.id.tv_promotion_due_date)
    TextView tvUntil;

    @BindView(R.id.tv_shop_name)
    TextView tvShopName;

    @BindView(R.id.tv_area)
    TextView tvArea;


    private PromotionsVO mPromotions;

    public PromotionsViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(PromotionsVO data) {
        mPromotions=data;
    }

    @Override
    public void bind(Context context) {
        if (mPromotions != null) {
            if (mPromotions.getBurpplePromotionTitle() != null)
                tvPromotionsTitle.setText(mPromotions.getBurpplePromotionTitle());

            if (mPromotions.getBurpplePromotionUntil() != null)
                tvUntil.setText(mPromotions.getBurpplePromotionUntil());

            if (mPromotions.getBurpplePromotionImage() != null) {
                Glide.with(context)
                        .load(mPromotions.getBurpplePromotionImage())
                        .into(ivPromotions);
            }

            if (mPromotions.getBurpplePromotionShop().getBurppleShopName() != null) {
                tvShopName.setText(mPromotions.getBurpplePromotionShop().getBurppleShopName() );
            }

            if (mPromotions.getBurpplePromotionShop().getBurppleShopArea() != null) {
                tvArea.setText(mPromotions.getBurpplePromotionShop().getBurppleShopArea() );
            }
        }
    }
}
