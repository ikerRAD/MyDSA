package testCollections;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import Social.Person;
import structures.TableWithCollision;
@DisplayName("Test case for TableWithCollisions collection:")
class testTableWithCollisions {
	Person pe1,pe2,pe3,pe4,pe5;
	@BeforeEach
	void setUp() throws Exception {
		String[] n1={"i1","aa","s1","bd1","male","bpl1","h1","st1","w1","f1","g1"};
		String[] n2={"i2","bb","s2","bd2","female","bpl2","h2","st2","w2","f2","g2"};
		String[] n3={"i3","cc","s3","bd3","male","bpl3","h3","st3","w3","f3","g3"};
		String[] n4={"i4","dd","s4","bd4","female","bpl4","h4","st4","w4","f4","g4"};
		String[] n5={"i5","dd","s5","bd5","male","bpl5","h5","st5","w5","f5","g5"};
		pe1=new Person(n1);
		pe2=new Person(n2);
		pe3=new Person(n3);
		pe4=new Person(n4);
		pe5=new Person(n5);
	}


	@Test
	@DisplayName("Test case for TableWithCollisions' add() method")
	void testPut() {
		TableWithCollision<String,Person> test=new TableWithCollision<String, Person>();
		test.put(pe1.getPersonData()[1],pe1);
		test.put(pe2.getPersonData()[1],pe2);
		test.put(pe3.getPersonData()[1],pe3);
		test.put(pe4.getPersonData()[1],pe4);
		test.put(pe5.getPersonData()[1],pe5);
		
		assertTrue(test.getTable().size()==4);//Assert that we only create 4 different classes
		//Assert the length of each class
		assertTrue(test.getTable().get(0).colli.size()==1);
		assertTrue(test.getTable().get(1).colli.size()==1);
		assertTrue(test.getTable().get(2).colli.size()==1);
		assertTrue(test.getTable().get(3).colli.size()==2);
		//Assert the content of each class and the order
		assertTrue(test.getTable().get(0).key=="aa");
		assertTrue(test.getTable().get(1).key=="bb");
		assertTrue(test.getTable().get(2).key=="cc");
		assertTrue(test.getTable().get(3).key=="dd");
		//Assert the exact objects at each class
		assertEquals(pe1, test.getTable().get(0).colli.get(0));
		assertEquals(pe2, test.getTable().get(1).colli.get(0));
		assertEquals(pe3, test.getTable().get(2).colli.get(0));
		assertEquals(pe4, test.getTable().get(3).colli.get(1));
		assertEquals(pe5, test.getTable().get(3).colli.get(0));
	}

}
