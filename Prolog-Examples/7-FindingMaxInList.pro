find_max([X], X).

find_max([Head | Tail], Max):- find_max(Tail, MaxTail),
                               (Head > MaxTail -> Max = Head; Max = MaxTail).
