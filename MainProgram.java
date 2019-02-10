import java.io.*;
class MainProgram
{
	public static int choice = 0;
	public static void main(String[] args) throws IOException
	{
		UserMediator userMediator = new UserMediator();
		getUserFactory factory = new getUserFactory();

		do
		{
			System.out.println("------------------------------------------------");
			System.out.println("USER ADMIN PORTAL");
			System.out.println("1. Add user");
			System.out.println("2. Modify user");
			System.out.println("3. Delete user");
			System.out.println("4. Display user");
			System.out.println("5. Exit");
			System.out.println("Enter your choice :");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			choice=Integer.parseInt(br.readLine());
			System.out.println("------------------------------------------------");

			
			switch(choice)
			{
				case 1: 
					{	
						System.out.println("Enter user type(premium/normal) : ");	
						BufferedReader br2 = new BufferedReader(new InputStreamReader(System.in));
						String type =br2.readLine();
						User u = factory.getUser(type);
						userMediator.addUser(u);
												
					}
					break;
				case 2: 
					{
						userMediator.modifyUser();
					}
					break;

   				case 3: 
					{
						userMediator.deleteUser();
						
					}
					break;
				case 4: 
					{
						userMediator.displayUser();
					}
					break;
				case 5:
					{
						System.out.println("BYE!!!");					
					}
					break;
				default:
					{
						System.out.println("INVALID CHOICE!!!");		
					}
					break;
			}
			
		}while(choice!=5);
	}
}

interface userFunctions
{	
	public void modifyUser()throws IOException;
	public void displayUser()throws IOException;
} 


class getUserFactory{  
      
       public User getUser(String userType) throws IOException
	{  
            if(userType == null)
	    {  
             return null;  
            }  
            if(userType.equalsIgnoreCase("PREMIUM")) 
	    {  
                 return new premiumUser();  
             }   
            else if(userType.equalsIgnoreCase("NORMAL"))
	    {  
                return new normalUser();  
            }   
           
            return null;  
     }  
} 
abstract class User implements userFunctions
{
	public String name = "";
	public String username = "";
	public String password = "";
	public long phoneNo;
	public int userID;
	public static int ID = 1;
	public String userType = "";

        public void modifyUser()throws IOException
	{
		System.out.println("Modify User Name to : ");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		name =br.readLine();

		System.out.println("Modify User Phone No to : " );
		BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
		phoneNo =Long.parseLong(br1.readLine());

		System.out.println("Modify User Password to : ");
		BufferedReader br2 = new BufferedReader(new InputStreamReader(System.in));
		password =br2.readLine();

	}

	public void displayUser()throws IOException
	{
		System.out.println();
		System.out.println("User Name : " + name);
		System.out.println("User UserName : " + username);
		System.out.println("User Phone No  : " + phoneNo);
		System.out.println("User ID : " + userID);
		System.out.println("User Type : " + userType);
		System.out.println("User Password : " + password);
		System.out.println();

	}
}
class premiumUser extends User
{
  	public premiumUser()throws IOException
	{
		System.out.println("Enter User Name : ");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		name =br.readLine();

		System.out.println("Enter User UserName : ");
		BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
		username =br1.readLine();

		System.out.println("Enter User Password : ");
		BufferedReader br2 = new BufferedReader(new InputStreamReader(System.in));
		password =br2.readLine();

		System.out.println("Enter User Phone No : " );
		BufferedReader br3 = new BufferedReader(new InputStreamReader(System.in));
		phoneNo =Long.parseLong(br3.readLine());
		
		userID = ID;
		ID+=1;
		this.userType = "Premium User";
	}
	
	
}
class normalUser extends User
{
  	public normalUser()throws IOException
	{
		System.out.println("Enter User Name : ");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		name =br.readLine();

		System.out.println("Enter User UserName : ");
		BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
		username =br1.readLine();

		System.out.println("Enter User Password : ");
		BufferedReader br2 = new BufferedReader(new InputStreamReader(System.in));
		password =br2.readLine();

		System.out.println("Enter User Phone No : " );
		BufferedReader br3 = new BufferedReader(new InputStreamReader(System.in));
		phoneNo =Long.parseLong(br3.readLine());
		
		userID = ID;
		ID+=1;
		this.userType = "Normal User";
	}

	
}


class UserMediator
{
		public static User[] arr = new User[1000];
		public static int i = 0;
		public void addUser(User u) throws IOException
		{
			if(i!=0)
			{

			for(int k = 0;k <= i;k++)
			{
				if(u.username.equals(arr[k].username))
				{
					System.out.println("THIS USERNAME IS ALREADY OCCUPIED!!ENTER CREDENTIALS AGAIN!!");
					return;
				}			
			}
			}
			arr[i] = u;
			i+=1;
			System.out.println();
			System.out.println("THE USER YOU ADDED IS:");
			u.displayUser();
			System.out.println();
		}
		public void modifyUser()throws IOException
		{
			if(i==0)
			{
				System.out.println("NO USERS PRESENT");
				return;
			}
			int flag = 0;
			User u = loginUser();
			if(u != null)
			{	
				u.modifyUser();
			}
			else
			{
				System.out.println();
				System.out.println("USER NOT FOUND");
				System.out.println();			
			}
		}
		public void displayUser()throws IOException
		{
			if(i==0)
			{
				System.out.println("NO USERS PRESENT");
				return;
			}
			User u = loginUser();
			if(u != null)
			{	
				u.displayUser();
			}
			else
			{
				System.out.println();
				System.out.println("USER NOT FOUND");
				System.out.println();			
			}
			
				
		}
		public void deleteUser()throws IOException
		{
			if(i==0)
			{
				System.out.println("NO USERS PRESENT");
				return;
			}
			int flag = 1;
			User u = loginUser();
			if(u != null)
			{	
				u.displayUser();
			}

			try{
				for(int k = 0; k <= i;k++)
				{
					if(arr[k].userID == u.userID)
					{
						flag = 0;
						for(int l = k;l <= i-1; l++ )
						{
							arr[l] = arr[l + 1];
						}
						i-=1;		
						System.out.println("USER SUCCESSFULLY DELETED!!");		
						break;
					}	
				}
			}catch(Exception e)
			{}
			if (flag == 1)
			{
				System.out.println("THE GIVEN USER IS NOT IN THE DATABASE");
			}
		}
		public User loginUser()throws IOException
		{
			int flag = 1;
			System.out.println();
			System.out.println("Enter the username and password of the user : ");
			System.out.print("USERNAME : ");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String username = br.readLine();
			System.out.print("PASSWORD : ");
			BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
			String password = br1.readLine();
			try{
				for(int k = 0; k <= i;k++)
				{
					if( arr[k].username.equals(username) && arr[k].password.equals(password) )
					{
						flag = 0;
						return arr[k];
					}	
				}
			}catch(Exception e)
			{}
			if(flag == 1)
			{
				System.out.println("INVALID CREDENTIALS !!");
			}
			return null;
		}
		
		
}

