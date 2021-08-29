# Hamiltonian-Path-Random-Walker

A hamiltonian path is a path that visits each vertex in a graph once, while a random walk is a path that 
consists of a sequence of random steps.

This program uses random walk to solve a simpler version of the hamiltonian path problem. The user picks 
the size of the lattice, and the starting position of the random walk.

The program then randomly chooses a direction (North, South, East, or West), and moves to the next vertex 
in the chosen direction, then it chooses another random direction and so on. There are only three things 
that restrict the random walker's movement:

1. The random walker cannot move in a direction it has already tried 
2. It cannot move outside the lattice 
3. It cannot move to a vertex it has already crossed at some point in its path 

If the random walker reaches a dead-end, it will move back and try a different direction. The program 
stops when a hamiltonian path is found (all vertices in the lattice has been visited exactly once).

Sample execution of program with 6x6 lattice:
https://user-images.githubusercontent.com/88196425/131259534-27ce4760-bee7-496c-b7a5-1c764ad57695.mp4


