package closest.pair;/*
 * Algorithms and Complexity                                October 15, 2022,
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class ClosestPair {

    public static ArrayList pointList = new ArrayList<Point>();
    public static void main(String[] args) {
        generate(6, 15,21);
        for (Object p : pointList) {
            Point a = (Point) p;
            System.out.println(a.x + ", " + a.y);
        }
        System.out.println("\nBy brute force only:");
        double[] closest= new double[5];
        Distance d = new Distance();
        closest= d.bruteForce(pointList);
        System.out.println("The closest points are: (" + closest[1] + ", " + closest[2] + ") and (" + closest[3] + ", " + closest[4] + ")");
        System.out.println("Distance: " + closest[0]);
        System.out.println("\nUsing divide and conquer:");
        double[] closestdiv= new double[5];
        closestdiv= d.divideAndConquer(pointList);
        System.out.println("The closest points are: (" + closestdiv[1] + ", " + closestdiv[2] + ") and (" + closestdiv[3] + ", " + closestdiv[4] + ")");
        System.out.println("Distance: " + closestdiv[0]);
    }

    /**
     * The following void creates and stores de desired amount of points in an
     * arraylist each point with random x and y coordinates ranging between 0
     * and 20.<p>
     * Once it creates the list with points it checks for any points with the same x value and
     * changes the x value of one of the points with a random one according to the parameters given.<p>
     * The comparison is done against all elements of the list and the list will be checked and modified until none of
     * the x values is modified.</p>
     * Finally, the list is sorted in ascending order according to the x value of the points.<p>
     * input: How many points you want and the maximum x value for half of the points.<p>
     * output: An ArrayList containing the desired amount of points sorted in ascending order.
     *
     * @param points The amount of points you want to generate.
     * @param midpoint The maximum possible value of x for the first half of the points,
     * and minimum value for the second half.
     * @param max The maximum possible value of x and y.
     */
    public static void generate(int points, int midpoint,int max) {
        Random random = new Random();
        Point a;
        boolean changed=false;
        for (int i = 1; i <= points / 2; i++) {
            pointList.add(new Point(random.nextInt(midpoint), random.nextInt(max)));
        }
        for (int i = points / 2 + 1; i <= points; i++) {
            pointList.add(new Point( random.nextInt(midpoint+1, max), random.nextInt(max)));
        }
        do{
            changed=false;
            for(Object p : pointList){
                for (int i = 0;i<pointList.size();i++){
                    a= (Point)pointList.get(i);
                    if(a.getX()== ((Point) p).getX() && i != pointList.indexOf((Point) p)){
                        if (a.getX()>=midpoint){
                            a.setX(random.nextInt(midpoint+1,max));
                        }else {
                            a.setX(random.nextInt(midpoint));
                        }
                        changed=true;
                    }
                }
            }
        }while (changed);
        Collections.sort(pointList);
    }

    /**
     * The following method returns the distance between two point objects
     * comparing their x and y.<p>
     * inputs: Point a and point b.<p>
     * outputs: The distance between the points.
     *
     * @param a The first point to compare.
     * @param b The second point to compare.
     * @return The distance between these points as a double.
     */
    public static double distance(Point a, Point b) {
        return Math.sqrt(Math.pow((b.y - a.y), 2) + Math.pow((b.x - a.x), 2));
    }
    public static class Distance{
        public ArrayList firsthalf = new ArrayList<Point>();
        public ArrayList secondhalf = new ArrayList<Point>();

        /**
         * The following method iterates through the ArrayList comparing each point
         * with the others and determines which pair has the minimal distance of all
         * and stores the pair and their distance in an array like this:<p>
         * [distance | firstPointX | firstPointY | secondPointX | secondPointY]<p>
         * input: The ArrayList with all the points.<p>
         * output: An array with the closest pair and their distance.
         * @param list An ArrayList containing all the points.
         * @return An array containing the distance in the first position and the points' coordinates in the rest of the array.
         */
        public double[] bruteForce(ArrayList<Point> list) {
            double[] closestd = new double[5];
            closestd[0] = distance(list.get(0), list.get(1));
            closestd[1] = list.get(0).x;
            closestd[2] = list.get(0).y;
            closestd[3] = list.get(1).x;
            closestd[4] = list.get(1).y;
            for (Point list1 : list) {
                for (int i = list.indexOf(list1) + 1; i < list.size(); i++) {
                    if (distance(list1, list.get(i)) < closestd[0]) {
                        closestd[0] = distance(list1, list.get(i));
                        closestd[1] = list1.x;
                        closestd[2] = list1.y;
                        closestd[3] = list.get(i).x;
                        closestd[4] = list.get(i).y;
                    }
                }
            }
            return closestd;
        }
        /**
         * The following method iterates through the ArrayList and splits it into two
         * ArrayLists with half the points each, then it executes the bruteforce method in
         * both, shows their output in the console and compares the closest distances obtained.<p>
         * Finally, it returns an array containing the points with the minor distance of the two.<p>
         * input: The ArrayList with all the points.<p>
         * output: An array with the closest pair and their distance in this way:<p>
         * [distance | firstPointX | firstPointY | secondPointX | secondPointY]
         * @param list An ArrayList containing all the points.
         * @return An array containing the distance in the first position and the points' coordinates in the rest of the array.
         */
        public double[] divideAndConquer(ArrayList<Point> list) {
            for (Point p : list.subList(0, list.size() / 2)) {
                firsthalf.add(p);
            }
            for (Point p : list.subList(list.size() / 2, list.size())) {
                secondhalf.add(p);
            }
            double[] first= new double[5];
            first = bruteForce(firsthalf);
            System.out.println("First Pair");
            System.out.println("(" + first[1] + ", " + first[2] + ") and (" + first[3] + ", " + first[4] + ")");
            System.out.println("Distance: " + first[0]+"\n");
            double[] second= new double[5];
            second = bruteForce(secondhalf);
            System.out.println("Second Pair");
            System.out.println("(" + second[1] + ", " + second[2] + ") and (" + second[3] + ", " + second[4] + ")");
            System.out.println("Distance: " + second[0]+"\n");
            if (first[0] > second[0]) {
                System.out.println("The second pair is the closest");
                return second;
            } else if (first[0] == second[0]) {
                System.out.println("Both pairs have equal distance");
                return second;
            } else {
                System.out.println("The first pair is the closest");
                return first;
            }
        }
    }
    public static class Point implements Comparable<Point> {

        private int x;
        private int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return this.x;
        }
        public void setX(int newX) {
            this.x= newX;
        }

        @Override
        public int compareTo(Point o) {
            return this.x - o.x;
        }
    }
}
