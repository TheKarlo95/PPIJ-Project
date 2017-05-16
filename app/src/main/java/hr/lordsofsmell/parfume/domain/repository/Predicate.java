package hr.lordsofsmell.parfume.domain.repository;
public interface Predicate<T> {
    boolean test(T t);
}
