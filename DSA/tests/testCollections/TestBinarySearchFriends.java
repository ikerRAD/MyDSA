package testCollections;
import structures.LinkedList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import Exceptions.ElementNotFoundException;
import Social.Person;
import Social.SocialList;
import structures.BinarySearchFriends;


class TestBinarySearchFriends {
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
	@DisplayName("Test for BinarySearchFriend's addElement() method")
	public void addElementTest() {
		BinarySearchFriends tree= new BinarySearchFriends();
		tree.addElement(pe1);
		assertEquals(pe1, tree.getRoot());
		
	}
	
	@Test
	@DisplayName("Test for BinarySearchFriend's removeElement() method")
	public void removeElementTest() throws ElementNotFoundException {
		BinarySearchFriends tree= new BinarySearchFriends();
		tree.add(pe1);
		tree.add(pe2);
		tree.add(pe3);
		tree.add(pe4);
		assertThrows(
			      ElementNotFoundException.class,
			      () -> tree.remove(pe5));
		tree.add(pe5);
		assertEquals(pe5, tree.removeElement(pe5));
	}
	
	@Test
	@DisplayName("Test for BinarySearchFriend's removeElement() method")
	public void findTest() throws ElementNotFoundException {
		BinarySearchFriends tree= new BinarySearchFriends();
		tree.add(pe1);
		tree.add(pe2);
		tree.add(pe3);
		tree.add(pe4);
		assertEquals(pe1, tree.find(pe1));
		assertEquals(pe2, tree.find(pe2));
		assertEquals(pe3, tree.find(pe3));
		assertEquals(pe4, tree.find(pe4));
	}
	
	@Test
	@DisplayName("test for BinarySearchID's toList() method")
	public void toListTest() {
		BinarySearchFriends tree= new BinarySearchFriends();
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

}