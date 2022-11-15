package closest.pair;
/*
 * Algorithms and Complexity                                November 9, 2022,
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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class ClosestPair {

    public static ArrayList<Point> pointList;

    public static void main(String[] args) {
        testBrute("brute.txt");
        testDivide("divide.txt");
    }

    //Performs the test with the BruteForce algorithm
    public static void testBrute(String name) {
        create(name);
        Distance d = new Distance();
        try {
            PrintWriter printer = new PrintWriter(name);
            for (int i = 2; i <= 18; i++) {
                int points = (int) Math.pow(2, i);
                double[] closest = new double[5];
                generate(points, (int) Math.pow(4, i), 1000);
                long start = System.nanoTime();
                closest = d.bruteForce(pointList);
                long end = System.nanoTime();
                long time = end - start;
                printer.printf("%s\n", points + " " + d.getIter() + " " + time);
                d.resetCounter();
            }
            printer.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    //Performs test with the divide and conquer algorithm
    public static void testDivide(String name) {
        create(name);
        Distance d = new Distance();
        try {
            PrintWriter printer = new PrintWriter(name);
            for (int i = 2; i <= 18; i++) {
                int points = (int) Math.pow(2, i);
                long sumIter = 0;
                long sumTime = 0;
                for (int j = 1; j <= 4; j++) {
                    double[] closest = new double[5];
                    generate(points, (int) Math.pow(4, i), 1000);
                    long start = System.nanoTime();
                    closest = d.divideAndConquer(pointList);
                    long end = System.nanoTime();
                    long time = end - start;
                    sumIter = sumIter + d.getIter();
                    sumTime = sumTime + time;
                    d.resetCounter();
                }
                printer.printf("%s\n", points + " " + sumIter / 4 + " " + sumTime / 4);
            }
            printer.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    //Creates the file
    public static void create(String name) {
        try {
            File f = new File(name);
            f.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * The following void creates and stores de desired amount of points in an
     * arraylist each point with random x and y coordinates ranging between 0
     * and 20.<p>
     * When the points with random coordinates are created the algorithm checks if it's x value has been taken by another point,
     * if it has, the new point is discarded and a new one is generated, if not, then the point gets added to the ArrayList<p>
     * Finally, the list is sorted in ascending order according to the x value of the points.<p>
     * input: How many points you want, the maximum x value and the maximum y value for the points.<p>
     * output: An ArrayList containing the desired amount of points sorted in ascending order.
     *
     * @param points The amount of points you want to generate.
     * @param maxX   The maximum possible value of x.
     * @param maxY   The maximum possible value of y.
     */
    public static void generate(int points, int maxX, int maxY) {
        pointList = new ArrayList<>();
        Random random = new Random();
        Point a;
        int midpoint = maxX / 2;
        int i = 1, j = points / 2 + 1;
        while (i <= points / 2) {
            a = new Point(random.nextInt(midpoint), random.nextInt(maxY));
            if (!xValueUsed(pointList, a)) {
                pointList.add(a);
                i++;
            }
        }
        while (j <= points) {
            a = new Point(midpoint + 1 + random.nextInt(maxX - midpoint), random.nextInt(maxY));
            if (!xValueUsed(pointList, a)) {
                pointList.add(a);
                j++;
            }
        }
        Collections.sort(pointList);
    }

    /**
     * The following void takes a point and compares it's x value with the x value of each point in an ArrayList<p>
     * If a match is found then returns true, if not, it returns false.<p>
     * Input: An ArrayList of points and a point<p>
     * Output: true or false.
     *
     * @param point  The point that will be compared with all the elements in the list
     * @param pointList The ArrayList containing all the points
     */
    public static boolean xValueUsed(ArrayList<Point> pointList, Point point) {
        for (Point p : pointList) {
            if (p.getX() == point.getX()) {
                return true;
            }
        }
        return false;
    }
}
