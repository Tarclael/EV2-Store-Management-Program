package storeUtils;

public class Supplier {
    // ===== Attributes (5 total) =====
    private int supplierId;
    private String name;
    private String company;
    private String phoneNumber;
    private boolean active;

    // ===== Constructors =====
    public Supplier() {
        this.supplierId = 0;
        this.name = "Unknown";
        this.company = "Unregistered";
        this.phoneNumber = "0000000000";
        this.active = false;
    }

    public Supplier(int supplierId, String name, String company, String phoneNumber, boolean active) {
        this.supplierId = supplierId;
        this.name = name;
        this.company = company;
        this.phoneNumber = phoneNumber;
        this.active = active;
    }

    // ===== Getter & Setter =====
    public int getSupplierId() { return supplierId; }
    public void setSupplierId(int supplierId) { this.supplierId = supplierId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCompany() { return company; }
    public void setCompany(String company) { this.company = company; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    // ===== Methods =====
    public void activateSupplier() {
        this.active = true;
        System.out.println(name + " (" + company + ") is now active.");
    }

    public void deactivateSupplier() {
        this.active = false;
        System.out.println(name + " (" + company + ") has been deactivated.");
    }

    public void updatePhone(String newPhone) {
        this.phoneNumber = newPhone;
        System.out.println("Phone number updated for " + name + ".");
    }

    public void showSupplierInfo() {
        System.out.println("Supplier ID: " + supplierId + " | Name: " + name +
                " | Company: " + company + " | Phone: " + phoneNumber +
                " | Active: " + (active ? "Yes" : "No"));
    }

    public String getSummary() {
        return name + " from " + company + " (" + (active ? "Active" : "Inactive") + ")";
    }

    // ===== Overriding toString() =====
    @Override
    public String toString() {
        return "Supplier[" + supplierId + ", " + name + ", " + company +
                ", " + phoneNumber + ", active=" + active + "]";
    }
}
