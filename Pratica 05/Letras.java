public class Letras {
	
    private String caracter;
    private String md5Code;

    public Letras(String c) {
        caracter = c;
        md5Code = SecurityProvider.md5(c);
    }
    public String get_caracter(){
    	return caracter;
    }
    
    public String get_md5Code(){
    	return md5Code;
    }
    
    public void set_caracter(String c){
    	caracter = c;
    }
    
    public void set_md5Code(String c){
    	md5Code = c;
    }
}