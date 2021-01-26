import java.util.Scanner;

public class Tickets {

    //A method to print out the menu 24/01/2021
    public static void displayMenuText(int menuItem, int counter, int numberOfRows, int numberOfSeatsInRow,
                                char[][] cinemaRoom, int rowNumber, int seatNumber, int checkSum,
                                int rowNumberBooked, int seatNumberBooked) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n" + "1. Show the seats" + "\n" + "2. Buy a ticket" + "\n" + "0. Exit");
        System.out.print("> ");
        menuItem = scanner.nextInt();
        selectMenuItem(menuItem,
                counter,
                numberOfRows,
                numberOfSeatsInRow,
                cinemaRoom,
                rowNumber,
                seatNumber,
                checkSum,
                rowNumberBooked,
                seatNumberBooked);
    }

    //A method to select a menu item 24/01/2021
    public static void selectMenuItem(int menuItem, int counter, int numberOfRows, int numberOfSeatsInRow,
                                     char[][] cinemaRoom, int rowNumber, int seatNumber,int checkSum,
                                      int rowNumberBooked, int seatNumberBooked) {
            if (menuItem == 1) {
                showTheSeats(numberOfRows,
                numberOfSeatsInRow,
                cinemaRoom,
                rowNumber,
                seatNumber,
                menuItem,
                checkSum,
                rowNumberBooked,
                seatNumberBooked);
            }
            else if (menuItem == 2) {
                buyTicket(numberOfRows,
                        numberOfSeatsInRow,
                        menuItem,
                        counter,
                        cinemaRoom);
            } else if (menuItem == 0) {
                System.exit(0);
            }
        }

    //A method for buying a ticket 24/01/2021*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-
    public static void buyTicket(int numberOfRows, int numberOfSeatsInRow, int menuItem, int counter, char[][] cinemaRoom) {
        int checkSum = 0;
        ++checkSum;
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n" + "Enter a row number: ");
        System.out.print("> ");
        int rowNumber = scanner.nextInt();
        int rowNumberBooked = rowNumber - 1;


        System.out.println("Enter a seat number in that row: ");
        System.out.print("> ");
        int seatNumber = scanner.nextInt();
        int seatNumberBooked = seatNumber - 1;
        int totalNumberOfSeats = numberOfRows * numberOfSeatsInRow;

        if (totalNumberOfSeats < 60) {
            calculateTicketPriceForLessThan60Seats();
        } else {
            System.out.println("Ticket price: $" + calculateTicketPriceForMoreThan60Seats(numberOfRows, rowNumber));
        }

        displayMenuText(menuItem,
                counter,
                numberOfRows,
                numberOfSeatsInRow,
                cinemaRoom,
                rowNumber,
                seatNumber,
                checkSum,
                rowNumberBooked,
                seatNumberBooked);
    }

//    public static void storeSeatsBooked(int seatNumberBooked, int rowNumberBooked, int checkSum) {
//
//    }

    //A method to show the seats 24/01/2021*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
    public static void showTheSeats(int numberOfRows,
                                    int numberOfSeatsInRow,
                                    char[][] cinemaRoom,
                                    int rowNumber,
                                    int seatNumber,
                                    int menuItem,
                                    int checkSum,
                                    int rowNumberBooked,
                                    int seatNumberBooked) {


        int counter = 0;
        printOutNumbersOfSeatsAtTop(numberOfSeatsInRow);

        if (checkSum > 0) {
            for (int i = 0; i < numberOfRows; i++) {
                System.out.print(++counter + " ");
                for (int j = 0; j < numberOfSeatsInRow; j++) {
                    if (i == rowNumberBooked && j == seatNumberBooked) {
                        System.out.print(cinemaRoom[i][j] = 'B');
                    } else {
                        System.out.print(cinemaRoom[i][j] = 'S');
                    }
                    System.out.print(" ");
                }
                System.out.println();
            }
        } else {
            for (int i = 0; i < numberOfRows; i++) {
                System.out.print(++counter + " ");
                for (int j = 0; j < numberOfSeatsInRow; j++) {

                    System.out.print(cinemaRoom[i][j] = 'S');
                    System.out.print(" ");
                }
                System.out.println();
            }
        }
        displayMenuText(menuItem,
                counter,
                numberOfRows,
                numberOfSeatsInRow,
                cinemaRoom,
                rowNumber,
                seatNumber,
                checkSum,
                rowNumberBooked,
                seatNumberBooked);
    }


    // A method to print out the number of seats at the very top
    public static void printOutNumbersOfSeatsAtTop(int numberOfSeatsInRow) {
        System.out.println("\n" + "Cinema:");
        for (int i = 0; i < numberOfSeatsInRow + 1; i++) {
            if (i == 0) {
                System.out.print("  ");
            }
            else {
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }

    public static void calculateTicketPriceForLessThan60Seats() {
        System.out.println("Ticket price: $10");
    }

    public static int calculateTicketPriceForMoreThan60Seats(int numberOfRows, int rowNumber) {

        int evenNumberOfRows = numberOfRows % 2;
        int firstHalfSeats;
        int noOfFrontRows;
        int ticketPrice = 0;

        switch (evenNumberOfRows) {
            case 0 -> {
                firstHalfSeats = numberOfRows / 2;
                if (rowNumber <= firstHalfSeats) {
                    ticketPrice = 10;
                } else
                    ticketPrice = 8;
            }
            case 1 -> {
                noOfFrontRows = (((numberOfRows + 1) / 2) - 1);
                if (rowNumber <= noOfFrontRows) {
                    ticketPrice = 10;
                } else {
                    ticketPrice = 8;
                }
            }
        }
        return ticketPrice;
    }
/*--MAIN----MAIN----MAIN----MAIN----MAIN----MAIN----MAIN----MAIN----MAIN----MAIN----MAIN----MAIN----MAIN----MAIN--*/
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numberOfSeatsInRow;
        int numberOfRows;
        var counter = 0;
        var rowNumber = 0;
        var seatNumber = 0;
        var menuItem = 0;
        var checkSum = 0;
        var rowNumberBooked = 0;
        var seatNumberBooked = 0;


        System.out.println("Enter the number of rows:");
        System.out.print("> ");
        numberOfRows = scanner.nextInt();

        while (numberOfRows > 9) {
            numberOfRows = scanner.nextInt();
        }

        System.out.println("Enter the number of seats in each row:");
        System.out.print("> ");
        numberOfSeatsInRow = scanner.nextInt();
        char[][] cinemaRoom = new char[numberOfRows][numberOfSeatsInRow];

        displayMenuText(menuItem,
                counter,
                numberOfRows,
                numberOfSeatsInRow,
                cinemaRoom,
                rowNumber,
                seatNumber,
                checkSum,
                rowNumberBooked,
                seatNumberBooked);
        menuItem = scanner.nextInt();
        selectMenuItem(menuItem,
                counter,
                numberOfRows,
                numberOfSeatsInRow,
                cinemaRoom,
                rowNumber,
                seatNumber,
                checkSum,
                rowNumberBooked,
                seatNumberBooked);
    }
}