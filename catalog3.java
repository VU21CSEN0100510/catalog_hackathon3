import java.util.ArrayList;
import java.util.Scanner;

class Record {
    private String itemName;
    private String manufacturer;
    private String handler;
    private String seller;
    private String buyer;
    private String stage;

    public Record(String itemName, String manufacturer) {
        this.itemName = itemName;
        this.manufacturer = manufacturer;
        this.handler = null;
        this.seller = null;
        this.buyer = null;
        this.stage = "Created";
    }

    public void transfer(String handler) {
        this.handler = handler;
        this.stage = "Transferred";
    }

    public void listForSale(String seller) {
        this.seller = seller;
        this.stage = "Listed for Sale";
    }

    public void purchase(String buyer) {
        this.buyer = buyer;
        this.stage = "Purchased";
    }

    public void display() {
        System.out.println("Item Name:      " + itemName);
        System.out.println("Manufacturer:   " + manufacturer);
        System.out.println("Handler:        " + (handler != null ? handler : "N/A"));
        System.out.println("Seller:         " + (seller != null ? seller : "N/A"));
        System.out.println("Buyer:          " + (buyer != null ? buyer : "N/A"));
        System.out.println("Current Stage:  " + stage);
        System.out.println("---------------------------------------------------");
    }

    public String getItemName() {
        return itemName;
    }

    public String getStage() {
        return stage;
    }
}

class SupplyChain {
    private ArrayList<Record> history;

    public SupplyChain() {
        this.history = new ArrayList<>();
    }

    public void createItem(String itemName, String manufacturer) {
        Record record = new Record(itemName, manufacturer);
        history.add(record);
        System.out.println("Creating an item...");
        System.out.println("Item Name: " + itemName);
        System.out.println("Manufacturer: " + manufacturer);
        System.out.println("---------------------------------------------------");
        System.out.println("Item Creation Complete");
        System.out.println("---------------------------------------------------");
    }

    public void transferItem(String itemName, String handler) {
        for (Record record : history) {
            if (record.getStage().equals("Created") && record.getItemName().equals(itemName)) {
                record.transfer(handler);
                System.out.println("Transferring item...");
                System.out.println("Item Name: " + itemName);
                System.out.println("Handler: " + handler);
                System.out.println("---------------------------------------------------");
                System.out.println("Item Transfer Complete");
                System.out.println("---------------------------------------------------");
                return;
            }
        }
        System.out.println("Item not found or already transferred.");
    }

    public void listItemForSale(String itemName, String seller) {
        for (Record record : history) {
            if (record.getStage().equals("Transferred") && record.getItemName().equals(itemName)) {
                record.listForSale(seller);
                System.out.println("Listing item for sale...");
                System.out.println("Item Name: " + itemName);
                System.out.println("Seller: " + seller);
                System.out.println("---------------------------------------------------");
                System.out.println("Item Listed for Sale");
                System.out.println("---------------------------------------------------");
                return;
            }
        }
        System.out.println("Item not found or not yet transferred.");
    }

    public void purchaseItem(String itemName, String buyer) {
        for (Record record : history) {
            if (record.getStage().equals("Listed for Sale") && record.getItemName().equals(itemName)) {
                record.purchase(buyer);
                System.out.println("Purchasing item...");
                System.out.println("Item Name: " + itemName);
                System.out.println("Buyer: " + buyer);
                System.out.println("---------------------------------------------------");
                System.out.println("Item Purchase Complete");
                System.out.println("---------------------------------------------------");
                return;
            }
        }
        System.out.println("Item not found or not yet listed for sale.");
    }

    public void viewHistory() {
        System.out.println("\nViewing history...");
        System.out.println("---------------------------------------------------");
        for (Record record : history) {
            record.display();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SupplyChain supplyChain = new SupplyChain();

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Create Item");
            System.out.println("2. Transfer Item");
            System.out.println("3. List Item for Sale");
            System.out.println("4. Purchase Item");
            System.out.println("5. View History");
            System.out.println("6. Exit");
            System.out.print("Choose an option (1-6): ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter item name: ");
                    String itemName = scanner.nextLine();
                    System.out.print("Enter manufacturer name: ");
                    String manufacturer = scanner.nextLine();
                    supplyChain.createItem(itemName, manufacturer);
                    break;

                case "2":
                    System.out.print("Enter item name for transfer: ");
                    itemName = scanner.nextLine();
                    System.out.print("Enter handler name: ");
                    String handler = scanner.nextLine();
                    supplyChain.transferItem(itemName, handler);
                    break;

                case "3":
                    System.out.print("Enter item name for sale listing: ");
                    itemName = scanner.nextLine();
                    System.out.print("Enter seller name: ");
                    String seller = scanner.nextLine();
                    supplyChain.listItemForSale(itemName, seller);
                    break;

                case "4":
                    System.out.print("Enter item name for purchase: ");
                    itemName = scanner.nextLine();
                    System.out.print("Enter buyer name: ");
                    String buyer = scanner.nextLine();
                    supplyChain.purchaseItem(itemName, buyer);
                    break;

                case "5":
                    supplyChain.viewHistory();
                    break;

                case "6":
                    System.out.println("Exiting the program...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }
}