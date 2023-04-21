import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String [] args){


        //
        // CHECKPOINT 1 Tests
        //

        /* TESTS FOR WEIGHTED DIRECTED */
        System.out.println("--- Directed, Weighted Graph ---");
        ListGraph<String> mapDW = new ListGraph<>(true, true);
        String[] vertecies  = new String[]{"a", "b", "c", "d", "e", "g", "h"} ;
        for(String vertex: vertecies){
            mapDW.addVertex(vertex);
        }
        System.out.println("Vertecies: " + mapDW.getVertecies());


        mapDW.addEdgeWeighted("a", "b", 10);
        mapDW.addEdgeWeighted("b", "e", 15);
        mapDW.addEdgeWeighted("c", "h", 5);
        mapDW.addEdgeWeighted("h", "b", 10);
        mapDW.addEdgeWeighted("a", "h", 20);
        for(String e: mapDW.getVertecies()){
           System.out.printf("%s -> %s\n", e, mapDW.getEdges(e));
        }

        /* TESTS FOR UNWEIGHTED UNDIRECTED */
        System.out.println("\n--- Undirected, Unweighted Graph ---");
        ListGraph<String> mapUDUW = new ListGraph<>(false, false);
        vertecies  = new String[]{"a", "b", "c", "d", "e", "g", "h"} ;
        for(String vertex: vertecies){
            mapUDUW.addVertex(vertex);
        }
        System.out.println("Vertecies: " + mapUDUW.getVertecies());


        mapUDUW.addEdgeUnweighted("a", "b");
        mapUDUW.addEdgeUnweighted("b", "e");
        mapUDUW.addEdgeUnweighted("c", "h");
        mapUDUW.addEdgeUnweighted("h", "b");
        mapUDUW.addEdgeUnweighted("a", "h");
        for(String e: mapUDUW.getVertecies()){
            System.out.printf("%s -> %s\n", e, mapUDUW.getEdges(e));
        }

        /*mapUDUW.removeVertex("a");
        for(String e: mapUDUW.getVertecies()){
            System.out.printf("%s -> %s\n", e, mapUDUW.getEdges(e));
        }*/
        
        //
        // CHECKPOINT 2 Tests
        //
        System.out.println("BFS: " + mapDW.bfs("a"));
        System.out.println("BFS: " + mapUDUW.bfs("a"));
        System.out.println("DFS: " + mapDW.dfs("a"));
        System.out.println("DFS: " + mapUDUW.dfs("a"));

    }
		
}
