package edu.ncsu.csc216.airport_customs.ui;

import java.util.Scanner;

import edu.ncsu.csc216.airport_customs.simulation.Simulator;
 
/**
 * Simple class to run customs hall simulations. 
 *   Input is from standard input. Output is to standard output.
 *   No error checking is performed. 
 * @author Jo Perry
 */
public class SimulationRunner {
	
	/**
	 * Starts the command line simple simulation.
	 * @param args command line arguments
	 */
	public static void main(String[] args) {		
		Scanner sc = new Scanner(System.in);
		boolean valid = false;
		int numberOfPassengers = 0;
		while(!valid) {
			System.out.print("Number of passengers: ");
			numberOfPassengers = sc.nextInt();
			if (numberOfPassengers >= 1) {
				valid = true;
				break;
			} else {
				System.out.println("There must be at least one passenger.");
			}
		}
		valid = false;
		int numberOfCustomsDesks = 0;
		while(!valid) {
			System.out.print("Number of customs desks: ");
			numberOfCustomsDesks = sc.nextInt();
			if (numberOfCustomsDesks >= 3 && numberOfCustomsDesks <= 17) {
				valid = true;
				break;
			} else {
				System.out.println("Number of custsoms desks must be between 3 and 17.");
			}
		}	
		Simulator s = new Simulator(numberOfCustomsDesks, numberOfPassengers);
		System.out.println(s.totalNumberOfSteps());
		while (s.moreSteps()) {
			s.step();
			if (s.passengerClearedCustoms())
				System.out.println("Step: " + s.getStepsTaken());
		}
		System.out.printf("Average Wait Time: %1$.2f minutes%n", s.averageWaitTime());
		System.out.printf("Average Process Time: %1$.2f minutes%n", s.averageProcessTime());
		System.out.printf("Average Wait Time: %1$.6f minutes%n", s.averageWaitTime());
		System.out.printf("Average Process Time: %1$.6f minutes%n", s.averageProcessTime());
		sc.close();
	}

}
