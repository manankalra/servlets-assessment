# servlets-assessment
<br />

![main](https://user-images.githubusercontent.com/12759088/45322292-7ab7f280-b565-11e8-884d-eb49761d5146.PNG)

### This is more like a combined timetable for all the trainers (Trainer 1 - Trainer 5). 

The first page allows you to:
- enter the class name a trainer is going to in a text-field
- select the days on which he's going to conduct classes (there can be multiple days)
- choose the timings for the same (earliest start: 8:00am; min length: 1hr)
- select a trainer from a drop-down
- choose the subjects he can teach
- add slot for the added details in the table shown below

### Code Structure:
- models:
  - Trainer
    - a class that directly corresponds to the Trainer table in the database
    - properties: name (name of the trainer; a string), subjectOne (the first subject he'll teach), and subjectTwo (the second subject he'll
    teach)
    - a trainer can teach a minimum of one subject and a maximum of two subjects
    - has getters and setters
  - Period
    - a class that represents a single block in the time-table visible on the main page.
    - properties: title (name of the class the trainer is going to teach), starttime (the time at which the class starts), endtime (the
     time at which it ends), day (an integer mapped to a day in a week), and a Trainer object (the trainer who'll teach that class in the
      alloted time-slot)
    - has setters and getters
  - Schedule
    - a class that represents a single row in the time-table visible on the main page
    - properties: a list of objects of type Period
    - has a method to add a Period to the list
    
- dao
  - TrainerDAO
    - contains methods to insert, update, list, etc. a trainer in order to populate the corresponding database tables and fetch the same to 
    populate the visible time-table
  - ScheduleDAO
    - contains methods to insert, update, lis, etc. a Period at the correct place in the corresponding Schedule table and the visible time-
    table
    
- controllers
  - ScheduleServlet
    - most important class; runs doPost(.., ..) on clicking *Add Slot*
    - all of the values of the form elements are passed as request parameters
    - starts a session
    - checks if the number of days entered by the user are null
    - if they're not:
      - starts a loop which iterates the whole table to find the right slot according to the parameters passed
      - cretaes a Trainer and a Period object
      - checks if the slot is already booked or not
        - ***as this is a time-table for the trainers, a single slot can be booked for different trainers for different classes only***;
         this is because at the same time two different trainers can teach two different classes
        - ***an error div appears on the page if you try to add the same trainer in the same slot (with a different or a same class) or if
        you try to add a different trainer with the same class***; this is because at the same time a single class cannot be taken care of 
        by multiple trainers and a single trainer cannot be present at different classes on the same time
    - populates the database tables
    - passes the schedule and trainerName as attributes
    - dispatches Schedule.jsp again
    
- util
  - DBConnectionManager
    - a class for initializing and connecting to the database
    - not being used as of now as I've some duplicated code for the same in both TrainerDAO and ScheduleDAO
    
- Schedule.jsp
  - Entry point
  - renders a form and an empty table at first
  - uses JSTL for the same:
    - choose, when, otherwise: for switch-case like functionality
    - forEach: to populate the table according to he attributes passes/values stores
  - on submission: action is */Schedule* and method is *post*
  
### Screenshots/Workflow

- populating the fowm with values/choices
![main2](https://user-images.githubusercontent.com/12759088/45322299-7e4b7980-b565-11e8-85b4-2de8480e8143.PNG)

- description of corresponding tables
![main3](https://user-images.githubusercontent.com/12759088/45322303-80add380-b565-11e8-8b33-66c3b0dfd734.PNG)

- current state of table
![main4](https://user-images.githubusercontent.com/12759088/45322311-83102d80-b565-11e8-9b20-e1ea0a18b079.PNG)

- submitting the form populates the time-table
![main5](https://user-images.githubusercontent.com/12759088/45322314-85728780-b565-11e8-9047-02da4e65da29.PNG)

- it also populates the databse tables which are then used to fetch the time-table for the same session
![main6](https://user-images.githubusercontent.com/12759088/45322316-87d4e180-b565-11e8-9e2e-6fb2ddaa2fee.PNG)

![main7](https://user-images.githubusercontent.com/12759088/45322322-8b686880-b565-11e8-985f-6344647d60d2.PNG)

- adding multiple fields at once (by selecting multiple days and/or selecting a time-span longer than 1 hour); doesn't allow you to 
add a a slot for the same class when it is already occupied (even if it's a different trainer with/without different subjects); doing this 
retains the parameters filled for you to edit and try again for an available slot
![main8](https://user-images.githubusercontent.com/12759088/45336056-83c5b580-b59f-11e8-808b-1ace8be6c2e2.PNG)

### To-do

- if a particular trainer is teaching a subject to a particular class, it should not be possible to add a trainer for the same class with similar subjects
- flexible break-times
