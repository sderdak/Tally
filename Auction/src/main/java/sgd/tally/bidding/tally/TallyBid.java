package sgd.tally.bidding.tally;

/**
 * Implements the
 */
final class TallyBid {
    private final Offer offer;
    private final int finalBid;

    public TallyBid(Offer offer) {
        this.offer = offer;
        final int maxBid = offer.maxBid();
        final int increment = offer.increment();
        final int startingBid = offer.startingBid();
        final int n = (maxBid - startingBid) / increment;
        this.finalBid = startingBid + n * increment;
        assert this.finalBid <= maxBid;
    }

    public Offer offer() {return offer;}

    public int overcall(TallyBid other) {
        return overcall(other.finalBid);
    }

    public int overcall(int amount) {
        final int increment = offer.increment();
        final int startingBid = offer.startingBid();
        if (amount < startingBid) return startingBid;
        assert finalBid >= amount;
        var m = (finalBid - amount) % increment;
        var adjustment = m == 0 ? increment : m;
        var overcall = amount + adjustment;
        assert overcall <= finalBid;
        return overcall;
    }

    public static WinningOffer winningOffer(TallyBid first, TallyBid second) {
        if (first == null) return null;
        final int effectiveBid;
        if (second == null) {
            effectiveBid = first.offer().startingBid();
        } else {
            effectiveBid = first.overcall(second);
        }
        return new WinningOffer(first.offer(), effectiveBid);
    }

    public int compareByFinalBid(TallyBid other) {return finalBid - other.finalBid;}

}
