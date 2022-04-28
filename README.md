# Sunday League Management Web App
Sunday League Management was made in semester 3 of the Software Enginnering specialisation at Fontys ICT. </br>

# Intro

Web App developed with Angular(frontend), Java Spring Boot(backend) and MySQL(database).

## Description

Sunday League Management is a website that manages a sports league(mainly football).

1. What is it about?
 - Made to make sports league management easy and accessible to every participant
 - It has 4 different kinds of users: players, team managers, referees and admins
 - Players have the ability to view their statistics, upcoming matches, past matches and the league table
 - Team managers can add unasigned players to their team and also view team statistics and all matches
 - Referees can input scores for the matches they are assigned to and can change scores if the match hasn't finished yet(interupted or postponed)
 - Admins can manage all users and all matches
2. Why Angular and Spring Boot?
 - For frontend I was requested to develop the web app in Angular or React and I choose Angular mainly beacuse after doing research it seemed like the better framework to use for bigger projects and also that Angular makes scalling easier than React
 - Spring Boot was the requested framework by the teachers to develop this certain project
 - The combination between Angular for frontend and Spring Boot for backend is one of the most popular ones in big IT companies, making it easy to scale up both frontend and backend and having access to a large community of experienced users
3. Challenges faced?
 - The biggest challenge faced was working on this individual project and the Ivanti industry project at the same time. So balancing the work between the 2 was difficult and at times I had to push through and motivate myself to be able to reach all deadlines with the promised features.

### How to use the project

1. On every visit to the website the user is greeted with the home page and a short guide explaining the possibilities of the site</br> </br>
 ![homepage](https://user-images.githubusercontent.com/76628104/165819491-74ffe628-a4ef-4979-a831-d8ceca3fface.png)
 </br> </br>
2. First time users need to create an account </br>  </br>
 ![signup](https://user-images.githubusercontent.com/76628104/165819622-2872425d-1e2b-4429-8626-84ae3a06c80c.PNG)
 </br> </br>
3. And the password is sent to their email account </br> </br>
![password_Email](https://user-images.githubusercontent.com/76628104/165819716-0c3121bb-3031-4a56-a503-71a4b9a2cc97.PNG)
</br> </br>
4. <b>The Admin</b> can manage all the users(add, delete, update, reset passwords) </br> </br>
![adminusermanagement](https://user-images.githubusercontent.com/76628104/165819838-b1c14461-84db-4072-8893-6a7b878140ee.png)
![adminusercreate](https://user-images.githubusercontent.com/76628104/165820046-38584b7b-6564-4430-ae5d-8ff7944d0539.png)
</br> </br>
5. <b>The Admin</b> can also manage all the matches(CRUD) </br> </br>
![admin_matchmanagement](https://user-images.githubusercontent.com/76628104/165820151-da5a0756-deaf-456c-8437-4b991a83b710.png)
![admin_creatematch](https://user-images.githubusercontent.com/76628104/165820171-39793da1-e188-4fd1-b529-87a8c7ef4fbf.png)
</br> </br>
6. <b>The Referee</b> can see all the matches that are assigned to him and he can input the results from said matches. He can also view all the matches he had already refereed and that are finished</br> </br>
![referee_upcomingmatches](https://user-images.githubusercontent.com/76628104/165820406-175c87a1-05c8-4217-b500-68baddb1d17f.png)
![referee_finishedmatches](https://user-images.githubusercontent.com/76628104/165820419-4fd7185e-106e-4f7d-9787-c60bac4b3cb3.png)
</br> </br>
7. <b>The Team Manager</b> can create a team if he doesn't have one already. Then he can add available players which don't have a team yet and afterwords he can remove the if he wishes</br> </br>
![teammanager_noteam](https://user-images.githubusercontent.com/76628104/165820618-5fac62c7-e7f4-4725-8f73-a27df63fbafe.png)
![teammanager_addplayer](https://user-images.githubusercontent.com/76628104/165820627-92ff144f-1dfa-4e7f-a7cf-fc8133ad771b.png)
![teammanager_team](https://user-images.githubusercontent.com/76628104/165820633-50b15785-f0ca-4c4b-9c62-38ea0b93d2ad.png)
</br> </br>
8. All users have access to their profile pages where they can change basic info
![profilepage](https://user-images.githubusercontent.com/76628104/165820933-e40ad110-8835-45dc-9047-31ea34aacf17.png)
</br> </br>
9. All users also have access to the chat room where they can talk to the football community
![chat](https://user-images.githubusercontent.com/76628104/165821060-715d2f6b-c9aa-47ae-bcce-e4b6159bf1e4.PNG)
</br> </br>

### How to run the project

1. Install Java Spring Boot
2. Install Angular CLI
3. Install MySQL
4. Open the football_backend folder in IntelliJ and run the code from there
5. Open the football-league-app folder in a text editor and run it with ng serve command
