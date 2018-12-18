package com.anand.generics;

public class BasicClass<T> {
	
	T t;
	BasicClass(T t){}
	 public T getT() {
		 return this.t;
	 }
	 public void setT(T t) {
		 this.t=t;
	 }

}
