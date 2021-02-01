// CSE1/4OOF Semester 2 2019 - Progress Check Test
import java.io.*;
import java.util.*;

public class Vector
{
   protected int[] values;
   protected int size;
   
   public Vector(int[] arr, int arrVal)
   {
      /*
         If the integer array parameter is null or contains less than two elements, values
         attribute is initialised of length 2, and both elements inside are set to the default
         value parameter; otherwise, values attribute is initialised of the same length as the
         integer array parameter, and all elements in the integer array parameter are copied
         to values attribute
      */
      if (arr.length < 2) {
         this.size = 2;
         this.values = new int[this.size];
         for (int i = 0; i < this.size; i++) {
            this.values[i] = arrVal;
         }
      } else {
         this.size = arr.length;
         this.values = new int[this.size];
         this.values = arr.clone();
      }
   }
   
   public Vector(int ...arr)
   {
      this(arr, 0);
   }
	// DO NOT MAKE ANY CHANGE ON main METHOD!!!
   
	public static void main(String[] args) throws IOException
	{
      /***      
         [ 0 0 ]
         [ 0 0 ]
         [ 1 2 ]
         [ 1 1 ]
         [ 4 5 6 ]
         [ 4 5 6 ]
         [ 4 5 6 ]
      **/
      
		Vector v = new Vector();
		System.out.println(v);

		v = new Vector(0);
		System.out.println(v);

		v = new Vector(1, 2);
		System.out.println(v);

		int[] a = {3};
		v = new Vector(a, 1);
		System.out.println(v);

		int[] b = {4, 5, 6};
		v = new Vector(b, 0);
		System.out.println(v);

		b[2] = 7;
		System.out.println(v);

		b = v.getValues();
		b[2] = 7;
		System.out.println(v);
	}
   
   public int[] getValues()
   {
      /*
         This is a public method, which has no parameters but returns an integer array. Specially,
         it returns a copy of all elements in values attribute. Note it is NOT allowed to return
         values attribute here. (Recall private leakage)
      */
      return this.values.clone();
   }
   public int getSize()
   {
      return this.size;
   }
   
   public String toString()
   {
      return Arrays.toString(this.getValues());
   }
} 