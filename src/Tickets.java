import java.util.Scanner;
import java.util.ArrayList;
import java.lang.Integer;

public class Tickets {

    public static int getNumberOfRows() {
        Scanner scanner = new Scanner(System.in);
        int numberOfRows;
        numberOfRows = scanner.nextInt();

        while (numberOfRows > 9) {
            numberOfRows = scanner.nextInt();
        }
        return numberOfRows;
    }

    public static int getNumberOfSeatsInRow() {
        Scanner scanner = new Scanner(System.in);
        int numberOfSeatsInRow;
        numberOfSeatsInRow = scanner.nextInt();
        return numberOfSeatsInRow;
    }

    //Display menu method
    public static void displayMenuText() {
        System.out.println("\n" + "1. Show the seats" + "\n" + "2. Buy a ticket" + "\n" + "3. Statistics" + "\n"
                + "0. Exit");
        System.out.print("> ");
    }

    //Buy a ticket method
    public static void buyTicket(int numberOfRows,
                                 int numberOfSeatsInRow,
                                 int rowNumber,
                                 int seatNumber,
                                 ArrayList<Integer> seatNumberBookedContainer,
                                 ArrayList<Integer> rowNumberBookedContainer) {

        int seatNumberBooked = seatNumber - 1;
        int rowNumberBooked = rowNumber - 1;
        rowNumberBookedContainer.add(rowNumberBooked);
        seatNumberBookedContainer.add(seatNumberBooked);

        int totalNumberOfSeats = numberOfRows * numberOfSeatsInRow;

        if (totalNumberOfSeats < 60) {
            System.out.println("\nTicket price: $" + calculateTicketPriceForLessThan60Seats());
        } else {
            System.out.println("\nTicket price: $" + calculateTicketPriceForMoreThanEqualTo60Seats(numberOfRows, rowNumber));
        }
    }

    //Show the seats method
    public static void showTheSeats(int numberOfRows,
                                    int numberOfSeatsInRow,
                                    char[][] cinemaRoom,
                                    ArrayList<Integer> seatNumberBookedContainer,
                                    ArrayList<Integer> rowNumberBookedContainer) {
        int counter = 0;
        printOutNumbersOfSeatsAtTop(numberOfSeatsInRow);

            for (int i = 0; i < numberOfRows; i++) {
                System.out.print(++counter + " ");
                for (int j = 0; j < numberOfSeatsInRow; j++) {
                    for (int k = 0; k < rowNumberBookedContainer.size(); k++) {
                        if (i == rowNumberBookedContainer.get(k) && j == seatNumberBookedContainer.get(k)) {
                            System.out.print(cinemaRoom[i][j] = 'B');
                            System.out.print(" ");
                        }
                    }
                    if (cinemaRoom[i][j] == 'B') continue;
                    System.out.print(cinemaRoom[i][j] = 'S');
                    System.out.print(" ");
                }
                System.out.println();
            }
    }

    //Statistics method
    public static void displayStatistics(int numberOfTicketsPurchased,
                                         int currentIncome,
                                         int numberOfRows,
                                         int numberOfSeatsInRow) {

        System.out.println("\nNumber of purchased tickets: " + numberOfTicketsPurchased);

        int totalNumberOfSeats = numberOfRows * numberOfSeatsInRow;
        double ticketsPurchasedPercentage = (double) numberOfTicketsPurchased / totalNumberOfSeats * 100;
        System.out.printf("Percentage: %.2f", ticketsPurchasedPercentage);
        System.out.println("%");

        System.out.printf("Current income: $%s", currentIncome);
        System.out.println();

        System.out.printf("Total income: $%s", calculateTotalIncome(numberOfRows, numberOfSeatsInRow));
        System.out.print("");

        System.out.println();
    }

