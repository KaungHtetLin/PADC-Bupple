package net.kaunghtetlin.brupple.viewholders;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import net.kaunghtetlin.brupple.data.vos.GuidesVO;
import net.kaunghtetlin.brupple.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Kaung Htet Lin on 1/10/2018.
 */

public class GuidesViewHolder extends BaseViewHolder<GuidesVO> {

    @BindView(R.id.iv_guide)
    ImageView ivGuide;

    private GuidesVO mGuides;

    public GuidesViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    @Override
    public void setData(GuidesVO data) {
        mGuides=data;
    }

    @Override
    public void bind(Context context) {
        if (mGuides != null) {

            if (mGuides.getBurppleGuidesImage() != null) {
                Glide.with(context)
                        .load(mGuides.getBurppleGuidesImage())
                        .into(ivGuide);
            }
        }
    }
}
