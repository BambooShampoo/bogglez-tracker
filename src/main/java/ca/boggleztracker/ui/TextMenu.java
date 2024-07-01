/**
 * File: TextMenu.java
 * Revision History:
 * - 2024-06-29: Function and variable declarations
 * Purpose:
 * TextMenu class is responsible for displaying and handling user input of the various menus.
 * This class uses an array of MenuEntry objects to represent menu options and their
 * corresponding actions.
 */
package ca.boggleztracker.ui;

import java.util.Scanner;

public class TextMenu {
    //=============================
    // Constants and Static Fields
    //=============================
    private static boolean shouldRepeat;

    //=============================
    // Member fields
    //=============================
    private String title;
    private MenuEntry[] entries;

    //=============================
    // Utility classes
    //=============================

    //-----------------------------
    /**
     * MenuEntry is a utility class to bundle the name of the menu entry and the corresponding action.
     */
    //---
    public static class MenuEntry {
        //=============================
        // Member fields
        //=============================
        private String text;
        private MenuAction action;

        //=============================
        // Constructors
        //=============================

        //-----------------------------
        /**
         * Two argument constructor for MenuEntry.
         *
         * @param text (in) String - Name of the menu item.
         * @param action (in) MenuAction - Action of the menu item.
         */
        //---
        public MenuEntry(String text, MenuAction action) {
            this.text = text;
            this.action = action;
        }
    }

    //=============================
    // Constructors
    //=============================

    //-----------------------------

    /**
     * Three argument constructor for TextMenu.
     *
     * @param title (in) String - Name of the (sub)menu.
     * @param shouldRepeat (in) boolean - Boolean needed to determine if menu should repeat
     *                    after performing the action.
     * @param entries (in) MenuEntry[] - Array of menu entries associated with the specified menu.
     */
    //---
    public TextMenu(String title, boolean shouldRepeat, MenuEntry[] entries) {
        this.title = title;
        this.shouldRepeat = shouldRepeat;
        this.entries = entries;
    }

    //=============================
    // Static Methods
    //=============================

    //-----------------------------
    /**
     * Static method that sets the shouldRepeat boolean.
     */
    //---
    public static void setShouldRepeat(boolean shouldRepeat) {
        TextMenu.shouldRepeat = shouldRepeat;
    }

    //=============================
    // Methods
    //=============================

    //-----------------------------
    /**
     * Displays the menu and performs the selected menu entry.
     */
    //---
    public void doMenu() {
        do {
            display();
            int option = getSelection();
            MenuAction action = entries[option - 1].action;

            if (action == null) {
                shouldRepeat = false;
            } else {
                action.performAction();
            }
        } while (shouldRepeat);
    }

    //-----------------------------
    /**
     * Responsible for getting user menu selection.
     */
    //---
    public int getSelection() {
        Scanner keyboard = new Scanner(System.in);
        return keyboard.nextInt();
    }

    //-----------------------------
    /**
     * Displays the menu.
     */
    //---
    public void display() {
        System.out.println();
        System.out.println(title);

        for (int i = 0; i < entries.length; i++) {
            System.out.println(i + 1 + ") " + entries[i].text);
        }
    }
}
