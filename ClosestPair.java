/*
 * Algorithms and Complexity                                September 14, 2022
 * IST 4310_01
 * Prof. M. Diaz-Maldonado
 * Estudiante: Alan Daniel Florez Cerro
 * Código: 200148604
 * Lab 2: Closest pair
 *
 * Synopsis:
 * Make an algorithm that determines the closest pair of points of a group of points
 * using first an implementation of brute force and then using the divide and conquer
 * strategy.
 *
 */
package closest.pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

/**
 * @author adcerro
 */
public class ClosestPair {

    public static ArrayList pointl = new ArrayList<Point>();

    public static void main(String[] args) {
        generate(6);
        for (Object p : pointl) {
            Point a = (Point) p;
            System.out.println(a.x + ", " + a.y);
        }
        bruteForce(pointl);
    }
    /**
     * The following void creates and stores de desired amount of points in an arraylist
     * each point with random x and y coordinates ranging between 0 and 20
     * @param points  The amount of points you want to generate
     * input: How many points you want
     * output: An ArrayList containing the desired amount of points
     */
    public static void generate(int points) {
        Random random = new Random();
        for (int i = 1; i <= points; i++) {
            pointl.add(new Point(random.nextInt(21), random.nextInt(21)));
        }
    }
    /**
     * The following void iterates through the ArrayList comparing each point with the others
     * and determines which pair has the minimal distance of all and prints the pair and their
     * distance in the console
     * @param list  An ArrayList containing all the points
     * input: The Array with all the points
     * output: The closest pair and their distance
     */
    public static void bruteForce(ArrayList<Point> list) {
        double closestd = distance(list.get(0), list.get(1));
        Point pointa=list.get(0), pointb=list.get(1);
        for (Point list1 : list) {
            for (int i = list.indexOf(list1) + 1; i < list.size(); i++) {
                if (distance(list1, list.get(i)) < closestd) {
                    closestd = distance(list1, list.get(i));
                    pointa=list1;
                    pointb=list.get(i);
                }
            }
        }
        System.out.println("The closest points are: ("+pointa.x+", "+pointa.y+") and ("+pointb.x+", "+pointb.y+")");
        System.out.println("Distance: "+closestd);
    }
/**
 * The following method returns the distance between two point objects
 * comparing their x and y
 * @param a  The first point to compare
 * @param b  The second point to compare
 * @return The distance between this points as a double
 * inputs: Point a and point b
 * outputs: The distance between the points
 */
    public static double distance(Point a, Point b) {

        return Math.sqrt(Math.pow((b.y - a.y), 2) + Math.pow((b.x - a.x), 2));
    }

    public static class Point {

        public int x;
        public int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
