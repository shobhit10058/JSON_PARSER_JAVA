package org.example.utilityclasses;

public class Tuple3<T, V, K> {
	T first;
	V second;
	K third;

	public Tuple3(T first, V second, K third) {
		this.first = first;
		this.second = second;
		this.third = third;
	}

    public T getFirst() {
        return first;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public V getSecond() {
        return second;
    }

    public void setSecond(V second) {
        this.second = second;
    }

    public K getThird() {
        return third;
    }

    public void setThird(K third) {
        this.third = third;
    }
}
