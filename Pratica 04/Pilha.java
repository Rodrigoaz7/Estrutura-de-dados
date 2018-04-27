import java.util.LinkedList;
import java.util.NoSuchElementException;

public class Pilha<X>{
	LinkedList<X> conteudo;
	
	public Pilha() {
		conteudo = new LinkedList<>();
	}
	public boolean estaVazia() {
		return conteudo.isEmpty();
	}
	public void empilha(X x) {
		this.conteudo.addFirst(x);
	}
	public X desempilha() {
		try {
			return this.conteudo.removeFirst();
		} catch (NoSuchElementException e){
			throw new Error("A lista esta vazia; erro: " + e.getMessage());
		}
		
	}
	public X topo() {
		try {
			return this.conteudo.getFirst();
		} catch (NoSuchElementException e){
			throw new Error("A lista esta vazia; erro: " + e.getMessage());
		}
	}
	
	public String toString() {
		return conteudo.toString();
	}

	public String toStringReverse() {
		LinkedList<X> temp = new LinkedList<>();
		LinkedList<X> aux = new LinkedList<>();

		int size_of_conteudo = conteudo.size();
		for(int i=0; i < size_of_conteudo; i++){
			X obj = conteudo.removeFirst();
			temp.addFirst(obj);
			aux.addFirst(obj);
		}
		for(int i=0; i < size_of_conteudo; i++){
			conteudo.addFirst(temp.removeFirst());
		}
		return aux.toString();
	}

	public void reinicialize(){
		conteudo.clear();
	}
	
	static void test1() {
		Pilha<Double> aPilha = new Pilha<Double>();
		aPilha.empilha(1.1);
		aPilha.empilha(2.1);
		aPilha.empilha(3.1);
		aPilha.empilha(4.1);
		aPilha.empilha(5.1);
		double valor = 0.0;
		valor = aPilha.topo();
		System.out.println("topo pilha = " + valor);
		valor = aPilha.desempilha();
		System.out.println("topo pilha = " + valor);
		valor = aPilha.desempilha();
		System.out.println("topo pilha = " + valor);
		valor = aPilha.desempilha();
		System.out.println("topo pilha = " + valor);
		valor = aPilha.topo();
		System.out.println("topo pilha = " + valor);
		valor = aPilha.desempilha();
		System.out.println("topo pilha = " + valor);
		}
	
	static void test2() {
		Pilha<Double> aPilha = new Pilha<Double>();
		System.out.println(aPilha);
		aPilha.empilha(1.1);
		System.out.println(aPilha);
		aPilha.empilha(2.1);
		System.out.println(aPilha);
		aPilha.empilha(3.1);
		System.out.println(aPilha);
		double valor = 0.0;
		valor = aPilha.desempilha();
		System.out.println("topo pilha = " + valor );
		System.out.println(aPilha);
		valor = aPilha.desempilha();
		System.out.println("topo pilha = " + valor );
		System.out.println(aPilha);
		valor = aPilha.desempilha();
		System.out.println("topo pilha = " + valor );
		System.out.println(aPilha);
}
	
	/*
	 * ==================================== Teste ====================================
	 */
	public static void main(String[] args) {
		//test1();
		test2();
	}
		
}