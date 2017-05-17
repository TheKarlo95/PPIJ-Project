package hr.lordsofsmell.parfume.utils;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import hr.lordsofsmell.parfume.R;

public class ImageUtils {

    private ImageUtils() {
    }

    public static void loadImage(@NonNull Context context,
                                 @NonNull ImageView imageView,
                                 String url) {
        if (url != null) {
            Glide.with(context)
                    .load(url)
                    .centerCrop()
                    .placeholder(R.drawable.ic_perfume_no_image)
                    .thumbnail(0.1f)
                    .crossFade()
                    .into(imageView);
        }
    }

    public static void loadImage(@NonNull Activity activity,
                                 @NonNull ImageView imageView,
                                 String url) {
        if (url != null) {
            Glide.with(activity)
                    .load(url)
                    .centerCrop()
                    .placeholder(R.drawable.ic_perfume_no_image)
                    .thumbnail(0.1f)
                    .crossFade()
                    .into(imageView);
        }
    }
}
