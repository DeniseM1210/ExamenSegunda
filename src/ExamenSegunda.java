import java.util.Scanner;

import javax.management.InstanceNotFoundException;

class Dijkstra{
	public int[] distancia = new int[10];
	public int[][] costo = new int[10][10];

	public void calc(int n, int s) {
	    int[] bandera = new int[n + 1];
	    int i, minpos = 1, k, c, minima;

	    for (i = 1; i <= n; i++) {
	        bandera[i] = 0;
	        this.distancia[i] = this.costo[s][i];
	    }
	    c = 2;
	    while (c <= n) {
	        minima = 99;
	        for (k = 1; k <= n; k++) {
	            if (this.distancia[k] < minima && bandera[k] != 1) {
	                minima = this.distancia[i];
	                minpos = k;
	            }
	        }
	        bandera[minpos] = 1;
	        c++;
	        for (k = 1; k <= n; k++) {
	            if (this.distancia[minpos] + this.costo[minpos][k] < this.distancia[k] && bandera[k] != 1) {
	                this.distancia[k] = this.distancia[minpos] + this.costo[minpos][k];
	            }
	        }
	    }
	}
}

class FloydWarshall{ 
	//Referencia: “Floyd Warshall Algorithm | DP-16 - GeeksforGeeks.” GeeksforGeeks, 11 Dec. 2018, www.geeksforgeeks.org/floyd-warshall-algorithm-dp-16/.

    final static int INF = 99999, V = 4; 
  
    void floydWarshall(int graph[][]) { 
        int dist[][] = new int[V][V]; 
        int i, j, k; 
  
        for (i = 0; i < V; i++) 
            for (j = 0; j < V; j++) 
                dist[i][j] = graph[i][j]; 
  
        for (k = 0; k < V; k++) 
        { 
            // Pick all vertices as source one by one 
            for (i = 0; i < V; i++) 
            { 
                // Pick all vertices as destination for the 
                // above picked source 
                for (j = 0; j < V; j++) 
                { 
                    // If vertex k is on the shortest path from 
                    // i to j, then update the value of dist[i][j] 
                    if (dist[i][k] + dist[k][j] < dist[i][j]) 
                        dist[i][j] = dist[i][k] + dist[k][j]; 
                } 
            } 
        } 
  
        // Print the shortest distance matrix 
        printSolution(dist); 
    } 
  
    void printSolution(int dist[][]) 
    { 
        System.out.println("Muesta la distancia mas corta en los vertices"); 
        for (int i=0; i<V; ++i) 
        { 
            for (int j=0; j<V; ++j) 
            { 
                if (dist[i][j]==INF) 
                    System.out.print("INF "); 
                else
                    System.out.print(dist[i][j]+"   "); 
            } 
            System.out.println(); 
        } 
    }
}

class BellmanFord { 
	//Referencia: “Bellman–Ford Algorithm | DP-23 - GeeksforGeeks.” GeeksforGeeks, Dec. 2012, www.geeksforgeeks.org/bellman-ford-algorithm-dp-23/.

    class Edge { 
        int src, dest, weight; 
        Edge() 
        { 
            src = dest = weight = 0; 
        } 
    }; 
  
    int V, E; 
    Edge edge[]; 
  
    // Crea un grafico con vertices y aristas
    BellmanFord(int v, int e) 
    { 
        V = v; 
        E = e; 
        edge = new Edge[e]; 
        for (int i = 0; i < e; ++i) 
            edge[i] = new Edge(); 
    } 
   
    void BellmanFord(BellmanFord graph, int src) 
    { 
        int V = graph.V, E = graph.E; 
        int dist[] = new int[V]; 
  
        for (int i = 0; i < V; ++i) 
            dist[i] = Integer.MAX_VALUE; 
        dist[src] = 0; 
  
        for (int i = 1; i < V; ++i) { 
            for (int j = 0; j < E; ++j) { 
                int u = graph.edge[j].src; 
                int v = graph.edge[j].dest; 
                int weight = graph.edge[j].weight; 
                if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) 
                    dist[v] = dist[u] + weight; 
            } 
        } 
   
