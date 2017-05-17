package hr.lordsofsmell.parfume.feature.core.adapter;

import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import java.util.List;

import hr.lordsofsmell.parfume.domain.model.response.PerfumeItem;

class PerfumeItemsDiffUtil extends DiffUtil.Callback {

    private List<PerfumeItem> oldList;

    private List<PerfumeItem> newList;

    PerfumeItemsDiffUtil(List<PerfumeItem> oldList, List<PerfumeItem> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        return newList.get(newItemPosition);
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).id() == newList.get(newItemPosition).id();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).equals(newList.get(newItemPosition));
    }
}