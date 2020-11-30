package src;

import java.util.ArrayList;

public class Pair<T> {
	private ArrayList<T> arr;

	public Pair(T x, T y) {
		this.arr = new ArrayList<T>();
		this.arr.add(x);
		this.arr.add(y);
	}

	public T get(int index) {
		return this.arr.get(index);
	}

	public String toString() {
		return "(" + arr.get(0) + ", " + arr.get(1) + ")";
	}
}
