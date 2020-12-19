package testSocial;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import Exceptions.AlreadyAddedPerson;
import Exceptions.ElementNotFoundException;
import Social.Person;
import Social.SocialList;
@DisplayName("Test case for SocialList(methods that print excluded):")
class TestSocialList {
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
		soci.addPerson(pe1);
		soci.addPerson(pe2);
		soci.addPerson(pe3);
		soci.addPerson(pe4);
		soci.addPerson(pe5);
	}

	@AfterEach
	void tearDown() throws Exception {
		SocialList.destroy();
	}

	@Test
	@DisplayName("Test case for the method addPerson() and its exception")
	void testAddPerson() {
		String st[]= {"p6"};
		String st1[]= {"p7"};
		String st2[]= {"p8"};
		String st3[]= {"p9"};
		String st4[]= {"p10"};
		Person pe6=new Person(st);
		Person pe7=new Person(st);//should be the same as pe6
		Person pe8=new Person(st1);
		Person pe9=new Person(st2);
		Person pe10=new Person(st3);
		Person pe11=new Person(st4);
		
		try {
			soci.addPerson(pe6);
			soci.addPerson(pe8);
			soci.addPerson(pe9);
			soci.addPerson(pe10);
			soci.addPerson(pe11);
		} catch (AlreadyAddedPerson e) {
			fail("Should not happen");
		}
		assertTrue(soci.isPerson(pe6.getPersonData()[0]));
		assertTrue(soci.isPerson(pe8.getPersonData()[0]));
		assertTrue(soci.isPerson(pe9.getPersonData()[0]));
		assertTrue(soci.isPerson(pe10.getPersonData()[0]));
		assertTrue(soci.isPerson(pe11.getPersonData()[0]));

		String prove="nope";
		try {
			soci.addPerson(pe7);
		} catch (AlreadyAddedPerson e) {
			prove=e.getMessage();
		}
		AlreadyAddedPerson ex = new AlreadyAddedPerson("Already added");
		assertEquals(ex.getMessage(),prove);
		
		
	}

	@Test
	@DisplayName("Test case for the method addFromFile()")//Should appear 2 "already added" on the console
	void testAddFromFile() throws FileNotFoundException {
		
		soci.addFromFile("testPeople.txt");
		assertTrue(soci.isPerson("arguinano"));
		assertTrue(soci.isPerson("metelo"));
		assertTrue(soci.isPerson("juan"));
		assertTrue(soci.isPerson("pedro"));
		assertTrue(soci.isPerson("roberto"));
		assertTrue(soci.isPerson("francisco"));
		assertTrue(soci.isPerson("pedro01"));
		assertTrue(soci.isPerson("pedro02"));
		assertTrue(soci.isPerson("juano"));
		assertTrue(soci.isPerson("peyo"));
		
	}
	
	@Test
	@DisplayName("Test case for the method setFriendships()")//1 relationship is already established and 1 is impossible to establish
	void testSetFriendShips() {
		String prove="nope";
		try {
			soci.setFriendships("testFriendships.txt");
		} catch (ElementNotFoundException e) {
			prove=e.getMessage();
		}
		assertTrue(pe1.isFriend(pe2));
		assertTrue(pe1.isFriend(pe3));
		assertTrue(pe1.isFriend(pe4));
		assertTrue(pe2.isFriend(pe4));
		assertTrue(pe4.isFriend(pe5));
		
		assertEquals("\n \u001B[31m"+"There where "+"1"+" impossible to stablish relations"+"\u001B[0m \n", prove);
	}
	
	@Test
	@DisplayName("Test case for the method removePerson()")
	void testRemovePerson() throws ElementNotFoundException {// throws ElementNotFoundException {
		assertTrue(soci.isPerson("n1"));
		assertTrue(soci.isPerson("n2"));
		assertTrue(soci.isPerson("n3"));
		assertTrue(soci.isPerson("n4"));
		assertTrue(soci.isPerson("n5"));
		soci.removePerson(soci.findPerson("n1"));
		soci.removePerson(soci.findPerson("n2"));
		soci.removePerson(soci.findPerson("n3"));
		soci.removePerson(soci.findPerson("n4"));
		soci.removePerson(soci.findPerson("n5"));
		assertFalse(soci.isPerson("n1"));
		assertFalse(soci.isPerson("n2"));
		assertFalse(soci.isPerson("n3"));
		assertFalse(soci.isPerson("n4"));
		assertFalse(soci.isPerson("n5"));
	}

	@Test
	@DisplayName("Test case for the method removeFromFile()")
	void testRemoveFromFile() throws FileNotFoundException {
		assertTrue(soci.isPerson("n1"));
		assertTrue(soci.isPerson("n2"));
		assertTrue(soci.isPerson("n3"));
		assertTrue(soci.isPerson("n4"));
		assertTrue(soci.isPerson("n5"));
		soci.removeFromFile("testRemovePeople.txt");
		assertFalse(soci.isPerson("n1"));
		assertFalse(soci.isPerson("n2"));
		assertFalse(soci.isPerson("n3"));
		assertFalse(soci.isPerson("n4"));
		assertFalse(soci.isPerson("n5"));
	}

	@Test
	@DisplayName("Test case for the method removeFriendships()")
	void testRemoveFriendShips() {
		String prove="nope";
		try {
			soci.setFriendships("testFriendships.txt");
		} catch (ElementNotFoundException e) {
			prove=e.getMessage();
		}
		assertTrue(pe1.isFriend(pe2));
		assertTrue(pe1.isFriend(pe3));
		assertTrue(pe1.isFriend(pe4));
		assertTrue(pe2.isFriend(pe4));
		assertTrue(pe4.isFriend(pe5));
		
		assertEquals("\n \u001B[31m"+"There where "+"1"+" impossible to stablish relations"+"\u001B[0m \n", prove);
		
		prove="nope";
		try {
			soci.removeFriendships("testFriendships.txt");
		} catch (ElementNotFoundException e) {
			prove=e.getMessage();
		}
		assertFalse(pe1.isFriend(pe2));
		assertFalse(pe1.isFriend(pe3));
		assertFalse(pe1.isFriend(pe4));
		assertFalse(pe2.isFriend(pe4));
		assertFalse(pe4.isFriend(pe5));
		
		assertEquals("\n \u001B[31m"+"There where "+"1"+" impossible to remove relations"+"\u001B[0m \n", prove);
	}

	@Test
	@DisplayName("Test case for the method findPerson()")
	void testFindPerson() {
		try {
			assertEquals(pe1,soci.findPerson("n1"));
			assertEquals(pe2,soci.findPerson("n2"));
			assertEquals(pe3,soci.findPerson("n3"));
			assertEquals(pe4,soci.findPerson("n4"));
			assertEquals(pe5,soci.findPerson("n5"));
		} catch (ElementNotFoundException e) {
			fail("Should never happen");
		}
		
		assertThrows(
			      ElementNotFoundException.class,
			      () -> soci.findPerson("juanito"));
			
		
	}
	
	@Test
	void testIsPerson() {
		assertTrue(soci.isPerson("n1"));
		assertTrue(soci.isPerson("n2"));
		assertTrue(soci.isPerson("n3"));
		assertTrue(soci.isPerson("n4"));
		assertTrue(soci.isPerson("n5"));
		assertFalse(soci.isPerson("n6"));
		assertFalse(soci.isPerson("n7"));
		assertFalse(soci.isPerson("n55"));
		assertFalse(soci.isPerson("n8"));
		assertFalse(soci.isPerson("n9"));
		assertFalse(soci.isPerson("n10"));
		assertFalse(soci.isPerson("aedfa"));
		assertFalse(soci.isPerson("juan"));
		assertFalse(soci.isPerson("peyo"));
	}

}
