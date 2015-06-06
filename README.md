# Class-Projects
Completed class projects for Java programming language

-Note: These projects are provided as learning material from my class studies at university and are not to be distributed otherwise

#Cash Register
Simulates a cash register in a book store with the options to purchase and refund items. Test cases provided for testing this programs methods. 

#Movie Inventory Project
Simple project representing a movie listing directory. User entered values are added and removed to the directory based on select use cases

#Project 1 (Airport Customs Simulator)
This project is meant to focuses on the relationships between classes (inheritance, aggregation, composition, etc.) as well as the concepts of encapsulation and decoupling. This project adheres to the MVC (Model-View-Controller) pattern
###Description 
This project represents an airport simulator where the user can enter the number of customs desk and passengers they would like in the simulation. The user can also control the animation speed. The simulation proceeds and shows the average wait/process time for a passenger once the simulation is complete.

*JUnit test cases are provided for testing various methods within the program*

####Usage
1. Run the program using the SimulationViewer.java class
2. Enter the number of customs desks (between 3-17)
3. Enter the number of passengers for the simulation 
4. Select animation speed using slider
5. Run animation by clicking start


#Project 2 (AndroTech Repair Center)
This project is meant to focus on LinkedLists and ArrayLists, which we implement/construct on our own within the project. This project also showcases file I/O by implementing methods that allow for a user selected file to be read when the program is executed. Exceptions classes as well as search algorithms are covered within this project. Lastly this project adheres to the MVC (Model-View-Controller) pattern

###Description 
This project represents an AndroTech repair center where VR, Com, and Expert droids are used to repair VR and Com devices.
The user can use and selected file to load devices into the list within the program or manually enter droids and devices into the program list. User options include assign a device to a tech droids for repair, add a new device/tech droid, and enter a string to search for a specific device within the device list. 

*JUnit test cases are provided for testing various methods within the program*

####Usage
1. Run the program using the AndroTechGUI.java class
2. Use dialog box to select a file containing device information or click cancel if no file is present
3. Depends on presence of file
  * If a file is present, it is traversed and the devices are added to the correct list. The tech droid list starts with 5 specific droids
  * If no file is present and empty device list is created and the tech droids is created with 5 specific droids 
4. User decides which options to select from GUI
5. To exit program click the "Quit" button along the bottom right of the GUI or the red "X" button along the top right of the GUI
 
#Project 3 (Car Rental Center  )
This project is meant to focus on Stacks and Queues which we implement/construct on our own within the project. This project also showcases file I/O by implementing methods that allow for a user selected file to be read when the program is executed, but also for the data to be saved when the user exits the program. While this program follows the MVC pattern, it also adheres to the state design pattern (using a FSM); this project also illustrates specific UI creation/interaction through the GUI classes 

###Description 
This project represents a car rental system where customers rent a car and go through different states of a car rental by following a Finite-State pattern. When starting the program the user is given the option to load a previous inventory file containing cars in different rental states (i.e. Rented, Out for Repair, Available, etc.), if no file is selected the user can manually add customers and cars to the inventory. Other user options include renting a car, returning a  car (with or w/out a problem), repairing a car, and detailing a car. Upon exiting the program the user is given the option to save the current inventory to a specified file and location. 

*JUnit test cases are provided for testing various methods within the program*

The input and output file is read and saved using the following format/pattern: 

####Usage
1. Run the program using the CarRentalGUI.java class
2. Use dialog box to select a file containing car inventory information or click cancel if no file is present
3. Depending on the presence of file:
  * If a file is present, it is traversed and the cars are added to the correct list. 
  * If no file is present an empty car list is created
4. User then decide which options to select from GUI (i.e. adding a customer, adding a car, renting a car, etc.)
5. To exit program click the red "X" button along the top right of the GUI
6. Upon exit, the user will be prompted to save the entire inventory list currently in the car rental program to a file/location of their choosing.
