package Social;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;

import Exceptions.*;
import GraphTraversals.BreadthFirstPathsFriendships;
import GraphTraversals.LongPathFinder;
//import structures.BinarySearchFriends;
import structures.BinarySearchID;
import structures.LinkedBinarySearchTree;
import structures.LinkedList;
import structures.PersonGraph;
import structures.Stack;
import structures.TableWithCollision;
/**
 * Class that represents a network
 * @author Iker Pintado, Jon Morinigo, Iker Fernandez
 *
 */
public class SocialList {
	/**
	 * the list of persons of the network
	 */
	private BinarySearchID list;
	
	/**
	 * boolean parameter that says if something in the social list has changed, it will be set to false every time a new graph is created
	 */
	public static boolean changed;
	
	/**
	 * parameter for the graph that represents the network, it will always be updated if anything changes in the list
	 */
	private PersonGraph theGraph;
	
	/**
	 * the only instance of the network--singleton pattern
	 */
	private static SocialList instance;
	/**
	 * Comparator for sorting the list in a not natural order
	 */
	public static Comparator<Person> BSNComparator=new Comparator<Person>() {
		/**
		 * compare method for sorting in some cases
		 */
		@Override
		public int compare(Person o1, Person o2) {
			int res=o1.getPersonData()[5].compareTo(o2.getPersonData()[5]);
			if(res==0) {
				res=o1.getPersonData()[2].compareTo(o2.getPersonData()[2]);
				if(res==0) {
					res=o1.getPersonData()[1].compareTo(o2.getPersonData()[1]);
				}
			}
			return res;
		}
	};

	/**
	 * private constructor of the class--singleton pattern
	 */
	private SocialList() {
		super();
		list=new BinarySearchID();
		changed=true;
	}
	/**
	 * method to get the only instance of the class--singleton pattern
	 * @return the only instance
	 */
	public static SocialList getInstance() {
		if(instance==null) {
			instance=new SocialList();
		}
		return instance;
	}
	/**
	 * method to add a person to the list
	 */
	public void addPerson(Person p) throws AlreadyAddedPerson{
		if(!list.isEmpty()) 
			if(list.contains(p)) throw new AlreadyAddedPerson("Already added");
		list.add(p);
		changed=true;
		
	}
	/**
	 * method to remove a person of the list. Deletes all the existence of that person in the network,
	 * including friendships
	 * @param person to remove
	 */
	public void removePerson(Person p){	
		try {
			list.remove(p);
			
		} catch (EmptyCollectionException | ElementNotFoundException e) {
			System.out.println("\n \u001B[31m"+"Already removed person or never existed"+"\u001B[0m \n");
		}
		if(!p.friendList.isEmpty()) {
			for(Person pe:p.friendList) {
				pe.removeFriend(p);
			}
		}
		changed=true;
		System.out.println("\n \u001B[32m DONE! \u001B[0m \n");
	}
	/**
	 * method to find a person from the id
	 * @param the id of the person
	 * @return the person with that id
	 * @throws ElementNotFoundException when the id is not on the network
	 */
	public Person findPerson(String id)throws ElementNotFoundException {
		String[] s=new String[1];
		s[0]=id;
		Person w=new Person(s);
		return list.find(w);

	}
	/**
	 * Method to know if a person is in the network
	 * @param the id of the person
	 * @return if the person is in the network
	 */
	public boolean isPerson(String id) {
		String[] pe=new String[1];
		pe[0]=id;
		Person p=new Person(pe);
		return list.contains(p);
	}
	/**
	 *Prints all the people of the network
	 */
	public void printPeople() {
		String pr="\n \u001B[33m"+"People:"+"\u001B[0m \n\n";
		if(list!=null) {
			for(Person p:list) {
				pr=pr+p.toString()+"\n";
			}
		}
		System.out.println(pr+"\n \u001B[33m"+"There are no more people"+"\u001B[0m \n");
	}
	