        for (int j = 0; j < E; ++j) { 
            int u = graph.edge[j].src; 
            int v = graph.edge[j].dest; 
            int weight = graph.edge[j].weight; 
            if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) { 
                System.out.println("Contiene tamaño negativo"); 
                return; 
            } 
        } 
        printArr(dist, V); 
    } 
  
    void printArr(int dist[], int V) 
    { 
        System.out.println("Distancia del vertice desde la fuente"); 
        for (int i = 0; i < V; ++i) 
            System.out.println(i + "\t\t" + dist[i]); 
    } 
}
public class ExamenSegunda {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int op = 0;
		
		do {
		System.out.println("1.- Dijkstra");
		System.out.println("2.- Floyd-Warshall");
		System.out.println("3.- Bellman-Ford");
		System.out.println("4.- Salir");
		op = sc.nextInt();
		
			switch(op) {
			case 1: int nodos, posicion, i, j;
		    
		    System.out.println("Ingrese el número de nodos \n");
		    nodos = sc.nextInt();
		    Dijkstra d = new Dijkstra();
		    
		    for (i = 1; i <= nodos; i++) {
		        for (j = 1; j <= nodos; j++) {
		        	System.out.println("Ingrese los pesos de la matriz de costos: ");
		            d.costo[i][j] = sc.nextInt();
		            if (d.costo[i][j] == 0) {
		                d.costo[i][j] = 999;
		            }
		        }
		    }
		    System.out.println("Ingrese la posicion de origen :\n");
		    posicion = sc.nextInt();

		    d.calc(nodos, posicion);
		    System.out.println("El camino más corto desde la posicion " + posicion + "  a todos los demás puntos son : \n");
		    for (i = 1; i <= nodos; i++) {
		        if (i != posicion) {
		            System.out.println("posicion :" + posicion + "\t destino :" + i + "\t Costo minimo es :" + d.distancia[i] + "\t");
		        }
		    }
		    break;
			case 2: FloydWarshall fw = new FloydWarshall(); 
				int graph[][] = { {0,   5,  fw.INF, 10}, 
                    {fw.INF, 0,   3, fw.INF}, 
                    {fw.INF, fw.INF, 0,   1}, 
                    {fw.INF, fw.INF, fw.INF, 0} };
					
					// Se imprime la solución
					fw.floydWarshall(graph);
			break;
			case 3: int V = 5; // Numero de vertices 
	        int E = 8; // Numero de aristas 
	        
	        BellmanFord graph2 = new BellmanFord(V, E); 
	  
	        // arista 0-1 
	        graph2.edge[0].src = 0; 
	        graph2.edge[0].dest = 1; 
	        graph2.edge[0].weight = -1; 
	  
	        // arista 0-2  
	        graph2.edge[1].src = 0; 
	        graph2.edge[1].dest = 2; 
	        graph2.edge[1].weight = 4; 
	  
	        // arista 1-2  
	        graph2.edge[2].src = 1; 
	        graph2.edge[2].dest = 2; 
	        graph2.edge[2].weight = 3; 
	  
	        // arista 1-3  
	        graph2.edge[3].src = 1; 
	        graph2.edge[3].dest = 3; 
	        graph2.edge[3].weight = 2; 
	  
	        // arista 1-4  
	        graph2.edge[4].src = 1; 
	        graph2.edge[4].dest = 4; 
	        graph2.edge[4].weight = 2; 
	  
	        // arista 3-2
	        graph2.edge[5].src = 3; 
	        graph2.edge[5].dest = 2; 
	        graph2.edge[5].weight = 5; 
	  
	        // arista 3-1  
	        graph2.edge[6].src = 3; 
	        graph2.edge[6].dest = 1; 
	        graph2.edge[6].weight = 1; 
	  
	        // arista 4-3
	        graph2.edge[7].src = 4; 
	        graph2.edge[7].dest = 3; 
	        graph2.edge[7].weight = -3; 
	  
	        graph2.BellmanFord(graph2, 0); 
	        break;
			case 4: break;

		}//switch
			
		}while(op != 4);
		
	}

}
