public class Aa {
	int valor;
	int cor; //0 para vermlho e 1 para preto
	Aa esq;
	Aa dir;
	static int R=0; //vermelho
	static int N=1; //preto

	Aa(int v, int c, Aa Esquerda, Aa Direita){
		valor = v;
		cor = c;
		esq = Esquerda;
		dir = Direita;
	}

	static String infixe(Aa a){
		if(a.esq == null){
			if(a.dir == null){
				return a.valor + " ";
			}
			return " " + a.valor + " " + infixe(a.dir);
		}
		if(a.dir == null){
			return " " + a.valor + " " + infixe(a.esq);
		}
		return infixe(a.esq) + a.valor + infixe(a.dir);
	}

	static Aa rodeDir(Aa a){

		// Rotacao a direita
		if(a.esq != null){
			Aa a1 = a.esq;
			a.esq = a1.dir;
			a1.dir = a;
			return a1;
		} 
		return a;
	}

	static Aa rodeEsq(Aa a){

		// Rotacao a esquerda
		if(a.dir != null){
			Aa a2 = a.dir;
			a.dir = a2.esq;
			a2.esq = a;
			return a2;
		} 
		
		return a;
	}

	static Aa insere(Aa a, int i){

		if(a == null) {
			a = new Aa(i, 0, null, null);
		}

		else if( i<a.valor ) {
			a.esq = insere(a.esq, i);
			//inserir um no a esquerda sempre sera preto!
			a.esq.cor = 1;
		}
		else if( i>=a.valor) {
			a.dir = insere(a.dir, i);
		} 

		a = rodeDir(a);
		a = rodeEsq(a);
		
		return a;
	}

	static Aa insereECorrigeRaiz(Aa a, int valor){
		a = insere(a, valor);
		if(a.cor == 0) a.cor = 1;
		return a;
	}

	static int nivel(Aa a){
		int cont = 0;
		Aa next = a;
		while(next != null){
			if(next.cor == 1) cont++;
			next = next.esq;
		}
		return cont;
	}

	static boolean testeArvoreAa(Aa a) {
		return testSubArvoreAa(a, nivel(a), false);
	}

	static boolean testeFilhos(Aa a){

		if(a.esq == null && a.dir == null) return true;
		// Se for vermelho, entao seu filhos devem ser pretos
		if(a.cor == 0){
			if(a.esq != null && a.esq.cor == 0) return false;
			if(a.dir != null && a.dir.cor == 0) return false;
		}
		//Se for preto, entao seu filho a esquerda deve ser preto
		else if(a.cor == 1){
			if(a.esq != null && a.esq.cor == 0) return false;
		}
		
		if(a.esq != null) return testeFilhos(a.esq);
		else return testeFilhos(a.dir);
	}

	static boolean testSubArvoreAa(Aa a, int nivel, boolean raizPodeSerVermelha){

		if(a == null && nivel !=0) return false;
		if(a.cor == 0 && !raizPodeSerVermelha) return false;

		return testeFilhos(a);
	}

	public static void main (String [] args) {
		// Aa a = new Aa (3, N, new Aa (1, N, null, null),
		// new Aa (8, R,
		// new Aa (5, N,
		// null,
		// new Aa (6, R, null, null)),
		// new Aa (9, N,
		// null,
		// new Aa (11, R, null, null))));

		// *********** TESTE DE ROTACAO A DIREITA
		// Aa a = new Aa (4, R,
		// new Aa (2, R,
		// new Aa (1, N, null, null),
		// new Aa (3, N, null, null)),
		// new Aa (5, N, null, null));
		// *********** FIM DO TESTE DE ROTACAO A DIREITA

		// ********* TESTE DE ROTACAO A ESQUERDA
		// Aa a = new Aa (2, N,
		// new Aa (1, N, null, null),
		// new Aa (4, R,
		// new Aa (3, N, null, null),
		// new Aa (6, R,
		// new Aa (5, N, null, null),
		// new Aa (7, N, null, null))));
		// ********* FIM DO TESTE DE ROTACAO A ESQUERDA

		// ************ TESTE DE INSERÇÃO
		Aa a = new Aa (2, N, null, null);
		new Fenetre(a, "Arvore 0");
		a = insereECorrigeRaiz(a, 1);
		new Fenetre(a, "Arvore 1");
		a = insereECorrigeRaiz(a, 8);
		new Fenetre(a, "Arvore 2");
		a = insereECorrigeRaiz(a, 2);
		new Fenetre(a, "Arvore 3");
		a = insereECorrigeRaiz(a, 0);
		new Fenetre(a, "Arvore 4");
		a = insereECorrigeRaiz(a, 5);
		new Fenetre(a, "Arvore 5");
		a = insereECorrigeRaiz(a, 10);
		new Fenetre(a, "Arvore 5");

		if(testeArvoreAa(a)){
			System.out.println("ARVORE CORRETA");
		} else {
			System.out.println("ARVORE INCORRETA");
		}
	}
}