import java.io.BufferedReader; //imports the modules used in this code
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class BubbleSort {

    public static int[] createRandomArray(int arrayLength) { //Function to generate a random array with the given length
        Random random = new Random(); //Creates a random object to use the class functions
        int[] arr = new int[arrayLength]; //Creates an array with the given length
        for (int i = 0; i < arr.length; i++) { //Loop that generates a random number between 0 and 100 and assigns it to each index in the array
            arr[i] = random.nextInt(100);
        }
        return arr; //Returns the generated array
    }

    public static void writeArrayToFile(int[] arr, String fileName) { //Function to write an array to a file
        try(FileWriter writer = new FileWriter(fileName)){ //Try statement with a new FileWriter object that will catch any exceptions with the object
            for (int i = 0; i < arr.length; i++) { //Loop that writes every number in the array and a newline character so each number will be on a different line
                writer.write(arr[i] + "\n");
            }
        } catch (Exception e) { //Catches any exceptions and prints the error message
            e.printStackTrace();
        }
    }

    public static int[] readFileToArray(String fileName) throws IOException{ //Function to read a given file and turn its contents into an integer array
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))){ //Try statement with a BufferedReader object that will catch any exceptions involving the object
            String line = ""; //Establishes a string to store the individual lines read from the file
            List<Integer> list = new ArrayList<>(); //ArrayList to store all the integers parsed from the lines of the file
            while ((line = reader.readLine()) != null) { //While loop that runs until a line with nothing is read
                if (line.isEmpty()){ //If the line is empty, skips it
                    continue;
                }
                list.add(Integer.parseInt(line)); //Converts the line to an integer and adds it to the ArrayList
            }
            int[] arr = new int[list.size()]; //Creates the array that will store the contents of the file
            for (int i = 0; i < arr.length; i++) { //For loop that adds every item in the ArrayList to the array
                arr[i] = list.get(i);
            }
            return arr; //Returns the array
        } catch (Exception e) { //Catches any exceptions with reading the file and prints the error
            e.printStackTrace();
        }
        return null; //Returns nothing if an error is caught
    }

    public static void bubbleSort(int[] arr) { //Function to perform the bubble sort method on an integer array
        boolean swapped; //Creates a boolean variable to tell if a pair of integers have already been swapped/sorted
        for (int i = 0; i < arr.length; i++) { //Outer loop controls how many times the loop will be passed over
            swapped = false; //Resets the swapped variable to false
            for (int j = 0; j < arr.length - 1; j++) { //Inner loop passes over array and compares each array index with the index after it to determine if it needs to be swapped/sorted
                if (arr[j] > arr[j + 1]) { //If the index is larger than the index after it, swaps the two of them
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true; //Sets the swappes variable to true
                }
            }
            if (!swapped) { //Checks if swapped was never changed to true, if so then ends the outer loop early
                break;
            }
        }
    }

    public static void main(String[] args) throws Exception { //The main class
        int choice = 0; //Establishes a control variable for the user to traverse the switch statement
        Scanner scnr = new Scanner(System.in);
        while (choice != 3) { //While loop that runs until the control variable is set to 3
            System.out.println("Please enter\n1 to generate a random array and store it to a file\n2 to sort an array from a file and write the sorted array to another file\n3 to quit");
            choice = scnr.nextInt(); //Assigns the user input to the control variable
            scnr.nextLine(); //Clears the next newline
            switch (choice) { //Switch statement that controls what the user's input for the choice variable will do
                case 1: //If the user enters 1, the program prompts them for a number and filename and generates an array of random numbers with a length matching the number they input and writes the array to the file they inputted.
                    System.out.println("Please enter the length of the array you'd like:");
                    int arrLength = scnr.nextInt(); //Gets the user's desired array length
                    scnr.nextLine();
                    System.out.println("Please enter the name of the file you'd like to write to:");
                    String fileName = scnr.nextLine(); //Gets the user's desired file name
                    int[] arr = createRandomArray(arrLength); //Generates a random array with the given length
                    writeArrayToFile(arr, fileName); //Writes the array to the given file name
                    System.out.println("Operation complete!");
                    break;
                case 2: //If the user enters 2, the program prompts the user for two filenames, for the first file it gets the numbers in it and creates an array and sorts it using the bubbleSort() method. Then, it writes the sorted array to the second file.
                    System.out.println("Please enter the name of the file with the array you want to sort:");
                    String fileName1 = scnr.nextLine(); //Gets the first file name from the user
                    System.out.println("Please enter the name of the file you'd like to write the sorted array to:");
                    String fileName2 = scnr.nextLine(); //Gets the second file name from the user
                    int[] arr1 = readFileToArray(fileName1); //Gets the array from the first file
                    bubbleSort(arr1); //Uses bubble sort to sort the array
                    writeArrayToFile(arr1, fileName2); //Writes the sorted array to the second file
                    System.out.println("Operation complete!");
                    break;
                case 3: //If user enters 3, quits the program
                    System.out.println("Goodbye!");
                    break;
                default: //If a different number is entered, prompts the user to enter a valid option
                    System.out.println("Please enter a valid choice.");
                    break;


            }
        }

    }
}
