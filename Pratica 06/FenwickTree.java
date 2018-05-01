public class FenwickTree {
	public int value;
	public int leftSize;
	public FenwickTree left;
	public FenwickTree right;

	FenwickTree(int Value) {
		value = Value;
		leftSize = 0;
		left = null;
		right = null;
	}

	FenwickTree(int LeftSize, FenwickTree Left, FenwickTree Right) {
		value = Left.value + Right.value;
		if (LeftSize == 0) {
			leftSize = 1;
		} else {
			leftSize = LeftSize;
		}
		left = Left;
		right = Right;
	}

	public String toString() {
		if (leftSize == 0) {
			if (right == null) {
				return "[" + value + "," + leftSize + "]";
			}
			return "[" + value + "," + leftSize + "," + right.toString() + "]";
		}
		if (right == null) {
			return "[" + value + "," + leftSize + "," + left.toString() + ","
					+ "]";
		}

		return "[" + value + "," + leftSize + "," + left.toString() + ","
				+ right.toString() + "]";
	}

	static FenwickTree allZeros(int n) {
		if (n == 0) {
			return null;
		}
		if (n == 1) {
			return new FenwickTree(0);
		}
		int m = n / 2;
		return new FenwickTree(n - m, allZeros(n - m), allZeros(m));
	}

	public int size() {
		if (leftSize == 0) {
			return 1;
		}
		return leftSize + right.size();
	}
	public void increment(int i, int delta) {
		value += delta;
		if (leftSize == 0)
			return;
		if (i < leftSize) {
			left.increment(i, delta);
		} else {
			right.increment(i - leftSize, delta);
		}
	}
	
    public int prefixSum(int upto) {
        if (upto == 0 && right == null && left == null) {
            return 0;
        }
        if (right == null && left == null) {
            return value;
        }

        if (upto >= leftSize) {
            return (value - right.value) + right.prefixSum(upto-leftSize);
        } else {
            return left.prefixSum(upto);
        }
    }
    
    public int between(int lo, int hi) {
        return prefixSum(hi) - prefixSum(lo);
    }


	public static void main(String[] args) {
		FenwickTree T = new FenwickTree(3, new FenwickTree(1, new FenwickTree(4),
				new FenwickTree(1, new FenwickTree(2), new FenwickTree(5))),
				new FenwickTree(1, new FenwickTree(3),
				new FenwickTree(1, new FenwickTree(6), new FenwickTree(1))));
				System.out.println("Arvore this : " + T);
				System.out.println("Soma das folhas entre lo e hi : ");
				System.out.print(" ");
				for(int lo = 0; lo <= 6; lo++)
				System.out.print("lo = " + lo + " ");
				System.out.println();
				for(int hi = 0; hi <= 6; hi++){
				System.out.print("hi = " + hi + " ");
				for(int lo = 0; lo <= hi; lo++){
				System.out.print(T.between(lo, hi) + " ");
				if(T.between(lo, hi) < 10) System.out.print(" ");
				}
				System.out.println();
	}
}


}