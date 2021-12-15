package com.sample.microservices.common.chapter.main;

public abstract class Whale implements Jump{

	public abstract void dive();
	
	public static void main(String[] args) {
		Whale whale = new Orca();
//		whale.dive(3); compile error

	}

}

interface Jump {
	static public int MAX =3;
}

class Orca extends Whale {

	@Override
	public void dive() {
		System.out.print("Orca diving");
		
	}
	
	public void dive(int... depth) {
		System.out.println("Orca diving deeper" + MAX);
	}
	
}