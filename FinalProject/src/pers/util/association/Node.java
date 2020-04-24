package pers.util.association;

import java.util.ArrayList;
import java.util.List;

public class Node {

	/**name the node**/
	private String name = "root";
	/**count the frequency**/
	private int count = 0;
	/**parent node of this node**/
	private Node parent = null;
	/**children of this node**/
	private List<Node> children = new ArrayList<>();
	
	public Node() {
		
	}
	
	
	public Node(String name, int count, Node parent) {
		super();
		this.name = name;
		this.count = count;
		this.parent = parent;
	}


	public Node(String name, int count,  Node parent, List<Node> children) {
		super();
		this.name = name;
		this.count = count;
		this.parent = parent;
		this.children = children;
	}
	
	public void updateCount() {
		this.count++;
	}
	public void updateCount(int update) {
		this.count = this.count +update;
	}
	public void addChild(String node) {
		this.children.add(new Node(node,1,this));
	}
	public void addChild(Node node) {
		this.children.add(node);
		node.setParent(this);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Node getParent() {
		return parent;
	}
	public void setParent(Node parent) {
		this.parent = parent;
	}
	public List<Node> getChildren() {
		return children;
	}
	public void cutTree(){
		this.children.clear();
	}
	public Node newNode() {
		return new Node(this.name,1,null);
	}
	
	public Node newNode(int number) {
		return new Node(this.name,number,null);
	}
	
	public Node getChild(String child) {
		for(int i=0;i<this.getChildren().size();i++) {
			if(this.getChildren().get(i).getName().equals(child))
				return this.getChildren().get(i);
		}
		return null;
	}
	
	public boolean isLeaf() {
		return this.children.size()==0?true:false;
	}
	
	@Override
	public String toString() {
		if(parent==null)
			return "Node [name=" + name + ", parent= null" + ",count="+this.getCount()+"]";
		return "Node [name=" + name + ", parent=" + parent.getName() + ",count="+this.getCount()+"]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + count;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((parent == null) ? 0 : parent.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (count != other.count)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (parent == null) {
			if (other.parent != null)
				return false;
		} else if (!parent.equals(other.parent))
			return false;
		return true;
	}
	
}
