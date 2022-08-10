package com.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FindNewID {

	public static void main(String[] args) {
		List<Integer> l = Arrays.asList(2, 3, 6, 16, 7, 8, 9, 5, 4, 10, 11, 12, 13, 15, 1, 14);
		ArrayList<Integer> al = new ArrayList<Integer>(l);
		System.out.println(al);
		System.out.println(findEmptyCell(al));

	}
	public static int findEmptyCell(ArrayList<Integer> al) {
		ArrayList<Integer> n = new ArrayList<Integer>();
		Collections.sort(al);;
		int result = 0;
		if (al.get(0) == 2) {
			result = 1;
		} else if (al.get(1) == 3) {
			result = 2;
		} else {
			for (int i = 0; i < al.size() - 1; i++) {
				if (al.get(i + 1) - al.get(i) == 1) {
					n.add(al.get(i));
					System.out.println(n);
				} else {
					n.add(al.get(i));
					break;
				}
				result = n.get(i) + 2; 
			}
		}
		return result;
	}
}
