/**
 * Pradipta Dasgupta
 * DFS Class
 * Project 4
 *
*/
import java.util.*;
import java.util.stream.Collectors;
import java.io.*;

public class Dfs {
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
		        //in each line integers are seperated by space so we split the line
		        //using delimiters space and pasrseint
		        
			String[] colm = list.get(i).split(" ");
			
			for(int j = 0; j < colm.length; j++) {
				graph[i][j] = Integer.parseInt(colm[j]);
			}
		}
		
		return graph;
	}
	
	//funtion which is responsible for DFS.
        //source = source node i.e from where the search starts
	public static List<Integer> DepthFirst(int[][] graph, int source) {
	        //calculating the no. of nodes which will be the same as number 
	        //of rows or coloumns in adj. matrix
		int n = graph.length;
		int[] visited = new int[n]; //keeps track of the visited nodes 
		//stores nodes in the order they are traversed
		List<Integer> path = new ArrayList<>();
		
		//initializing each node as not visited
		for(int i = 0; i < n; i++) {
			visited[i] = -1;
		}
		
		//DFS uses stack data structure
		Stack<Integer> s = new Stack<Integer>();
		//add source to stack (-1 just to keep the numbers starting from 0)
		s.add(source - 1);
		visited[source - 1] = 1;  //marking source as viited
		
		
		while(!s.isEmpty()) {
			int top = s.pop(); //pop the node from the stack
			path.add(top+1);
			
			//for all the other nodes that have an edge to top
			for(int i = 0; i < n; i++) {
				if(graph[top][i] == 1 && visited[i] == -1) {
					s.add(i);
					visited[i] = top; //mark visited
				}
			}
		}
		return path;
	}
	
	
	
	
	//writes output to file
	//output is sequence of node as trasversed during dfs
	public static void Output(List<Integer> path, String output) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(output));
			
			bw.write("DFS:\n");
		    String result = path.stream()
		    	      .map(n -> String.valueOf(n))
		    	      //formats nodes in list to be in form of 1->2->3
		    	      //as in dfs
		    	      .collect(Collectors.joining("->"));
		    
			bw.write(result);
			bw.close();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	public static void dfs() {
		int[][] graph = Dfs.readFile("input2.txt");
		List<Integer> output = Dfs.DepthFirst(graph, 1);
		Dfs.Output(output, "DFS.txt");
	}
}
