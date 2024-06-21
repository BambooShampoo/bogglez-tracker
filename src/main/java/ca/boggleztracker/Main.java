package ca.boggleztracker;

import ca.boggleztracker.model.ProductManager;
import ca.boggleztracker.ui.TextUI;

public class Main {
    public static void main(String[] args) {
        ProductManager manager = new ProductManager();
        TextUI ui = new TextUI(manager);

        ui.start();
    }
}
