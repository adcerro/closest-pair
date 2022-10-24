package closest.pair;
/*
 * Algorithms and Complexity                                October 23, 2022,
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

    public static ArrayList<Point> pointList = new ArrayList<Point>();
    public static void main(String[] args) {
        generate(6, 15,21);
        for (Point p : pointList) {
            System.out.println(p.getX() + ", " + p.getY());
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
            pointList.add(new Point( midpoint+1+random.nextInt(max-midpoint), random.nextInt(max)));
        }
        do{
            changed=false;
            for(int j=0;j<pointList.size() ; j++){
                Point p = pointList.get(j);
                for (int i = 0;i<pointList.size();i++){
                    a= pointList.get(i);
                    if(a.getX()== p.getX() && i != pointList.indexOf(p)){
                        if (a.getX()>=midpoint){
                            pointList.remove(i);
                            pointList.add(pointList.indexOf(p),new Point(midpoint+1+random.nextInt(max-midpoint),p.getY()));
                        }else {
                            pointList.remove(i);
                            pointList.add(pointList.indexOf(p),new Point(random.nextInt(midpoint),p.getY()));
                        }
                        changed=true;
                    }
                }
            }
        }while (changed);
        Collections.sort(pointList);
    }
}
