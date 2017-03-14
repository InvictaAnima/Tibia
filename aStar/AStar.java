package aStar;

import java.util.LinkedList;

import gameCharactersMain.GameCharacter;
import gameCharactersMain.GameObjects;
import tibia.Point;

public class AStar {
	LinkedList <Node> openList;   //the set of currently discovered nodes still to be evaluated (OL)
	LinkedList <Node> closedList; //the set of nodes already evaluated	(CL)
	GameCharacter gameCharacter;
	
	public AStar(){
		closedList = new LinkedList<>();
		openList = new LinkedList<>();		
	}
	
	public LinkedList<Point> AStarProcedure(Point start, Point goal){
		openList.add(new Node(start));
		Node q; //node from OL with the lowest f value
		
		while(openList.isEmpty() == false){			
			q = selectNodeWithLowestF(openList);
			
			openList.remove(q);
			closedList.add(q);
			
			if(q.getPoint().equals(goal)){
				//win
				return returnList(start,goal,closedList);
			} else {
			
			for(int i=-1;i<2;i++){
				for(int j=-1;j<2;j++){
					if(Math.abs(i) != Math.abs(j)){ 
					int row = q.getPoint().getRow()+i;
					int column = q.getPoint().getColumn()+j;
					Point point = new Point(row,column);
					if(containtsNodeWithPoint(point,closedList) || !GameObjects.checkField(row,column,gameCharacter)){
						//do nothing
					} else if(!containtsNodeWithPoint(point,openList)){
						Node neighbor = new Node(point);
						openList.add(neighbor);
						neighbor.setFatherReference(q);
						neighbor.setG(Math.abs(start.getRow() - point.getRow()) + Math.abs(start.getColumn() - point.getColumn()));
						neighbor.setH(Math.abs(goal.getRow() - point.getRow()) + Math.abs(goal.getColumn() - point.getColumn()));
						neighbor.setF(neighbor.getG() + neighbor.getH());
					} else {
						Node neighbor = getNodeWherePoint(point,openList);
						int newG = Math.abs(start.getRow() - point.getRow()) + Math.abs(start.getColumn() - point.getColumn());
						if(newG < neighbor.getG()){
							neighbor.setG(newG);
							neighbor.setFatherReference(q);
							neighbor.setF(neighbor.getG() + neighbor.getH());
						}
						
					}
				}
				}
			}
		 }					
		}
		return null;		
		
	}
	
	
	public Node selectNodeWithLowestF(LinkedList<Node> list){
		Node nodeWithLowestF = list.getFirst();
		
		for(Node tmp : list){
			if(tmp.getF() < nodeWithLowestF.getF()){
				nodeWithLowestF = tmp;
			}
		}
				
		return nodeWithLowestF;
	}
	
	public boolean containtsNodeWithPoint(Point point,LinkedList<Node> list){
		
		for(Node tmp : list){
			if(tmp.getPoint().equals(point)){
				return true;
			}
		}
		
		return false;
	}
	
	public Node getNodeWherePoint(Point point, LinkedList<Node> list){
		for(Node tmp : list){
			if(tmp.getPoint().equals(point)){
				return tmp;
			}
		}
		
		return null;
	}
	
	public void printList(Point start, Point goal,LinkedList <Node> list){		
		Node goalNode = null;
		for(Node tmp : list){
			if(tmp.getPoint().equals(goal)){
				goalNode = tmp;
			}				
		}
		
		while(goalNode.getFatherReference() != null){
			System.out.println(goalNode.getPoint().getRow() + ", " + goalNode.getPoint().getColumn() );
			goalNode = goalNode.getFatherReference();
		}
	}
	
	public LinkedList<Point> returnList(Point start, Point goal,LinkedList <Node> list){
	  LinkedList<Point> wayPoints = new LinkedList<>();
		Node goalNode = null;
		for(Node tmp : list){
			if(tmp.getPoint().equals(goal)){
				goalNode = tmp;
			}				
		}		
		
		while(goalNode.getFatherReference() != null){
			wayPoints.add(new Point(goalNode.getPoint().getRow() ,goalNode.getPoint().getColumn()));
			goalNode = goalNode.getFatherReference();
		}
		return wayPoints;
	}
	
}
