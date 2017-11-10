//mda.java
/*
For evaluating the minimal distance of motif - breakpoint and breakpoint-motif.

This program will take tabulated result for mtDNA QFP position and deletion breakpoint
and return the p-value of the comparison of the control set.

The control set was generated by rotating the motif set around the circular genome in
1-nucleotide (nt) increments relative to the breakpoint set. 

For a round of calculation, the program will take all QFP midpoint and calculate the minimal
distance to the nearest break-point, then average the number. 
The genome will be shited by 1nt and the calculation will iterate.

The program will then rotate the genome by 1nt, and then iterate through the same calcualtion

*/
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.*;
import java.util.Collections;

public class MDA{
	public static void main(String[] args)throws FileNotFoundException{
		
		double initialAverage = getAve(0);
		System.out.println("Minimal distance average at position 0 is " + initialAverage);

		//Scanner sc = new Scanner(System.in);


		//System.out.println("Input your genome length here");

		ArrayList<Double> collectionForPValue = new ArrayList<Double>();
		for(int i = 1; i < 16567; i+=200){
			double myAverage = 0.0;
			myAverage = getAve(i);
			if (initialAverage >= myAverage){
				collectionForPValue.add(myAverage);
			}
			System.out.println(myAverage);
		}
		//System.out.println(collectionForPValue);
		double frequency = collectionForPValue.size()/16567;
		System.out.println("your p-value is " + frequency);


	}

	public static double getAve(int pos)throws FileNotFoundException{

		ArrayList<Double> myBP = importBP();
		ArrayList<Double> myMP = importMP();
		double position = (double) pos;

		ArrayList<Double> minimalDistance = new ArrayList<Double>();
		
		for (int i = 0; i<myMP.size(); i++){
			double currentMP = myMP.get(i);
			ArrayList<Double> findSmall = new ArrayList<Double>();
			for (int j = 0; j<myBP.size(); j++){
				double currentBP = myBP.get(j);
				double difference = 0.0;
				double absDifference = 0.0;
				if (currentBP + position <= 16567){
					difference = currentMP - currentBP + position;
					absDifference = Math.abs(difference);
					findSmall.add(absDifference);
				}else{
					difference = currentMP + (currentBP - 16567) + position;
					absDifference = Math.abs(difference);
					findSmall.add(absDifference);
				}
			}
			double smallNum = Collections.min(findSmall);
			//System.out.println(smallNum);
			minimalDistance.add(smallNum);
		}
		//System.out.println(minimalDistance);

		double mySum = 0.0;
		for (int k = 0; k<minimalDistance.size(); k++){			
			mySum += minimalDistance.get(k);
		}

		double averageDistance = mySum / minimalDistance.size();

		//System.out.println(averageDistance);
		return averageDistance;
	}

	public static ArrayList<Double> importBP() throws FileNotFoundException{

		Scanner allBPFile = new Scanner(new File("5pBP.txt"));

    	ArrayList<Double> fivePrimeBP = new ArrayList<Double>();

    	while(allBPFile.hasNextLine()){
            String line = allBPFile.nextLine();

            Scanner scanner = new Scanner(line);
            //scanner.useDelimiter(",");
            while(scanner.hasNextDouble()){
                fivePrimeBP.add(scanner.nextDouble());
            }
            scanner.close();
        }

        allBPFile.close();

        //System.out.println(fivePrimeBP);

        return fivePrimeBP;
	}

	public static ArrayList<Double> importMP() throws FileNotFoundException{

		Scanner allMPFile = new Scanner(new File("2G33N-human.txt"));

    	ArrayList<Double> midpoint = new ArrayList<Double>();

    	while(allMPFile.hasNextLine()){
            String line = allMPFile.nextLine();

            Scanner scanner = new Scanner(line);
            //scanner.useDelimiter(",");
            while(scanner.hasNextDouble()){
                midpoint.add(scanner.nextDouble());
            }
            scanner.close();
        }

        allMPFile.close();

        //System.out.println(midpoint);

        return midpoint;
	}



}

