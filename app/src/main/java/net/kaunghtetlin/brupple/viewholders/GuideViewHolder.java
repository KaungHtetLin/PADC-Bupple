package net.kaunghtetlin.brupple.viewholders;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import net.kaunghtetlin.brupple.Data.VOs.GuideVO;
import net.kaunghtetlin.brupple.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Kaung Htet Lin on 1/10/2018.
 */

public class GuideViewHolder extends BaseViewHolder<GuideVO> {

    @BindView(R.id.iv_guide)
    ImageView ivGuide;

    public GuideViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    @Override
    public void setData(GuideVO data) {

    }

    @Override
    public void bind(Context context) {

    }
}
