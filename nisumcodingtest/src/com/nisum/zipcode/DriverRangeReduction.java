package com.nisum.zipcode;

import java.util.ArrayList;
import java.util.List;

import com.nisum.zipcode.model.Ranges;
import com.nisum.zipcode.processor.RangeProcessor;

public class DriverRangeReduction {

	public static void main(String[] args) {
		Ranges<Integer> ranges1 = new Ranges();
		ranges1.addRange(94133, 94133);
		Ranges<Integer> ranges2 = new Ranges();
		ranges2.addRange(94200, 94299);
		Ranges<Integer> ranges3 = new Ranges();
		ranges3.addRange(94226, 94399);
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
