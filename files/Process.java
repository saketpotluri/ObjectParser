package Trees;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by odegaard on 11/21/15.
 */
public class Process {
    private ArrayList<Vertex> vertexes;
    //ingoreline represents as optional parameter that will allow you to skip reading
    //every (ignoreLine) vertex i.e. is ignoreLine is 2, it will ignore every second line
    //ingoreLines at 1 will not ingore any lines
    public Process() {
        vertexes = new ArrayList<Vertex>();
    }
    public void fillVertexes(String fileName, int ignoreLines) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(new File(fileName)));
        int current = 0;
        String line = "";

        while((line = br.readLine()) != null) {
            if(current % ignoreLines == 0) {
                String[] comps = line.split(" +");
                if(comps.length <= 4) {
                    break;
                }
                //System.out.println(line);
                //comps[0] is either v or vn which is irrelevant to us
                Double x = Double.parseDouble(comps[1]);
                Double y = Double.parseDouble(comps[2]);
                Double z = Double.parseDouble(comps[3]);
                Double red = Double.parseDouble(comps[4]);
                Double green = Double.parseDouble(comps[5]);
                Double blue = Double.parseDouble(comps[6]);
                Vertex v = new Vertex(x, y, z, red, green, blue);
                vertexes.add(v);
            }
            current++;
        }
    }

    class IdealPointComparator implements Comparator<Vertex> {
        private static final double EPS = 1e-16 ;
        private double idealRed;
        private double idealGreen;
        private double idealBlue;

        public IdealPointComparator(double idealRed, double idealGreen, double idealBlue) {
            this.idealRed = idealRed;
            this.idealGreen = idealGreen;
            this.idealBlue = idealBlue;
        }

        @Override
        public int compare(Vertex a, Vertex b) {
            //computes euclidean distance
          double distA = a.computeEuclidDistance(idealRed, idealGreen, idealBlue);
            double distB = b.computeEuclidDistance(idealRed, idealGreen, idealBlue);
            double diff = distA - distB;
            if(diff < 0) {
                return -1;
            } else if (Math.abs(diff) <= EPS) {
                return 0;
            } else {
                return 1;
            }
        }
    }

    public void sortVertices(String method, double idealRed, double idealGreen, double idealBlue) {
        Collections.sort(vertexes, new IdealPointComparator(idealRed, idealGreen, idealBlue));
    }

    public void writeVertices(ArrayList<Vertex> list, String fileName) {
        PrintWriter writer = null;
        try {
             writer = new PrintWriter(new File(fileName));
            System.out.println("Size of writing vertex: " + list.size());

            for (Vertex v: list) {
                writer.write("v " + v.getX() + " " + v.getY() + " " + v.getZ() + " " + v.getRed() + " " + v.getGreen() + " " +
                       v.getBlue() + "\n");
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        writer.close();

    }

    public ArrayList<Vertex> selectNBestVertexes(int n) {
        System.out.println("Size of vertices arraylist: " + vertexes.size());
        ArrayList<Vertex> list = new ArrayList<Vertex>();
        for(int i = 0; i < n; i++) {
            Vertex goodV = vertexes.get(i);
            list.add(goodV);
        }
        return list;
    }
}
