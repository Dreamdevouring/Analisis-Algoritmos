package mx.unam.ciencias.algorithms;

import mx.unam.ciencias.edd.ComparableIndexable;

/**
 * Binomial Min Heap. A Binomial Heap is a forest of Binomial Trees of unique
 * rank, that is, no two trees of the same rank can exist at the same time, this
 * can be achieved by melding trees. See {@link BinomialTree#meld} for more
 * information.
 * Each tree satisfies the heap property.
 * Operations:
 * - Find minimum: returns the minimum value. O(1) with pointer to the minimum.
 * - Delete minimum: removes and returns the minimum element. O(log n).
 * - Insert: inserts a new element. O(log n), relaxed version takes O(1).
 * - Decrease key: decreases the key for the given element and reorganizes the
 *                 structure. O(log n).
 * - Is empty: tells whether the heap is empty or not. O(1).
 * @author Gilberto Isaac López García.
 * @param <T> Type Argument, must implement {@link ComparableIndexable}
 *            interface.
 */
public class BinomialHeap<T extends ComparableIndexable<T>> {
	
	//Set of Elements.
	private BinomialTree<T>[] set;
	//Accumulator for reorganize().
	private BinomialTree<T>[] ac;
	//Minimum element.
	private BinomialTree<T> min;
	//First tree.
	private BinomialTree<T> fst;
	//Last Tree.
	private BinomialTree<T> lst;
	//Next position in set.
	private int next;
	//Size.
	private int size;
	
	/*
	 * Dirty trick for creating generic Arrays. "Generic" classes don't know
	 * the type argument they were created with at run time (bullsh*t), so we
	 * create an array of the raw type BinomialTree and use casting.
	 * SuppressWarnings for "unchecked" type conversion in order for this thing
	 * to work.
	 * See Type Erasure: http://docs.oracle.com/javase/tutorial/java/generics/erasure.html
	 * and Raw Types: http://docs.oracle.com/javase/tutorial/java/generics/rawTypes.html
	 * for more information.
	 */
	@SuppressWarnings("unchecked") private void initialize (int size, int fs) {
		set = (BinomialTree<T>[]) new BinomialTree[size];
		ac = (BinomialTree<T>[]) new BinomialTree[fs];
	}

	/**
	 * Constructs a new empty Binomial Heap of the given size.
	 * @param size The Heap size.
	 */
	public BinomialHeap (int size) {
		next = this.size = 0;
		double fs = Math.floor(Math.log((double) size) / Math.log(2.0)) + 1;
		initialize(size, (int) fs);
	}
	
	/**
	 * Returns the minimum element in the heap.
	 * @return The minimum element.
	 */
	public T findMinimum () {
		return min.elem;
	}
	
	/**
	 * Removes and returns the minimum element of the heap.
	 * @return The minimum element.
	 */
	public T deleteMinimum () {
		T minimum = min.elem;
		BinomialTree<T> s = min;
		set[minimum.getIndice()] = null;
		if (s.rank == 0) {
			if (s == fst) {
				fst = s.r_brother;
				if (fst != null)
					fst.l_brother = null;
			} else {
				s.l_brother.r_brother = s.r_brother;
				if (s.r_brother != null)
					s.r_brother.l_brother = s.l_brother;
			}
			if (s == lst) {
				fst = s.l_brother;
				if (lst != null)
					lst.r_brother = null;
			} else {
				s.r_brother.l_brother = s.l_brother;
				if (s.l_brother != null)
					s.l_brother.r_brother = s.r_brother;
			}
		} else {
			BinomialTree<T> tmp = s.f_child;
			while (tmp != null) {
				tmp.father = null;
				tmp = tmp.r_brother;
			}
			if (s == fst) {
				fst = s.f_child;
				s.l_child.r_brother = s.r_brother;
				if (s.r_brother != null)
					s.r_brother.l_brother = s.l_child;
			} else {
				s.f_child.l_brother = s.l_brother;
				s.l_brother.r_brother = s.f_child;			
			}
			if (s == lst) {
				lst = s.l_child;
				s.f_child.l_brother = s.l_brother;
				if (s.l_brother != null)
					s.l_brother.r_brother = s.f_child;
			} else {
				s.l_child.r_brother = s.r_brother;
				s.r_brother.l_brother = s.l_child;
			}
		}
		reorganize();
		size--;
		return minimum;
	}
	
