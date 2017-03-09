package hr.lordsofsmell.parfume.utils;

import android.support.annotation.NonNull;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;

import static io.reactivex.Observable.concat;

/**
 * Created by Karlo Vrbic on 08.03.17.
 */
public class ObservableUtils {

    private static <T> Observable<T> getFirstNonNull(@NonNull ObservableSource<? extends T> source1,
                                                     @NonNull ObservableSource<? extends T> source2,
                                                     @NonNull ObservableSource<? extends T> source3,
                                                     @NonNull ObservableSource<? extends T> source4) {
        return Observable
                .concat(source1, source2, source3, source4)
                .filter(obj -> obj != null)
                .firstElement()
                .toObservable();
    }

    private static <T> Observable<T> getFirstNonNull(@NonNull ObservableSource<? extends T> source1,
                                                     @NonNull ObservableSource<? extends T> source2,
                                                     @NonNull ObservableSource<? extends T> source3) {
        return
                concat(source1, source2, source3)
                .filter(obj -> obj != null)
                .firstElement()
                .toObservable();
    }

    private static <T> Observable<T> getFirstNonNull(@NonNull ObservableSource<? extends T> source1,
                                                     @NonNull ObservableSource<? extends T> source2) {
        return
                concat(source1, source2)
                .filter(obj -> obj != null)
                .firstElement()
                .toObservable();
    }
}
