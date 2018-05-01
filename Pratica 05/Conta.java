import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;


public class Conta {

	public static String nomeCliente;
	public String saldo;
	public static String agencia;
	public static String numero;
	public static String senha;
	public String md5;

	public Conta(String Agencia, String Numero, String Senha) {
		agencia = Agencia;
		numero = Numero;
		senha = Senha;
		nomeCliente = null;
		//md5 = null;
		md5 = SecurityProvider.md5ToServer(this);
		saldo = null;
	}

	public Conta(String Agencia, String Numero, String Senha, String Saldo,
			String NomeCliente) {
		agencia = Agencia;
		numero = Numero;
		senha = Senha;
		saldo = Saldo;
		nomeCliente = NomeCliente;
		//md5 = null;
		md5 = SecurityProvider.md5ToServer(this);
	}

	public Conta(String NomeCliente, String Saldo) {
		saldo = Saldo;
		nomeCliente = NomeCliente;
		agencia = null;
		numero = null;
		senha = null;
		md5 = null;
	}

	public static void test() {
		Conta c1 = new Conta("124", "333", "1234", "10", "john doe");
		System.out.println(c1);
		Conta c2 = new Conta("John Doe", "10");
		System.out.println(c2);
		Conta c3 = new Conta("123", "321", "666");
		System.out.println(c3);
	}

	public String get_senha() {
		return senha;
	}

	public String get_saldo() {
		return saldo;
	}

	public String get_agencia() {
		return agencia;
	}

	public String get_numero() {
		return numero;
	}

	public String get_nomeCliente() {
		return nomeCliente;
	}

	public String get_md5() {
		return md5;
	}

	public void set_senha(String Senha) {
		senha = Senha;
	}

	public void set_saldo(String Saldo) {
		saldo = Saldo;
	}

	public void set_nomeCliente(String NomeCliente) {
		nomeCliente = NomeCliente;
	}

	public void set_agencia(String Agencia) {
		agencia = Agencia;
	}

	public void set_numero(String Numero) {
		numero = Numero;
	}

	public void set_md5(String Md5) {
		md5 = Md5;
	}

	public String toString() {
		String dados = "Agencia: ";
		dados += agencia;
		dados += System.getProperty("line.separator");
		dados += "Conta: ";
		dados += numero;
		dados += System.getProperty("line.separator");
		dados += "Senha: ";
		dados += senha;
		dados += System.getProperty("line.separator");
		dados += "Nome Cliente: ";
		dados += nomeCliente;
		dados += System.getProperty("line.separator");
		dados += "Saldo: ";
		dados += saldo;
		dados += System.getProperty("line.separator");
		dados += "MD5: ";
		dados += md5;
		dados += System.getProperty("line.separator");
		return dados;
	}

	public static void main(String[] args) {
		Conta.test();

	}
}