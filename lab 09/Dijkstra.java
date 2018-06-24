import java.util.PriorityQueue;
import java.util.ArrayList;

// Algoritmo de Dijkstra
public class Dijkstra {
	final Graph g; 
	final int n; 
	final int source; 
	final int dest;
    final int[] dist;
    final int[] pred;
    final boolean[] settled;
    final PriorityQueue<Node> naoAcomodados;
	Fenetre f; 

	// construtor
	Dijkstra(Graph g, int source, int dest) {
		this.g = g;
		n = g.n;
		this.source = source;
		this.dest = dest;

		// Inicializando variaveis
		dist = new int[n];
		pred = new int[n];
		settled = new boolean[n];
		naoAcomodados = new PriorityQueue<>();

		// loop para definir os valores iniciais das distancias, acomodados e S
		for (int i = 0; i < n; ++i) {
			dist[i] = Integer.MAX_VALUE;
			pred[i] = -1;
			settled[i] = false;
			if (i != source) naoAcomodados.add(new Node(i, dist[i]));
		}

		// Inicializa os valores padroes para a origem
		dist[source] = 0;
		pred[source] = source;
		naoAcomodados.add(new Node(source, dist[source]));
	}
	
	// atualizacao da distancia, da prioridade, e do predecessor de um no
	void update(int y, int x) {

        if (g.value(x, y) == 0 ) return;

        if (dist[y] > dist[x] + g.value(x, y)) {
			dist[y] = dist[x] + g.value(x, y);
			naoAcomodados.add(new Node(y, dist[y]));
			g.drawUnsettledPoint(f, y);
			pred[y] = x;
		}
	}
	
	// retorna o próximo nó a ser acomodado
	int nextNode() {

		while (!naoAcomodados.isEmpty()) {
			Node temp = naoAcomodados.poll();
			if (!settled[temp.id]) return temp.id;
		}

		return -1;
	}
	
	// uma etapa do algoritmo de Dijkstra
	int oneStep() {
	    slow();

	    int v = nextNode();

		if (v == -1) return -1;

		settled[v] = true;
		g.drawSettledPoint(f, v);

		// Pula os valores cujos menores caminhos ja foram calculados.
        // Checa se a distancia e MAX_VALUE para evitar integer overflow
		for (int i = 0; i < n; ++i)
			if (!settled[i] && dist[v] != Integer.MAX_VALUE)
			    update(i, v);

		return v;
	}
	
	// algoritmo de Dijsktra completo
	int compute() {
		int mid;

	    do {
	    	mid = oneStep();
		} while (mid != -1 && !settled[dest]);

	    if (dist[dest] == Integer.MAX_VALUE) return -1;

	    return dist[dest];
	}

	// desacelera o visualizador
	void slow(){
	    if(f == null) return;
	    try {
	        Thread.sleep(5);
	    } catch (InterruptedException e) {}
	}
}
