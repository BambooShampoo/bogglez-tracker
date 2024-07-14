/**
 * File: ScenarioManager.java
 * Revision History:
 * - 2024-06-29: Function and variable declarations
 * - 2024-07-02: System redesign remove storing records into RAM
 * - 2024-07-04: Created individual RandomAccessFiles for each file, opened on start and closed on system shut down.
 * - 2024-07-08: Completed add methods
 * - 2024-07-08: read helper methods
 * Purpose:
 * ScenarioManager class is responsible for opening and closing the data file,
 * populating the array lists of products and requesters, and supports various interactions
 * with these lists.
 */

package ca.boggleztracker.model;

import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ScenarioManager {
    //=============================
    // Constants and static fields
    //=============================
    private static final String REQUESTER_FILE = "requester.dat";
    private static final String PRODUCT_FILE = "product.dat";
    private static final String RELEASE_FILE = "release.dat";
    private static final String CHANGE_ITEM_FILE = "change-item.dat";
    private static final String CHANGE_REQUEST_FILE = "change-request.dat";
    private static final int LOCAL_DATE_LENGTH = 10;
    public static final int MAX_EMAIL = 24;
    public static final int MAX_NAME = 30;
    public static final int MAX_DEPARTMENT = 2;
    public static final long BYTES_SIZE_REQUESTER = 120;

    //=============================
    // Member fields
    //=============================
    private RandomAccessFile requesterFile;
    private RandomAccessFile productFile;
    private RandomAccessFile releaseFile;
    private RandomAccessFile changeItemFile;
    private RandomAccessFile changeRequestFile;
    private int requesterBytes = 120;
    public ArrayList<Requester> requesterArray = new ArrayList<Requester>;

    //=============================
    // Constructor
    //=============================
    public ScenarioManager() throws IOException {
        requesterFile = new RandomAccessFile(REQUESTER_FILE, "rw");
        productFile = new RandomAccessFile(PRODUCT_FILE, "rw");
        releaseFile = new RandomAccessFile(RELEASE_FILE, "rw");
        changeItemFile = new RandomAccessFile(CHANGE_ITEM_FILE, "rw");
        changeRequestFile = new RandomAccessFile(CHANGE_REQUEST_FILE, "rw");
    }

    //=============================
    // Methods
    //=============================

    //-----------------------------
    /**
     * Helper function to pad character array with spaces to ensure
     * it's of desired length.
     *
     * @param charArray (in) char[] - character array to pad.
     * @param padLength (in) int - length of new character array.
     * @return (out) char[] - character array with padded spaces.
     */
    //---
    public static char[] padCharArray(char[] charArray, int padLength) {
        char[] temp = new char[padLength];

        if (charArray.length > padLength) {
            for (int i = 0; i < charArray.length; i++) {
                temp[i] = charArray[i];
            }
        } else {
            // Copy all characters from productName
            for (int i = 0; i < charArray.length; i++) {
                temp[i] = charArray[i];
            }

            // Pad with spaces to max length
            for (int i = charArray.length; i < padLength; i++) {
                temp[i] = ' ';
            }
        }
        return temp;
    }

    //-----------------------------
    /**
     * Helper function to read char arrays from file.
     *
     * @param file (in) RandomAccessFile - file to read char array from.
     * @param numChars (in) int - number of bytes the char array consists of.
     */
    //---
    public static char[] readCharsFromFile(RandomAccessFile file, int numChars) throws IOException {
        char[] temp = new char[numChars];

        //reads each byte
        for (int i = 0; i < numChars; i++) {
            temp[i] = file.readChar();
        }
        return temp;
    }

    //-----------------------------
    /**
     * Helper function to read local dates from file.
     *
     * @param file (in) RandomAccessFile - file to read local date from.
     */
    //---
    public static LocalDate readDateFromFile(RandomAccessFile file) throws IOException {
        char[] temp = readCharsFromFile(file, LOCAL_DATE_LENGTH);
        String date = new String(temp);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(date, formatter);
    }

    //-----------------------------
    /**
     * Adds a new requester to file.
     *
     * @param email (in) String - Email of new requester.
     * @param name (in) String - Name of new requester.
     * @param phoneNumber (in) int - Phone number of new requester.
     * @param department (in) String - Department of new requester. This can be left as empty.
     */
    //---
    public void addRequester(String email, String name, long phoneNumber, String department) {
        Requester requester = new Requester(email, name, phoneNumber, department);
        try {
            requesterFile.seek(requesterFile.length());
            requester.writeRequester(requesterFile);
        } catch (IOException e) {
            System.err.println("Error writing requester to file " + e.getMessage());
        }
    }

    //-----------------------------
    /**
     * Adds a new product to file.
     *
     * @param productName (in) String - Name of the new product.
     */
    //---
    public void addProduct(String productName) {
        Product product = new Product(productName);
        try {
            productFile.seek(productFile.length());
            product.writeProduct(productFile);
        } catch (IOException e) {
            System.err.println("Error writing product to file " + e.getMessage());
        }
    }

    //-----------------------------
    /**
     * Adds a new request to the file.
     *
     * @param changeID (in) int - Identifier of the ChangeItem this request is for.
     * @param productName (in) String - Name of product of change request.
     * @param reportedRelease (in) String - Release version of the product
     * @param requesterEmail (in) String - Email of the requester.
     * @param reportedDate (in) LocalDate - Date of when the request was made.
     */
    //---
    public void addChangeRequest(int changeID, String productName, String reportedRelease,
                                 String requesterEmail, LocalDate reportedDate) {
        ChangeRequest changeRequest = new ChangeRequest(changeID, productName,
                reportedRelease, requesterEmail, reportedDate);
        try {
            changeRequestFile.seek(changeRequestFile.length());
            changeRequest.writeChangeRequest(changeRequestFile);
        } catch (IOException e) {
            System.err.println("Error writing product to file " + e.getMessage());
        }
    }

    /**
     * Adds a new change item to the file.
     *
     * @param releaseID (in) int - Identifier of the release this change item is for.
     * @param productName (in) String - Product name of reported change item.
     * @param changeDescription (in) String - Change description of change.
     * @param priority (in) int - Priority of the change.
     * @param status (in) String - Status of the change.
     * @param anticipatedReleaseDate (in) LocalDate - Date of anticipated release date.
     */
    public void addChangeItem(String productName, String releaseID, String changeDescription, int priority,
                              String status, LocalDate anticipatedReleaseDate) {
        ChangeItem changeItem = new ChangeItem(productName, releaseID, changeDescription,
                priority, status, anticipatedReleaseDate);
        try {
            changeItemFile.seek(changeItemFile.length());
            changeItem.writeChangeItem(changeItemFile);
        } catch (IOException e) {
            System.err.println("Error writing change item to file " + e.getMessage());
        }
    }

    /**
     * Modifies a specific change item in the file.
     *
     * @param changeID (in) int - Change ID reference to be searched in file.
     * @param modifiedChangeItem (in) ChangeItem - The new modified change item to be written
     *                          into file.
     */
    public void modifyChangeItem(int changeID, ChangeItem modifiedChangeItem) {

        try {
            int pos = 0; // current position in file
            char[] buffer = new char[142]; // to store unneeded read characters from <readCharFromFile>
            char[] readChangeID = new char[6];
            for(long i = 0; i < releaseFile.length(); i += ChangeItem.BYTES_SIZE_CHANGE_ITEM){
                // update current position
                pos += ChangeItem.BYTES_SIZE_CHANGE_ITEM;
                // convert the char[] into int to compare with <changeID>
                readChangeID = readCharsFromFile(requesterFile, 6);
                String temp = new String(readChangeID);
                if(changeID == Integer.parseInt(temp)){
                    break;
                }
                buffer = readCharsFromFile(requesterFile, 142);
                readCharsFromFile(requesterFile, 1); // new line
            }
            // write the new ChangeItem over the old one
            changeItemFile.seek(pos);
            modifiedChangeItem.writeChangeItem(changeItemFile);
        } catch (IOException e) {
            System.err.println("Error writing change item to file " + e.getMessage());
        }
    }

    /**
     * Modifies a specific release in the file.
     *
     * @param releaseID (in) String - Release ID reference to be searched in file.
     * @param modifiedRelease (in) Release - The new modified release to be written into file.
     */
    public void modifyRelease(String releaseID, Release modifiedRelease) {
        try {
            int pos = 0; // current position in file
            char[] buffer = new char[10]; // to store unneeded read characters from <readCharFromFile>
            char[] tempReleaseID = new char[Release.MAX_RELEASE_ID]; // to store the read releaseID
            for(long i = 0; i < releaseFile.length(); i += Release.BYTES_SIZE_RELEASE){
                // update position in file
                pos += Release.BYTES_SIZE_RELEASE;
                // read Product in buffer
                buffer = readCharsFromFile(requesterFile, 10);
                // read and parse releaseID
                tempReleaseID = readCharsFromFile(requesterFile, Release.MAX_RELEASE_ID);
                String temp = new String(tempReleaseID);
                if(releaseID.equals(temp)){
                    break;
                }
                // read rest of line
                buffer = readCharsFromFile(requesterFile, 10);
                readCharsFromFile(requesterFile, 1); // new line
            }
            // Write the new Release over the old one
            releaseFile.seek(pos);
            modifiedRelease.writeRelease(releaseFile);
        } catch (IOException e) {
            System.err.println("Error writing release to file " + e.getMessage());
        }
    }

    /**
     * Adds a new release to the file.
     *
     * @param productName (in) String - Identifier of the product this release is for.
     * @param releaseID (in) String - Identifier for the release
     * @param date (in) LocalDate - Date of release
     */
    public void addRelease(String productName, String releaseID, LocalDate date) {
        Release release = new Release(productName, releaseID, date);
        try {
            releaseFile.seek(releaseFile.length());
            release.writeRelease(releaseFile);
        } catch (IOException e) {
            System.err.println("Error writing release to file " + e.getMessage());
        }
    }

    //-----------------------------
    /**
     * Gets a list of Requesters from the file to display for user.
     *
     * @param page (in) int - Counter to track what page of Requester are displayed for user.
     * @param pageSize (in) int - How many items of data each page can hold.
     */
    //---
    public ArrayList<Requester> generateRequesterPage(int page, int pageSize) {

        // fields, char array versions are not exactly needed but help with debugging
        // can do email = new String(reachCharsFromFile(releaseFile, MAX_EMAIL)); instead later
        String email = "";
        char[] emailArr;
        String name = "";
        char[] nameArr;
        long phoneNumber = 0;
        String department = "";
        char[] departmentArr;
        long startingPage = page * pageSize * BYTES_SIZE_REQUESTER;


        try {
            releaseFile.seek(startingPage);
        } catch (IOException e) {
            System.err.println("Error in finding requester page" + e.getMessage());
        }

        for (int i = 0; i < 6; i++) {
            try {
                emailArr = readCharsFromFile(releaseFile, MAX_EMAIL);
                email = new String(emailArr);
                nameArr = readCharsFromFile(releaseFile, MAX_NAME);
                name = new String(nameArr);
                phoneNumber = readLong();
                departmentArr = readCharsFromFile(releaseFile, MAX_DEPARTMENT);
                department = new String(departmentArr);

                thisRequester = new Requester(email, name, phoneNumber, department);
                requesterArray.add(thisRequester);
            } catch (IOException e) {
                System.err.println("Error in reading from file" + e.getMessage());
            } catch (EOFException e) {
                break; // catch EOF and break out of loop
            }
        }
        return requesterArray;
    }

    //-----------------------------
    /**
     * Gets a list of Products from the file to display for user.
     *
     * @param page (in) int - Counter to track what page of Product are displayed for user.
     * @param pageSize (in) int - How many items of data each page can hold.
     */
    //---
    public String generateProductPage(int page, int pageSize) {
        return "";
    }

    //-----------------------------
    /**
     * Gets a list of Valid Releases from the file to display for user.
     *
     * @param page (in) int - Counter to track what page of Release are displayed for user.
     * @param pageSize (in) int - How many items of data each page can hold.
     */
    //---
    public String generateReleasePage(int page, int pageSize) {
        return "";
    }

    //-----------------------------
    /**
     * Gets a list of Valid ChangeItem from the file to display for user.
     *
     * @param page (in) int - Counter to track what page of ChangeItem are displayed for user.
     * @param pageSize (in) int - How many items of data each page can hold.
     */
    //---
    public String generateChangeItemPage(int page, int pageSize) {
        return "";
    }

    //-----------------------------
    /**
     * Get s a list of all pending change items of a specific product.
     *
     * @param productName (in) String - Product name reference to find all pending changes.
     */
    //---
    public String generatePendingChangesPage(String productName) {
        return "";
    }

    //-----------------------------
    /**
     * Gets a list of all completed changes for customer notification.
     */
    //---
    public String generateCompletedChangesPage() {
        return "";
    }

    // ****temporary testing method - delete later.
    public void readAllChangeItem() throws IOException {
        ChangeItem change = new ChangeItem("", "", "", 0, "", LocalDate.of(2000, 1 ,1));
        changeItemFile.seek(0);
        try {
            while (true) {
                change.readChangeItems(changeItemFile);
                System.out.println(change);
            }
        } catch (EOFException e) {
            System.out.println("\n");
        }
    }

    //-----------------------------
    /**
     * Closes the file, on system shut down
     */
    //---
    public void closeFiles() {
        try {
            requesterFile.close();
        } catch (IOException e) {
            System.err.println("Error closing files " + e.getMessage());
        }
    }
}
