package com.xworkz.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TestRunner {
	public static void main(String[] args) {
		
		
	List<Integer> list=new ArrayList<Integer>();
	list.add(100);
	list.add(200);
	list.add(300);
	list.add(500);
	list.add(400);
	list.add(500);
	
	//System.out.println(list);
//System.out.println(list.contains(100));
	//System.out.println(list.remove(1));
	//System.out.println(list);
	//System.out.println(list.removeAll(list));
	//System.out.println(list);
	//System.out.println(list.isEmpty());
	
	
	//System.out.println(list.get(1));
	//list.set(0, 900);
	//System.out.println(list);
//	list.add(1, 1100);
//	System.out.println(list);
	/*
	 * System.out.println(list.indexOf(500));
	 * System.out.println(list.lastIndexOf(500));
	 */
	
	Stack<String> stack=new Stack<String>();
	stack.push("HellO");
	stack.push("Hi");
	stack.push("Bye");
	System.out.println(stack);
	System.out.println(stack.peek());
	System.out.println(stack);
	System.out.println(stack.pop());
	System.out.println(stack);
	System.out.println(stack.search("HellO"));
	
	
	
	
		
	}

}
