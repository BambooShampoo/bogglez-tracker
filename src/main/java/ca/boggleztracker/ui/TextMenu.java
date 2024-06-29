package ca.boggleztracker.ui;

import java.util.Scanner;

public class TextMenu {
    private String title;
    private static boolean shouldRepeat;
    private MenuEntry[] entries;

    // Utility class to bundle text string and menu action for each menu entry
    public static class MenuEntry {
        private String text;
        private MenuAction action;

        public MenuEntry(String text, MenuAction action) {
            this.text = text;
            this.action = action;
        }
    }

    public TextMenu(String title, boolean shouldRepeat, MenuEntry[] entries) {
        this.title = title;
        this.shouldRepeat = shouldRepeat;
        this.entries = entries;
    }

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

    public static void setShouldRepeat(boolean shouldRepeat) {
        TextMenu.shouldRepeat = shouldRepeat;
    }

    public int getSelection() {
        Scanner keyboard = new Scanner(System.in);
        return keyboard.nextInt();
    }

    public void display() {
        System.out.println();
        System.out.println(title);

        for (int i = 0; i < entries.length; i++) {
            System.out.println(i + 1 + ") " + entries[i].text);
        }
    }
}
