reverse([],[]).

reverse([Head | Tail], ReversedList):- reverse(Tail,ReversedTail), 
                                       append(ReversedTail,[Head],ReversedList).

