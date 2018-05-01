import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SecurityProvider {

	public static String salt = "5a1t";

	public static String md5(String stringToConvert) {
		String hashtext = "";
		stringToConvert += salt;
		MessageDigest m;
		try {
			m = MessageDigest.getInstance("MD5");
			m.reset();
			m.update(stringToConvert.getBytes());
			byte[] digest = m.digest();
			BigInteger bigInt = new BigInteger(1, digest);
			hashtext = bigInt.toString(16);
		} catch (NoSuchAlgorithmException ex) {
			Logger.getLogger(SecurityProvider.class.getName()).log(
					Level.SEVERE, null, ex);
		}
		return hashtext;
	}

	public static void main(String[] args) {
		//System.out.println(SecurityProvider.md5("teste"));
		//test2();
		test4();
	}

	public static String md5ToServer(Conta conta) {
		String cat = conta.agencia + conta.numero + conta.senha + SecurityProvider.salt;
		return md5(cat);
	}

	public static String[] md5ToClient(Conta conta) {
		String toCrypt = conta.get_nomeCliente() + " " + conta.get_saldo();
		String[] S = new String[toCrypt.length()];

		for (int i = 0; i < toCrypt.length(); i++) {
			S[i] = SecurityProvider.md5(String.valueOf(toCrypt.charAt(i)));
		}

		return S;
	}

	public static void test2()
	{
		Conta c = new Conta("1234", "2222","1245");
		System.out.println(SecurityProvider.md5ToServer(c));
	}

	public static void test4() {
		Conta c = new Conta("124", "333", "1234", "10", "john doe");
		ServerDatabase.insereConta(c);
		String chave = SecurityProvider.md5ToServer(c);
		Conta conta = ServerDatabase.getConta(chave);
		String chars[];
		chars = SecurityProvider.md5ToClient(conta);
		for (int i = 0; i < chars.length; i++) {
			System.out.println(chars[i]);
		}
	}

}