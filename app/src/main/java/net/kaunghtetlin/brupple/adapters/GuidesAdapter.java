package net.kaunghtetlin.brupple.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import net.kaunghtetlin.brupple.data.vos.GuidesVO;
import net.kaunghtetlin.brupple.R;
import net.kaunghtetlin.brupple.viewholders.GuidesViewHolder;

/**
 * Created by Kaung Htet Lin on 1/10/2018.
 */

public class GuidesAdapter extends BaseRecyclerAdapter<GuidesViewHolder,GuidesVO> {

    public GuidesAdapter(Context context) {
        super(context);
    }

    @Override
    public GuidesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View guideScreenView = mLayoutInflator.inflate(R.layout.view_item_guides, parent, false);
        return new GuidesViewHolder(guideScreenView);
    }
}