	/**
	 * Inserts a new element in the heap. This is a relaxed version, creates a
	 * new zero rank tree without reorganizing the structure. Time O(1).
	 * @param elem The new element.
	 */
	public void insert (T elem) {
		elem.setIndice(next);
		BinomialTree<T> a = new BinomialTree<>(elem);
		set[next] = a;
		if (next == 0)
			min = fst = lst = a;
		else {
			lst.r_brother = a;
			a.l_brother = lst;
			lst = a;
			if (elem.compareTo(min.elem) < 0)
				min = a;
		}
		next++;
		size++;
	}
	
	/**
	 * Tells whether the Heap is Empty or not.
	 * @return true if the Heap is empty, false otherwise.
	 */
	public boolean isEmpty () {
		return size == 0;
	}
	
	/**
	 * Decreases the key of the given element and reorganizes the structure. The
	 * new key must be lesser or equal to the previous key.
	 * @param element The element whose key will be decreased.
	 * @param newKey The new key.
	 */
	public void decreaseKey (T element, int newKey) {
		BinomialTree<T> t = set[element.getIndice()];
		if (t == null)
			return;
		element.setLlave(newKey);
		BinomialTree<T> tmp = t.father;
		while (tmp != null) {
			if (t.elem.compareTo(tmp.elem) >= 0)
				break;
			swap(t, tmp);
			t = tmp;
			tmp = tmp.father;
		}
		if (tmp == null && t.elem.compareTo(min.elem) < 0)
			min = t;
	}
	
	/*
	 * Swap t1 element with t2 element. Excahnges the elements and indexes.
	 */
	private void swap (BinomialTree<T> t1, BinomialTree<T> t2) {
		int tmpI = t1.elem.getIndice();
		t1.elem.setIndice(t2.elem.getIndice());
		t2.elem.setIndice(tmpI);
		T tmpElem = t1.elem;
		t1.elem = t2.elem;
		t2.elem = tmpElem;
	}
	
	/*
	 * Reorganizes the structure. Needs an accumulator. Once the minimum element
	 * is removed, the method melds trees of the same rank unitl no two trees
	 * of the same rank exist.
	 */
	private void reorganize () {
		for (int i = 0; i < ac.length; i++)
			ac[i] = null;
		BinomialTree<T> tmp = fst;
		while (tmp != null) {
			int rank = tmp.rank;
			if (ac[rank] == null) {
				ac[rank] = tmp;
				tmp = tmp.r_brother;
			} else {
				BinomialTree<T> r = tmp.r_brother;
				BinomialTree<T> m = BinomialTree.meld(tmp, ac[rank]);
				m.r_brother = r;
				tmp = m;
				ac[rank] = null;
			}
		}
		fst = null;
		lst = null;
		min = null;
		BinomialTree<T> l;
		int i = 0;
		while (i < ac.length && ac[i] == null)
			i++;
		if (i == ac.length)
			return;
		fst = ac[i];
		fst.l_brother = null;
		lst = l = fst;
		while (i < ac.length) {
			int j = i+1;
			while (j < ac.length && ac[j] == null)
				j++;
			if (j < ac.length) {
				lst = ac[j];
				l.r_brother = lst;
				lst.l_brother = l;
				l = lst;
			}
			i = j;
		}
		lst.r_brother = null;
		tmp = fst;
		min = tmp;
		while (tmp != null) {
			if (tmp.elem.compareTo(min.elem) < 0)
				min = tmp;
			tmp = tmp.r_brother;
		}
	}

}
