package edu.gatech.seclass.scm.service.impl;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.seclass.scm.model.Customer;
import edu.gatech.seclass.scm.model.Order;
import edu.gatech.seclass.scm.model.OrderEntry;
import edu.gatech.seclass.scm.model.Product;
import edu.gatech.seclass.scm.service.CartService;

/**
 * Created by shivendrasrivastava on 10/25/15.
 */
public class CartServiceImpl implements CartService {
    private String msg = "CartServiceImpl : ";
    private int orderEntryIdLastUsed = 0;
    private Double lastReward;

    @Override
    public Order addToOrder(Customer customer, Order order, Product product, int qty) {
        boolean contains = true;
        if(null != order.getOrderEntries()){
            for(OrderEntry entry : order.getOrderEntries()){
                if(entry.getProductID() == product.getProductID()){
                    contains = true;
                    entry.setOrderEntryPrice(product.getPrice() * qty);
                    entry.setQty(qty);
                    break;
                }else{
                    contains = false;
                }
            }
            if(!contains || order.getOrderEntries().size()==0){
                List orderEntries = order.getOrderEntries();
                orderEntries.addAll(populateOrderEntry(order, product, customer, qty));
                order.setOrderEntries(orderEntries);
            }
        }else{
            order.setOrderEntries(populateOrderEntry(order, product, customer, qty));
        }


        order.setTax(0.0);
        if(null == order.getSubtotalPrice()){
            order.setSubtotalPrice(0.0);
        }
        Double subtotalPrice = 0.0;
        Log.w("SmoothieCartManager :::", "Total Price " + order.getTotalPrice());
        for(OrderEntry entry : order.getOrderEntries()){
            Log.w("SmoothieCartManager :::", "Product ID "+entry.getProductID());
            Log.w("SmoothieCartManager :::", "Customer ID "+entry.getCustomerID());
            Log.w("SmoothieCartManager :::", "Product Desc "+entry.getProductDesc());
            Log.w("SmoothieCartManager :::", "OrderEntry Price "+entry.getOrderEntryPrice());
            Log.w("SmoothieCartManager :::", "OrderEntryID "+entry.getOrderEntryID());
            subtotalPrice += entry.getOrderEntryPrice();
        }
        order.setSubtotalPrice(subtotalPrice);
        return order;
    }

    @Override
    public Order removeFromOrder(Customer customer, Order order, Product product) {
        List<OrderEntry> orderEntries = order.getOrderEntries();

        List<OrderEntry> removeOrderEntry = new ArrayList<OrderEntry>();

        for(OrderEntry orderEntry : orderEntries){
            if(product.getProductID() == orderEntry.getProductID()){
                removeOrderEntry.add(orderEntry);
                break;
            }
        }

        for(OrderEntry orderEntry : removeOrderEntry){
            order.setSubtotalPrice(order.getSubtotalPrice() - orderEntry.getOrderEntryPrice());
        }

        orderEntries.removeAll(removeOrderEntry);

        return order;
    }

    @Override
    public Order calculateOrder(Customer customer, Order order) {

        Double reward     = customer.getReward();
        Double subtotal   = order.getSubtotalPrice();
        Double discount   = 0.0;
        Double toRemove;

        Log.i(msg, "Calculating Order");
        Log.i(msg, "-- Reward:   " + reward);
        Log.i(msg, "-- Subtotal: " + subtotal);
        Log.i(msg, "-- Discount: " + discount);

        // Handle Gold Status
        if(customer.getDiscountPercentage() > 0){
            Log.i(msg, "-- Gold Status Used");
            discount += (customer.getDiscountPercentage()/100) * subtotal;
            subtotal -= discount;

            if(order.getDiscountType().isEmpty()){
                order.setDiscountType("GOLDSTATUS|5");
            }else{
                order.setDiscountType(order.getDiscountType() + "GOLDSTATUS|5");
            }
        }
        // Handle Rewards
        if(customer.getReward() > 0){
            Log.i(msg, "-- Reward Used");
            if(subtotal > reward){
                discount += reward;
                subtotal -= reward;
                lastReward = 0.0;
                order.setDiscountType("REWARD|" + customer.getReward().toString());
            }else if(subtotal <= reward){
                Log.i(msg, "-- discount += subtotal;");
                discount += subtotal;
                lastReward = reward - subtotal;
                Log.i(msg, "-- subtotal = 0.0;");
                subtotal = 0.0;
                order.setDiscountType("REWARD|"+ subtotal);
            }
        }

        Log.i(msg, "Results");
        Log.i(msg, "-- Reward:   " + reward);
        Log.i(msg, "-- Subtotal: " + subtotal);
        Log.i(msg, "-- Discount: " + discount);

        order.setDiscount(discount);
        order.setTotalPrice(subtotal);

        return order;
    }

    @Override
    public Order deductReward(Customer customer, Order order) {
        customer.setReward(lastReward);
        lastReward = 0.0;
        return order;
    }


    /**
     *
     * @param order
     * @param product
     * @param customer
     * @param qty
     * @return List<OrderEntry>
     */
    private List<OrderEntry> populateOrderEntry(Order order, Product product, Customer customer, int qty){
        List<OrderEntry> orderEntries = new ArrayList<OrderEntry>();

        OrderEntry orderEntry = new OrderEntry();
        orderEntry.setOrderEntryPrice(product.getPrice() * qty);
        orderEntry.setProductID(product.getProductID());
        orderEntry.setProductDesc(product.getProductDesc());
        orderEntry.setCustomerID(customer.getCustomerID());
        orderEntry.setQty(qty);

        orderEntries.add(orderEntry);
        return orderEntries;
    }
}
