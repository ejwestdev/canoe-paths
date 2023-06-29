
/**************************************************************/
/* Edward West */
/* BroncoID: 012958036 */
/* CS 3310, Spring 2023 */
/* Programming Assignment 3 */
/* Main path finding class */
/**************************************************************/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Prog3 {
	
	
	/**************************************************************/
	/* Method: main method */
	/* Purpose: Call the pathfinding methods*/
	/* to get the optimal canoe path */
	/* Parameters: */
	/* Scanner inputScanner: Inputs the filename */
	/* String fileName: Name of the input file */
	/* Scanner scanner: Scans the file and processes the matrix */
	/* int numPosts: First value from the input file */
	/* the number of trading posts */
	/* costsPerPost: The cost matrix of the trading posts */
	/* findPaths: Array of the cost of the paths */
	/* path: A sample path to compare with the answer */
	/**************************************************************/
	public static void main(String[] args) throws FileNotFoundException {
		//This takes an input of a file and processes it.
		System.out.println("Input filename: ");
		Scanner inputScanner = new Scanner(System.in);
		final String fileName = inputScanner.nextLine();
		inputScanner.close();
		Scanner scanner = new Scanner(new File(fileName));
		
		//This section creates the cost matrix from the file 
		int numPosts = scanner.nextInt();
		int[][] costsPerPost = readCosts(numPosts, scanner);
		scanner.close();

		//This is where the algorithm starts
		//Calls the methods to determine the paths and the minimum cost.
		int[] findPaths = computeMinimumCosts(numPosts, costsPerPost);
		int[] path = computePaths(numPosts, findPaths, costsPerPost);

		printResult(findPaths, path, numPosts);
	}
	/**************************************************************/
	/* Method: read the costs */
	/* Purpose: Helper method that just reads the cost of each post in the matrix */
	/**************************************************************/
	private static int[][] readCosts(int numPosts, Scanner scanner) {
		//Creates the matrix to read the costs
		int[][] costsPerPost = new int[numPosts + 1][numPosts + 1];

		for (int i = 1; i < numPosts; i++) {
			for (int j = i + 1; j <= numPosts; j++) {
				costsPerPost[i][j] = scanner.nextInt();
			}
		}

		return costsPerPost;
	}
	
	
	/**************************************************************/
	/* Method: computeMinimumCosts */
	/* Purpose: Uses the algorithm to compute the minimum cost of the posts*/
	/* Returns: int[]: the costs */
	/**************************************************************/
	private static int[] computeMinimumCosts(int numPosts, int[][] c) {
		//Algorithm to find the minimum costs of the path
		int[] findPaths = new int[numPosts + 1];
		findPaths[1] = 0;

		for (int i = 2; i <= numPosts; i++) {
			//set the first value to an arbitrary large number
			findPaths[i] = Integer.MAX_VALUE;

			for (int j = 1; j < i; j++) {
				int cost = findPaths[j] + c[j][i];
				//if the cost is smaller than the value of findPaths[i]
				//then sets findPaths[i] to equal the cost
				//This ensures that the first cheapest path is always found 
				if (cost < findPaths[i]) {
					findPaths[i] = cost;
				}
			}
		}

		return findPaths;
	}
	/**************************************************************/
	/* Method: computePaths */
	/* Purpose: Search through all the paths and computes their costs */
	/* Returns: int[]: the path in reverse order */
	/**************************************************************/
	private static int[] computePaths(int numPosts, int[] findPaths, int[][] c) {
		int[] path = new int[numPosts + 1];
		path[1] = -1;

		for (int i = 2; i <= numPosts; i++) {
			for (int j = 1; j < i; j++) {
				int cost = findPaths[j] + c[j][i];

				if (cost == findPaths[i]) {
					path[i] = j;
					break;
				}
			}
		}

		return path;
	}
	/**************************************************************/
	/* Method: print results */
	/* Purpose: Search all the records in the current database */
	/* Parameters: */
	/* <Integer> posts: the path that is traversed by the algorithm*/
	/* Returns: Strings that show the answer */
	/**************************************************************/
	private static void printResult(int[] findPaths, int[] path, int numPosts) {
		System.out.println("Minimum cost: " + findPaths[numPosts]);

		ArrayList<Integer> posts = new ArrayList<>();
		int current = numPosts;

		while (current != -1) {
			posts.add(current);
			current = path[current];
		}

		System.out.print("The minimum path: ");

		for (int i = posts.size() - 1; i >= 0; i--) {
			System.out.print(posts.get(i) + " ");
		}
	}
}
