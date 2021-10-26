package sgd.tally.bidding.common;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Maintains the state of the auction:
 * <li>holds the current 2 top bids</li>
 * <li>accepts no more offers after the bidding is concluded</li>
 * <li>is thread safe see the readme for more discussion of this</li>
 *
 * The operations are synchronized, clients can call on different threads.
 * Read the parallelization considerations.
 *
 * @param <OFFER>
 * @param <BID>
 * @param <WINNINGBID>
 */
final public class Engine<OFFER, BID, WINNINGBID> implements Auction<OFFER, WINNINGBID> {
    private final String name;
    private final Function<OFFER, BID> newBid;
    private TopTwo<BID> topTwo;
    private final BiFunction<BID, BID, WINNINGBID> winningBid;
    private boolean isOpen;

    public Engine(String name, Function<OFFER, BID> newBid, TopTwo<BID> topTwo, BiFunction<BID, BID, WINNINGBID> winningBid) {
        this.name = Objects.requireNonNull(name);
        this.newBid = Objects.requireNonNull(newBid);
        this.topTwo = topTwo;
        this.winningBid = Objects.requireNonNull(winningBid);
        this.isOpen = true;
    }

    public String name() {return name;}

    synchronized public boolean offer(OFFER offer) {
        if (!isOpen) return false;
        var bid = newBid.apply(offer);
        topTwo = topTwo.offer(bid);
        return true;
    }

    synchronized public WINNINGBID conclude() {
        isOpen = false;
        return winningBid.apply(topTwo.first(), topTwo.second());
    }

    @Override
    public String toString() {
        return "ConfiguredAuction{" + name()  + '}';
    }
}
