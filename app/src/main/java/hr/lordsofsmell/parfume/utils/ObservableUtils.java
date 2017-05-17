package hr.lordsofsmell.parfume.utils;

import io.reactivex.Observable;

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
