package storeUtils;

public class Employee {
    // ===== Attributes (5 total, with various data types) =====
    private int id;
    private String name;
    private String position;
    private double salary;
    private boolean onDuty;

    // ===== Constructors =====
    public Employee() {
        this.id = 0;
        this.name = "Unknown";
        this.position = "Staff";
        this.salary = 0.0;
        this.onDuty = false;
    }

    public Employee(int id, String name, String position, double salary, boolean onDuty) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.salary = salary;
        this.onDuty = onDuty;
    }

    // ===== Getter & Setter =====
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }

    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }

    public boolean isOnDuty() { return onDuty; }
    public void setOnDuty(boolean onDuty) { this.onDuty = onDuty; }

    // ===== Methods =====
    public void startDuty() {
        this.onDuty = true;
        System.out.println(name + " is now on duty.");
    }

    public void endDuty() {
        this.onDuty = false;
        System.out.println(name + " has ended their duty.");
    }

    public void promote(String newPosition, double raise) {
        this.position = newPosition;
        this.salary += raise;
        System.out.println(name + " has been promoted to " + position + ".");
    }

    public void displayInfo() {
        System.out.println("Employee ID: " + id + " | Name: " + name +
                " | Position: " + position + " | Salary: $" + salary +
                " | On Duty: " + (onDuty ? "Yes" : "No"));
    }

    public String getSummary() {
        return name + " (" + position + ") - $" + salary;
    }

    // ===== Overriding toString() =====
    @Override
    public String toString() {
        return "Employee[" + id + ", " + name + ", " + position + ", $" + salary + ", onDuty=" + onDuty + "]";
    }
}
