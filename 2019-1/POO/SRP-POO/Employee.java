public class Employee {
    private long id;
    private String name;
    private String department;
    private Boolean working;

    public Employee(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getDepartment() {
      return this.department;
    }

    public Boolean getWorking() {
      return this.working;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setWorking(Boolean working) {
        this.working = working;
    }

    public String toString() {
        return "id:" + this.id + ";name:" + this.name + ";department:" + this.department + ";working:"
                + this.working.toString();
    }
}