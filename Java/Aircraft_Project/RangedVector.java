// CSE1/4OOF Semester 2 2019 - Progress Check Test
import java.io.*;
import java.util.*;

public class RangedVector extends Vector
{
   private int lowerbound;
   private int upperbound;
	// CHANGE ON main METHOD IS ONLY ALLOWED BETWEEN THE TWO COMMENTS INSIDE!!!
   public RangedVector(int[] arr, int lowerlimit, int upperlimit)
   {
      super(arr, lowerlimit);
      this.fixValuesToBound(arr, lowerlimit, upperlimit);
      
      //Assign values to object variables
      this.lowerbound = lowerlimit;
      this.upperbound = upperlimit;
      this.values = arr;
      
   }
	public static void main(String[] args) throws IOException
	{
		if (args == null || args.length != 3) {
			System.out.println("Error: The program requires as input 3 .dat files.");
		} else {
			RangedVector[] rv = new RangedVector[3];
			//---CHANGE ON main METHOD STARTS HERE   
         // Add Command line argument file content to String array
         for (int i = 0; i < args.length; i++) {
            File file = new File(args[i]);
            Scanner keyboard = new Scanner(file);
            String[] parts = keyboard.nextLine().trim().split(" ");
            
            int lowerbound = Integer.parseInt(parts[0]);
            int upperbound = Integer.parseInt(parts[1]);
            
            String[] str = Arrays.copyOfRange(parts, 2, parts.length);
            int[] values = new int[str.length];
            
            for(int k = 0; k < str.length; k++) {
               values[k] = Integer.parseInt(str[k]);
            }
            rv[i] = new RangedVector(values, lowerbound, upperbound);
         }
         //---CHANGE ON main METHOD ENDS HERE

			for (int i = 0; i < 3; i++) {
				System.out.printf("RV %d: %s\n", i, rv[i]);
			}
			System.out.println("--->");
			
			for (int i = 0; i < 3; i++) {
				for (int j = i + 1; j < 3; j++) {
					System.out.printf("Euclidean distance between RV %d and RV %d: ", i, j);
					System.out.printf("%.2f\n", rv[i].getDistance(rv[j]));

					System.out.printf("Addition of RV %d and RV %d: ", i, j);
					Vector v = rv[i].add(rv[j]);
					if (v != null) {
						System.out.println(v);
					} else {
						System.out.println("Invalid!");
					}
				}
			}
		}
	}
   // Replace values of array from lowerbound and upperbound if not matched
   private void fixValuesToBound(int[] arr, int lowerbound, int upperbound)
   {
      for (int i = 0; i < arr.length; i++) {
         if (arr[i] < lowerbound) {
            arr[i] = lowerbound;
         } else if (arr[i] > upperbound) {
            arr[i] = upperbound;
         }
      }
   }
   
   // Get Euclidean distance between 2 RangeVector object values
   public double getDistance(RangedVector rv)
   {
      if (rv == null || (rv.values.length != this.values.length)) {
         return -1.0;
      }
      double d = 0.0;
      for (int i = 0; i < this.values.length; i++) {
         int r = this.values[i] - rv.values[i];
         d += r * r;
      }
      return Math.sqrt(d); //prints 10;
   }
   
   public Vector add(RangedVector rv)
   {
      if (rv == null || (rv.values.length != this.values.length)) {
         return null;
      }
      int sum[] = new int[this.values.length];
      
      for (int i = 0; i < this.values.length; i++) {
         sum[i] = this.values[i] + rv.values[i];
      }
      Vector v = new Vector(sum, 0);   
      return v;
   }
   
   public String toString()
   {
      //If lowerbound and upperbound attributes are same
      if (this.lowerbound == this.upperbound) {         
         return super.toString();
      }
      //Otherwise, it returns a String about the values attribute, e.g. {1, 2, 3, 4}, the lowerbound attribute 
      return Arrays.toString(this.values) + " in range of " + "[" + this.lowerbound + ", " + this.upperbound + "]";
   }
}