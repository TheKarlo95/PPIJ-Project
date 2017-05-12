package hr.lordsofsmell.parfume.feature.core.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.infinum.mjolnirrecyclerview.MjolnirRecyclerAdapter;
import co.infinum.mjolnirrecyclerview.MjolnirViewHolder;
import hr.lordsofsmell.parfume.R;
import hr.lordsofsmell.parfume.domain.model.response.PerfumeItem;
import hr.lordsofsmell.parfume.utils.ImageUtils;
import hr.lordsofsmell.parfume.utils.PreferencesUtil;

public class PerfumeAdapter extends MjolnirRecyclerAdapter<PerfumeItem> {

    private OnPerfumeLikeClickListener likeListener;
    private OnPerfumeWishlistClickListener wishlistListener;
    private OnPerfumeOwnedClickListener ownedListener;

    public PerfumeAdapter(Context context,
                          OnPerfumeLikeClickListener likeListener,
                          OnPerfumeWishlistClickListener wishlistListener,
                          OnPerfumeOwnedClickListener ownedListener) {
        super(context, Collections.<PerfumeItem>emptyList());
        this.likeListener = likeListener;
        this.wishlistListener = wishlistListener;
        this.ownedListener = ownedListener;

    }

    @Override
    protected MjolnirViewHolder<PerfumeItem> onCreateItemViewHolder(ViewGroup parent,
                                                                    int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_perfume,
                parent,
                false);
        return new ViewHolder(view);
    }

    class ViewHolder extends MjolnirViewHolder<PerfumeItem> {

        @BindView(R.id.item_perfume_root) View rootView;

        @BindView(R.id.iv_perfume_image) ImageView ivImage;
        @BindView(R.id.tv_perfume_company) TextView tvCompany;
        @BindView(R.id.tv_perfume_name) TextView tvModel;
        @BindView(R.id.tv_perfume_year) TextView tvYear;
        @BindView(R.id.cb_perfume_like) CheckBox cbFavorite;
        @BindView(R.id.cb_perfume_wishlist) CheckBox cbWishlist;
        @BindView(R.id.cb_perfume_owned) CheckBox cbOwned;

        ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }

        @Override
        protected void bind(final PerfumeItem item, final int position, List<Object> payloads) {
            if (payloads.isEmpty()) {
                bind(item, position);
            } else {
                PerfumeItem perfume = (PerfumeItem) payloads.get(0);
                if (perfume != null) {
                    bind(perfume, position);
                }
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

        private void bind(PerfumeItem perfume, int position) {
            ImageUtils.loadImage(getContext(), ivImage, perfume.image());

            tvCompany.setText(perfume.company());
            tvModel.setText(perfume.model());
            tvYear.setText(perfume.year());

            boolean isLoggedIn = PreferencesUtil.isLoggedIn();
//            isLoggedIn = true; // use to bypass login

            setEnabled(isLoggedIn);

            if (isLoggedIn) {
                cbFavorite.setChecked(perfume.liked());
                cbWishlist.setChecked(perfume.wishlisted());
                cbOwned.setChecked(perfume.owned());
            }

            setListeners(perfume, position);
        }

        private void setEnabled(boolean enabled) {
            cbFavorite.setTag(enabled);
            cbFavorite.setFocusable(enabled);
            cbWishlist.setTag(enabled);
            cbWishlist.setFocusable(enabled);
            cbOwned.setTag(enabled);
            cbOwned.setFocusable(enabled);

            if (enabled) {
                cbFavorite.setButtonDrawable(R.drawable.ic_favorite);
                cbWishlist.setButtonDrawable(R.drawable.ic_wishlist);
                cbOwned.setButtonDrawable(R.drawable.ic_owned);
            } else {
                cbFavorite.setButtonDrawable(R.drawable.ic_favorite_disabled);
                cbWishlist.setButtonDrawable(R.drawable.ic_wishlist_disabled);
                cbOwned.setButtonDrawable(R.drawable.ic_owned_disabled);
            }
        }

        private void setListeners(final PerfumeItem perfume, final int position) {
            if (likeListener != null) {
                cbFavorite.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        likeListener.onLikeClick(itemView,
                                perfume,
                                position,
                                (Boolean) cbFavorite.getTag());
                    }
                });
            }
            if (wishlistListener != null) {
                cbWishlist.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        wishlistListener.onWishlistClick(itemView,
                                perfume,
                                position,
                                (Boolean) cbWishlist.getTag());
                    }
                });
            }
            if (ownedListener != null) {
                cbOwned.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ownedListener.onOwnedClick(itemView,
                                perfume,
                                position,
                                (Boolean) cbOwned.getTag());
                    }
                });
            }
        }
    }

    public interface OnPerfumeLikeClickListener {
        void onLikeClick(View view, PerfumeItem perfume, int position, boolean enabled);
    }

    public interface OnPerfumeWishlistClickListener {
        void onWishlistClick(View view, PerfumeItem perfume, int position, boolean enabled);
    }

    public interface OnPerfumeOwnedClickListener {
        void onOwnedClick(View view, PerfumeItem perfume, int position, boolean enabled);
    }
}
