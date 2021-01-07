package testCollections;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import structures.LinkedList;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import Exceptions.AlreadyAddedFriend;
import Exceptions.ElementNotFoundException;

import Social.Person;
import Social.PersonForGraph;
import Social.SocialList;
import structures.BinarySearchID;


public class TestBinarySearchId {
	Person pe1,pe2,pe3,pe4,pe5,pe6;
	SocialList soci;
	@BeforeEach
	void setUp() throws Exception {
		soci=SocialList.getInstance();
		String[] n1={"n1","p1","s1","bd1","male","bpl1","h1","st1","w1","f1","g1"};
		String[] n2={"n2","p2","s2","bd2","female","bpl2","h2","st2","w2","f2","g2"};
		String[] n3={"n3","p3","s3","bd3","male","bpl3","h3","st3","w3","f3","g3"};
		String[] n4={"n4","p4","s4","bd4","female","bpl4","h4","st4","w4","f4","g4"};
		String[] n5={"n5","p5","s5","bd5","male","bpl5","h5","st5","w5","f5","g5"};
		String[] n6={"n6","p6","s6","bd6","male","bpl6","h6","st6","w6","f6","g6"};
		pe1=new Person(n1);
		pe2=new Person(n2);
		pe3=new Person(n3);
		pe4=new Person(n4);
		pe5=new Person(n5);
		pe6=new Person(n6);
	}
	
	@Test
	@DisplayName("Test for BinarySearchID's add() method")
	public void addTest() {
		BinarySearchID tree= new BinarySearchID();
		tree.add(pe1);
		assertEquals(pe1, tree.getRoot());
	}
	
	@Test
	@DisplayName("Test for BinarySearchID's remove() method")
	public void removeTest() throws ElementNotFoundException{
		BinarySearchID tree= new BinarySearchID();
		tree.add(pe1);
		tree.add(pe2);
		tree.add(pe3);
		tree.add(pe4);
		assertThrows(
			      ElementNotFoundException.class,
			      () -> tree.remove(pe5));
		tree.add(pe5);
		assertEquals(pe5, tree.remove(pe5));
	}
	@Test
	@DisplayName("test for BinarySearchID's toList() method")
	public void toListTest() {
		BinarySearchID tree= new BinarySearchID();
		tree.add(pe1);
		tree.add(pe2);
		tree.add(pe3);
		tree.add(pe4);
		
		LinkedList<Person> list = tree.toList();
		assertEquals(pe1.getPersonData()[0], list.get(0).getPersonData()[0]);
		assertEquals(pe2.getPersonData()[0], list.get(1).getPersonData()[0]);
		assertEquals(pe3.getPersonData()[0], list.get(2).getPersonData()[0]);
		assertEquals(pe4.getPersonData()[0], list.get(3).getPersonData()[0]);
		
	}
	@Test
	@DisplayName("Test for BianarySearchID's toFameList() method ")
	public void toFameListTest() {
		BinarySearchID tree= new BinarySearchID();
	
		try {
			pe1.addFriend(pe2);
			pe1.addFriend(pe3);
			pe1.addFriend(pe4);
			pe1.addFriend(pe5);
			pe2.addFriend(pe3);
			pe2.addFriend(pe6);

			
		} catch (AlreadyAddedFriend e) {
			
			e.printStackTrace();
		}
		tree.add(pe1);
		tree.add(pe2);
		tree.add(pe3);
		tree.add(pe4);
		LinkedList<Person> list = tree.toFameList();
		assertEquals(pe1.getPersonData()[0], list.get(0).getPersonData()[0]);
		assertEquals(pe2.getPersonData()[0], list.get(1).getPersonData()[0]);
		assertEquals(pe3.getPersonData()[0], list.get(2).getPersonData()[0]);
		assertEquals(pe4.getPersonData()[0], list.get(3).getPersonData()[0]);
		
	}
	
	@Test
	@DisplayName("Test for BianarySearchID's toValueArray() method ")
	public void toValueArrayTest() {
		BinarySearchID tree= new BinarySearchID();
		tree.add(pe1);
		tree.add(pe2);
		tree.add(pe3);
		tree.add(pe4);
		tree.add(pe5);
		try {
			pe1.addFriend(pe2);
			pe1.addFriend(pe3);
			pe1.addFriend(pe4);
			pe2.addFriend(pe3);
		} catch (AlreadyAddedFriend e) {}
		PersonForGraph peg1=new PersonForGraph(pe1, 0);
		PersonForGraph peg2=new PersonForGraph(pe2, 1);
		PersonForGraph peg3=new PersonForGraph(pe3, 2);
		PersonForGraph peg4=new PersonForGraph(pe4, 3);
		PersonForGraph peg5=new PersonForGraph(pe5, 4);
		peg1.addToTheList(peg2);
		peg1.addToTheList(peg3);
		peg1.addToTheList(peg4);
		peg2.addToTheList(peg1);
		peg2.addToTheList(peg3);
		peg3.addToTheList(peg1);
		peg3.addToTheList(peg2);
		peg4.addToTheList(peg1);
		PersonForGraph[] val=new PersonForGraph[5];
		val[0]=peg1;
		val[1]=peg2;
		val[2]=peg3;
		val[3]=peg4;
		val[4]=peg5;
		PersonForGraph[] valu=tree.toValueArray();
		for(int i=0;i<5;i++)
			assertEquals(valu[i],val[i]);
		
	}
	
	
}
