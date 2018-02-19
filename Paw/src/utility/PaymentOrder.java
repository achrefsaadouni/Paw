package Utility;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.lang.String;
import com.stripe.Stripe;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.Invoice;
import com.stripe.model.Token;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Mohamed Abdelhafidh
 * @Description International Credit/Debit Payment Gateway.Use it carefully
 * To switch it to live mode just change the api_token to live mode tokens
 * use it carefully
 * All right reserved Mohamed Abdelhafidh
 */
public class PaymentOrder {
    
    private String cardnumber,cvv,exp_month,exp_year,email,city,state,address,country,zip,name;
    private double ammount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public   PaymentOrder(String cardnumber, String cvv, String exp_month, String exp_year, Double ammount, String email, String city, String state, String address, String country, String zip) {
        this.cardnumber = cardnumber;
        this.cvv = cvv;
        this.exp_month = exp_month;
        this.exp_year = exp_year;
        this.ammount = ammount;
        this.email = email;
        this.city = city;
        this.state = state;
        this.address = address;
        this.country = country;
        this.zip = zip;
    }

    public String getCardnumber() {
        return cardnumber;
    }

    public void setCardnumber(String cardnumber) {
        this.cardnumber = cardnumber;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getExp_month() {
        return exp_month;
    }

    public void setExp_month(String exp_month) {
        this.exp_month = exp_month;
    }

    public String getExp_year() {
        return exp_year;
    }

    public void setExp_year(String exp_year) {
        this.exp_year = exp_year;
    }

    public Double getAmmount() {
        return ammount;
    }

    public void setAmmount(Double ammount) {
        this.ammount = ammount;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
        public  Charge createCharge(String api,double ammount,String name,String cc,String exp_m,String exp_y,String cvv,String address,String city,String state,String country,String zip,String email) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException
    {
                        Stripe.apiKey=api;

        Charge charge;
        Map<String, Object> chargeParams = new HashMap<>();
       
chargeParams.put("amount",(int) Math.round(ammount*42));
chargeParams.put("currency", "usd");
chargeParams.put("description", "Charge from "+email);
chargeParams.put("customer", createClient(email, name, cc, exp_y, exp_m, cvv, address, city, state, zip, country).getId());
            charge =Charge.create(chargeParams);
        return charge;
    }
        public  Token createToken(String name,String cc,String exp_y,String exp_m,String cvv,String address,String city,String state,String zip,String country) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException 

    {
        Token token ;
            
                Map<String, Object> tokenParams = new HashMap<>();
                Map<String, Object> cardParams = new HashMap<>();
                cardParams.put("name",name);
                cardParams.put("number", cc);
                cardParams.put("exp_month", exp_m);
                cardParams.put("exp_year", exp_y);
                cardParams.put("cvc", cvv);
                cardParams.put("address_country", country);
                cardParams.put("address_city", city);
                cardParams.put("address_state", state);
                cardParams.put("address_line1", address);
                cardParams.put("address_zip", zip);
                tokenParams.put("card", cardParams);
                
                token= Token.create(tokenParams);
                  
           
    return token;

    }
        
        
        
    public  Customer createClient(String email,String name,String cc,String exp_y,String exp_m,String cvv,String address,String city,String state,String zip,String country) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException
    {
Customer customer = null;
        Map<String,Object> customerParams = new HashMap<String, Object>();
        
        
        customerParams.put("email", email);
        customerParams.put("source", createToken(name, cc, exp_y, exp_m, cvv, address, city, state, zip, country).getId());
            customer = Customer.create(customerParams);
            
        return customer;
    }
    
}
