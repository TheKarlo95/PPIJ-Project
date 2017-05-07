package hr.lordsofsmell.parfume.utils;

import android.support.annotation.NonNull;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Predicate;

import static io.reactivex.Observable.concat;

/**
 * Created by Karlo Vrbic on 08.03.17.
 */
public class ObservableUtils {

    @SafeVarargs
    public static <T> Observable<T> getFirstNonNull(Observable<T>... objects) {
        Observable<T> out = null;

        for(Observable<T> object : objects) {
            if(object != null) {
                out = object;
                break;
            }
        }

        return out;
    }
}
