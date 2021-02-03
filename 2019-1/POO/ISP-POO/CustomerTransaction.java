interface CustomerTransaction {
    public void chargeCustomer();

    public void prepareInvoice();

    public String getDate();

    public String getName();

    public void getProductBreakDown();
}