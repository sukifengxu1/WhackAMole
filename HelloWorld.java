package hello;

import java.util.ArrayList;//comment added


public class HelloWorld {

	public static void main(String[] args) {
		int[] array = new int[] {1, 2, 3};
		ArrayList<Integer> copyArray = new ArrayList<>();
		for (int i = 0; i < array.length; i++){
			copyArray.add(array[i]);
			System.out.println(array[i]);
		}		
	}
}
