import java.util.PriorityQueue;


// Algoritmo de Dijkstra
public class Dijkstra {
	final Graph g; 
	final int n; 
	final int source; 
	final int dest; 
	Fenetre f; 
	int[] dist;
	int[] pred;
	boolean[] settled;
	PriorityQueue<Node> naoAcomodados;
	Fenetre fen;

	// construtor
	Dijkstra(Graph g, int source, int dest) {
		this.g = g;
		n = g.n;
		this.source = source;
		this.dest = dest;
		this.pred = new int[g.n];
		this.dist = new int[g.n];
		this.settled = new boolean[g.n];
		this.naoAcomodados = new PriorityQueue<Node>();

		// iniciando arrays
		for(int i=0; i<g.n; i++) {
			dist[i] = Integer.MAX_VALUE;
			pred[i] = -1;
			settled[i] = false;
			if (i != source) naoAcomodados.add(new Node(i, dist[i]));
		}
		// Inicializa os valores padroes para a origem
		dist[source] = 0;
		pred[source] = source;
		naoAcomodados.add(new Node(source, dist[source]));

		this.naoAcomodados.add(new Node(source, dist[source]));
	}
	
	// atualizacao da distancia, da prioridade, e do predecessor de um no
	void update(int y, int x) {
		if (g.value(x, y) == 0 ) return;

		if (dist[y] > dist[x] + g.value(x, y)) {
			dist[y] = dist[x] + g.value(x, y);
			naoAcomodados.add(new Node(y, dist[y]));
			g.drawUnsettledPoint(fen, y);
			pred[y] = x;
		}
	}
	
	// retorna o próximo nó a ser acomodado
	int nextNode() {
		while (!naoAcomodados.isEmpty()) {
			Node temporario = naoAcomodados.poll();
			if (!settled[temporario.id]) return temporario.id;
		}

		return -1;
	}
	
	// uma etapa do algoritmo de Dijkstra
	int oneStep() {
		slow();

	    int v = nextNode();

		if (v == -1) return -1;

		settled[v] = true;
		g.drawSettledPoint(fen, v);

		// atualização
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
	    if(fen == null) return;
	    try {
	        Thread.sleep(5);
	    } catch (InterruptedException e) {}
	}
}
