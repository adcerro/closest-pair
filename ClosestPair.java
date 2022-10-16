package closest.pair;/*
 * Algorithms and Complexity                                October 5, 2022,
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

    public static ArrayList pointl = new ArrayList<Point>();
    public static void main(String[] args) {
        generate(6, 15,21);
        for (Object p : pointl) {
            Point a = (Point) p;
            System.out.println(a.x + ", " + a.y);
        }
        System.out.println("\nBy brute force only:");
        double[] closest= new double[5];
        Distance d = new Distance();
        closest= d.bruteForce(pointl);
        System.out.println("The closest points are: (" + closest[1] + ", " + closest[2] + ") and (" + closest[3] + ", " + closest[4] + ")");
        System.out.println("Distance: " + closest[0]);
        System.out.println("\nUsing divide and conquer:");
        double[] closestdiv= new double[5];
        closestdiv= d.divideandconquer(pointl);
        System.out.println("The closest points are: (" + closestdiv[1] + ", " + closestdiv[2] + ") and (" + closestdiv[3] + ", " + closestdiv[4] + ")");
        System.out.println("Distance: " + closestdiv[0]);


    }

    /**
     * The following void creates and stores de desired amount of points in an
     * arraylist each point with random x and y coordinates ranging between 0
     * and 20
     *
     * @param points The amount of points you want to generate
     * @param midpoint The maximum value of x for the first half of the points,
     * and minimun value for the second half input: How many points you want and
     * the maximun x value for half of the points output: An ArrayList
     * containing the desired amount of points sorted in ascending order
     * @param max The maximum value of x and y
     */
    public static void generate(int points, int midpoint,int max) {
        Random random = new Random();
        for (int i = 1; i <= points / 2; i++) {
            pointl.add(new Point(random.nextInt(midpoint), random.nextInt(max)));
        }
        for (int i = points / 2 + 1; i <= points; i++) {
            pointl.add(new Point( random.nextInt(midpoint+1, max), random.nextInt(max)));
        }
        avoidRepeatedX(midpoint,max);
        Collections.sort(pointl);
    }
    public static void avoidRepeatedX(int midpoint, int max){
        Point a;
        Random random = new Random();
        for(Object p : pointl){
            for (int i = 0;i<pointl.size();i++){
                a= (Point)pointl.get(i);
                if(a.getX()== ((Point) p).getX() && i != pointl.indexOf((Point) p)){
                    if (a.getX()>=midpoint){
                        a.setX(random.nextInt(midpoint+1,max));
                    }else {
                        a.setX(random.nextInt(midpoint));
                    }
                    System.out.println("Changes have been made");
                }
            }
        }

    }


    /**
     * The following method returns the distance between two point objects
     * comparing their x and y
     *
     * @param a The first point to compare
     * @param b The second point to compare
     * @return The distance between these points as a double inputs: Point a and
     * point b outputs: The distance between the points
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
         * and prints the pair and their distance in the console
         *
         * @param list An ArrayList containing all the points
         * @return The closest distance input: The Array with all the points output:
         * The closest pair and their distance
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
        public double[] divideandconquer(ArrayList<Point> list) {
            for (Point p : list.subList(0, list.size() / 2)) {
                firsthalf.add(p);
            }
            for (Point p : list.subList(list.size() / 2, list.size())) {
                secondhalf.add(p);
            }
            double[] first= new double[5];
            first = bruteForce(firsthalf);
            double[] second= new double[5];
            second = bruteForce(secondhalf);
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
