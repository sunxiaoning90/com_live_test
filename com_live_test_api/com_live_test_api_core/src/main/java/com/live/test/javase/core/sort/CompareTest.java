package com.live.test.javase.core.sort;

import org.springframework.core.PriorityOrdered;
import org.springframework.core.OrderComparator.OrderSourceProvider;

/**
 * 摘自 Spring 
 * @author live
 */
public class CompareTest {

	private int doCompare(Object o1, Object o2, Class sourceProvider) {
		boolean p1 = (o1 instanceof PriorityOrdered);
		boolean p2 = (o2 instanceof PriorityOrdered);
		if (p1 && !p2) {
			return -1;
		}
		else if (p2 && !p1) {
			return 1;
		}

		// Direct evaluation instead of Integer.compareTo to avoid unnecessary object creation.
		int i1 = getOrder(o1, sourceProvider);
		int i2 = getOrder(o2, sourceProvider);
		return (i1 < i2) ? -1 : (i1 > i2) ? 1 : 0;
	}
	
}
