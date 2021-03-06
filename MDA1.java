//mda.java//mda.java
/*
For evaluating the minimal distance of motif - breakpoint.
This program will take listed result (txt) for mtDNA QFP position and deletion breakpoint
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

public class MDA1{
	/*main method that will prompt user to input the file name of breakpoint list, midpoint list and reading window,
	then call the getAve() method that generate desireed averages for calculation.
	The calculation at position 0 is calculated independently and would be used to compare with averages generated by 
	each shift of breakpoint frame.
	An arraylist is built to store all average values smaller than that generated at position 0.
	Another array list is built to store all average values biger than that generated at position 0.
	the final "p-value" was generated by diving the count of smaller average values by count of all average values.*/
	public static void main(String[] args)throws FileNotFoundException{
		Scanner sc = new Scanner(System.in);
		String nameBP = "";
		String nameMP = "";
		String readWindows = "";
		//int readWindow = 1;
		System.out.println("Please select the textfile for listed deletion break points here:");
		//System.out.println("1. 5 prime breakpoint");
		//System.out.println("2. 3 prime breakpoint");
		nameBP = sc.nextLine();
		System.out.println("Please input the textfile for listed QFP midpoints here:");
		nameMP = sc.nextLine();
		/*System.out.println("Input your deletion frame shift here");
		readWindows = sc.nextLine();*/
		//readWindow = 1;//Integer.parseInt(readWindows);
		
		ArrayList<Double> myBP = importBP(nameBP);
		ArrayList<Double> myMP = importMP(nameMP);

		double frequency = 0.0;
		double numOfLarge = 0.0;
		double numOfSmall = 0.0;
		double initialAverage = getAve(0, myMP, myBP);
		System.out.println("Minimal distance average at position 0 is " + initialAverage);

		//Scanner sc = new Scanner(System.in);


		//System.out.println("Input your genome length here");
		//now returning controls every 200 nt, just for time sake
		ArrayList<Double> collectionForPValue = new ArrayList<Double>();
		ArrayList<Double> antiCollectionForPValue = new ArrayList<Double>();

		for(int i = 1; i < 16569; i+=1){
			double myAverage = 0.0;
			myAverage = getAve(i,myMP, myBP);
			if (initialAverage > myAverage){
				collectionForPValue.add(myAverage);
			}
			//System.out.println(myAverage);
		}
		//System.out.println(collectionForPValue);
		
		numOfSmall = collectionForPValue.size();
		//numOfLarge = antiCollectionForPValue.size();


		frequency = numOfSmall/16569;
		

		System.out.println("your p-value is " + frequency);


	}
	/*@param the integer of position number, representing the shift of breakpoints
	  @param file name of midpoint list text file
	  @param file name of breakpoint text file
	  @return Double average value at a certin position
	  This method calculates the minimal average distance between QFP midpoints and deletion 
	  breakpoints. 
	  The method would first import the arraylist by calling 2 import methods, including Breakpoint
	  list and Midpoint list.
	  The method will then iterate through the list of QFP midpoints; for each midpoint, it will 
	  iterate through every elements of the breakpoint list and calculate the distance between this 
	  QFP midpoint and the deletion breakpoint. 
	  If the position of deletion breakpoint exceeds the end of the genome, the method will minus the 
	  genome length to get the actual position.
	  The calculated distance would be added to an arraylist inside the iteration.
	  Note that we are still in one iteration for one midpoint of QFP. After we have the list of the distance
	  between this midpoint and all midpoints, the method would find the smallest distance, then add this
	  smallest distance to another arraylist. 
	  By iterating through all the midpoints, we would have an arraylist that contains the distance between 
	  all midpoints and it's nearest breakpoint neighbors. 
	  The method would then sum all element of the arraylist and therefore calculate and return the average
	  distance of all midpoint at one position.
	*/
	public static double getAve(int pos, ArrayList<Double> myMP, ArrayList<Double> myBP)throws FileNotFoundException{

		//ArrayList<Double> myBP = importBP(nameBP);
		//ArrayList<Double> myMP = importMP(nameMP);
		double position = (double) pos;

		ArrayList<Double> minimalDistance = new ArrayList<Double>();
		
		for (int i = 0; i<myMP.size(); i++){
			double currentMP = myMP.get(i);
			ArrayList<Double> findSmall = new ArrayList<Double>();
			for (int j = 0; j<myBP.size(); j++){
				double currentBP = myBP.get(j) + pos;
				double difference = 0.0;
				double absDifference = 0.0;
				if (currentBP < 16569){
					//difference = currentMP - currentBP - position;
					if(currentBP > (8284.5 + currentMP)){
						difference = currentMP + (16569- currentBP);
						absDifference = Math.abs(difference);
						findSmall.add(absDifference);
					}else{
						difference = currentBP - currentMP;
						absDifference = Math.abs(difference);
						findSmall.add(absDifference);
					}
				}else{
					//difference = currentMP - (currentBP - 16569) - position;
					currentBP = currentBP-16569;
					if(currentBP > (8284.5 + currentMP)){
						difference = currentMP-(16569- currentBP);
						absDifference = Math.abs(difference);
						findSmall.add(absDifference);
					}else{
						difference = currentBP - currentMP;
						absDifference = Math.abs(difference);
						findSmall.add(absDifference);
					}
					
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

	/*@param the string of file name
	  @return an arraylist of all deletion breakpoints
	*/
	public static ArrayList<Double> importBP(String nameBP) throws FileNotFoundException{

		Scanner allBPFile = new Scanner(new File(nameBP));

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
	/*@param the string of file name
	  @return an arraylist of all QFP midpoints
	*/
	public static ArrayList<Double> importMP(String nameMP) throws FileNotFoundException{

		Scanner allMPFile = new Scanner(new File(nameMP));

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