# HR Tool
In this tool there is a two types of users:

##### Employee
Responsibilties: 
- Can only view his own data and the company benifts (offers) and search for an offer.
##### HR Admin
Responsiblities:
- Add/update/delete/view users (Employee, HR Admin)
- Add/update/delete/view company offers.

#### The Used Technologies:
- Spring MVC
- Spring Scurity
- Hibernate
- Apached Derby as light weight embedded DB
- Bootstrap CSS
- Maven

#### Installation
- Export the war using: mvn package
- Deploy the war in any Application server
- No DB scripts needed as the DB tables will be created automatically

### Simple Journey in the app:

- You can login using the intial created user (Admin/admin)
![alt text](https://github.com/AhNady/My-Projects/blob/master/Login.PNG)

- After login you will be directed to the Main page where you can view your data and also can edit in it and as you are admin you will be able to view the employees data as shown below 
![alt text](https://github.com/AhNady/My-Projects/blob/master/AdminMainPage.PNG)

- You can now view all the employees data including you and you can delete any one but you cannot delete yourself
![alt text](https://github.com/AhNady/My-Projects/blob/master/EmployeesList.PNG)

- You can also edit any employee data
![alt text](https://github.com/AhNady/My-Projects/blob/master/EditEmployeeData.PNG)

- You can view all the company offers also you can delete the offer if you have HRADMIN Role as shown below
![alt text](https://github.com/AhNady/My-Projects/blob/master/OfferPage.PNG)

- If you have Role HRADMIN you can edit in the offer
![alt text](https://github.com/AhNady/My-Projects/blob/master/EditOffer.PNG)

- Also you can add a new offer if you have Role HRADMIN
![alt text](https://github.com/AhNady/My-Projects/blob/master/AddNewOffer.PNG)


