package aStar;

import tibia.Point;

public class Node {
	private Point point;
	Node fatherReference;	
	private int g; //g(n) is the cost of the path from the start node to n
	private int h; //h(n) is a heuristic that estimates the cost of the cheapest path from n to the goa
	private int f; //g(n) + h(n)
	
	public Node(Point point){
		this.point = point;
	}
	
	public Point getPoint() {
		return point;
	}
	
	public Node getFatherReference() {
		return fatherReference;
	}
	
	public int getG() {
		return g;
	}
	
	public int getH() {
		return h;
	}
	
	public int getF() {
		return f;
	}
	
	public void setPoint(Point point) {
		this.point = point;
	}
	
	public void setFatherReference(Node fatherReference) {
		this.fatherReference = fatherReference;
	}
	
	public void setG(int g) {
		this.g = g;
	}
	
	public void setH(int h) {
		this.h = h;
	}
	
	public void setF(int f) {
		this.f = f;
	}
}
