/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;

/**
 *
 * @author halpin
 */
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

    public void set(int index, T value) {
        this.arr.set(index, value);
    }

    @Override
    public String toString() {
        return "(" + arr.get(0) + ", " + arr.get(1) + ")";
    }
}