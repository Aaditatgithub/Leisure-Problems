list_length([], 0).

list_length([_ | Tail], R):- list_length(Tail, Len), R is Len + 1.
