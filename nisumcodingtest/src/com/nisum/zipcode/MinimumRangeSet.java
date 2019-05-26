package com.nisum.zipcode;

import java.util.ArrayList;
import java.util.List;

import com.nisum.zipcode.model.Ranges;
import com.nisum.zipcode.processor.RangeProcessor;

public class MinimumRangeSet {

	public static void main(String[] args) {
		Ranges ranges1 = new Ranges();
		ranges1.addRange(94133, 94133);
		Ranges ranges2 = new Ranges();
		ranges2.addRange(94299, 94200);
		Ranges ranges3 = new Ranges();
		ranges3.addRange(94399, 94226);

		List<ArrayList<Integer>> rangesList = new ArrayList<ArrayList<Integer>>();
		rangesList.add(ranges2.getRange());
		rangesList.add(ranges1.getRange());
		rangesList.add(ranges3.getRange());
		// Test inputs
		for(ArrayList<Integer> rangeList : rangesList) {
			System.out.println("restricted zipcode Ranges : " + rangeList);
		}
		
		// Calling processor to reduce ranges.
		for (Ranges<Integer> ranges : RangeProcessor.getReducedRanges(rangesList)) {
			System.out.println("Reduced Ranges : " + ranges.getRange());
		}
		

	}

}
