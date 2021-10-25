package sgd.tally.bidding.common;

/**
 * Interface for the auction engine.
 *
 * @param <T> type of the offer
 * @param <R> type of the result
 */
public interface Auction<T, R> {
    /**
     * As long an auction is not concluded offers can be added.
     * @param offer submitted to the auction
     * @return true if the offer was accepted for the bidding
     */
    boolean offer(T offer);
    R conclude();

}
