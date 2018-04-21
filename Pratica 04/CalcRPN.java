import java.io.*;
import java.util.NoSuchElementException;

class CalcRPN {
	// variaveis da instancia :
	// uma pilha para os calculos
	Pilha<Double> aPilha;
	Pilha<Operacao> hist;
	// construtor
	CalcRPN () {
		aPilha = new Pilha<Double>();
		hist = new Pilha<Operacao>();
	}
	// Adic~ao de dois elementos do topo da pilha
	void mais() {
		//Desempilho duas vezes e as somo
		try {
			double soma1 = aPilha.desempilha();
			double soma2 = aPilha.desempilha();
			aPilha.empilha(soma1+soma2);
			hist.empilha(new Operacao('+', soma1, soma2));
		} catch (NoSuchElementException e){
			throw new Error("A lista eh invalida; erro eh: " + e.getMessage());
		} 
	}
	//Subtrac~ao de dois elementos do topo da pilha
	void menos() {
		double sub1 = aPilha.desempilha();
		double sub2 = aPilha.desempilha();

		aPilha.empilha(sub2-sub1);
		hist.empilha(new Operacao('-', sub2, sub1));
	}
	// Multiplicac~ao de dois elementos do topo da pilha
	void vezes() {
		try {
			double multiplicacao1 = aPilha.desempilha();
			double multiplicacao2 = aPilha.desempilha();
			aPilha.empilha(multiplicacao1 * multiplicacao2);
			hist.empilha(new Operacao('*', multiplicacao1, multiplicacao2));
		} catch (NoSuchElementException e){
			throw new Error("A lista eh invalida; erro eh: " + e.getMessage());
		} 
	}
	// Divis~ao de dois elementos no topo da pilha
	void dividido() {
		double div1 = aPilha.desempilha();
		double div2 = aPilha.desempilha();

		if(div1 != 0) {
			aPilha.empilha(div2/div1);
			hist.empilha(new Operacao('/', div2, div1));
		}
		else System.out.println("ERRO: divisão por zero \n");
	}
	// retorna o conteudo do topo da pilha
	Double resultado() {
		return aPilha.topo();
	}

	void limpar(){
		aPilha.reinicialize();
		hist.reinicialize();
	}

	void imprimir_historico(){
		System.out.println("Historico = " + hist.toStringReverse());
	}

	void cancela(){
		// String resultado = hist.desempilha();
		// if(resultado.charAt(0) == '+' || resultado.charAt(0) == '-' ||
		// 	resultado.charAt(0) == '*' || resultado.charAt(0) == '/'){
		// 	System.out.println("nao fiz, eh operacao");
		// }
		// else {
		// 	aPilha.desempilha();
		// }
		// this.imprimir_historico();
	}


	// interpretador de comandos
	void exec(String cmd) {

		//Tive de comparar caracteres ao inves de strings (nao estava funcionando)
		if(cmd.charAt(0) == '+') this.mais();
		else if(cmd.charAt(0) == '-') this.menos();
		else if(cmd.charAt(0) == '*') this.vezes();
		else if(cmd.charAt(0) == '/') this.dividido();
		else if(cmd.equals("clear")) this.limpar();
		else if(cmd.equals("hist")) this.imprimir_historico();
		else if(cmd.equals("undo")) this.cancela();
		else {
			this.aPilha.empilha(Double.parseDouble(cmd));
			this.hist.empilha(new Operacao(Double.parseDouble(cmd)));
		}
	}

	static void test() {
		CalcRPN calc = new CalcRPN() ;
		System.out.print("3 2 + = ");
		calc.aPilha.empilha(3.0);
		calc.aPilha.empilha(2.0);
		calc.mais();
		System.out.println(calc.resultado());
		calc = new CalcRPN();
		System.out.print("3 2 - = ");
		calc.aPilha.empilha(3.0);
		calc.aPilha.empilha(2.0);
		calc.menos();
		System.out.println(calc.resultado());
		calc = new CalcRPN();
		System.out.print("3 2 * = ");
		calc.aPilha.empilha(3.0);
		calc.aPilha.empilha(2.0);
		calc.vezes();
		System.out.println(calc.resultado());
		calc = new CalcRPN();
		System.out.print("3 2 / = ");
		calc.aPilha.empilha(3.0);
		calc.aPilha.empilha(2.0);
		calc.dividido();
		System.out.println(calc.resultado());
		calc = new CalcRPN();
		System.out.print("1 2 + 3 4 - / 10 3 - * = ");
		calc.aPilha.empilha(1.0);
		calc.aPilha.empilha(2.0);
		calc.mais();
		calc.aPilha.empilha(3.0);
		calc.aPilha.empilha(4.0);
		calc.menos();
		calc.dividido();
		calc.aPilha.empilha(10.0);
		calc.aPilha.empilha(3.0);
		calc.menos();
		calc.vezes();
		System.out.println(calc.resultado());
	}

	public static void main(String[] args) throws IOException{
		//test();
		CalcRPN calc = new CalcRPN() ;
		String line;
		BufferedReader reader = new BufferedReader
		(new InputStreamReader (System.in));
		while((line = reader.readLine()) != null) {
		if (line.isEmpty())
		continue;
		for (String s : line.split(" "))
		calc.exec(s);
		//System.out.println("Historico = " + calc.hist);
		System.out.println("Pilha = " + calc.aPilha);
		}
		System.out.println("Ate logo");
		}
	
}


