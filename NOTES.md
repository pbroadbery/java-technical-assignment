# Notes

Please add here any notes, assumptions and design decisions that might help up understand your thought process.

## Implementation notes

The principal change is that the basket class now contains a "Discounter".
This is a class that builds up a set of discounts for a basket - listening to changes to the basket, and
produces a calculated total discount.

The rationale comes from a supermarket receipt; the "total" is shown, along with an itemised lists of 
items and discounts.
In some cases the discount is applied to multiple object (eg. Buy 2 get 1 free), or a single object (eg. half price).

As an implementation shortcut, offers apply to a single product, not product classes.
I'd expect to replace the Offer Map in discounter with some kind of factory class.

The state of the discounter is a set of discounts - mapped by product.  As items are added we create new
discounts.  These act as buckets for cases where we are discounting multiple items.

As it stands, Discount is a concrete class, but as time goes by I'd expect some abstraction to deal 
with "Buy 3 & cheapest is half price", ie. where the state is a collection of items on different products.

I've not worried about weighed produce - the treatment is similar, but will (I expect) need distinct Offer and Discount analogues.
  
