package closest.pair;

import java.util.ArrayList;

public class Distance{
    public ArrayList firsthalf = new ArrayList<Point>();
    public ArrayList secondhalf = new ArrayList<Point>();
    public ArrayList firstgroup = new ArrayList<Point>();
    public ArrayList secondgroup = new ArrayList<Point>();

    public void bruteForce(ArrayList<Point> listA, ArrayList<Point> listB, double[] dist){
        for (Point pa : listA) {
            if (dist[1] - pa.getX() <= dist[0]) {
                firstgroup.add(pa);
            }
        }
        for (Point pb : listB) {
            if (dist[1] - pb.getX() <= dist[0]) {
                secondgroup.add(pb);
            }
        }
    }
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
        closestd[1] = list.get(0).getX();
        closestd[2] = list.get(0).getY();
        closestd[3] = list.get(1).getX();
        closestd[4] = list.get(1).getY();
        for (int j = 0; j < list.size()-1; j++) {
            Point pj = list.get(j);
            for (int i = list.indexOf(pj) + 1; i < list.size(); i++) {
                if (distance(pj, list.get(i)) < closestd[0]) {
                    closestd[0] = distance(pj, list.get(i));
                    closestd[1] = pj.getX();
                    closestd[2] = pj.getX();
                    closestd[3] = list.get(i).getX();
                    closestd[4] = list.get(i).getY();
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
    public double distance(Point a, Point b) {
        return Math.sqrt(Math.pow((b.getY() - a.getY()), 2) + Math.pow((b.getX() - a.getX()), 2));
    }
}