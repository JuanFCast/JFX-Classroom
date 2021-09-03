package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Classroom {
private ArrayList<UserAccount> accounts;
	
	public Classroom() {
		setAccounts(new ArrayList<UserAccount>());
	}

	public ArrayList<UserAccount> getAccounts() {
		return accounts;
	}

	public void setAccounts(ArrayList<UserAccount> accounts) {
		this.accounts = accounts;
	}
	
	public void createAccount(String userName, String password, String profilePhoto, String gender, String career, LocalDate birthday, String browser) {
		UserAccount userAcc= new UserAccount(userName, password, profilePhoto, gender, career, birthday, browser);
		accounts.add(userAcc);
		
	}
		
	public UserAccount searchAccount(String userName, String password) {
		boolean found=false;
		UserAccount ua=null;
		for(int i=0; i<accounts.size() && !found;i++ ) {
			if(accounts.get(i).getUserName().equals(userName) && accounts.get(i).getPassword().equals(password)) {
				ua=accounts.get(i);
				found=true;						
			}
		}
		return ua;
	}
	
	public boolean findAccount(String userName, String password) {
		UserAccount ua=searchAccount(userName, password);
		boolean found=false;
		if(ua!=null) {
			found=true;
		}
						
		return found;
	}
	
	public String profilePhotoResource(String userName, String password) {
		UserAccount ua=searchAccount(userName, password);
		
		return ua.getProfilePhoto();
	}

}