	/**
	 *Prints all the people of the network ordered by fame
	 */
	public void printFamePeople() {
		String pr="\n \u001B[33m"+"People:"+"\u001B[0m \n\n";
		if(list!=null) {
			LinkedList<Person> fameList=list.toFameList();
			for(Person p:fameList) {
				pr=pr+p.toString()+"\n";
			}
		}
		System.out.println(pr+"\n \u001B[33m"+"There are no more people"+"\u001B[0m \n");
	}
	
	/**
	 * method that adds people from a file
	 * @param the name of the file
	 * @throws FileNotFoundException if the file name is wrong
	 */
	public void addFromFile(String s) throws FileNotFoundException{
		String splitBy=",";
		String[] pData;
		String line="";
		try {

			Scanner sr=new Scanner(new File(s));
			while(sr.hasNextLine()) {
				line=sr.nextLine();
				if(!line.equals("")) {
					pData=line.split(splitBy);
				
					try {
						addPerson(new Person(pData));
					} catch (AlreadyAddedPerson e) {
						System.out.println("\n \u001B[31m"+"already added"+"\u001B[0m \n");
					}
				}
				
			}
				
		
			sr.close();
			changed=true;
		}catch(IOException e) {
			System.out.println("\n \u001B[31m"+e.getMessage()+"\u001B[0m \n");
		}
		
	}
	/**
	 * method that removes from the network all the people in the file
	 * @param the name of the file
	 * @throws FileNotFoundException if the name of the file is wrong
	 */
	public void removeFromFile(String s) throws FileNotFoundException{
		String splitBy=",";
		String[] pData;
		String line="";
		try {

			Scanner sr=new Scanner(new File(s));
			while(sr.hasNextLine()) {
				line=sr.nextLine();
				if(!line.equals("")) {
					pData=line.split(splitBy);				
					removePerson(new Person(pData));
				}
				
			}
				
		
			sr.close();
			changed=true;
		}catch(IOException e) {
			System.out.println("\n \u001B[31m"+e.getMessage()+"\u001B[0m \n");
		}
		
	}
	/**
	 * method that reads from a file and sets the friendships
	 * @param the name of the file
	 * @throws ElementNotFoundException if one person of the file is not in the list
	 */
	public void setFriendships(String s) throws ElementNotFoundException{
		String splitBy=",";
		String[] friends;
		String line="";
		int q=3,w=3,det=0;
		Iterator<Person> it;
		Person p1,p2,pp,pa1,pa2;
		p1=p2=pp=pa1=pa2=null;
		LinkedList<Person> listit=list.toList();
		try {
			Scanner sr=new Scanner(new File(s));
			while(sr.hasNextLine()) {
				line=sr.nextLine();
				if(!line.equals("")) {
					friends=line.split(splitBy);
					it=listit.iterator();	
					try {
						pa1=findPerson(friends[0]);
						pa2=findPerson(friends[1]);
						while(w+q!=0 && it.hasNext()) {
							pp=it.next();
							if(pp.equals(pa1)) {
								p1=pp;//amigo 1
								q=0;
							}else if(pp.equals(pa2)) {
								p2=pp;// amigo 2
								w=0;
							}
						}
						
						if(w+q==0) {
							try {
								p1.addFriend(p2);
							} catch (AlreadyAddedFriend e) {
								System.out.println("\n \u001B[31m"+"Already added friend"+"\u001B[0m \n");
							}
						}
						w=3;
						q=3;				
					} catch (ElementNotFoundException e1) {
						det++;
					}
				}
					
			}
			sr.close();
			changed=true;
			if(det>0)
				throw new ElementNotFoundException("\n \u001B[31m"+"There where "+det+" impossible to stablish relations"+"\u001B[0m \n");
				
			
		}catch(FileNotFoundException e) {
			System.out.println("\n \u001B[31m"+"file not found"+"\u001B[0m \n");
		}
	}
	/**
	 * method that removes the read from file relationships
	 * @param name of the file
	 * @throws ElementNotFoundException if some name on the file does not appear in the network
	 */
	public void removeFriendships(String s) throws ElementNotFoundException{
		String splitBy=",";
		String[] friends;
		String line="";
		int q=3,w=3,det=0;
		Iterator<Person> it;
		Person p1,p2,pp,pa1,pa2;
		p1=p2=pp=pa1=pa2=null;
		LinkedList<Person> listit=list.toList();
		try {
			Scanner sr=new Scanner(new File(s));
			
			while(sr.hasNextLine()) {
				line=sr.nextLine();
				if(!line.equals("")) {
					friends=line.split(splitBy);
					it=listit.iterator();	
					try {
						pa1=findPerson(friends[0]);
						pa2=findPerson(friends[1]);
						while(w+q!=0 && it.hasNext()) {
							pp=it.next();
								if(pp.equals(pa1)) {
									p1=pp;//amigo 1
									q=0;
								}else if(pp.equals(pa2)) {
									p2=pp;//amigo 2
									w=0;
								}
						}
						
						if(w+q==0) {
							
							p1.removeFriend(p2);
							
						}
						w=3;
						q=3;
					
					} catch (ElementNotFoundException e) {
						det++;
					}
				}
			}
			changed=true;
			sr.close();
			if(det>0)
				throw new ElementNotFoundException("\n \u001B[31m"+"There where "+det+" impossible to remove relations"+"\u001B[0m \n");
				
			
			
		}catch(FileNotFoundException e) {
			System.out.println("\n \u001B[31m"+"file not found"+"\u001B[0m \n");
		}
	}
	/**
	 * method that prints all the people of the network on a .txt
	 */
	public void getTXT() {
		try {
			PrintWriter wrfile =new PrintWriter(new File("wrotePeople.txt"));
			String pr="";
			for(Person p:list) {
				pr=pr+p.toStringTXT()+"\n";
			}
			wrfile.println(pr);
			wrfile.close();
		} catch (FileNotFoundException e) {
			System.out.println("\n \u001B[31m"+"txt not found"+"\u001B[0m \n");
		}
	}
	/**
	 * method that prints all the existing relationships on a .txt
	 */
	public void getRelasTXT() {
		try {
			Person au2,au;
			String print="";
			PrintWriter wrfile =new PrintWriter(new File("wroteRelas.txt"));
			LinkedList<Person> link=list.toFameList();
			Iterator<Person> it,ite=link.iterator();
			while(ite.hasNext()) {
				au=ite.next();
				try {
					link.remove(au);//always removes the first one
				} catch (EmptyCollectionException | ElementNotFoundException e) {}//the element will surely be found and the collection will never be empty
				it=link.iterator();
				while(it.hasNext()) {
					au2=it.next();
					if(au.isFriend(au2)) {
						print=print+au.getPersonData()[0]+","+au2.getPersonData()[0]+"\n";
					}
				}
			}
			wrfile.println(print);
			
			wrfile.close();
		} catch (FileNotFoundException e) {
			System.out.println("\n \u001B[31m"+"txt not found"+"\u001B[0m \n");
		}
	}
	/**
	 * This method sorts by birthplace,surname and name all the people born between the given dates
	 * if the dates are not given in order, the method order them
	 * @param d1 the first date, should be less than d2
	 * @param d2 the second date, should be more than d1
	 */
	public void sortByAge(int d1, int d2) {
		ArrayList<Person> print=new ArrayList<Person>();
		String date="",prnt="\u001B[33m"+"The people born between "+ "\u001B[32m"+d1+ "\u001B[33m"+" and "+ "\u001B[32m"+d2+ "\u001B[33m"+" are:"+ "\u001B[0m \n";
		Integer num=-1;
		Person au=null;
		if(d1>d2) {//change the values if they are unordered
			int aux=d1;
			d1=d2;
			d2=aux;
		}
		for(Iterator<Person> it=list.iterator();it.hasNext();) {//it is planned so the two outing conditions will be activated at the same time but we do this just in case
			au=it.next();
			date=au.getPersonData()[3].split("-")[2];
			num=Integer.parseInt(date);
			if(num.intValue()>=d1 && num.intValue()<=d2) {
				print.add(au);
			}
		}
		int i=print.size();
		if(i>0) {
			Collections.sort(print,BSNComparator);
			for(int j=0;j<i;j++) {
				prnt=prnt+print.get(j).toString()+"\n";
			}
		}else {
			prnt=prnt+"\u001B[33m"+"There are no people born between the given dates"+"\u001B[30m";
		}
		System.out.println(prnt);		
	}
	/**
     * This method prints all the friends of a user with a given surname.
     * If thereÂ´s no users with the given surname nothing is printed
     * @param surname    the surname to search
     */
    public void searchFriendsBySurname(String surname) {
        Person au=null;
        int i=0;
        for(Iterator<Person> it=list.iterator(); it.hasNext();) {
            au=it.next();
            if(au.getPersonData()[2].equals(surname)) {
                i++;
                System.out.println("These are the friends of " + au.getPersonData()[0] + " (" + au.getPersonData()[1] + " " + au.getPersonData()[2] + ")");
                au.printFriends();
            }
        }
        if(i==0) {
            System.out.println("There isn´t any user with the surname " + surname + ".");
        }
    }
	/**
	 * This method prints in console given a city all the people from the social network where born in that city
	 * @param city, city that the user inserts
	 */
	public void retrieveByCity(String city) {
		Iterator<Person> it= list.iterator();
		String prin="";
		int cn=0;
		while(it.hasNext()) {
			Person per=it.next();
			if(city.equals(per.getPersonData()[5])) {
				prin=prin+"\u001B[33m"+"-------------------------------- "+"\u001B[0m"+" \n"
					+"\u001B[27m"+"Id: "+per.getPersonData()[0]+"\u001B[0m \n"
					+"\u001B[27m"+"Surname: "+per.getPersonData()[2]+"\u001B[0m \n"
					+"\u001B[33m"+"-------------------------------- "+"\u001B[0m"+" \n";
				cn++;
			}
			
		}
		if(cn==0){
			System.out.println("There are no people born in "+city+".");
		}else{
			System.out.println(prin);
		}
	}
	
