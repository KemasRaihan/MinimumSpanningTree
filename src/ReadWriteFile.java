

import java.io.*;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.Random;

public class ReadWriteFile {

    public static void main(String[] args) {
        //double [][] arrayGraph = readCSV ();
        double [][] arrayGraph = genData(5);
        printArray(arrayGraph);

    }

    public static double [][]  genData (int n)
    {
        double[][] res = new double[n][n];
        double randNumber;
        final double min = 0, max = 100.0;

        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                if(i==j)
                {
                    res[i][j] = 0.0;
                }
                else {
                    Random r = new Random();
                    DecimalFormat df = new DecimalFormat("###.##");
                    randNumber = Double.parseDouble(df.format(r.nextDouble()*100));
                    res[i][j] = randNumber;
                    res[j][i] = randNumber;
                }
            }
        }
        return res;

    }

    public static void writeResult(double [][] result, int dataset) {
        try {
            String filename = "G:\\My Drive\\Sheffield Hallam University\\Second Year\\Algorithms and Data Structures\\Week 9\\MSTResults\\" + dataset + ".csv";

            FileWriter fw = new FileWriter(filename);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write("Iter");
            bw.write(",");
            bw.write("Runtime");
            bw.newLine();

            for (int i = 0; i < result.length; i++) {
                bw.write(Double.toString(result[i][0]));
                bw.write(",");
                bw.write(Double.toString(result[i][1]));
                bw.newLine();
            }
            bw.close();
            fw.close();
        }catch(IOException e){
            e.printStackTrace();
        }


    }

    public static double [][] readCSV () {

        String file = "graph.csv";
        double [][] graph = null;

        try {

            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String line = null;
            int row=0;
            int col=0;

            while((line=br.readLine())!=null) {
                row++;
                String [] rowLine = line.split(",");
                col = rowLine.length;
            }

            graph = new double [row][col];

            br = new BufferedReader(new FileReader(file));
            int i=0;
            line = null;

            while ((line=br.readLine())!=null) {

                String colData [] = line.split(",");
                for (int j=0; j<colData.length; j++) {
                    graph[i][j]=Double.parseDouble(colData[j]);
                }
                i++;
            }

        }
        catch (Exception e) {
            System.err.println("Error in reading the file");
        }

        return graph;
    }

    public static void printArray (double [][] array) {

        for (int i=0; i<array.length; i++) {
            for (int j=0; j<array.length; j++) {
                System.out.print(array[i][j]);
                System.out.print(",");
            }
            System.out.println();
        }
    }


}