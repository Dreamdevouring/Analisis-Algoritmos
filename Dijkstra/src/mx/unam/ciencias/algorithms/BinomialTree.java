package mx.unam.ciencias.algorithms;

import mx.unam.ciencias.edd.ComparableIndexable;

/**
 * Class for Binomial Trees with Min Heap Property.
 * A Binomial Tree is:
 * - A Binomial Tree of rank 0, that is, a single node.
 * - A Binomial Tree of rank k has  a root whose children are roots of binomial
 *   trees of ranks 0,1,...,k-1 in that order.
 * A Binomial Tree of rank k has 2^k nodes and height k.
 * A Binomial Tree of rank k can be constructed from two tress of rank k-1 using
 * merge method.
 * Each Tree Node has the Min Heap Property, that is, is lesser or equal to all
 * of its children.
 * Each Tree Node knows its father, first and last child, its brothers, its
 * rank and element (the root's element).
 * @author Gilberto Isaac López García.
 * @param <T> The type parameter. Must implement {@link ComparableIndexable}
 *            interface.
 */
public class BinomialTree<T extends ComparableIndexable<T>> {
	
	/** Tree's rank. */
	public int rank;
	/** Root's element. */
	public T elem;
	/** Father. */
	public BinomialTree<T> father;
	/** Left brother. */
	public BinomialTree<T> l_brother;
	/** Right brother. */
	public BinomialTree<T> r_brother;
	/** First child. */
	public BinomialTree<T> f_child;
	/** Last child. */
	public BinomialTree<T> l_child;
	
	/**
	 * Constructs a new Binomial Tree B0 with an element at its root.
	 * @param elem Root's element.
	 */
	public BinomialTree (T elem) {
		this.elem = elem;
		rank = 0;
	}
	
	/**
	 * Melds two Binomial Trees of the same rank, t1 and t2, with root the
	 * minimum between t1's root and t2's root. The resulting tree has rank
	 * t1.rank + 1.
	 * @param <T> Type parameter. Must implement {@link ComparableIndexable}
	 *            interface.
	 * @param t1 The first tree.
	 * @param t2 The second tree.
	 * @return The new tree resulting from melding t1 with t2. null if either
	 *         t1 or t2 are null or if they have different ranks.
	 */
	public static <T extends ComparableIndexable<T>> BinomialTree<T>
		meld (BinomialTree<T> t1, BinomialTree<T> t2) {
		if (t1 == null || t2 == null)
			return null;
		if (t1.rank != t2.rank)
			return null;
		//Minimum and maximum trees.
		BinomialTree<T> min;
		BinomialTree<T> max;
		if (t1.elem.compareTo(t2.elem) <= 0) {
			min = t1;
			max = t2;
		} else {
			min = t2;
			max = t1;
		}
		if (t1.rank == 0) {
			max.father = min;
			min.l_child = min.f_child = max;
			max.l_brother = max.r_brother = null;
			min.rank++;
			return min;
		}
		max.father = min;
		max.l_brother = min.l_child;
		min.l_child.r_brother = max;
		min.l_child = max;
		min.f_child.l_brother = min.l_child.r_brother = null;
		min.rank++;
		return min;
	}

}
