fibo(0,0).
fibo(1,0).
fibo(2,1).

fibo(N, Result):- N > 2, 
                  N1 is N - 1,
                  N2 is N - 2,
                  fibo(N1, Result1),
                  fibo(N2, Result2),
                  Result is Result1 + Result2.
                  