	/**
	 * given a filename retrieves all the home places of the people of the file
	 * @param filename :the name of the file
	 */
	public void residentialRecovery(String filename) {
		Person p=null;
		String hometown="";
		try {
			LinkedBinarySearchTree<String> hometowns=new LinkedBinarySearchTree<String>();
			Scanner sc= new Scanner(new File(filename));
			while(sc.hasNext()) {
				try {
					p=findPerson(sc.nextLine());
					hometown=p.getPersonData()[6];
					if(!hometowns.contains(hometown)) {
						hometowns.addElement(hometown);
					}
				} catch (ElementNotFoundException e) {
					System.out.println("\n \u001B[31m"+"no such person found registered in the network"+"\u001B[0m \n");
				}
				
			}					
			list.printResidential(hometowns);
		} catch (FileNotFoundException e) {
			System.out.println("\n \u001B[31m"+"txt not found"+"\u001B[0m \n");
		}
		
	}
	
	/**
	 * method that retrieves all the people into classes depending on their favourite movies
	 * @return the table of the classes
	 */
	public TableWithCollision<String, Person> retrieveIntoClassesFromMovies(){
		TableWithCollision<String, Person> reta=new TableWithCollision<String, Person>();
		for(Person p:list) {
			reta.put(p.getPersonData()[9],p);
		}
		return reta;
	}
	
