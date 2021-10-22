# MovieTheater

**Problem Statement**

Implement an algorithm for assigning seats within a movie theater to
fulfill reservation requests. Assume the movie theater has the seating
arrangement of 10 rows x 20 seats, as illustrated to the right.
Your homework assignment is to design and write a seat assignment
program to maximize both customer satisfaction and customer
safety. For the purpose of public safety, assume that a buffer of three
seats and/or one row is required.

#Given Input
    - Theater Layout is 10 * 20
    - There are 10 rows i.e A - J . Row 'A' is nearer to the screen.
    - There are 20 seats in each row.
    - Need to maximize customer satisfaction and customer safety
    - Customer Satisfaction
        - Each reservation is not split. (all people in the group are assigned together)
        - Seats are provided based on the reservation priority.
        - Seats are allocated from farther the screen. (i.e starting from J)
    - Customer Safety
        - A buffer of 3 seats between each reservation.

#Assumptions
    - Seating booking is done from starting (i.e 1st position of a row)
    - Maximum seats that can be reserved is total number of seats per row (i.e 20)
    - Reservation is not split. If the seats cannot be allocated for the entire group, reservation is failed.

#Algorithm
    - Greedy algorithm is chosen to allocate seats
    - Create the Theater Layout Initially.
    - Create movieTheaterSeating Class
    - Parse the input file based on the input file name provided by the user.
    - Allocate the seat and update the layout.
    - Write the final reservation into a output file.

#Class Diagram for the Design


    #Data Members :
	  We need layout of theater[HashMap<Character, ArrayList<Integer>>]
		We need to save the input [HashMap<String, Integer>]
		Output [HashMap<String, ArrayList<String>>]
		Number of seats per row : 20
		Number of rows : J-A = 10
		total number of seats = 200

	#Member Functions:
		Constructor : populate the theatre layout
		parseInputFile
		validateInput
		allocateSeats
		getTheBestRow
		updateSeats
		printSeats


