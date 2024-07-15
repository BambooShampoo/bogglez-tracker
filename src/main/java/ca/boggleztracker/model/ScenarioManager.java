/**
 * File: ScenarioManager.java
 * Revision History:
 * - 2024-06-29: Function and variable declarations
 * - 2024-07-02: System redesign remove storing records into RAM
 * - 2024-07-04: Created individual RandomAccessFiles for each file, opened on start and closed on system shut down.
 * - 2024-07-08: Completed add methods
 * - 2024-07-08: read helper methods
 * - 2024-07-09: implemented modify changeItem, modify release, and generateRandomChangeID
 * - 2024-07-10: implemented modified add requester to check if email already exists
 * - 2024-07-13: implemented all add methods with uniqueness check
 * - 2024-07-14: implemented generateRequesterPage method
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
import java.util.Random;

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
    private final RandomAccessFile requesterFile;
    private final RandomAccessFile productFile;
    private final RandomAccessFile releaseFile;
    private final RandomAccessFile changeItemFile;
    private final RandomAccessFile changeRequestFile;
    private int requesterBytes = 120;
    public ArrayList<Requester> requesterArray = new ArrayList<Requester>();
    public ArrayList<String> productNameArray = new ArrayList<String>();
    public ArrayList<String> releaseArray = new ArrayList<String>();
    public ArrayList<ChangeItem> changeItemArray = new ArrayList<ChangeItem>();
    // pending changes and completed changes can go to change item array

    //=============================
    // Constructor
    //=============================

    //-----------------------------
    /**
     * Default construction for scenario manager, opens all files.
     */
    //---
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
     * Helper function that generates a random change ID upon instantiation of object.
     * @return (out) int - random generated change ID.
     */
    //---
    public int generateRandomChangeID() throws IOException{
        ChangeItem dummy = new ChangeItem();
        Random rand = new Random();
        int random = rand.nextInt(1000000);
        if(changeItemFile.length() == 0){
            return random;
        }
        try{
            int pos = 0;
            changeItemFile.seek(pos);
            while (pos < changeItemFile.length()){
                dummy.readChangeItems(changeItemFile);
                if (dummy.getChangeID() == random){
                    random = rand.nextInt(1000000);
                    changeItemFile.seek(0);
                    break;
                }
                pos += ChangeItem.BYTES_SIZE_CHANGE_ITEM;
            }
            return random;
        }catch (IOException e){
            System.err.println("Error generating Random ChangeID " + e.getMessage());
            return -1;
        }
    }

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
        String date = new String(temp).trim(); // remove white spaces

        if (date.isEmpty()) {
            return null;
        }

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
        try {
            boolean requesterExists = Requester.requesterExists(requesterFile, email);

            if (!requesterExists) {
                Requester requester = new Requester(email, name, phoneNumber, department);
                requesterFile.seek(requesterFile.length());
                requester.writeRequester(requesterFile);
                System.out.println("The new requester is successfully added.");
            } else {
                System.out.println("Error: requester email already exists");
            }
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
        try {
            boolean productExists = Product.productExists(productFile, productName);

            if (!productExists) {
                Product product = new Product(productName);
                productFile.seek(productFile.length());
                product.writeProduct(productFile);
                System.out.println("The new product has been added.");
            } else {
                System.out.println("Error: product name already exists");
            }
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
            System.err.println("Error writing request to file " + e.getMessage());
        }
    }

    //-----------------------------
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
    //---
    public void addChangeItem(String productName, String releaseID, String changeDescription, char priority,
                              String status, LocalDate anticipatedReleaseDate) {
        try {
            int changeID = generateRandomChangeID();
            ChangeItem changeItem = new ChangeItem(changeID, productName, releaseID, changeDescription,
                    priority, status, anticipatedReleaseDate);
            changeItemFile.seek(changeItemFile.length());
            changeItem.writeChangeItem(changeItemFile);
        } catch (IOException e) {
            System.err.println("Error writing change item to file " + e.getMessage());
        }
    }

    //-----------------------------
    /**
     * Modifies a specific change item in the file.
     *
     * @param changeID (in) int - Change ID reference to be searched in file.
     * @param modifiedChangeItem (in) ChangeItem - The new modified change item to be written
     *                          into file.
     */
    //---
    public void modifyChangeItem(int changeID, ChangeItem modifiedChangeItem) {
        ChangeItem change = new ChangeItem();
        int pos = 0;

        try {
            changeItemFile.seek(pos);
            //locate correct ChangeItem from file
            while (true){
                change.readChangeItems(changeItemFile);

                if (changeID == change.getChangeID()){
                    break;
                }
                pos += ChangeItem.BYTES_SIZE_CHANGE_ITEM;
            }
            changeItemFile.seek(pos);
            modifiedChangeItem.writeChangeItem(changeItemFile);
        } catch (IOException e) {
            System.err.println("Error modifying change item to file " + e.getMessage());
        }
    }

    //-----------------------------
    /**
     * Modifies a specific release in the file.
     *
     * @param releaseID (in) String - Release ID reference to be searched in file.
     * @param modifiedRelease (in) Release - The new modified release to be written into file.
     */
    //---
    public void modifyRelease(String releaseID, Release modifiedRelease) {
        Release fileRelease = new Release();
        int pos = 0;

        try {
            releaseFile.seek(pos);
            // locate correct Release from file
            while (true){
                fileRelease.readRelease(releaseFile);
                // convert char[] to String
                String releaseIDToBeChanged = String.valueOf(fileRelease.getReleaseID()).trim();
                if (releaseID.equals(releaseIDToBeChanged)) {
                    break;
                }
                pos += Release.BYTES_SIZE_RELEASE;
            }
            releaseFile.seek(pos);
            modifiedRelease.writeRelease(releaseFile);
        } catch (IOException e) {
            System.err.println("Error modifying release to file " + e.getMessage());
        }
    }

    //-----------------------------
    /**
     * Adds a new release to the file.
     *
     * @param productName (in) String - Identifier of the product this release is for.
     * @param releaseID (in) String - Identifier for the release
     * @param date (in) LocalDate - Date of release
     */
    //---
    public void addRelease(String productName, String releaseID, LocalDate date) {
        try {
            boolean releaseExists = Release.releaseExists(releaseFile, releaseID);

            if (!releaseExists) {
                Release release = new Release(productName, releaseID, date);
                releaseFile.seek(releaseFile.length());
                release.writeRelease(releaseFile);
                System.out.println("The new release ID has been added.");
            } else {
                System.out.println("Error: release ID already exists");
            }
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
                phoneNumber = releaseFile.readLong();
                departmentArr = readCharsFromFile(releaseFile, MAX_DEPARTMENT);
                department = new String(departmentArr);

                Requester thisRequester = new Requester(email, name, phoneNumber, department);
                requesterArray.add(thisRequester);
            } catch (IOException e) {
                System.err.println("Error in reading from file" + e.getMessage());
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
    public ArrayList<String> generateProductPage(int page, int pageSize) {
        String productName = "";
        char[] productNameArr;
        long startingPage = page * pageSize * Product.BYTES_SIZE_PRODUCT;

        try {
            releaseFile.seek(startingPage);
        } catch (IOException e) {
            System.err.println("Error in finding product page" + e.getMessage());
        }

        for (int i = 0; i < 6; i++) {
            try {
                productNameArr = readCharsFromFile(productFile, Product.MAX_PRODUCT_NAME);
                productName = new String(productNameArr);
                productNameArray.add(productName);
            } catch (IOException e) {
                System.err.println("Error in reading from file" + e.getMessage());
            }
        }
        return productNameArray;
    }

    //-----------------------------
    /**
     * Gets a list of Valid Releases from the file to display for user.
     *
     * @param page (in) int - Counter to track what page of Release are displayed for user.
     * @param pageSize (in) int - How many items of data each page can hold.
     */
    //---
    public ArrayList<String> generateReleasePage(int page, int pageSize) {
        char[] productNameArr; // used to temporarily hold product name
        String releaseVersion = "";
        char[] releaseVersionArr;
        long startingPage = page * pageSize * Release.BYTES_SIZE_RELEASE;

        try {
            releaseFile.seek(startingPage);
        } catch (IOException e) {
            System.err.println("Error in finding release page" + e.getMessage());
        }

        for (int i = 0; i < 6; i++) {
            try {
                productNameArr = readCharsFromFile(releaseFile, Product.MAX_PRODUCT_NAME);
                releaseVersionArr = readCharsFromFile(releaseFile, Release.MAX_RELEASE_ID);
                releaseVersion = new String(releaseVersionArr);
                releaseArray.add(releaseVersion);
            } catch (IOException e) {
                System.err.println("Error in reading from file" + e.getMessage());
            }
        }
        return releaseArray;
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
        ChangeItem change = new ChangeItem();
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

    // ****temporary testing method - delete later.
    public void readAllRequesters() throws IOException {
        Requester r = new Requester();
        requesterFile.seek(0);
        try {
            while (true) {
                r.readRequester(requesterFile);
                System.out.println(r);
            }
        } catch (EOFException e) {
            System.out.println("\n");
        }
    }

    // ****temporary testing method - delete later.
    public void readAllProducts() throws IOException {
        Product p = new Product();
        productFile.seek(0);
        try {
            while (true) {
                p.readProduct(productFile);
                System.out.println(p);
            }
        } catch (EOFException e) {
            System.out.println("\n");
        }
    }

    // ****temporary testing method - delete later.
    public void readAllReleases() throws IOException {
        Release r = new Release();
        releaseFile.seek(0);
        try {
            while (true) {
                r.readRelease(releaseFile);
                System.out.println(r);
            }
        } catch (EOFException e) {
            System.out.println("\n");
        }
    }

    // ****temporary testing method - delete later.
    public void readAllChangeRequests() throws IOException {
        ChangeRequest r = new ChangeRequest();
        changeRequestFile.seek(0);
        try {
            while (true) {
                r.readChangeRequest(changeRequestFile);
                System.out.println(r);
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
