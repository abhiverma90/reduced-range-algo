package com.nisum.zipcode.range_reduction.processor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

import com.nisum.zipcode.range_reduction.model.Ranges;

public class RangeProcessor {

	public static List<Ranges<Integer>> getReducedRanges(List<ArrayList<Integer>> rangesToReduce) {
		getSortedZipcodeRanges(rangesToReduce);
		List<Ranges<Integer>> reducedRanges = extractRanges(rangesToReduce);
		return reducedRanges;
	}

	private static List<Ranges<Integer>> extractRanges(List<ArrayList<Integer>> rangesToReduce) {
		ListIterator<ArrayList<Integer>> listIterator = rangesToReduce.listIterator();
		List<Ranges<Integer>> reducedRanges = new ArrayList<Ranges<Integer>>();
		boolean[] traversedRangeList = new boolean[rangesToReduce.size()];
		while (listIterator.hasNext()) {
			List<Integer> ranges = listIterator.next();
			int nextIndex = listIterator.nextIndex();
			boolean flag = traversedRangeList[nextIndex - 1];
			if (flag) {
				continue;
			}

			if (rangesToReduce.size() != nextIndex) {
				int min = ranges.get(0);
				int max = ranges.get(1);

				int nextMin = rangesToReduce.get(nextIndex).get(0);
				int nextMax = rangesToReduce.get(nextIndex).get(1);

				if (max >= nextMin) {
					Ranges<Integer> ranges1 = new Ranges<Integer>();
					if (max >= nextMax) {
						ranges1.addRange(max, min);
						reducedRanges.add(ranges1);
					} else {
						ranges1.addRange(nextMax, min);
						reducedRanges.add(ranges1);
					}
					traversedRangeList[nextIndex] = true;

				} else {
					populateAndAddReducedRanges(reducedRanges, ranges);
				}
			} else {
				populateAndAddReducedRanges(reducedRanges, ranges);
			}
		}
		return reducedRanges;
	}

	private static void populateAndAddReducedRanges(final List<Ranges<Integer>> reducedRanges, final List<Integer> ranges) {
		Ranges<Integer> ranges2 = new Ranges<Integer>();
		ranges2.getRange().addAll(ranges);
		reducedRanges.add(ranges2);
	}

	private static void getSortedZipcodeRanges(List<ArrayList<Integer>> rangesToReduce) {
		Collections.sort(rangesToReduce, new Ranges<Integer>());
		for (List<Integer> ranges : rangesToReduce) {
			Collections.sort(ranges);
		}
	}

}
