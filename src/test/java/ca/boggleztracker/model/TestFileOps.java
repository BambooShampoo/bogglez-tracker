/**
 * File: Main.java
 * Revision History:
 * - 2024-07-04: File creation
 * - 2024-07-15: Added Unit test for reading and writing to file for ChangeItem and Requester
 * Purpose: TestFileOps class represents a unit test to test writing and reading of data records.
 */
package ca.boggleztracker.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.time.LocalDate;

public class TestFileOps {
    //=============================
    // Static Method Declarations
    //=============================

    //-----------------------------
    /**
     * Unit Test of Requester and ChangeItem reading and writing to files
     * @param args (in) String[] - Command line arguments
     */
    //---
    public static void main(String[] args) {
        // TEST FOR REQUESTER
        Requester testerRequester = new Requester("testEmail@sfu.ca","Test Name", 16046041111L,"QA");
        try{
            RandomAccessFile myFile = new RandomAccessFile("UnitTest02Text.dat", "rw");
            myFile.seek(myFile.length());
            testerRequester.writeRequester(myFile);
            System.out.println("writeRequester: TEST PASSED");
        }catch(IOException e){
            System.out.println("writeRequester: TEST FAILED");
            System.err.println("Error opening record files " + e.getMessage());
        }
        try{
            RandomAccessFile myFile = new RandomAccessFile("UnitTest02Text.dat", "rw");
            myFile.seek(0);
            testerRequester.readRequester(myFile);
            System.out.println("readRequester: TEST PASSED");
        }catch(IOException e){
            System.out.println("readRequester: TEST FAILED");
            System.err.println("Error opening record files " + e.getMessage());
        }

        // TEST FOR CHANGEITEM
        ChangeItem testerChangeItem = new ChangeItem(0,"TestProd","v1.0", "Test Description",'5', "Open", LocalDate.of(2024,8,8));
        try{
            RandomAccessFile myFile = new RandomAccessFile("UnitTest01Text.dat", "rw");
            myFile.seek(myFile.length());
            testerChangeItem.writeChangeItem(myFile);
            System.out.println("writeRequester: TEST PASSED");
        }catch(IOException e){
            System.out.println("writeRequester: TEST FAILED");
            System.err.println("Error opening record files " + e.getMessage());
        }
        try{
            RandomAccessFile myFile = new RandomAccessFile("UnitTest01Text.dat", "rw");
            myFile.seek(0);
            testerChangeItem.readChangeItems(myFile);
            System.out.println("readRequester: TEST PASSED");
        }catch(IOException e){
            System.out.println("readRequester: TEST FAILED");
            System.err.println("Error opening record files " + e.getMessage());
        }
    }

}
