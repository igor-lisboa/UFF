public class Customer implements CustomerTransaction {
    private String name="jao";

    @Override
    public void chargeCustomer() {
        System.out.println("Me de dinheiro!");
    }

    @Override
    public String getName() {
        return this.name;
    }
}