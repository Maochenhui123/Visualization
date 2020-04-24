package pers.util.association;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * FP_growth algorithm, where we can establish tree
 * to mining the frequent one 
 * @author mch
 */
public class FP_growth {
	/**the initial node, namely the root of the Tree**/
	public static Node ini = new Node();
	
	public static int minsup = 2;
	
	public static Map<String,Integer> freqMap = new HashMap<>();
	
	/**
	 * count the frequency for every element appear in the list E.g.
	 * {{I2,I3},{I2,I3},{I4,I5},{I1,I2},{I5,I6}}
	 * then we can get the frequency map is
	 * {[I2]:3,[I3]:2,[I4]:1,[I5]:2,[I6]:1}
	 * @param list: the input list where frequency needed to be counted
	 */
	public static void freqCount(List<List<String>> list) {
		for(int i=0;i<list.size();i++) {
			List<String> count = list.get(i);
			for(int j=0;j<count.size();j++) {
				if(freqMap.containsKey(count.get(j)))
					freqMap.put(count.get(j), freqMap.get(count.get(j))+1);
				else
					freqMap.put(count.get(j), 1);
			}
		}
	}
	
	/**
	 * Calculate the minimum support in the list
	 * @param list: the input list where the minimum support needed to be calculated
	 * @return the Map store the result
	 */
	public static Map<Integer, Integer> minsup(List<Integer> list) {
		Map<Integer,Integer> count = new HashMap<>();
		for(int i = 0; i<list.size();i++) {
			if(!count.containsKey(list.get(i)))
				count.put(list.get(i), 1);
			else
				count.put(list.get(i), count.get(list.get(i))+1);
		}
		

		return count;
	}
	
	/**
	 * Generate the Tree by the input
	 * @param item List<List<String>>, where List<String>
	 * store the list of incident. 
	 * E.g. {T1,I1} means T1, I1 appear at the same time
	 * List<List<String>> store the total incident independently
	 * E.g. {{T1,I1},{T2,I2}} means {T1,I1} and {T2,I2} happens at two different times
	 * @return the root node of the generated tree
	 */
	public static Node init(List<List<String>> item) {
		/**rotate with the length of the List**/
		for(int i=0;i<item.size();i++) {
			List<String> itemcopy = new ArrayList<>(item.get(i));
			addNode(ini,itemcopy);
		}
		return ini;
	}
	
	/**
	 * A recursion method that can be used to add node into tree
	 * @param parent: parent node where the list of item needed to be added under the parent
	 * @param item: a series of item that waited to be added, once add one remove the one just being added
	 */
	public static void addNode(Node parent,List<String> item) {
		/**
		 * if the item is not empty, then continue the recursion
		 * else the item is empty, then stop
		 **/
		if(item.size()>0) {
			/**
			 * Check whether there are repetition, E.g
			 * the tree:
			 *     root
			 *      |
			 *      I1:2
			 *     |   |  
			 *   I2:1  I3:1
			 * and we want to add node I1 into this tree, we can find that
			 * I1 already appear in this tree so we just need to update 
			 * node I1, and the tree become:
			 *     root
			 *      |
			 *      I1:3
			 *     |   |  
			 *   I2:1  I3:1
			 * 
			 */
			if(Tree.repetitionCheck(parent, item.get(0))) {
				Node repetition = parent.getChild(item.get(0));
				repetition.updateCount();
				item.remove(0);
				addNode(repetition,item);
			}
			/**
			 * if there is no repetition then we just direct add into it, E.g.
			 * 	   root
			 *      |
			 *      I1:2
			 *     |   |  
			 *   I2:1  I3:1
			 *   
			 *   and we want to add Node I2 into this tree
			 *   this tree become:
			 *   		root
			 *      |			|
			 *      I1:2        I2:1
			 *     |   |  		
			 *   I2:1  I3:1		
			 */
			else {
				parent.addChild(item.get(0));
				String rmnode = item.remove(0);
				addNode(parent.getChild(rmnode),item);
			}
			
		}
	}
	
	
	/**
	 * print the tree's structure
	 * @param init: the root of this tree
	 */
	public static void traversePrint(Node init) {
		for(int i=0; i < init.getChildren().size();i++) {
			System.out.print(init.getChildren().get(i)+" ");
		}
		System.out.println();
		for(int i = 0; i < init.getChildren().size();i++) {
			traversePrint(init.getChildren().get(i));
		}
	}
	
