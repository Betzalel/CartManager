# Use Case Model

**Author**: Gail Larkin

**Company**: Team 11 LLC.

## 1 Use Case Diagram

![alt text](https://github.com/gt-ud-softeng/6300Fall15Team11/blob/GL-D1/Project2/Docs/SmoothieCart_UseCase.png "UseCaseDiagram")

## 2 Use Case Descriptions

### CUSTOMER MAKES A PURCHASE

Requirement #1c – PROCESS PURCHASES

Requirement #1d – KEEP TRACK OF REWARDS

Requirement #4 – CUSTOMER PAYMENT BY CREDIT CARD

Requirement #5 – CREDIT CARD SCANNER READS CREDIT CARD INFORMATION

Requirement #6 – CREDIT CARD PAYMENT PROCESSING

Requirement #9 – EARNING/APPLYING STATUS CREDIT 

Requirement #10 – EMAIL CUSTOMER WHEN REWARD CREDIT IS EARNED

**Pre-conditions:**

Customer presents a customer card that has customerID encoded in a QR code in the system

**Post-conditions:**  

1. An approved transaction will have purchase transaction and loyalty discount (if applicable) details recorded  
2. A declined transaction will be recorded and loyalty discounts will not be applied to the customer.

**Scenarios:**  

1.	Manager scans customer’s QR code with scanner retrieving customerID  
2.	Customer makes selection of items to be purchased  
3.	System provides total purchase price  
  a.	 System checks for and applies existing Discounts (Status discount first, if applicable and then Reward discount)  
  b.	 If customer is not currently a Gold status customer, System will check if Gold status has been earned
4.  Customer pays with a credit card  
5.	Customer’s credit card is swiped by a card scanner  
6.	System reads the credit card information: cardholder’s name, account number, expiration date    
7.	Manager enters the card’s security code    
8.	System sends credit card information and purchase amount to payment processing service  
9.	System confirms approved payment status    
10.	System records customer transaction details (See TRACK PURCHASES use case)  
11.	System records any loyalty discounts earned from purchase (See TRACK REWARDS use case)  

**Alternative:  Customer Discount**

*At step 3a:*

Customer already has a “gold” status discount:

1.	System calculates a 5% discount off purchase price => New total purchase price is calculated

Customer has a reward discount:

1.	System checks expiration of discount 

  a.	if discount was awarded a year ago or less, the system subtracts discount off total purchase price =>  New total purchase price is calculated


**Alternative:  Immediate Customer Discount(s) earned**

*At step 3b:*

System checks if customer has just earned “gold” status

1.	System checks customer’s total purchases in a calendar year including current total purchase price.  If total purchases are $500 or above, system calculates a 5% discount off purchase price => New total purchase price is calculated
2.  Customer's account is updated to "gold" status
3.  System checks if customer has earned a reward discount (See TRACK REWARDS use case)

**Alternative:  Authorization Failure**

*At step 9:*

System cannot confirm an approved payment status or the customer’s card is declined
1.	Allow user to scan card again or scan a new card (starting at step #4)

If all attempts are declined; system cancels transaction and steps 9 – 11 are not completed

### TRACK REWARDS

Requirement #1d – KEEP TRACK OF REWARDS

Requirement #7 – EARNING REWARD CREDIT TOWARDS NEXT PURCHASE

Requirement #8 – EMAIL CUSTOMER WHEN REWARD CREDIT IS EARNED

**Pre-conditions:**

Customer completes a purchase transaction greater than or equal to $50 dollars

**Post-conditions:**

System awards customer with a $5 dollar credit.
System initiates email to customer to notifying them of credit.

**Scenarios:**

1.	System checks total transaction price of purchase; when purchase > $50 dollars:  
  a.	Customer is rewarded with a $5 credit towards next purchase  
  b.	Customer is emailed that reward credit is earned  

### TRACK PURCHASES

Requirement #1d – KEEP TRACK OF PURCHASES

**Pre-conditions:**

Customer completes a purchase transaction.  

*Post-conditions:*

System records transaction details: date of transaction, amount of purchase before discount, applicable discount and discount type

**Scenarios:**

1.	Customer completes a purchase transaction.  
2.  System records customer’s transaction information: date of transaction, amount of purchase before discount, applicable discount and discount type

### PURCHASE HISTORY REPORTING

Requirement #11 – CUSTOMER PURCHASE DETAILS

**Pre-conditions:**

Customer presents a customer ID that exists in system

**Post-conditions:**

System displays list of customer transactions

**Scenarios:**

1.	Manager uses a customer ID to display a list customer’s transactions by:  
  a.	Transaction date  
  b.	Transaction purchase before discounts  
  c.	Applicable discounts and type

### ADD CUSTOMERS

Requirement #1a – ADD A CUSTOMER

Requirement #2 – CUSTOMER DETAILS (a-d)

Requirement #3 – CUSTOMER CARD

**Pre-conditions:**

Customer provides required information

**Post-conditions:**

Customer receives customer card encoded with customer's unique ID

**Scenarios:**

1.	Customer provides: name, billing address, email address
2.	System assigns a 9-digit unique numeric ID
3.	Customer is provided with a customer card encoded with the customer’s unique ID

### EDIT CUSTOMERS

Requirement #1b – EDIT A CUSTOMER

Requirement #2 – CUSTOMER DETAILS (a – c)

**Pre-conditions:**

Existing customer presents customer card

**Post-conditions:**

1.  Manager can edit customer information saving changes  
2. Manager can choose to cancel edit action; discarding any changes that may have been made  

**Scenarios:**  

1.	Customer provides customer card    
2.	Manager can edit customer information: name, billing address, email address  
3.	Manager saves changes  

**Alternative:  Cancel changes**

*At step 3.*

Manager can cancel with changes not saved 