package fr.calculator.parser;

public class Literal implements Term {
	
	public final String name;
	
	public Literal(String name) {
		this.name = name;
	}
	
	@Override
	public Term reverse() {
		return new Division(new IntegerTerm(1), this);
	}
	
	@Override
	public Term negate() {
		return new Multiplication(new IntegerTerm(-1), this);
	}
	
	@Override
	public Term simplify() {
		return this;
	}
	
	@Override
	public String toString() {
		return "Literal: " + name;
	}
	
	@Override
	public boolean equals(Object obj) {
		return (obj instanceof Literal) && name == ((Literal) obj).name;
	}
	
}
