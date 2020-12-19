package testCollections;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import structures.LinkedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import Exceptions.NotEnoughElementsException;
import Social.Person;
import Social.SocialList;

@DisplayName("Test case for LinkedList collection:")
public class TestLinkedList {
	Person pe1,pe2,pe3,pe4,pe5;
	SocialList soci;
	@BeforeEach
	void setUp() throws Exception {
		soci=SocialList.getInstance();
		String[] n1={"n1","p1","s1","bd1","male","bpl1","h1","st1","w1","f1","g1"};
		String[] n2={"n2","p2","s2","bd2","female","bpl2","h2","st2","w2","f2","g2"};
		String[] n3={"n3","p3","s3","bd3","male","bpl3","h3","st3","w3","f3","g3"};
		String[] n4={"n4","p4","s4","bd4","female","bpl4","h4","st4","w4","f4","g4"};
		String[] n5={"n5","p5","s5","bd5","male","bpl5","h5","st5","w5","f5","g5"};
		pe1=new Person(n1);
		pe2=new Person(n2);
		pe3=new Person(n3);
		pe4=new Person(n4);
		pe5=new Person(n5);
	}
	
	
	
	@Test
	@DisplayName("Test case for LinkedList's add() method")
	public void addTest() throws NotEnoughElementsException{
		LinkedList<Person> list= new LinkedList<>();
		assertThrows(
			      NotEnoughElementsException.class,
			      () -> list.add(pe1, 3));
		list.add(pe2, 0);
		assertEquals(pe2, list.get(0));
		
	}
	
	@Test
	@DisplayName("Test case for LinkedList's remove(index) method")
	public void removeTest() throws NotEnoughElementsException{
		LinkedList<Person> list= new LinkedList<>();
		list.add(pe2,0);
		list.add(pe1, 1);
		assertThrows(
			      NotEnoughElementsException.class,
			      () -> list.remove(4));
		assertEquals(pe1, list.remove(1));
		
	}
	
	@Test
	@DisplayName("Test case for LinkedList's get method")
	public void getTest() throws NotEnoughElementsException{
		LinkedList<Person> list= new LinkedList<>();
		assertThrows(
					NotEnoughElementsException.class,
			      () -> list.get(3));
		list.add(pe1,  0);
		assertEquals(pe1, list.get(0));
	}
	
}

