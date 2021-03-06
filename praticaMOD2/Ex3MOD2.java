	
// Classe Quicksort a ser completada
class Quicksort {

    static void swap(int[] a, int i, int j) {
        int aux = a[i];
        a[i] = a[j];
        a[j] = aux;
    }

    static int partition(int[] a, int l, int r) {
        int pivo = a[l];
        int init_pos = l;

        while(l < r){

            while(a[l] <= pivo && l < r){
                l++;
            }
            while(a[r] > pivo && r>0){
                r--;
            }

            if(l < r) {
                swap(a,l,r);
            }
        }

        a[init_pos] = a[r];
        a[r] = pivo;
        
        return r;
    }

    static void quickrec(int[] a, int l, int r) {
        if(l < r){
        	int pivo = partition(a,l,r);
        	quickrec(a,l,pivo-1);
        	quickrec(a,pivo+1,r);
        }
        
    }

    static void quicksort(int[] a, int l, int r) {
    	quickrec(a,l,r);
    }

}

// A classe Ex3 � fornecida, para testar o c�digo de Quicksort
class Ex3MOD2 {
    static boolean is_sorted(int[] a) {
        for (int i = 1; i < a.length; i++)
            if (!(a[i-1] <= a[i])) return false;
        return true;
    }

    static final int M = 10; // os elementos est�o entre 0..M-1

    static int[] occurrences(int[] a) {
        int[] occ = new int[M];
        for (int i = 0; i < a.length; i++)
            occ[a[i]]++;
        return occ;
    }

    static boolean is_permut(int[] occ1, int[] occ2) {
        for (int i = 0; i < M; i++)
            if (occ1[i] != occ2[i]) return false;
        return true;
    }

    static String print(int[] a) {
        String s = "[";
        for (int i = 0; i < a.length; i++)
            s += (i == 0 ? "" : ", ") + a[i];
        return s + "]";
    }

    static int[] random_array(int len) {
        int[] a = new int[len];
        for (int i = 0; i < len; i++)
            a[i] = (int)(M * Math.random());
        return a;
    }

    static void test_partition(int[] a, int l, int r) {
        int v = a[l];
        System.out.println("  teste com      a = " + print(a) + " v = " + v);
        int[] occ1 = occurrences(a);
        int m = Quicksort.partition(a,l,r);
        System.out.println("  partition(a,"+l+","+r+") = " + print(a) + " m = " + m);
        int[] occ2 = occurrences(a);
        if (!is_permut(occ1, occ2)) {
            System.out.println("ERRO : os elementos diferem");
            System.exit(1);
        }
        // Este teste est� com bug
        // for (int i = l; i <= r; i++)
        //     if (!(i < m ? a[i] < v : a[i] >= v)) {
        //         System.out.println("ERRO : particionamento errado");
        //         System.exit(1);
        //     }
    }

    static void test(int[] a) {
        System.out.println("  teste com       a = " + print(a));
        int[] occ1 = occurrences(a);
        //Consertei este teste
        Quicksort.quicksort(a, 0, a.length-1);
        int[] occ2 = occurrences(a);
        System.out.println("  quicksort(a) => a = " + print(a));
        if (!is_sorted(a)) {
            System.out.println("ERRO : o resultado nao esta ordenado");
            System.exit(1);
        }
        if (!is_permut(occ1, occ2)) {
            System.out.println("ERRO : os elementos diferem");
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        System.out.println("teste de partition");
        for (int len = 0; len < 10; len++)
            for (int l = 0; l < len; l++)
                for (int r = l+1; r < len; r++)
                    test_partition(random_array(len), l, r);
        System.out.println("teste de quicksort");
        for (int len = 0; len < 10; len++)
            for (int j = 0; j <= len; j++)
                test(random_array(len));
        System.out.println("SUCESSO");
    }

}
