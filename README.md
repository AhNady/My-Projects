# HR Tool
In this tool there is a two types of users:

##### Employee
Responsibilties: 
- Can only view his own data and the company benifts (offers)
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

- After login you will be directed to the Main page and as you are admin you will be able to view the employees data as shown below 
![alt text](https://github.com/AhNady/My-Projects/blob/master/Login.PNG)
