
import java.util.*;

public class MST
{

    public static void main (String args []) {
        final int row = 10, col = 2;

        // col1: experiment number, col2: time for algorithm
        double[][] result = new double[row][col];



        for(int i = 0; i < result.length; i++)
        {
            runMST(100*(i*2+1), i, result);
        }

        ReadWriteFile.writeResult(result, (int)result[result.length-1][0]);







    }


    public static void runMST (int dataSize, int iter, double [][] result) {
        double[][] dataset = ReadWriteFile.genData(dataSize);
        PrimsMST(dataset, dataSize,iter, result);

    }

    //Search for the next applicable edge
    static private Edge LocateEdge(ArrayList<Integer> v,ArrayList<Edge> edges)
    {
        for (Iterator<Edge> it = edges.iterator(); it.hasNext();)
        {
            Edge e = it.next();
            //e.Print();

            int x = e.i; //from node i
            int y = e.j; //to node j
            int xv = v.indexOf(x); //to check whether the node (i) is already in the ArrayList - return -1 if not available
            int yv = v.indexOf(y); //to check whether the node (j) is already in the ArrayList - return -1 if not available
            //System.out.println(v);
            if (xv > -1 && yv == -1) //if node i is in the list but not j is not in the list. So we can connect - get the e
            {
                return(e);
            }
            if (xv == -1 && yv > -1) //if node j is in the list but not i is not in the list. So we can connect - get the e
            {
                return(e);
            }
        }
        //Error condition
        return(new Edge(-1,-1,0.0));
    }
    @SuppressWarnings("unchecked")
    //d is a distance matrix, high value edges are more costly
    //Assume that d is symmetric and square
    public static double[][] PrimsMST(double[][] dataset, int datasize, int iter, double[][] result)
    {
        long timestart = System.currentTimeMillis();

        int i,j,n = dataset.length;
        double res[][] = new double[n][n];

        //Store edges as an ArrayList
        ArrayList<Edge> edges = new ArrayList<Edge>();
        for(i=0;i<n-1;++i)
        {
            for(j=i+1;j<n;++j) //symetric array. 0,0 is zero
            {
                //Only non zero edges
                if (dataset[i][j] != 0.0) edges.add(new Edge(i,j,dataset[i][j]));
            }
        }

        //Sort the edges by weight (ascending order)
        Collections.sort(edges, new CompareEdge());

        //Don't do anything more if all the edges are zero
        if (edges.size() == 0) return(res);

        //List of variables that have been allocated
        ArrayList<Integer> v = new ArrayList<Integer>(); //we store node(vertices) in v

        //Pick cheapest (weight)
        v.add(edges.get(0).i);

        //System.out.println("Node "+v.get(0));

        //Loop while there are still nodes to connect
        while(v.size() != n)
        {
            //System.out.println("Node : "+v);
            Edge e = LocateEdge(v,edges);
            if (v.indexOf(e.i) == -1) v.add(e.i); //if the node is not available, then add (to connect)
            if (v.indexOf(e.j) == -1) v.add(e.j); //if the node is not available, then add (to connect)
            //symetric array
            res[e.i][e.j] = e.w;
            res[e.j][e.i] = e.w;
        }
        System.out.println("Final Node : "+v);

        long timeEnd = System.currentTimeMillis();
        double time = (double)(timeEnd-timestart)/1000;

        result[iter][0] = datasize;
        result[iter][1] = time;


        return(result);
    }
}