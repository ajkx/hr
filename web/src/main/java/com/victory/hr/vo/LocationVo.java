package com.victory.hr.vo;

/**
 * @author ajkx_Du
 * @create 2016-11-29 9:44
 */
public class LocationVo {
    private String name;
    private String address;
    private String postCode;
    private String city;
    private int countryid;
    private String phone;
    private String fax;
    private double showOrder;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getCountryid() {
        return countryid;
    }

    public void setCountryid(int countryid) {
        this.countryid = countryid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public double getShowOrder() {
        return showOrder;
    }

    public void setShowOrder(double showOrder) {
        this.showOrder = showOrder;
    }

    @Override
    public String toString(){
        return "Location{" +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", postCode='" + postCode + '\'' +
                ", address='" + address + '\'' +
                ", city=" + city +
                ", countryid='" + countryid + '\'' +
                ", phone='" + phone + '\'' +
                ", fax='" + fax + '\'' +
                ", showOrder='" + showOrder + '\'' +
                '}';
    }
}
