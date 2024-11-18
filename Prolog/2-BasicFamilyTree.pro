parent(sarah, john).
parent(sarah, emma).
parent(emma, peter).
parent(emma, lily).


% Unique set of people
grandparent(X, Y) :-
    findall(Grandparent, (parent(Grandparent, Z), parent(Z, Y)), GrandparentsSet),
    list_to_set(GrandparentsSet, UniqueGrandparents),  
    write(UniqueGrandparents).
    
sibling(X,Y):- parent(P,X), parent(P,Y), X \= Y.


% finding relation between 2 people
relation(X,Y):-
        (parent(X,Y), write(X), write(' is parent of '), write(Y)).
relation(X,Y):- 
        (sibling(X,Y), write(X), write(' is sibling of '), write(Y)).