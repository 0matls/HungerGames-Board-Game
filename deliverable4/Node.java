package deliverable4;

import java.util.*;

public class Node {
	
	private Node parent;
	private ArrayList<Node> children = new ArrayList<Node>();
	private int nodeDepth;
	private int[] nodeMove;
	private Board nodeBoard;
	private double nodeEvaluation;
	
	/**
	 * Emptyonstructor of the class.
	 */
	public Node() {
	}
	
	
	/**
	 * functions (a.k.a setters) to set the values of the variables in the class
	 * @param parent, nodeDepth, nodeMove, nodeBoard, nodeEvaluation
	 */
	public void setParent( Node parent) {
		this.parent = parent;
	}
	
	public void setNodeDepth( int nodeDepth) {
		this.nodeDepth = nodeDepth;
	}
	
	public void setNodeMove( int[] nodeMove) {
		this.nodeMove = nodeMove;
	}

	public void setNodeBoard( Board nodeBoard) {
		this.nodeBoard = nodeBoard;
	}
	
	public void setNodeEvaluation( double nodeEvaluation) {
		this.nodeEvaluation = nodeEvaluation;
	}
	
	
	/**
     * functions (a.k.a getters) to get the values of the variables in the class
     * @return parent, nodeDepth, nodeMove, nodeBoard, nodeEvaluation, children 
     */
	public Node getParent() {
		return parent;
	}
	
	public int getNodeDepth() {
		return nodeDepth;
	}
	
	public int[] getNodeMove() {
		return nodeMove;
	}
	
	public Board getNodeboard() {
		return nodeBoard;
	}
	
	public double getNodeEvaluation() {
		return nodeEvaluation;
	}
			
	
			
	public ArrayList<Node> getArrayList(){
		return children;
	}
	
	/**
	 * function to add a child in the ArrayList children, in other words to add a node 
	 * to the tree with depth 1
	 * @param n
	 */
	public void addChild(Node n) {
		children.add(n);
	}
}

