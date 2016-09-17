# projects

This is a simple web app for looking and commenting books made using Java, Spring, AngularJS. 
Point of this app was mostly playing around with spring, testing various options like how 
to set up entities in java so that ORM(hiberante in this case) would make tables in DB 
the way I planed it on paper. I used spring boot(unfortunately) mainly for speed and simplicity.
Hopefully soon I will make similar project using just xml configurations, which take time
but force you to understand a litle better how everything is connected. 
Basic authentication and authorization is used, also one of the thing I plan to improve as 
soon as I can. Most of functionality is located around books, and users which can easily 
be noticed as you go through books chain on controller and services down to repository. 
For queries I relied on jpa's repository and specification interfaces. Found that specification
gave me nice option of easily combinig query conditions that can easily be reused in various methods.
On front-end I used angular. I didn't concern myself with looks, just with some basic functionality
mainly to se how everything works with back-end. Future plans are to look into: 
  -exception handling for spring exceptions
  -implement some testing 
  -improve authentication and authorization ....