	/**
	 * Method that retrieves the shortest chain of friends between 2 people
	 * @param from person 1
	 * @param to person 2
	 */
	public void retrieveShortestChain(Person from,Person to) {
		PersonForGraph[] valu;
		if(changed) {
			valu=list.toValueArray();
			theGraph=new PersonGraph(valu);
			changed=false;
		}else {
			valu=theGraph.getValues();
		}
		int s=-1,v=-1,i=0;
		while(i<valu.length&&(s==-1||v==-1)) {//find the vertices that represent the persons in the graph
			if(valu[i].thePerson.equals(from)) {
				s=i;
			}
			if(valu[i].thePerson.equals(to)) {
				v=i;
			}
			i++;
		}
		if(s!=-1 && v!=-1) {
			BreadthFirstPathsFriendships traverser=new BreadthFirstPathsFriendships(theGraph, s);
			String print;
			if(traverser.hasPathTo(v)) {
				String card="st";
				i=1;
				Stack<Person> path=traverser.pathTo(v);
				print="\u001B[33m"+"The shortest chain between "+"\u001B[36m"+from.getPersonData()[0]+"\u001B[33m"+" and "+"\u001B[36m"+to.getPersonData()[0]+" is:\n \n";
				Person poped;
				while(!path.isEmpty()) {
					if(i==2)		card="nd";
					else if(i==3)	card="rd";
					else if(i==4)	card="th";
					poped=path.pop();
					print=print+"\u001B[33m"+"-----------"+"\u001B[32m"+i+card+"\u001B[33m"+"---------- "+"\u001B[0m"+" \n"+
							"\u001B[36m"+"Id: "+poped.getPersonData()[0]+"\u001B[0m"+" \n"+
							"\u001B[36m"+"Name: "+poped.getPersonData()[1]      +"\u001B[0m"+" \n"+
			                "\u001B[36m"+"Surname: "+poped.getPersonData()[2]   +"\u001B[0m"+" \n";
					i++;	
				}
			}else {
				print="\u001B[31m"+"There is no chain of friends between "+"\u001B[36m"+from.getPersonData()[0]+"\u001B[31m"+" and "+"\u001B[36m"+to.getPersonData()[0]+"\u001B[0m";
			}
			System.out.println(print);
		}else {
			System.out.println("\n \u001B[31m"+"One of the elements has not been found"+"\u001B[0m \n");
		}
	}
	
