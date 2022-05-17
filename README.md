# tic-tac-toe

Backend : 
Java - 11
Spring boot - 2.6.7
MySql - 8.0.29

How to run backend : 
1. pull the code from repository
2. go to folder backend and open folder game
3. load maven using IDE (i'm use Intellij IDEA)
4. in the application properties file you need do some action, that is you need to put the your local MySql password
5. for the first time to running application you need uncomment #spring.jpa.hibernate.ddl-auto=create
6. after you already run the project you need to uncomment #spring.jpa.hibernate.ddl-auto=create
7. go to Main class (GameApplication.java)
8. click icon play in the left bar

notes :
spring.jpa.hibernate.ddl-auto=create - this is for create automatically table in your database


Frontend : 
Vue - vue/cli 5.0.4
Nuxt Framework
Javascript

How to run frontend : 
1. pull the code from repository
2. go to frontend folder
3. in terminal go to folder tic-tac-toe
4. npm install
5. execute script "npm run dev"

notes : 
if you got the error after npm install and execute npm run dev , might be you need to delete the package-lock.json file and npm install again
