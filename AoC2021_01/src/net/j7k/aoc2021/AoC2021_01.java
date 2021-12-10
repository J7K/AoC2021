package net.j7k.aoc2021;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;



public class AoC2021_01 {
	
	public static int countIncrease(List<Integer> data)
	{
		assert(data.size() > 0);

		Iterator<Integer> it = data.iterator();
		int current = it.next();
		
		int incCount = 0;
		while(it.hasNext())
		{
			int last = current;
			current = it.next();
			if (current > last) incCount++;
		}
		return incCount;
	}
	
	public static int countIncreaseByWindow(List<Integer> data)
	{
		assert(data.size() > 3);
		
		List<Integer> window = data.stream().limit(3).collect(Collectors.toList());
		List<Integer> winValues = new ArrayList<Integer>();
		
		
		data.stream().skip(3).forEach(x -> {
			window.remove(0);
			window.add(x);
			winValues.add(window.stream().mapToInt(Integer::intValue).sum());
		});
		
		return countIncrease(winValues);
	}


	public static void main(String[] args) {
		try
		{
			Path datafile= Path.of(args[0]);
			List<Integer> depths = Files.lines(datafile).mapToInt(x -> Integer.parseInt(x)).boxed().collect(Collectors.toList());
			
			
			System.out.println("Increase count: " + countIncrease(depths));
			System.out.println("Increase count by window: " + countIncreaseByWindow(depths));
		}

		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
