/*
 * "a Hamiltonian path is a path in an undirected or directed graph that visits each vertex 
 * exactly once". 
 *
 * "a random walk is a mathematical object, known as a stochastic or random process, that 
 * describes a path that consists of a succession of random steps"
 * 
 * This program uses random walk to solve a simpler version of the hamiltonian path problem,
 * finding a hamiltonian path for a given lattice size, given a starting x and y coordinate.
 */

import java.util.*;
import java.awt.*;
import javax.swing.JFrame;

public class HamiltonianPathFinder {
    public static final int SPACING = 50; 
    public static final int LINE_WIDTH = 10; 
    public static final int TIME_DELAY = 10; // ms 

    // starting position
    private int x;
    private int y;
    
    private boolean[][] lattice;
    private Graphics g; 
    private int solution;
    
    // pre: starting x and y coordinate should be valid, throws IllegalArgumentException
    //      if not
    // post: creates hamiltonian path finder object and intializes the graphics
    public HamiltonianPathFinder(int width, int height, int x, int y) {
        if (x >= width || y >= height || x < 1 || y < 1) {
            throw new IllegalArgumentException();
        }
        
        // initialize lattice
        lattice = new boolean[height + 2][width + 2];
        setBorder();
        
        // intitialize starting position
        this.x = x;
        this.y = y;
        
        // initialize graphics 
        JFrame frame = new JFrame("simulation");
        Canvas canvas = new Canvas();
        canvas.setSize((width + 1) * SPACING + LINE_WIDTH, (height + 1) * SPACING + LINE_WIDTH);
        frame.add(canvas);
        frame.pack();
        frame.setVisible(true);
        g = canvas.getGraphics();

        wait(TIME_DELAY); 
        g.fillRect(x * SPACING, y * SPACING, LINE_WIDTH, LINE_WIDTH);
        
        solution = height * width; 
    }
    
    // post: finds a hamiltonian path from the starting position
    public void findHamiltonianPath() {
        int count = 1; 
        findHamiltonianPathHelper(x, y, count);
    }
    
    // post: finds a hamiltonian path with the given x and y coordinate
    private void findHamiltonianPathHelper(int currX, int currY, int count) {
        wait(TIME_DELAY);
        
        lattice[currY][currX] = true; // choose
        if (count == solution) { // if solution
            wait(999999); 
        } else if (!lattice[currY + 1][currX] || !lattice[currY - 1][currX] || 
                   !lattice[currY][currX - 1] || !lattice[currY][currX + 1]) { // if not a dead end
            tryAllPaths(currX, currY, count); // explore
        }
        lattice[currY][currX] = false; // unchoose 
    }
    
    // post: randomly tries a path for a given x and y coordinate until all paths are tried
    private void tryAllPaths(int currX, int currY, int count) {
        boolean goneUp, goneLeft, goneDown, goneRight;
        goneUp = goneLeft = goneDown = goneRight = false;
        
        while (!goneUp || !goneLeft || !goneRight || !goneDown) { // while there are paths unexplored
            double r = Math.random(); // pick random direction
           
            if (r < 0.25 && !goneUp) { // try up
                if (!lattice[currY - 1][currX]) { 
                    // choose
                    g.fillRect(currX * SPACING, currY * SPACING - SPACING, LINE_WIDTH, SPACING); 
                    // explore
                    findHamiltonianPathHelper(currX, currY - 1, count + 1); 
                    // unchoose
                    g.clearRect(currX * SPACING, currY * SPACING - SPACING, LINE_WIDTH, SPACING); 
                }
                goneUp = true;
            } else if (r < 0.5 && !goneLeft) { // try left
                if (!lattice[currY][currX - 1]) { 
                    g.fillRect(currX * SPACING - SPACING, currY * SPACING, SPACING, LINE_WIDTH); 
                    findHamiltonianPathHelper(currX - 1, currY, count + 1); 
                    g.clearRect(currX * SPACING - SPACING, currY * SPACING, SPACING, LINE_WIDTH);
                }
                goneLeft = true;
            } else if (r < 0.75 && !goneDown) { // try down
                if (!lattice[currY + 1][currX]) { 
                    g.fillRect(currX * SPACING, currY * SPACING + LINE_WIDTH, LINE_WIDTH, SPACING); 
                    findHamiltonianPathHelper(currX, currY + 1, count + 1); 
                    g.clearRect(currX * SPACING, currY * SPACING + LINE_WIDTH, LINE_WIDTH, SPACING);
                }
                goneDown = true;
            } else if (!goneRight) { // try right
                if (!lattice[currY][currX + 1]) { 
                    g.fillRect(currX * SPACING + LINE_WIDTH, currY * SPACING, 
                               SPACING, LINE_WIDTH); 
                    findHamiltonianPathHelper(currX + 1, currY, count + 1); 
                    g.clearRect(currX * SPACING + LINE_WIDTH, currY * SPACING, SPACING, LINE_WIDTH);
                }
                goneRight = true;
            }
        }
    }
    
    // post: sets all edges to true for the lattice
    private void setBorder() {
        for (int i = 0; i < lattice.length; i++) {
            lattice[i][0] = true;
            lattice[i][lattice[0].length - 1] = true;
        }
        for (int i = 0; i < lattice[0].length; i++) {
            lattice[0][i] = true; 
            lattice[lattice.length - 1][i] = true;
        }
    }
    
    // post: pauses thread for given milliseconds
    public void wait(int ms) {
        try {
            Thread.sleep(ms);
        }
        catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
}