import java.util.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        
        System.out.print("Lattice width? ");
        int width = console.nextInt();
        System.out.print("Lattice height? ");
        int height = console.nextInt();
        System.out.print("Starting x coordinate? ");
        int x = console.nextInt();
        System.out.print("Starting y coordinate? ");
        int y = console.nextInt(); 
        
        HamiltonianPathFinder finder = new HamiltonianPathFinder(width, height, x, y);
        finder.findHamiltonianPath();
    } 
} 
