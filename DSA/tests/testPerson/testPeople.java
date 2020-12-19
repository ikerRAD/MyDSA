package testPerson;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import Exceptions.AlreadyAddedFriend;
import Exceptions.ElementNotFoundException;
import Social.Person;


class TestPeople {
	
	Person pe1,pe2,pe3,pe4,pe5;
	
	@BeforeEach
	void setUp() throws Exception {
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
	@DisplayName("Test case for the method addFriend() and its exception")
	public void testAddFriend() {
		try {
			pe1.addFriend(pe2);
			pe1.addFriend(pe3);
			pe1.addFriend(pe4);
			pe1.addFriend(pe5);
		}catch(AlreadyAddedFriend e){
			fail("Should not happen");
		}
		assertTrue(pe1.isFriend(pe2));
		assertTrue(pe1.isFriend(pe3));
		assertTrue(pe1.isFriend(pe4));
		assertTrue(pe1.isFriend(pe5));
		
		String prove="nope";
		try {
			pe1.addFriend(pe2);
		} catch (AlreadyAddedFriend e) {
			prove="Already added";
		}
		AlreadyAddedFriend ex = new AlreadyAddedFriend("Already added");
		assertEquals(ex.getMessage(),prove);
	}
	
	@Test
	@DisplayName("Test case for the method removeFriend()")
	public void testRemoveFriend() throws ElementNotFoundException{
		try {
			pe1.addFriend(pe2);
			pe1.addFriend(pe3);
			pe1.addFriend(pe4);
			pe1.addFriend(pe5);
		}catch(AlreadyAddedFriend e){
			fail("Should not happen");
		}
		pe1.removeFriend(pe2);
		pe1.removeFriend(pe3);
		pe1.removeFriend(pe4);
		pe1.removeFriend(pe5);
		assertFalse(pe1.isFriend(pe2));
		assertFalse(pe1.isFriend(pe3));
		assertFalse(pe1.isFriend(pe4));
		assertFalse(pe1.isFriend(pe5));
		
	}
	
	@Test
	@DisplayName("Test case for the method numFriend()")
	public void testNumFriend() {
		try {
			pe1.addFriend(pe2);
			pe1.addFriend(pe3);
			pe1.addFriend(pe4);
			pe1.addFriend(pe5);
		}catch(AlreadyAddedFriend e){
			fail("Should not happen");
		}
		assertEquals(pe1.getNumFriends(),4);
	}
	
	@Test
	@DisplayName("Test case for the method AddWork()")
	public void testAddWork() {
		pe1.addWork("WWA");
		assertEquals(pe1.getPersonData()[8],"w1;WWA");
		pe2.addWork("WWE");
		assertEquals(pe2.getPersonData()[8],"w2;WWE");
		pe3.addWork("WWI");
		assertEquals(pe3.getPersonData()[8],"w3;WWI");
		pe4.addWork("WWO");
		assertEquals(pe4.getPersonData()[8],"w4;WWO");
		pe5.addWork("WWU");
		assertEquals(pe5.getPersonData()[8],"w5;WWU");
	}
	
	@Test
	@DisplayName("Test case for the method AddFilm()")
	void testAddFilm() {
		pe1.addFilm("FFF");
		assertEquals(pe1.getPersonData()[9],"f1;FFF");
		pe2.addFilm("ZZZ");
		assertEquals(pe2.getPersonData()[9],"f2;ZZZ");
		pe3.addFilm("MMM");
		assertEquals(pe3.getPersonData()[9],"f3;MMM");
		pe4.addFilm("QQQ");
		assertEquals(pe4.getPersonData()[9],"f4;QQQ");
		pe5.addFilm("XXX");
		assertEquals(pe5.getPersonData()[9],"f5;XXX");
	}
	
	@Test
	@DisplayName("Test case for the method testEquals()")
	public void testEquals() {
		assertTrue(pe1.equals(pe1));
		assertTrue(pe2.equals(pe2));
		assertTrue(pe3.equals(pe3));
		assertTrue(pe4.equals(pe4));
		assertTrue(pe5.equals(pe5));
		assertFalse(pe1.equals(pe5));
		assertFalse(pe2.equals(pe1));
		assertFalse(pe3.equals(pe2));
		assertFalse(pe4.equals(pe3));
		assertFalse(pe5.equals(pe4));
	}
	
	@Test
    @DisplayName("Test case for the method sortString()")
    void testSortString() {
        String re = "Mango;Huevo;Patatas;Azucar";
        String reb = "Azucar;Huevo;Mango;Patatas";
        String mi = "Xiaomi;Apple;Samsung;Huawei";
        String mib = "Apple;Huawei;Samsung;Xiaomi";
        String sol = "Ana;Juan;Pedro;Borja";
        String solb = "Ana;Borja;Juan;Pedro";
        String fa = "Dior;Gucci;Prada;Versace";
        
        assertEquals(pe1.sortString(re), reb);
        assertEquals(pe1.sortString(mi), mib);
        assertEquals(pe1.sortString(sol), solb);
        assertEquals(pe1.sortString(fa), fa);
        
    }
}

