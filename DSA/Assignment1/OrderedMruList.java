package dsa_assignment1;

public class OrderedMruList<E extends Comparable<E>> implements OrderedMruListInterface<E>
{
	/**
	 * The head field is an <code>MLNode</code> object, where the
	 * <code>next1</code> and <code>prev1</code> pointers are for the circular Ordered list,
	 * and the <code>next2</code> and <code>prev2</code> are for the circular MRU list.
	 * It always contains the value <code>Null</code>.
	 * These lists are considered empty if there is no
	 * <b>other</b> <code>MLNode</code> object on the lists other
	 * than the <code>head</code> node itself
	 * 
	 */
	MLNodeInterface<E>	head	= new MLNode<E>(null);

	public OrderedMruList()
	{
	}
	
	public boolean isEmptyOrdered()
	{
		return head.getNext1() == head;
	}

	public boolean isEmptyMru()
	{
		return head.getNext2() == head;
	}

	public OrderedMruListInterface<E> touch(MLNodeInterface<E> target)
	{
		if (head.getNext2()!= target)
			target.addAfter2(head);
		return this;
	}
	
	public MLNodeInterface<E> getFirstMru()
	{
		if(isEmptyMru())
			return null;
		return head.getNext2();
	}
	
	public MLNodeInterface<E> getFirstOrdered()
	{
		if(isEmptyOrdered())
			return null;
		return head.getNext1();
	}
	
	public MLNodeInterface<E> getNextOrdered(MLNodeInterface<E> current)
	{
		MLNodeInterface<E> next = current.getNext1();
		if(next ==head)
			return null;
		return next;
	}

	public MLNodeInterface<E> getNextMru(MLNodeInterface<E> current)
	{
		MLNodeInterface<E> next = current.getNext2();
		if(next == head)
			return null;
		return next;
	}

	public OrderedMruListInterface<E> remove(MLNodeInterface<E> target)
	{
		target.remove1();
		target.remove2();
		return this;
	}
	
	public OrderedMruListInterface<E> add(E element)
	{
		MLNodeInterface<E> newNode = new MLNode<E>(element);
		
		MLNodeInterface<E> nextOrdered = head.getNext1();
		while(nextOrdered != head && element.compareTo(nextOrdered.getElement())>0)
				nextOrdered = nextOrdered.getNext1();
			newNode.addBefore1(nextOrdered);
			newNode.addAfter2(head);
			return this;
	}
}
