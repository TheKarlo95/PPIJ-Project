package hr.lordsofsmell.parfume.feature.core;

import android.support.v7.widget.RecyclerView;

public abstract class OnScrollToBottomListener extends RecyclerView.OnScrollListener {

    @Override
    public final void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        if (!recyclerView.canScrollVertically(1)) {
            onScrollToBottom(recyclerView, dx, dy);
        }
    }

    public abstract void onScrollToBottom(RecyclerView recyclerView, int dx, int dy);
}