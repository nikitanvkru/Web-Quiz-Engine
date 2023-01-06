# Web service for solving and creating quizzes
##  Application description
Web service should be able to create new quiz from given json, also response back with some given information.
Responsible http method post "api/quizzes"
<image
  src="images/postman1.jpg"
  alt="Post quiz"
  caption="Post new quiz>
<image
  src="images/postman2.jpg"
  alt="Posted quiz"
  caption="Posted quiz">
Web service schould return all existing quizzes
Responsible http method get "api/quizzes"
<image
  src="images/postman3.jpg"
  alt="Quizzes list"
  caption="Returned quizzes list">
Web service schould return quiz with requested id
Responsible http method get "api/quizzes/{id}"
<image
  src="images/postman4.jpg"
  alt="Quiz"
  caption="Quiz with given id">
Web service schould solve quiz with requested id and params
Responsible http method post "api/quizzes/{id}/solve"
<image
  src="images/postman5.jpg"
  alt="Answer"
  caption="Answer with description">
Also after server restarts all quizzes should remain accessible, for that reason we store all data in postgres db.
<image
  src="images/postgres.jpg"
  alt="Screen db"
  caption="Stored data">