	/**
	 * Method that retrieves the longest chain of friend between 2 people
	 * @param from person 1
	 * @param to person 2
	 */
	public void retrieveLongestChain(Person from,Person to) {
		PersonForGraph[] valu;
		if(changed) {
			valu=list.toValueArray();
			theGraph=new PersonGraph(valu);
			changed=false;
		}else {
			valu=theGraph.getValues();
		}
		int s=-1,v=-1,i=0;
		while(i<valu.length&&(s==-1||v==-1)) {//find the vertices that represent the persons in the graph
			if(valu[i].thePerson.equals(from)) {
				s=i;
			}
			if(valu[i].thePerson.equals(to)) {
				v=i;
			}
			i++;
		}
		if(s!=-1 && v!=-1) {
			boolean[] cp=new boolean[theGraph.V()];
			int[] et=new int[theGraph.V()];
			LongPathFinder.BackTrack(theGraph, 0, s, v, cp, et);
			Stack<Person> path=LongPathFinder.getSolution();
			String print;
			if(path!=null) {
				String card="st";
				i=1;
				print="\u001B[33m"+"The longest chain between "+"\u001B[36m"+from.getPersonData()[0]+"\u001B[33m"+" and "+"\u001B[36m"+to.getPersonData()[0]+" is:\n \n";
				Person poped;
				while(!path.isEmpty()) {
					if(i==2)		card="nd";
					else if(i==3)	card="rd";
					else if(i==4)	card="th";
					poped=path.pop();
					print=print+"\u001B[33m"+"-----------"+"\u001B[32m"+i+card+"\u001B[33m"+"---------- "+"\u001B[0m"+" \n"+
							"\u001B[36m"+"Id: "+poped.getPersonData()[0]+"\u001B[0m"+" \n"+
							"\u001B[36m"+"Name: "+poped.getPersonData()[1]      +"\u001B[0m"+" \n"+
			                "\u001B[36m"+"Surname: "+poped.getPersonData()[2]   +"\u001B[0m"+" \n";
					i++;	
				}
			}else {
				print="\u001B[31m"+"There is no chain of friends between "+"\u001B[36m"+from.getPersonData()[0]+"\u001B[31m"+" and "+"\u001B[36m"+to.getPersonData()[0]+"\u001B[0m";
			}
			System.out.println(print);
		}else {
			System.out.println("\n \u001B[31m"+"One of the elements has not been found"+"\u001B[0m \n");
		}
	}
	
	/**
	 * method mainly used at Junit5 tests, destroys the instance of the class
	 */
	public static void destroy() {
		instance=null;
	}
	
}
