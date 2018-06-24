import java.util.ArrayList;

public class Database {
	public static final ArrayList<Letras> caracteres;

	static {
		caracteres = new ArrayList<Letras>();

		for (int i = (int) 'a'; i < (int) 'z'; i++) {
			char x = (char) i;
			caracteres.add(new Letras(String.valueOf(x)));
		}
		for (int i = (int) 'A'; i < (int) 'Z'; i++) {
			char x = (char) i;
			caracteres.add(new Letras(String.valueOf(x)));
		}
		for (int i = (int) '0'; i < (int) '9'; i++) {
			char x = (char) i;
			caracteres.add(new Letras(String.valueOf(x)));
		}
		caracteres.add(new Letras(" "));

	}

	public static Letras get_Letras(String md5) {
		for (int i = 0; i < caracteres.size(); i++) {
			if (caracteres.get(i).get_md5Code().equals(md5)) {
				return caracteres.get(i);
			}
		}
		return null;
	}

	public static void test6() {
		Letras l = new Letras("a");
		String md5 = l.get_md5Code();
		System.out.println(md5);
		Letras ll = Database.get_Letras(md5);
		System.out.println(ll.get_caracter());
	}

	public static void main(String[] args) {
		test6();
		//Database.test5();
	}

	public static Conta get_conta(String[] md5_v) {
		String x = new String();
		for (int i = 0; i < md5_v.length; i++) {
			x += Database.get_Letras(md5_v[i]).get_caracter();
		}
		String[] aux = x.split(" ");
		String cliente = new String();
		for (int i = 0; i < aux.length - 1; i++) {
			cliente += aux[i] + " ";
		}
		Conta C = new Conta(cliente, aux[aux.length - 1]);
		return C;
	}

	public static void test5() {
		Conta c = new Conta("124", "333", "1234", "10", "john doe");
		ServerDatabase.insereConta(c);
		String chave = SecurityProvider.md5ToServer(c);
		Conta conta = ServerDatabase.getConta(chave);
		String chars[];
		chars = SecurityProvider.md5ToClient(conta);
		System.out.println(Database.get_conta(chars));
	}

}