/**
 * Exam 3
 * https://ucsd-cse11-f21.github.io/assignments/exam3.html
 *
 * @author Natalie Young
 * @since 2021-12-01
 */

import java.util.*;
import tester.*;

class ComparatorLookupTable<K, V> implements Comparator<K>
{
	List<K> keys;
	List<V> values;
	Comparator<K> comp;

	ComparatorLookupTable(List<K> keys, List<V> values, Comparator<K> comp)
	{
		this.keys = keys;
		this.values = values;
		this.comp = comp;
	}

	public int compare(K key1, K key2)
	{
		return comp.compare(key1, key2);
	}

	/**
	 * Returns true if key was in initial list of keys or has been
	 * added, false otherwise.
	 *
	 * @param key
	 */
	boolean contains(K key)
	{
		for (int i = 0; i < this.keys.size(); i++)
		{
			if ((compare(key, this.keys.get(i))) == 0)
			{
				return true;
			}
		}

		return false;
	}

	/**
	 * Adds given pair of key and value to table; if key already in
	 * table, throw IllegalArgumentException.
	 *
	 * @param key
	 * @param value
	 */
	void add(K key, V value)
	{
		if (this.keys.contains(key))
		{
			throw new IllegalArgumentException();
		}
		else
		{
			this.keys.add(key);
			this.values.add(value);
		}
	}

	/**
	 * Returns value corresponding to given key or throws
	 * NoSuchElementException if not defined.
	 * 
	 * @param key
	 * @return value
	 */
	V find(K key)
	{
		int index;
		V value;

		if (this.keys.contains(key))
		{
			index = this.keys.indexOf(key);

			value = this.values.get(index);

			return value;
		}
		else
		{
			throw new NoSuchElementException();
		}
	}

	/**
	 * Changes the value stored in key to value or throws
	 * NoSuchElementException if not defined.
	 *
	 * @param key
	 * @param value
	 */
	void update(K key, V value)
	{
		this.find(key);

		int index = this.keys.indexOf(key);
		this.values.set(index, value);
	}
}

class StringComparator implements Comparator<String>
{
	public int compare(String s1, String s2)
	{
		return s1.compareTo(s2);
	}
}

class ComparatorLookupTableExamples
{
	void testUpdate(Tester t)
	{
		List<String> strs = new ArrayList<>(Arrays.asList("a", "b", "c"));
		List<Integer> nums = new ArrayList<>(Arrays.asList(1, 2, 3));
		ComparatorLookupTable<String, Integer> ctl = new ComparatorLookupTable<>(strs, nums, new StringComparator());
		
		t.checkExpect(ctl.contains("a"), true);
		ctl.update("a", 9);
		t.checkExpect(ctl.find("a"), 9);
		ctl.add("z", 10);
		t.checkExpect(ctl.keys, Arrays.asList("a", "b", "c", "z"));
		t.checkExpect(ctl.values, Arrays.asList(9, 2, 3, 10));
		
		t.checkException(new IllegalArgumentException(), ctl, "add", "z", 5);
		t.checkException(new NoSuchElementException(), ctl, "find", "y");
	}
}
