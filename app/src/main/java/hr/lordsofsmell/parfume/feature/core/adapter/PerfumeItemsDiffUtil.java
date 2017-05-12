package hr.lordsofsmell.parfume.feature.core.adapter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import java.util.List;
import java.util.Objects;

import hr.lordsofsmell.parfume.domain.model.response.PerfumeItem;

class PerfumeItemsDiffUtil extends DiffUtil.Callback {

    private List<PerfumeItem> oldList;

    private List<PerfumeItem> newList;

    public PerfumeItemsDiffUtil(List<PerfumeItem> oldList, List<PerfumeItem> newList) {
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
        return oldList.get(oldItemPosition).id().equals(newList.get(newItemPosition).id());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).equals(newList.get(newItemPosition));
    }

    private static void putToBundle(@NonNull Bundle bundle, @NonNull String key, String oldValue, String newValue) {
        if (!Objects.equals(oldValue, newValue) && newValue != null) {
            bundle.putString(key, newValue);
        }
    }
}