package hr.lordsofsmell.parfume.feature.perfumelist.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.infinum.mjolnirrecyclerview.MjolnirRecyclerAdapter;
import co.infinum.mjolnirrecyclerview.MjolnirViewHolder;
import hr.lordsofsmell.parfume.R;
import hr.lordsofsmell.parfume.domain.model.response.PerfumeItem;

public class PerfumeAdapter extends MjolnirRecyclerAdapter<PerfumeItem> {

    public PerfumeAdapter(Context context, Collection<PerfumeItem> list) {
        super(context, list);
    }

    public PerfumeAdapter(Context context) {
        this(context, Collections.<PerfumeItem>emptyList());
    }

    @Override
    protected MjolnirViewHolder<PerfumeItem> onCreateItemViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_perfume, parent, false);
        return new ViewHolder(view);
    }

    private class ViewHolder extends MjolnirViewHolder<PerfumeItem> {

        @BindView(R.id.item_perfume_root)
        View rootView;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        protected void bind(final PerfumeItem item, final int position, List<Object> payloads) {
            if (payloads.isEmpty()) {
                // use item
            } else {
                // use payloads
            }

            rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onClick(position, item);
                    }
                }
            });
        }
    }
}
