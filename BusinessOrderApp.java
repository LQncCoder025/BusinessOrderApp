import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import javax.swing.*;

// Interface for order processing
interface OrderProcessor {
    void processOrder();
}

// Interface for appointment scheduling
interface AppointmentScheduler {
    void scheduleAppointment();
}

// Base class for a business
class Business {
    protected String name;
    protected String[] services;
    protected double[] prices;

    public Business(String name, String[] services, double[] prices) {
        this.name = name;
        this.services = services;
        this.prices = prices;
    }

    public void displayServices() {
        System.out.println("Mobile Car Detailer Services!:");
        for (int i = 0; i < services.length; i++) {
            System.out.println((i + 1) + ". " + services[i] + " - $" + prices[i]);
        }
    }
}

// Derived class for a specific business type
class MobileCarDetailer extends Business implements OrderProcessor, AppointmentScheduler {
    private int selectedService;
    private String appointmentDate;
    private String appointmentTime;

    public MobileCarDetailer() {
        super("Mobile Car Detailer", new String[]{"Basic Wash", "Full Detail", "Interior Cleaning"}, new double[]{25.0, 50.0, 40.0});
    }

    public void takeOrder() {
        Scanner scanner = new Scanner(System.in);
        displayServices();
        System.out.print("Select a service (1-3): ");
        selectedService = scanner.nextInt();
        scanner.nextLine();
        scheduleAppointment();
    }

    @Override
    public void processOrder() {
        System.out.println("Order Confirmed: " + services[selectedService - 1] + " - $" + prices[selectedService - 1]);
    }

    @Override
    public void scheduleAppointment() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter appointment date (YYYY-MM-DD): ");
        appointmentDate = scanner.nextLine();
        System.out.print("Enter appointment time (HH:MM AM/PM): ");
        appointmentTime = scanner.nextLine();
        saveOrderToFile();
    }

    public void saveOrderToFile() {
        try (FileWriter writer = new FileWriter("order_summary.txt")) {
            writer.write("Business: " + name + "\n");
            writer.write("Service: " + services[selectedService - 1] + "\n");
            writer.write("Price: $" + prices[selectedService - 1] + "\n");
            writer.write("Appointment Date: " + appointmentDate + "\n");
            writer.write("Appointment Time: " + appointmentTime + "\n");
            System.out.println("Order summary saved to file.");
        } catch (IOException e) {
            System.out.println("Error saving order.");
        }
    }
}

// Main class
public class BusinessOrderApp {
    public static void main(String[] args) {
        MobileCarDetailer business = new MobileCarDetailer();
        business.takeOrder();
        business.processOrder();
    }
}
