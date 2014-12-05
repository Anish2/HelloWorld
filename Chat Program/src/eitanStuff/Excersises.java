package eitanStuff;

import java.awt.Color;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class Excersises 
{
	public static void main(String[] agrs)
	{
		//Hash set, Tree set, ConcurrentSkipListSet,  CopyOnWriteArraySet, EnumSet
		//Equals and remove all
		//The interface has add, contains, remove and size
	}
	
	public void programming2()
	{
		HashSet<Color> set = new HashSet<Color>();
		set.add(Color.black);
		set.add(Color.BLUE);
		set.add(Color.cyan);
		set.add(Color.DARK_GRAY);
		set.add(Color.GRAY);
		set.add(Color.green);
		set.add(Color.LIGHT_GRAY);
		set.add(Color.MAGENTA);
		set.add(Color.ORANGE);
		set.add(Color.pink);
		set.add(Color.red);
		set.add(Color.white);
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Please enter three doubles representing the RGB values they should be between 0 and 1");
		
		Color color = new Color(scan.nextFloat(), scan.nextFloat(), scan.nextFloat());
		if(set.contains(color))
		{
			System.out.println("It is here");
		}
		else
		{
			System.out.println("It is not here");
		}
		scan.close();
	}
	
	public Set intersection(Set s1, Set s2)
	{
		HashSet s3 = new HashSet();
		Iterator it = s1.iterator();
		for(int x= 0; x < s1.size(); x++)
		{
			Object ob  = it.next();
			if(s2.contains(ob))
			{
				s3.add(ob);
			}
		}
		return s3;
	}
	
	public Set union(Set s1, Set s2)
	{
		HashSet s3 = new HashSet();
		Iterator it = s1.iterator();
		Iterator it2 = s2.iterator();
		for(int x= 0; x < s1.size(); x++)
		{
			s3.add(it.next());
		}
		for(int x= 0; x < s2.size(); x++)
		{
			s3.add(it2.next());
		}
		return s3;
	}
}
