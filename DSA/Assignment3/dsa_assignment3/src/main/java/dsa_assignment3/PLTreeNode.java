package dsa_assignment3;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * A node in a binary tree representing a Propositional Logic expression.
 * <p>
 * Each node has a type (AND node, OR node, NOT node etc.) and can have zero one
 * or two children as required by the node type (AND has two children, NOT has
 * one, TRUE, FALSE and variables have none
 * </p>
 * <p>
 * This class is mutable, and some of the operations are intended to modify the
 * tree internally. There are a few cases when copies need to be made of whole
 * sub-tree nodes in such a way that these copied trees do not share any nodes
 * with their originals. To do this the class implements a deep copying copy
 * constructor <code>PLTreeNode(PLTreeNode node)</code>
 * </p>
 * 
 */
public final class PLTreeNode implements PLTreeNodeInterface
{
	private static final Logger logger = Logger.getLogger(PLTreeNode.class);

	NodeType                    type;
	PLTreeNode                  child1;
	PLTreeNode                  child2;

	/**
	 * For marking purposes
	 * 
	 * @return Your student id
	 */
	public static String getStudentID()
	{
		//change this return value to return your student id number
		return "2018963";
	}

	/**
	 * For marking purposes
	 * 
	 * @return Your name
	 */
	public static String getStudentName()
	{
		//change this return value to return your name
		return "XUEMENG LI";
	}

	/**
	 * The default constructor should never be called and has been made private
	 */
	private PLTreeNode()
	{
		throw new RuntimeException("Error: default PLTreeNode constuctor called");
	}

	/**
	 * The usual constructor used internally in this class. No checks made on
	 * validity of parameters as this has been made private, so can only be
	 * called within this class.
	 * 
	 * @param type
	 *            The <code>NodeType</code> represented by this node
	 * @param child1
	 *            The first child, if there is one, or null if there isn't
	 * @param child2
	 *            The second child, if there is one, or null if there isn't
	 */
	private PLTreeNode(NodeType type, PLTreeNode child1, PLTreeNode child2)
	{
		// Don't need to do lots of tests because only this class can create nodes directly,
		// any construction required by another class has to go through reversePolishBuilder,
		// which does all the checks
		this.type = type;
		this.child1 = child1;
		this.child2 = child2;
	}

	/**
	 * A copy constructor to take a (recursive) deep copy of the sub-tree based
	 * on this node. Guarantees that no sub-node is shared with the original
	 * other
	 * 
	 * @param node
	 *            The node that should be deep copied
	 */
	private PLTreeNode(PLTreeNode node)
	{
		if (node == null)
			throw new RuntimeException("Error: tried to call the deep copy constructor on a null PLTreeNode");
		type = node.type;
		if (node.child1 == null)
			child1 = null;
		else
			child1 = new PLTreeNode(node.child1);
		if (node.child2 == null)
			child2 = null;
		else
			child2 = new PLTreeNode(node.child2);
	}


	/**
	 * Takes a list of <code>NodeType</code> values describing a valid
	 * propositional logic expression in reverse polish notation and constructs
	 * the corresponding expression tree. c.f. <a href=
	 * "https://en.wikipedia.org/wiki/Reverse_Polish_notation">https://en.wikipedia.org/wiki/Reverse_Polish_notation</a>
	 * <p>
	 * Thus an input containing
	 * </p>
	 * <pre>
	 * {NodeType.P, NodeType.Q, NodeType.NOT, NodeType.AND.
	 * </pre>
	 * 
	 * corresponds to
	 * 
	 * <pre>
	 * P鈭琎
	 * </pre>
	 * 
	 * Leaving out the <code>NodeType</code> enum class specifier, we get that
	 * 
	 * <pre>
	 * { R, P, OR, TRUE, Q, NOT, AND, IMPLIES }
	 * </pre>
	 * 
	 * represents
	 * 
	 * <pre>
	 * ((R鈭≒)鈫�(鈯も埀卢Q))
	 * </pre>
	 * 
	 * @param typeList
	 *            An <code>NodeType</code> array in reverse polish order
	 * @return the <code>PLTreeNode</code> of the root of the tree representing
	 *         the expression constructed for the reverse polish order
	 *         <code>NodeType</code> array
	 */
	public static PLTreeNodeInterface reversePolishBuilder(NodeType[] typeList)
	{
		if (typeList == null || typeList.length == 0)
		{
			logger.error("Trying to create an empty PLTree");
			return null;
		}

		Deque<PLTreeNode> nodeStack = new LinkedList<>();

		for (NodeType type : typeList)
		{
			int arity = type.getArity();

			if (nodeStack.size() < arity)
			{
				logger.error(String.format(
						"Error: Malformed reverse polish type list: \"%s\" has arity %d, but is being applied to only %d arguments", //
						type, arity, nodeStack.size()));
				return null;
			}
			if (arity == 0)
				nodeStack.addFirst(new PLTreeNode(type, null, null));
			else if (arity == 1)
			{
				PLTreeNode node1 = nodeStack.removeFirst();
				nodeStack.addFirst(new PLTreeNode(type, node1, null));
			}
			else
			{
				PLTreeNode node2 = nodeStack.removeFirst();
				PLTreeNode node1 = nodeStack.removeFirst();
				nodeStack.addFirst(new PLTreeNode(type, node1, node2));
			}
		}
		if (nodeStack.size() > 1)
		{
			logger.error("Error: Incomplete term: multiple subterms not combined by top level symbol");
			return null;
		}

		return nodeStack.removeFirst();
	}

	/* (non-Javadoc)
	 * @see dsa_assignment3.PLTreeNodeInterface#getReversePolish()
	 */
	@Override
	public NodeType[] getReversePolish()
	{
		Deque<NodeType> nodeQueue = new LinkedList<>();

		getReversePolish(nodeQueue);

		return nodeQueue.toArray(new NodeType[0]);

	}

	/**
	 * A helper method for <code>getReversePolish()</code> used to accumulate
	 * the elements of the reverse polish notation description of the current
	 * tree
	 * 
	 * @param nodeQueue
	 *            A queue of <code>NodeType</code> objects used to accumulate
	 *            the values of the reverse polish notation description of the
	 *            current tree
	 */
	private void getReversePolish(Deque<NodeType> nodeQueue)
	{
		if (child1 != null)
			child1.getReversePolish(nodeQueue);
		if (child2 != null)
			child2.getReversePolish(nodeQueue);
		nodeQueue.addLast(type);
	}

	/* (non-Javadoc)
	 * @see dsa_assignment3.PLTreeNodeInterface#toString()
	 */
	@Override
	public String toString()
	{
		return toStringPrefix();
	}

	/* (non-Javadoc)
	 * @see dsa_assignment3.PLTreeNodeInterface#toStringPrefix()
	 */
	@Override
	public String toStringPrefix()
	{
		String pref = type.getPrefixName();
		if (this.type.getArity() == 2) {
			pref = pref + "(" + this.child1.toStringPrefix() + "," + this.child2.toStringPrefix() + ")";
		} 
		if (this.type.getArity() == 1) {
			pref = pref + "(" + this.child1.toStringPrefix() + ")";
		}
		return pref;
	}

	/* (non-Javadoc)
	 * @see dsa_assignment3.PLTreeNodeInterface#toStringInfix()
	 */
	@Override
	public String toStringInfix()
	{
		String inf = "";
		
		if (this.type.getArity() == 1){
			inf +=this.type.getInfixName();
			inf +=this.child1.toStringInfix();
		} 
		else if (this.type.getArity() == 2) {
			inf +="(" ;
			inf +=this.child1.toStringInfix()+this.type.getInfixName() + this.child2.toStringInfix();
			inf += ")";		
		}
		else {
					inf += this.type.getInfixName();
		}
		return inf;			 
	}

	/* (non-Javadoc)
	 * @see dsa_assignment3.PLTreeNodeInterface#applyVarBindings(java.util.Map)
	 */
	@Override
	public void applyVarBindings(Map<NodeType, Boolean> bindings)
	{

		if (this.type.getArity() > 0) {
			if (this.child1 != null) {
				child1.applyVarBindings(bindings);
			}
			if (this.child2 != null) {
				child2.applyVarBindings(bindings);
			}
			} 
		if (this.type.getArity() <= 0) {
			if (this.type.isVar() && bindings.containsKey(this.type)) {
			if (bindings.get(this.type).booleanValue()) {
					this.type = NodeType.TRUE;
				} else {
					this.type = NodeType.FALSE;
				}
			}
		}
		return;
	}

	/* (non-Javadoc)
	 * @see dsa_assignment3.PLTreeNodeInterface#evaluateConstantSubtrees()
	 */
	@Override
	public Boolean evaluateConstantSubtrees()
	{
		if (this.child1 == null) {
			if(this.type.getPrefixName().equals("true")) {
				return true;
			}else
				return false;
		}
		
		if (this.child1.child1 != null) {
			
			this.child1.evaluateConstantSubtrees();
		}
		
		switch (this.type.getPrefixName()) {
		case "not": {
			
			if (this.child1.type.getPrefixName().equals("true")) {
				this.type = NodeType.FALSE;
				this.child1 = null;
				return false;
			} else if (this.child1.type.getPrefixName().equals("false")) {
				this.type = NodeType.TRUE;
				this.child1 = null;
				return true;
			} else {
				return false; 
			}

		}
		case "or": {
			
			if (this.child1.type.getPrefixName().equals("true")) {
				this.type = NodeType.TRUE;
				this.child1 = this.child2 = null;
				return true;
			} else if(this.child1.type.getPrefixName().equals("false")){

				if (this.child2.evaluateConstantSubtrees()) {
					this.type = NodeType.TRUE;
					this.child1 = this.child2 = null;
					return true;
					
				} else {
					if (this.child2.type.getPrefixName().equals("false")) {
						this.type = NodeType.FALSE;
						this.child1 = this.child2 = null;
						return false;
						
					}else {
						this.type = this.child2.type;
						this.child1 = this.child2.child1;
						this.child2 = this.child2.child2;
					}
				}
			}else {
				return false;
			}
		}
		case "and": {
			if (this.child1.type.getPrefixName().equals("false")) {
				this.type = NodeType.FALSE;
				this.child1 = this.child2 = null;
				return false;
				
			} else if(this.child1.type.getPrefixName().equals("true")){

				if (this.child2.evaluateConstantSubtrees()) {
					this.type = NodeType.TRUE;
					this.child1 = this.child2 = null;
					return true;
					
				} else {
					if (this.child2.type.getPrefixName().equals("false")) {
						this.type = NodeType.FALSE;
						this.child1 = this.child2 = null;
						return false;
						
					}else {
						this.type = this.child2.type;
						this.child1 = this.child2.child1;
						this.child2 = this.child2.child2;
					}
					return false; 
				}
			}else {
				return false;
			}

		}
		case "implies": {
			
			if (this.child2.evaluateConstantSubtrees()) {
				this.type = NodeType.TRUE;
				this.child1 = this.child2 = null;
				return true;
				
			} else {
				if (this.child2.type.getPrefixName().equals("false")) {

					this.child1 = this.child2 = null;
					if (!this.child1.evaluateConstantSubtrees()) {
						this.type = NodeType.TRUE;
					} else {
						this.type = NodeType.FALSE;
					}
					return !this.child1.evaluateConstantSubtrees();
				} else {
					
					return false;
				}
			}
		}
		default:
			return false;
		}

		
		}


	/* (non-Javadoc)
	 * @see dsa_assignment3.PLTreeNodeInterface#reduceToCNF()
	 */
	@Override
	public void reduceToCNF()
	{
		replaceImplies();
		pushNotDown();
		pushOrBelowAnd();
		makeAndOrRightDeep();
		return;
	}

	/* (non-Javadoc)
	 * @see dsa_assignment3.PLTreeNodeInterface#replaceImplies()
	 */
	@Override
	public void replaceImplies()
	{
		if (this.type.getPrefixName().equals("implies")) {
			
			this.type = NodeType.OR;
			this.child1 =  new PLTreeNode(NodeType.NOT, this.child1, null);
		}

		if (this.child1 != null) {
			child1.replaceImplies();
		}

		if (this.child2 != null) {
			child2.replaceImplies();
		}
		return;
	}


	/* (non-Javadoc)
	 * @see dsa_assignment3.PLTreeNodeInterface#pushNotDown()
	 */
	@Override
	public void pushNotDown()
	{
		if (this.type.getPrefixName().equals("not")) {
			
			if (this.child1.type.getPrefixName().equals("not")) {

				PLTreeNode L = this.child1.child1.child1;
				PLTreeNode R = this.child1.child1.child2;
				this.type = this.child1.child1.type;
				this.child1 =L;
				this.child2 = R;

			} else if (this.child1.type.getPrefixName().equals("or")) {
		
				PLTreeNode x1 = new PLTreeNode(NodeType.NOT, this.child1.child1, null);
				PLTreeNode x2 = new PLTreeNode(NodeType.NOT, this.child1.child2, null);
				this.type = NodeType.AND;
				this.child1 =x1;
				this.child2 =x2;
				
			} else if (this.child1.type.getPrefixName().equals("and")) {
				
				this.type = NodeType.OR;
				this.child1 = new PLTreeNode(NodeType.NOT, this.child1.child1, null);
				this.child2 =new PLTreeNode(NodeType.NOT, this.child1.child2, null);
			}
		}

		if (this.child1 != null) {
			child1.pushNotDown();			
		}
		if (this.child2 != null) {
			child2.pushNotDown();			
		}
	}


	/* (non-Javadoc)
	 * @see dsa_assignment3.PLTreeNodeInterface#pushOrBelowAnd()
	 */
	@Override
	public void pushOrBelowAnd()
	{

		if (this.type.getPrefixName().equals("or")) {
			
			if(this.child1.type.getPrefixName().equals("and")) {
				PLTreeNode sub1 = new PLTreeNode(NodeType.OR, this.child1.child1, this.child2);
				PLTreeNode sub2 = new PLTreeNode(NodeType.OR, this.child1.child2, this.child2);
				
				this.type = NodeType.AND;
				this.child1 = sub1;
				this.child2 = sub2;
				this.child1.type = NodeType.OR;
			}
			else if(this.child2.type.getPrefixName().equals("and")) {
				PLTreeNode sub3 = new PLTreeNode(NodeType.OR, this.child1, this.child2.child1);
				PLTreeNode sub4 = new PLTreeNode(NodeType.OR, this.child1, this.child2.child2);
				
				this.type = NodeType.AND;
				this.child1 = sub3;
				this.child2 = sub4;
				this.child2.type = NodeType.OR;
			}
		}
		if (this.child1 != null) {
			this.child1.pushOrBelowAnd();
		}
		if (this.child2 != null) {
			this.child2.pushOrBelowAnd();
		}
		return;
	}
	

	/* (non-Javadoc)
	 * @see dsa_assignment3.PLTreeNodeInterface#makeAndOrRightDeep()
	 */
	@Override
	public void makeAndOrRightDeep()
	{
		PLTreeNode Left = this.child1;
		PLTreeNode Right = this.child2;
		
		if (this.type.getPrefixName().equals("and")) {
			

			if (this.child1.type.getPrefixName().equals("and")) {
				
				this.child1 = Left.child1;
				this.child2 = Left;
				Left.child1 = Left.child2;
				Left.child2 = Right;
			}
		}

		if (this.type.getPrefixName().equals("or")) {
			
			if (this.child1.type.getPrefixName().equals("or")) {				
				this.child1 = Left.child1;
				this.child2 = Left;
				Left.child1 = Left.child2;
				Left.child2 = Right;
			}
		}


		if (this.child1 != null)
			this.child1.makeAndOrRightDeep();
		if (this.child2 != null)
			this.child2.makeAndOrRightDeep();
		return;

	}

}
