package com.nisum.zipcode.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Ranges<T extends Comparable<T>> implements Comparator<List<T>> {

	private ArrayList<Integer> range;
	
	public Ranges() {
		this.range = new ArrayList<Integer>();
	}

	public ArrayList<Integer> getRange() {
		return range;
	}

	public void addRange(int min, int max) {
		this.range.add(max);
		this.range.add(min);
	}

	@Override
	public int compare(List<T> o1, List<T> o2) {
		for (int index = 0; index < Math.min(o1.size(), o2.size()); index++) {
			int c = o1.get(index).compareTo(o2.get(index));
			if (c != 0) {
				return c;
			}
		}
		return Integer.compare(o1.size(), o2.size());
	}

	@Override
	public String toString() {
		return "Ranges [range=" + range + "]";
	}
}
