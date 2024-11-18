likes(Alice, chocolate).
likes(Bob, chocolate).
likes(Bob, vanilla).

%rules
likes(Alice, X) :- likes(Bob, X).
