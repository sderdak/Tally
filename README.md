# Tally test
#Solution structure
* sgd.tally.bidding.common - common, reusable bidding support, is agnostic of the particular tally auction rules and types
* sgd.tally.bidding.tally - tally auction rules specific code

#Some design decisions
* TopTwo tracks the top to offers, it is immutable, whenever its updated it returns the unchanged self, or a new instance
* Engine is synchronized accepts calls on different threads, holds the current TopTwo, and such is the single mutable class.
* The code is separated into tally bidding support and common bidding support. to allow for some changes, such as using a different final price determination, or different input and output structures - Offer, WinningOffer
* Use java record and not interface for Offer and WinningOffer, to assure their immutability, avoid bloated abstraction and not last take advantage of the terse form

#Not implemented but to be considered
* How to scale better (still without persistence)
    - E.G taking advantage of the immutable TopTwo a non sync filter function could be implemented to allow only possible candidates to the synchronized TopTwo::offer, however this would fail if the offers arrive in ascending order
* Use java modules to restrict the client code's access
* Use java service provider interfaces instead allowing access in the client code to the TallyAuction::newAuction factory method 
* Use Optional instead of NULL pointer - I decided not to use as typically RestAPI developers will convert it back to null.  

## Test coverage
* Current test coverage  is complete. (Intellij)

# IntelliJ - MVN
* developed as mvn project in intellij, tests can be run from the command line
