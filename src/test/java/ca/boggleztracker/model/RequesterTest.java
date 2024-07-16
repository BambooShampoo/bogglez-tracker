/**
 * File: ChangeItemTest.java
 * Revision History:
 * - 2024-07-03: Test methods written
 * Purpose:
 * ChangeItemTest class is a unit test written to test all public methods of
 * ChangeItem.java.
 */

package ca.boggleztracker.model;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.io.RandomAccessFile;

class RequesterTest {
    //=============================
    // Member fields
    //=============================
    private String email = "testemail@sfu.ca";
    private String name = "Name Of Person";
    private long phoneNumber = 16040000000L;
    private String department = "QA";
    public String filename = "UnitTest02Text.dat";

    //=============================
    // Tests
    //=============================

    //-----------------------------
    /*
     *  Description: unit test to test the creation of the Requester (constructor).
     *  Precondition: UnitTest02Text.dat contains no records.
     */
    //-----------------------------
    /*
     *   Description: Unit test to test the writing of a change item to a random access file.
     *   Precondition: UnitTest01Text.dat contains no records.
     */
    void testFileWriting() {
        Requester requester = new Requester(email,name,phoneNumber,department);
        try {
            RandomAccessFile myFile = new RandomAccessFile(filename, "rw");
            requester.writeRequester(myFile);
        }
        catch(IOException e) {
            System.out.println("File not found\n");
        }
    }
    //-----------------------------
    /*
     *   Description: Unit test to test the reading of a single change item from a random access file
     *   Precondition: UnitTest01Text.dat contains a single record with the variables initialized
     *                 earlier (can be done by running the previous test)
     */
    void testFileReading() {
        Requester requester = new Requester(email,name,phoneNumber,department);
        try {
            RandomAccessFile myFile = new RandomAccessFile(filename, "r");
            requester.readRequester(myFile);
        }
        catch(FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("File not found\n");
        }
    }
    //-----------------------------

    public static void main (String [] args) {
        RequesterTest test = new RequesterTest();
        try{
            test.testFileWriting();
            System.out.println("writeRequester: TEST PASSED");
        }catch (RuntimeException e){
            System.out.println("writeRequester: TEST FAILED");
        }
        try{
            test.testFileReading();
            System.out.println("readRequester: TEST PASSED");
        }catch (RuntimeException e){
            System.out.println("readRequester: TEST FAILED");
        }
    }
}