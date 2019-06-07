/**
 * Pradipta Dasgupta
 * BFS Class
 * Project 4
 *
 */

import java.util.*;
import java.util.stream.Collectors;
import java.io.*;


public class Bfs{
	//file Reader
	public static int[][] readFile(String file) {
		int[][] graph; //the adj. matrix
		
		BufferedReader reader;
		List<String> list = new ArrayList<String>();
		//reading all the lines in the file and storing it in a list
		
		try{
			reader = new BufferedReader(new FileReader(file));
			String str = reader.readLine();
			while(str != null) {
				list.add(str);
				str = reader.readLine();
			}
			reader.close();
			
		} catch (Exception e) {
		    
			
		}
		
		//Rows in adj. matrix = number. of lines in the text file
		//as each line is a row
		
		int row = list.size();
		int col = list.get(0).split(" ").length;
		
		//declaning adj. matrix dimensions as calculated above
		
		graph = new int[row][col];
		
		
		//iterating over each line 
		for(int i = 0; i < row; i++){
		     //in each line integers are seperated by space so we split 
		     //the line using delimiters  space and parseint
			String[] colm = list.get(i).split(" ");
			
			for(int j = 0; j < colm.length; j++) {
				graph[i][j] = Integer.parseInt(colm[j]);
			}
		}
		
		return graph;
	}
	
	//function responsible for BFS.
	public static List<Integer> BreadthFirst(int[][] graph, int source) {
	        //number of nodes determined by adj. matrix length(N*N)
	        
		int n = graph.length;
		
		int[] visited = new int[n]; //visited array to track if a node
		                            //has been visited
		                            
		//keeps track of the sequence in which nodes are visited                            
		List<Integer> path = new ArrayList<Integer>();
		
		//initializing visited to -1, marking nodes that are 
		//initialy not visited
		
		for(int i = 0; i < n; i++) {
			visited[i] = -1;
		}
		
		//BFS uses queue as it travels though nodes at similar
		//distance first
		
		Queue<Integer> q = new LinkedList<Integer>();
		
		//add the source i.e node from where the search starts
		
		q.add(source - 1);
		
		//marking as visited
		visited[source - 1] = 0;
		
		
		//iterate till q is not empty
		while(!q.isEmpty()) {
		        //remove the node from q and add its children
			int top = q.poll();
			//add this to the path list since this is traversed
			path.add(top + 1);
			
			
			//iterating over all other nodes to check if they have an edge
			for(int i = 0; i < n; i++) {
				if(graph[top][i] == 1 && visited[i] == -1) {
				        //there is an edge
				        //add to q
					q.add(i);
					//marked as visited
					visited[i] = top;
				}
			}
		}
		return path;
	}
	
	
	
	
	//writes output to file
	//output is sequence of node as trasversed during bfs
	public static void Output(List<Integer> path, String output) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(output));
			
			bw.write("BFS:\n");
		    String result = path.stream()
		    	      .map(n -> String.valueOf(n))
		    //format all the ints in path array to String in form
		    //of 1->2->3-4 (order of traversal). 
		    	      .collect(Collectors.joining("->"));
		        //write to file
			bw.write(result);
			bw.close();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void bfs() {
		int[][] graph = Bfs.readFile("input1.txt");
		
		List<Integer> path = Bfs.BreadthFirst(graph, 1);
		Bfs.Output(path, "BFS.txt");
	}
}
