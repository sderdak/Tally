package sgd.tally.bidding.common;

/**
 * Keeps two elements, "first" and "second" from the offered elements.
 * Must be immutable, whenever the state changes, a new instance is returned.
 * The underlying implementation decides which two elements, and in what order are to be kept.
 * @param <T>
 */
public interface TopTwo<T> {
    TopTwo<T> offer(T t);

    T first();

    T second();
}
