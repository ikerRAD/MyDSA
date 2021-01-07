package testPerson;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import Exceptions.FullListException;
import Social.Person;
import Social.PersonForGraph;
import Social.SocialList;

class testPeopleForGraphs {
	Person pe1,pe2,pe3,pe4,pe5,pe6;
	PersonForGraph peg1,peg2,peg3,peg4,peg5;
	SocialList soci;
	PersonForGraph[] val;
	@BeforeEach
	void setUp() throws Exception {
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
		pe1.addFriend(pe2);
		pe1.addFriend(pe3);
		pe1.addFriend(pe4);
		pe2.addFriend(pe3);
		peg1=new PersonForGraph(pe1, 0);
		peg2=new PersonForGraph(pe2, 1);
		peg3=new PersonForGraph(pe3, 2);
		peg4=new PersonForGraph(pe4, 3);
		peg5=new PersonForGraph(pe5, 4);
		
	}

	@Test
	@DisplayName("Test for PersonForGraph's addToTheList() method")
	void testAddToTheList() {
		assertTrue(peg1.filled==0);
		assertTrue(peg2.filled==0);
		assertTrue(peg3.filled==0);
		assertTrue(peg4.filled==0);
		
		
		
		peg1.addToTheList(peg2);
		assertTrue(peg1.filled==1);
		assertEquals(peg2, peg1.griendList[0]);
		
		peg1.addToTheList(peg3);
		assertTrue(peg1.filled==2);
		assertEquals(peg3, peg1.griendList[1]);
		
		peg1.addToTheList(peg4);
		assertTrue(peg1.filled==peg1.length);
		assertEquals(peg4, peg1.griendList[2]);
		
		peg2.addToTheList(peg1);
		assertTrue(peg2.filled==1);
		assertEquals(peg1, peg2.griendList[0]);
		
		peg2.addToTheList(peg3);
		assertTrue(peg2.filled==peg2.length);
		assertEquals(peg3, peg2.griendList[1]);
		
		peg3.addToTheList(peg1);
		assertTrue(peg3.filled==1);
		assertEquals(peg1, peg3.griendList[0]);
		
		peg3.addToTheList(peg2);
		assertTrue(peg3.filled==peg3.length);
		assertEquals(peg2, peg3.griendList[1]);
		
		peg4.addToTheList(peg1);
		assertTrue(peg4.filled==peg4.length);
		assertEquals(peg1, peg4.griendList[0]);
		
		assertThrows(
				FullListException.class,
			      () -> peg4.addToTheList(peg5));
	}

}
