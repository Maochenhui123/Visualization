package pers.util.association;

import java.util.List;

public class Tree {
	
	/**
	 * check if there are repetition in the same level
	 * @param init the start Node
	 * @param node the node where we want to find
	 * @return true for there is repetition, false for there is no repetition
	 */
	public static boolean repetitionCheck(Node init, String node) {	
		for(int i=0;i<init.getChildren().size();i++) {
			if(node.equals(init.getChildren().get(i).getName()))
				return true;
		}
		
		
		return false;
	}
	
	/**
	 * Retrieve the given node by the name of the node in the leaf
	 * @param init: the root of the tree
	 * @param Node: the name of the given node
	 * @param list: once find the given node, then add the node into the list
	 * @return return: the list of the node, mainly for recursion purpose
	 */
	public static List<Node> retrieveNode(Node init, String Node, List<Node> list){
		for(int i=0;i<init.getChildren().size();i++) {
			
			if(init.getChildren().get(i).getName().equals(Node)) {
				list.add(init.getChildren().get(i));
				continue;
			}
			/**current node is not a leaf, then keep recursing until reach the leaf**/
			retrieveNode(init.getChildren().get(i),Node,list);
			
		}
		return list;
	}
	
	
	/**
	 * given the leaf node, find the root of this leaf
	 * @param leaf: the leaf node
	 * @return the root node
	 */
	public static Node findRoot(Node leaf) {
		Node previousNode = leaf;
		Node parent = leaf;
		while(true) {
			parent = parent.getParent();
			if(parent == null)
				break;
			previousNode = parent;
		}
		return previousNode;
	}
	
	/**
	 * Add the tree to the other tree, E.g.
	 * there are two tree:
	 * 		I1:1         I1:1
	 * 	    |            |
	 * 		I2:1         I2:1
	 * 		|            |
	 * 		I3:1         I4:1
	 * then we add the second tree to the first tree, then we can get the new tree is 
	 * 			I1:2         
	 * 	    	|            
	 * 			I2:2         
	 * 		|   	|          
	 * 		I3:1    I4:1     
	 * @param root: the root of one tree, where the other tree needed to be added to 
	 * @param tree: the root of the other tree
	 */
	public static void addTree(Node root, Node tree) {
		/**current Node for the first tree**/
		Node currentNode_ini = root;
		/**previous Node for the first tree**/
		Node previousNode_ini = root;
		/**current Node for the second tree**/
		Node currentNode_tree = tree;
		/**judge whether we should add the tree**/
		boolean flag = false;
		/**iterate the tree to check the repetition**/
		while(true) {
			/**if the node of the first tree contain the node of the second tree**/
			if((currentNode_ini=currentNode_ini.getChild(currentNode_tree.getName()))!=null) {
				previousNode_ini = currentNode_ini;
				/**add the node in the second tree to the first tree**/
				currentNode_ini.updateCount(currentNode_tree.getCount());
				if(!currentNode_tree.isLeaf()) {
					currentNode_tree=currentNode_tree.getChildren().get(0);
				}else {
					flag = true;
					break;
				}
				continue;
			}
			break;
		}
		if(flag == false)
			previousNode_ini.addChild(currentNode_tree);
	}
	
	/**
	 * Add item into item table, follow the tree, E.g.
	 * 			I1:2         
	 * 	    	|            
	 * 			I2:2         
	 * 		|   	|          
	 * 		I3:1    I4:1
	 *  so we can add the node to item list, we can get the item list
	 *  {[Name:I1,count:2],[Name:I2,count:2],[Name:I3,count:1],[Name:I4,count:1]}
	 * @param item: the list of item where needed to be added to
	 * @param root: the root of the tree
	 */
	public static void addTid(List<Tid> item, Node root) {
		for(int i=0;i<root.getChildren().size();i++) {
			/**
			 * check the repetition of the content of the item list
			 */
			checkItem(item,new Tid(root.getChildren().get(i).getName(),root.getChildren().get(i).getCount()));
			addTid(item,root.getChildren().get(i));
		}
	}
	
	/**
	 * check if there are repetition in the item,
	 * if there are repetition, then add the count in the list
	 * else there are no repetition, then add new item into the list
	 * E.g. for the item list: {[Name:I1,count:2],[Name:I2,count:2],[Name:I3,count:1],[Name:I4,count:1]}
	 * if we want to add [Name:I1,count:2], since there are repetition, then we add to the count
	 * the list become {[Name:I1,count:4],[Name:I2,count:2],[Name:I3,count:1],[Name:I4,count:1]}
	 * else we want to add[Name:I5,count:1], since there is no repetition, then we add new item
	 * the list become {[Name:I1,count:2],[Name:I2,count:2],[Name:I3,count:1],[Name:I4,count:1],[Name:I5,count:1]}
	 * @param item: the list of item we needed to store the item
	 * @param t the new item we want to add
	 */
	public static void checkItem(List<Tid> item, Tid t) {
		/**the flag where we can judge whether to put the item or not**/
		boolean flag = false;
		
		/**iterate the item list check whether there is repetition**/
		for(int i = 0;i<item.size();i++) {
			if(item.get(i).getName().equals(t.getName())) {
				item.get(i).setCount(t.getCount()+item.get(i).getCount());
				flag = true;
				break;
			}	
		}
		if(flag == false)
			item.add(t);
	}
	
	
}
