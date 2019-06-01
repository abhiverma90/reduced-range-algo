package com.nisum.zipcode.range_reduction.processor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.nisum.zipcode.range_reduction.model.Ranges;

import junitx.framework.ListAssert;

public class RangeProcessorTest {
	
	private Ranges<Integer> ranges;
	private List<ArrayList<Integer>> rangesList;
	private List<Ranges<Integer>> expectedRangeList;
	private List<Ranges<Integer>> actualReducedRangeList;
	
	@Before
	public void setUp() {
		rangesList = new ArrayList<ArrayList<Integer>>();
		actualReducedRangeList = new ArrayList<Ranges<Integer>>();
	}

	@Test
	public void testGetReducedRangesOfOverlapedZipRanges() {
		givenOverlappingZipRanges();	
		givenActualReducedRangeOfOverlappingRanges();
		whenProcessToReducingRangesCalled();
		thenVerifyRestrictedRangesResult(expectedRangeList, actualReducedRangeList);
		
	}
	
	@Test
	public void testGetReducedRangesOfNonOverlapedRanges() {
		givenNonOverlapedZipRanges();
		givenActualNonOverlapedReducedZipRanges();
		whenProcessToReducingRangesCalled();
		thenVerifyRestrictedRangesResult(expectedRangeList, actualReducedRangeList);
	}
	
	private void givenNonOverlapedZipRanges() {
		addingRange(94133, 94233);
		addingRange(94250, 94299);
		addingRange(94333, 94399);
	}

	private void givenActualNonOverlapedReducedZipRanges() {
		actualAddingRangeList(94133, 94233);
		actualAddingRangeList(94250, 94299);
		actualAddingRangeList(94333, 94399);
		getSortedZipcodeRanges(actualReducedRangeList);
	}

	private void givenActualReducedRangeOfOverlappingRanges() {
		actualAddingRangeList(94133, 94133);
		actualAddingRangeList(94200, 94399);
		getSortedZipcodeRanges(actualReducedRangeList);
	}

	private void givenOverlappingZipRanges() {
		addingRange(94133, 94133);
		addingRange(94200, 94299);
		addingRange(94226, 94399);
	}
	
	private void addingRange(int min, int max){
		ranges = new Ranges<Integer>();
		ranges.addRange(min, max);
		rangesList.add(ranges.getRange());
	}
	
	private void whenProcessToReducingRangesCalled() {
		expectedRangeList = RangeProcessor.getReducedRanges(rangesList);
	}
	
	private void actualAddingRangeList(int min, int max) {
		ranges = new Ranges<Integer>();
		ranges.addRange(min, max);
		actualReducedRangeList.add(ranges);
	}
	
	private static void getSortedZipcodeRanges(List<Ranges<Integer>> actualSortedRangeList) {
		for(Ranges<Integer> ranges: actualSortedRangeList) {
			Collections.sort(ranges.getRange());
		}
	}
	
	private void thenVerifyRestrictedRangesResult(final List<Ranges<Integer>> expectedRangeList, final List<Ranges<Integer>> actualReducedRangeList) {
		Assert.assertEquals(expectedRangeList.size(), actualReducedRangeList.size());
		for(Ranges<Integer> expectedRanges : expectedRangeList) {
			int index = expectedRangeList.indexOf(expectedRanges);
			ListAssert.assertEquals(expectedRanges.getRange(), actualReducedRangeList.get(index).getRange());
		}
	}

}
