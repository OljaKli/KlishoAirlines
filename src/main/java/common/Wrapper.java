package common;

/**
 * Created by Ola-Mola on 30/05/16.
 */
@FunctionalInterface
public interface Wrapper<T> {
    @Private
    T toSrc();
}
