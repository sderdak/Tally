package sgd.tally.bidding.common;


import java.util.Comparator;
import java.util.Objects;

/**
 * Tracks the two largest offered elements.
 */
public class TopTwoByComparator<T> implements TopTwo<T> {
    private final Comparator<T> comparator;
    private final T first;
    private final T second;

    public TopTwoByComparator(Comparator<T> comparator) {
        this(comparator,null, null);
    }

    private TopTwoByComparator(Comparator<T> comparator, T first) {
        this(comparator, Objects.requireNonNull(first), null);
    }

    private TopTwoByComparator(Comparator<T> comparator, T first, T second) {
        this.comparator = Objects.requireNonNull(comparator);
        this.first = first;
        this.second = second;
    }

    /**
     * An element offered replaces the current first or second elements only if it is greater than the current ones.
     * The receiver is immutable, when unchanged the receiver is returned, otherwise a new instance.
     */
    @Override
    public TopTwoByComparator<T> offer(T t) {
        if (first == null) {
            return new TopTwoByComparator<>(comparator, t);
        } else {
            if (comparator.compare(t, first) > 0) {
                return new TopTwoByComparator<>(comparator, t, first);
            } else if (second == null || comparator.compare(t, second) > 0) {
                return new TopTwoByComparator<>(comparator, first, t);
            } else {
                return this;
            }
        }
    }

    @Override
    public T first() {return first;}

    @Override
    public T second() {return second;}
}
