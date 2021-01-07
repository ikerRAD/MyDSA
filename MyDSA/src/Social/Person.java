package Social;

//import structures.BinarySearchFriends;
import structures.BinarySearchID;
import structures.LinkedList;

import java.util.Arrays;

//import java.util.Iterator;

import Exceptions.AlreadyAddedFriend;
import Exceptions.ElementNotFoundException;
import Exceptions.EmptyCollectionException;


/**
 * Class that represents all the people of a social media
 * @author ikerb
 *
 */

public class Person implements Comparable<Person>{
		/**
		 * parameter that has all the important data of any person
		 * index:
		 * 0-id
		 * 1-name
		 * 2-surname(s)
		 * 3-bday
		 * 4-gender
		 * 5-bplce
		 * 6-home
		 * 7-studied
		 * 8-work
		 * 9-films
		 * 10-group
		 */
		private String[] personData;
		/**
		 * friend list
		 */
		protected BinarySearchID friendList;
		
		/**
		 * constructor of the class, makes sure that no field is null
		 * @param the data of the person
		 */
		
		public Person(String[] p) {
            String[] pe=new String[11];
            if(p!=null) {
                for(int i=0;i<pe.length;i++) {
                    if(i<p.length) {
                        pe[i]=p[i];
                    }else {
                        pe[i]="";
                    }
                }
                if( pe[9]!="") {
                	pe[9]=sortString(pe[9]);
                }
            }else {
            	for(int i=0;i<pe.length;i++) {
            		pe[i]="";
            	}
            }
            
            this.personData = pe;
            friendList=new BinarySearchID();
        }

		/**
		 * @return the personData
		 */
		public String[] getPersonData() {
			return personData;
		}

	

		/**
		 * @param idPreson the idPreson to set
		 */
		public void setIdPreson(String idPreson) {
			personData[0] = idPreson;
		}


		/**
		 * @param name the name to set
		 */
		public void setName(String name) {
			personData[1] = name;
		}

	

		/**
		 * @param lastName the lastName to set
		 */
		public void setLastName(String lastName) {
			personData[2] = lastName;
		}

	

		/**
		 * @param birthDate the birthDate to set
		 */
		public void setBirthDate(String birthDate) {
			personData[3] = birthDate;
		}


		/**
		 * @param gender the gender to set
		 */
		public void setGender(String gender) {
			personData[4] = gender;
		}

	

		/**
		 * @param birdPlace the birdPlace to set
		 */
		public void setBirthPlace(String birthPlace) {
			personData[5] = birthPlace;
		}


		/**
		 * @param home the home to set
		 */
		public void setHome(String home) {
			personData[6]= home;
		}


		/**
		 * @param studiedAt the studiedAt to set
		 */
		public void setStudiedAt(String studiedAt) {
			personData[7] = studiedAt;
		}

	

		/**
		 * @param workPlaces the workPlaces to set
		 */
		public void setWorkPlaces(String workPlaces) {
			personData[8] = workPlaces;
		}


		/**
		 * @param films the films to set
		 */
		public void setFilms(String films) {
			personData[9] = films;
		}

		

		/**
		 * @param groupCode the groupCode to set
		 */
		public void setGroupCode(String groupCode) {
			personData[10] = groupCode;
		}

	