    // A method to print out the number of seats at the very top
    public static void printOutNumbersOfSeatsAtTop(int numberOfSeatsInRow) {
        System.out.println("\n" + "Cinema:");
        for (int i = 0; i < numberOfSeatsInRow + 1; i++) {
            if (i == 0) {
                System.out.print("  ");
            } else {
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }

    public static int calculateTicketPriceForLessThan60Seats() {
        return 10;
    }

    public static int calculateTicketPriceForMoreThanEqualTo60Seats(int numberOfRows, int rowNumber) {

        int evenNumberOfRows = numberOfRows % 2;
        int firstHalfSeats;
        int noOfFrontRows;
        int ticketPrice = 0;

        switch (evenNumberOfRows) {
            case 0:
                firstHalfSeats = numberOfRows / 2;
                if (rowNumber <= firstHalfSeats) {
                    ticketPrice = 10;
                } else
                    ticketPrice = 8;
                break;
            case 1:
                noOfFrontRows = (((numberOfRows + 1) / 2) - 1);
                if (rowNumber <= noOfFrontRows) {
                    ticketPrice = 10;
                } else {
                    ticketPrice = 8;
                }
                break;
        }
        return ticketPrice;
    }

    public static int calculateTotalIncome(int numberOfRows, int numberOfSeatsInRow) {
        int evenNumberOfRows = numberOfRows % 2;
        int incomeFromFrontRows;
        int incomeFromBackRows;
        int totalIncome = 0;
        if (numberOfRows * numberOfSeatsInRow < 60) {
            totalIncome = numberOfRows * numberOfSeatsInRow * 10;
        } else {
            switch (evenNumberOfRows) {
                case 0:
                    totalIncome = (numberOfRows / 2 * numberOfSeatsInRow * 10) + (numberOfRows / 2 * numberOfSeatsInRow * 8);
                    break;
                case 1:
                    incomeFromFrontRows = (((numberOfRows + 1) / 2) - 1) * numberOfSeatsInRow * 10;
                    incomeFromBackRows = (numberOfRows + 1) / 2 * numberOfSeatsInRow * 8;
                    totalIncome = incomeFromFrontRows + incomeFromBackRows;
                    break;
            }
        }
        return totalIncome;
    }

    public static int getRowNumber(Scanner scanner) {
        int rowNumber;
        System.out.println("\nEnter a row number: ");
        System.out.print("> ");
        rowNumber = scanner.nextInt();
        return rowNumber;
    }
    public static int getSeatNumber(Scanner scanner) {
        int seatNumber;
        System.out.println("Enter a seat number in that row: ");
        System.out.print("> ");
        seatNumber = scanner.nextInt();
        return seatNumber;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        var rowNumber = 0;
        var seatNumber = 0;
        int menuItem;
        int numberOfTicketsPurchased = 0;
        int currentIncome = 0;

        ArrayList<Integer> seatNumberBookedContainer = new ArrayList<>();
        ArrayList<Integer> rowNumberBookedContainer = new ArrayList<>();

        System.out.println("Enter the number of rows:");
        System.out.print("> ");
        int numberOfRows = getNumberOfRows();

        System.out.println("Enter the number of seats in each row:");
        System.out.print("> ");
        int numberOfSeatsInRow = getNumberOfSeatsInRow();

        char[][] cinemaRoom = new char[numberOfRows][numberOfSeatsInRow];
        boolean isOutOfBounds = !(rowNumber > numberOfRows || seatNumber > numberOfSeatsInRow);

        displayMenuText();
        menuItem = scanner.nextInt();

        while (menuItem != 0) {
            switch (menuItem) {
                case 1:
                    showTheSeats(numberOfRows,
                            numberOfSeatsInRow,
                            cinemaRoom,
                            seatNumberBookedContainer,
                            rowNumberBookedContainer);
                    displayMenuText();
                    break;
                case 2:

                    numberOfTicketsPurchased++;
                    rowNumber = getRowNumber(scanner);
                    seatNumber = getSeatNumber(scanner);
                    while (isOutOfBounds || rowNumberBookedContainer.contains(rowNumber - 1)) {
                        if (isOutOfBounds) {
                            System.out.println("Wrong input!");
                            rowNumber = getRowNumber(scanner);
                            seatNumber = getSeatNumber(scanner);
                            break;
                        } else if (seatNumberBookedContainer.contains(seatNumber - 1)) {
                            for (int i = 0; i < rowNumberBookedContainer.size() && i < seatNumberBookedContainer.size(); i++) {
                                    if (rowNumberBookedContainer.get(i).equals(rowNumber - 1) && seatNumberBookedContainer.get(i).equals(seatNumber - 1)) {
                                        System.out.println("\nThat ticket has already been purchased!");
                                        rowNumber = getRowNumber(scanner);
                                        seatNumber = getSeatNumber(scanner);
                                        break;
                                    }
                            }
                        }
                        break;
                    }

                    if (numberOfRows * numberOfSeatsInRow < 60 && numberOfTicketsPurchased > 0) {
                        currentIncome += calculateTicketPriceForLessThan60Seats();
                    } else if (numberOfRows * numberOfSeatsInRow >= 60 && numberOfTicketsPurchased > 0) {
                        currentIncome += calculateTicketPriceForMoreThanEqualTo60Seats(numberOfRows, rowNumber);
                    }
                        buyTicket(numberOfRows,
                                numberOfSeatsInRow,
                                rowNumber,
                                seatNumber,
                                seatNumberBookedContainer,
                                rowNumberBookedContainer);
                        displayMenuText();

                    break;
                case 3:
                    displayStatistics(numberOfTicketsPurchased,
                            currentIncome,
                            numberOfRows,
                            numberOfSeatsInRow);
                    displayMenuText();
                    break;
                case 0:
                    return;
            }
            menuItem = scanner.nextInt();
        }
    }
}