	/**
	 * construct the fpTree, under certain nodes
	 * where we need to find the parent tree where contains these nodes
	 * @param list: a list of node contains aim-tree, E.g
	 * 
	 * 		   	root
	 *      |			|
	 *      I1:2        I3:1
	 *     |   |  		|
	 *   I2:1  I3:1		I2:1
	 * For this tree if we want to find the FP-Tree for I2, the list will be
	 * {Node:[name:I2,count:1,parent:I1],Node:[name:I2,count:1,parent:I3]}
	 * and the FP-tree will become:
	 *  		 root
	 *      |			|
	 *      I1:1        I3:1
	 *      		
	 *    	
	 * @return  the root of this FP-Tree node
	 */
	public static Node fpTree(List<Node> list, List<Tid> item) {
		Node root = new Node();
		for(int i=0; i<list.size(); i++) {
			if(list.get(i).getParent().equals(ini))
				continue;
			Node fLevel = generateTreeBottom(list.get(i).getParent().newNode(list.get(i).getCount()),list.get(i).getParent().getParent());
			fLevel = Tree.findRoot(fLevel);
			
			Tree.addTree(root, fLevel);
		}
		
		checkFP(root);
		Tree.addTid(item, root);
		
		return root;
	}
	
	/**
	 * Check the FP-tree and remove the node where do not satisfy the smallest support
	 * @param root: the node of the FP-tree
	 */
	public static void checkFP(Node root) {
		int i = 0;
		while(i<root.getChildren().size()) {
			if(root.getChildren().get(i).getCount() < minsup)
				root.getChildren().remove(i);
			else {
				checkFP(root.getChildren().get(i));
				i++;
			}
		}
	}
	
	/**
	 * generate the new tree from the bottom to the root
	 * @param child:the child node needed to be added to the parent node
	 * @param parent: the parent node
	 * @return return the leaf of this tree
	 */
	public static Node generateTreeBottom(Node child, Node parent) {
		
		if(!parent.equals(ini)) {	
			Node pNode = parent.newNode(child.getCount());
			pNode.addChild(child);
			generateTreeBottom(pNode,parent.getParent());
		}
		return child;
	}
	
	
	/**
	 * generate the frequency count for the FP-tree, E.g. the FP-tree:
	 * 			root
	 *      |			|
	 *      I1:2        I3:1
	 *     |   |  		|
	 *   I2:1  I3:1		I2:1
	 *   
	 * and its frequency count is:
	 * [I1]:2,[I1,I2]:1,[I1,I3]:1,[I2]:2,[I3]:2,[I2,I3]:1
	 * @param item: the list of item
	 * @param root: the root of the FP-Tree
	 * @param Name: the name of current prefix 
	 * @param map: the map where stored the frequency
	 */
	public static void freqSet(List<Tid> item, Node root, String Name, Map<String,Integer> map) {
		for(int i=0;i<item.size();i++) {
			String condition = item.get(i).getName()+"-"+Name;
			map.put(condition, item.get(i).getCount());
			
			List<Tid> subItem = new ArrayList<>();
			List<Node> l = new ArrayList<>();
			Tree.retrieveNode(root, item.get(i).getName(), l);
			
			Node subRoot = fpTree(l,subItem);
			freqSet(subItem, subRoot, condition,map);
		}
	}
	

	
	
//	public static void main(String[] args) {
//		List<String> list1= new ArrayList<>();
//		list1.add("A");list1.add("C");list1.add("E");list1.add("B");list1.add("F");
//		List<String> list2= new ArrayList<>();
//		list2.add("A");list2.add("C");list2.add("G");
//		List<String> list3= new ArrayList<>();
//		list3.add("E");
//		List<String> list4 = new ArrayList<>();
//		list4.add("A");list4.add("C");list4.add("E");list4.add("G");list4.add("D");
//		List<String> list5= new ArrayList<>();
//		list5.add("A");list5.add("C");list5.add("E");list5.add("G");
//		List<String> list6= new ArrayList<>();
//		list6.add("E");
//		List<String> list7= new ArrayList<>();
//		list7.add("A");list7.add("C");list7.add("E");list7.add("B");list7.add("F");
//		List<String> list8 = new ArrayList<>();
//		list8.add("A");list8.add("C");list8.add("D");
//		List<String> list9 = new ArrayList<>();
//		list9.add("A");list9.add("C");list9.add("E");list9.add("G");
//		List<String> list10 = new ArrayList<>();
//		list10.add("A");list10.add("C");list10.add("E");list10.add("G");
//		
//		List<List<String>> list = new ArrayList<>();
//		list.add(list1);list.add(list2);list.add(list3);list.add(list4);
//		list.add(list5);list.add(list6);list.add(list7);list.add(list8);
//		list.add(list9);list.add(list10);
//		
//		init(list);
//		
//		List<Tid> item = new ArrayList<>();
//		List<Node> l = new ArrayList<>();
//		Tree.retrieveNode(ini, "F", l);
//		Node fp = fpTree(l,item);
//		
//		Map<String,Integer> map = new HashMap<>();
//		freqSet(item,fp,"F",map);
//		System.out.println(map);
//		System.out.println(Association.support(map,list));
//	}
}