		/**
		 * @return the numFriends
		 */
		public int getNumFriends() {
			return friendList.size();
		}
		/**
		 * Method that removes a friend of type person. It큦 reciprocal
		 * @param the friend to remove
		 */
		public void removeFriend(Person p) {
			try {
				
				p.friendList.remove(this);
				this.friendList.remove(p);
				SocialList.changed=true;
			} catch (EmptyCollectionException | ElementNotFoundException e) {
				System.out.println("\n "+"\u001B[31m"+"friend already removed or has never existedd"+"\u001B[0m \n");
			}

			
		}
		/**
		 * reciprocal method to add friends
		 * @param f
		 * @throws AlreadyAddedFriend
		 */
		public void addFriend(Person f) throws AlreadyAddedFriend{
			if(f.isFriend(this)) throw new AlreadyAddedFriend();
			//Person p1,p2;
			//p1=p2=null;
			this.friendList.add(f);
			f.friendList.add(this);
			SocialList.changed=true;
			
		}
		/**
		 * Method to add a film to the film list
		 * @param new film큦 name
		 */
		public void addFilm(String f) {
			String movies=personData[9];
			if(movies=="") {
				personData[9]=f;
				
			}else  {
	          	personData[9]=sortString(getPersonData()[9]+";"+f);
			}
            
		}
		/**
		 * Method to add a new work to the work list
		 * @param the new work큦 name
		 */
		public void addWork(String f) {
			if(personData[8]!="") {
				setWorkPlaces((getPersonData()[8])+";"+f);
			}else {
				personData[8]=f;
			}
		}
		/**
		 * Overridden toString method
		 */
		@Override	
		public String toString() {
			String r="\u001B[33m"+"-------------------------------- "+"\u001B[0m"+" \n"
					+"\u001B[36m"+ " Id: "+getPersonData()[0];
			if(!getPersonData()[1].equals(""))	
				r=r+"\n Name: "+getPersonData()[1];
			if(!getPersonData()[2].equals(""))
				r=r+"\n Last name: "+getPersonData()[2];
			if(!getPersonData()[3].equals(""))
				r=r+"\n Birthday: "+getPersonData()[3];
			if(!getPersonData()[4].equals(""))
				r=r+"\n Gender: "+getPersonData()[4];
			if(!getPersonData()[5].equals(""))
				r=r+"\n Birth place: "+getPersonData()[5];
			if(!getPersonData()[6].equals(""))
				r=r+"\n Home: "+getPersonData()[6];
			if(!getPersonData()[7].equals(""))
				r=r+"\n Has studied at: "+getPersonData()[7];
			if(!getPersonData()[8].equals(""))
				r=r+"\n Worked at: "+getPersonData()[8];
			if(!getPersonData()[9].equals(""))
				r=r+"\n Favourite films: "+getPersonData()[9];
				
			if(!getPersonData()[10].equals(""))
				r=r+"\n Group code: "+getPersonData()[10];
			
			r=r+"\n Has "+getNumFriends()+" friends";
				r=r+"\u001B[0m"+"\n"+"\u001B[33m"+"--------------------------------"+"\u001B[0m";
			return r;
			
		}
		/**
		 * To string method to print the object in a .txt
		 * @return the object transformed into a string
		 */
		public String toStringTXT() {
			return getPersonData()[0]+","+getPersonData()[1]+","+getPersonData()[2]+","+getPersonData()[3]+","+getPersonData()[4]+","+
					getPersonData()[5]+","+getPersonData()[6]+","+getPersonData()[7]+","+getPersonData()[8]+","+getPersonData()[9]+
					","+getPersonData()[10]+"\n";
		}
		/**
		 * Returns true if they are the same person, this means that they have the same id 
		 */
		@Override
		public boolean equals(Object e) {
			boolean res=false;
			if(this==e) {res=true;}
			else
				if(e!=null) {
					if(getClass()==e.getClass()) {
						Person per=(Person)e;
						res=(per.getPersonData()[0].equals(this.getPersonData()[0]));
					}
				}
			
			return res;
		}
		/**
		 * Prints all the friends of this person
		 */
		public void printFriends() {
			String pr="Friends:\n";
			String lone="";
			if(!friendList.isEmpty()) {
				for(Person p:friendList) {
					pr=pr+p.toString()+"\n";
				}
				lone=" more";
			}
			System.out.println(pr+"There are no"+lone+" friends...");
		}
		
		/**
		 * Prints all the friends of this person in order of fame
		 */
		public void printFamousFriends() {
			String pr="Friends:\n";
			String lone="";
			if(!friendList.isEmpty()) {
				LinkedList<Person> friendFameList=friendList.toFameList();
				for(Person p:friendFameList) {
					pr=pr+p.toString()+"\n";
				}
				lone=" more";
			}
			System.out.println(pr+"There are no"+lone+" friends...");
		}
		
		/**
		 * Prints all the friends큦 id's of this person
		 */
		public void printFriendsNames() {
			String pr="Friends:\n";
			String lone="";
			if(!friendList.isEmpty()) {
				for(Person p:friendList) {
					pr=pr+"\u001B[36m"+p.getPersonData()[0]+"\u001B[0m \n";
				}
				lone=" more";
			}
			System.out.println(pr+"There are no"+lone+" friends...");
		}
		
		/**
		 * Prints all the friends큦 id's of this person
		 */
		public void printFamousFriendsNames() {
			String pr="Friends:\n";
			String lone="";
			if(!friendList.isEmpty()) {
				LinkedList<Person> friendFameList=friendList.toFameList();
				for(Person p:friendFameList) {
					pr=pr+"\u001B[36m"+p.getPersonData()[0]+"\u001B[0m \n";
				}
				lone=" more";
			}
			System.out.println(pr+"There are no"+lone+" friends...");
		}
		
		/**
		 * Method to know if a person object id in friend list
		 * @param The person we will check
		 * @return true if the person is in the list, false if not
		 */
		public boolean isFriend(Person p) {
			
			return friendList.contains(p);
		}
		/**
		 * Overridden compareTo method from comparable interface
		 */
		@Override
		public int compareTo(Person o) {
			
			return this.getPersonData()[0].compareTo(o.getPersonData()[0]);
		}
		public int compareToByFriends(Person o) {
			
			return this.getNumFriends()-o.getNumFriends();
		}
		/**
         * Method to sort a string 
         * @param re
         * @return
         */
        public String sortString(String re) {    
            String[] mi= re.split(";");
            Arrays.sort(mi);
            String fa=mi[0];
            for(int i=1; i<mi.length; i++) {
            	fa=fa + ";" + mi[i];
            }
            return fa;
        }


		
			
		
		

		
}
