# Test Plan

**Author**: Monica Ruth 

**Company**: Team 11 LLC

## 1 Testing Strategy

### 1.1 Overall strategy


**Unit-Testing Strategies**: JUnit tests will be written by Monica Ruth (mruth3).  Unit tests will be written for each relevant method in each class.  Unit tests will be ran throughout the design, especially during integration testing and regression testing.  Unit tests will be used to run white-box tests as well as some black-box test.  

**Integration-Testing Strategies**: As each team member integrates their part of the code they must ensure that their code runs as expected and properly executes with the other pieces of code.  If there is an error the team members involved in writing the affected code will work together to resolve the issue.  Test cases that only involve the modules being integrated at that time will be ran.

**System-Testing Strategies**: Using the test-cases as a guide, all team members will begin to test the system once all unit tests pass.  The most common test-cases will be tested first, with each iteration testing a more specific, less common scenario.  All team members are involved in this process.  Test cases will be divided amongst team members.  Testing will begin with making sure the system works as expected, and then testing will focus on trying to break the system.  

**Regression-Testing Strategies**: As a bug is fixed, the unit tests and system tests that have already been completed will be re-run.  Focusing on the classes/test case scenarios that are directly or indirectly affected by the code changes.  All team members are responsible for regression testing their bug fixes.


### 1.2 Test Selection


The system testing will focus on black-box testing.  The Category-Partition method will be used for the black-box testing.  Most of the white-box testing will be done through the unit tests.  The white-box testing techniques we will use will be Limits of the different data types will be tested as well on any formulas.

The majority of the system testing will revolve around black-box testing.  Test cases will be written around the requirements, focusing heavily on the business rules that need to be implemented.  Any possible annomolies will be targeted.    

### 1.3 Adequacy Criterion

The test cases will be compared with the project requirements.  There must be a test case for every criteria and each option available for that criteria.  Functional (black-box and specification-based) testing will be derived from the software specifications. This will be done during the system testing. The structural test obligation will come from the code.  The structural testing will be done during the unit testing. The goal is to have 100% statement coverage.  If the test cases address all the possible scenarios of the project requirements then they should be considered adequate.

### 1.4 Bug Tracking

Any bugs found will be documented in a shared table, specifying the steps followed, the bug/error encountered, the date/time and team member name who identified the bug.  When the problem is fixed, the table will be updated to reflect this.  Regression testing will be done when code fixes are pushed.  All team members are responsible to help fix errors in the code.

### 1.5 Technology

JUnit will be used to complete our unit testing.  System testing will be assisted with a pre-populated database of test customers, each customer fitting a different test scenario.  TSLGenerator will be used to develop the different test cases for the system.  Android Studio will be used for the project development.

## 2 Test Cases

|Test Case|Steps|Expected Result|Actual Result|Notes|
|---------|-----|---------------|-------------|-----|
|1. New Customer Makes Purchase|1.Open the smoothie cart app and press the 'Add Customer' button.|The "Customer Edit" screen appears|||
||2.Enter the Customer information|The customer information is saved in the system.  The customer is assigned a customer ID. The user is redirected to the 'Customer Review' screen.  The QR reader is activated on this screen.|||
||3.Scan a new customer card with the QR reader|New customer card QR code is linked to the new customer|||
||4. Press the "Shop" button|Redirected to the "Items" screen|||
||5. Select a smoothie to purchase and press the "Add to Cart" button.|A smoothie is added to the cart.  The user stays on the same page.  The total cost of the smoothie is shown on the screen.|||
||6. Select the "Checkout" button|Redirected to the 'Current Order' screen|||
||7. Press the "Pay" button|Redirected to 'Payment View' screen|||
||8. Swipe the credit card|Payment confirmation screen is displayed.Transaction information is saved.  Possible credit or status change is calculated and saved. E-receipt is sent to customer Redirected to "Home" screen|||
|2. Existing Customer Makes Purchase|1. From the "Home" page, press the "Scan QR Code" button, then scan the customer card|Go to the "Customer Review" screen|||
||2. Press the "New Order" button|Go to the "Current Order" screen|||
||3. Select a smoothie|Selected smoothie is added to card of customer|||
||4. Press the 'Pay' button|Taken to "Payment" screen"|||
||5. Swipe the credit card| Payment confirmation screen is displayed.Transaction information is saved.  Possible credit or status change is calculated and saved. E-receipt is sent to customer Redirected to "Home" screen|||
|3. View Customer Transactions|1.Swipe the customer card|Taken to the Customer Review Screen|||
||2. Press the "Past Orders" button|"Past Purchases" screen appears, displaying all past order information|||
|4. Customer Receives a Credit|1. Add >50.00 of product into cart.  Select the "Pay" button.|Taken to the "Payment" screen|||
||2. Swipe the credit card|Transaction saved, credit earned and date earned stored, email and e-receipt sent. Payment confirmation screen displayed and redirected to home page.|||
||3. Select the "Customer Review" button|Customer information is displayed along with 'Credit' displaying $5.00|||
|5. Customer Reaches 'Gold" Status - No Reward|1. Use existing test customer 'Robert".  Add 50.00 of smoothie to cart.|Gold status reached.  Display discount on screen|||
||2. Press "Pay" button|Amount owed is reduced to $47.50 - no credit is earned|||
||3. Swipe the credit card|Transaction saved, e-receipt and email for gold status sent.  Payment confirmation screen displayed and redirected to home page|||
||4. Select the "Customer Review" button|Customer information is displayed along with 'Credit' displaying $0.00 and 'Status' set to "Gold"|||
|6. Customer Reaches 'Gold" Status - Reward|1. Use existing test customer "Robert".  Add $55.00 of smoothie to cart|Gold status reached.  Display discount on screen|||
||2. Press "Pay" button|Amount owed is reduced to $52.25 - credit is earned|||
||3. Swipe the credit card|Transaction saved, credit earned and date earned stored, email and e-receipt sent. Payment confirmation screen displayed and redirected to home page.|||
||4. Select the "Customer Review" button|Customer information is displayed along with 'Credit' displaying $5.00 and 'Status' set to "Gold"|||
|7. Edit Customer Information|1. Scan Customer card|Taken to "Customer Review" screen|||
||2. Press the "Edit Customer" button|Taken to the "Customer Edit" screen|||
||3. Change any/all customer fields, press "Save"|Customer record should be changed.  Redirected to "Customer Review" scren|||
|8. Customer Makes a Purchase Without a Customer Card|1. From the "Home" screen, press the "Select Customer" button|"Customer Select" screen is displayed|||
||2. Search for the customer by Last Name, click on the customer name|"Customer Review Screen" is displayed|||
||3. Select the "New Order" button|"Current Order" screen is displayed|||
||4. Add smoothies to cart|Total is displayed with any discounts for customer|||
||5. Select "Pay"|Redirected to "Payment" screen|||
||6. Press "Scan Card"|Credit card scanner is activated|||
||7. Scan credit card|Transaction information is saved.  If >=$50 then a $5.00 credit is saved for next purchase.  If transaction totals to date=>$500 then customer status is set to 'Gold'. Redirected to "Home" screen|||
|9. Credit Card Declined|1. After arriving on "Payment" screen and scanning the credit card, transaction is denied.|Error message stating "Transaction Denied".  No updates are added to the customer record, redirected to "Current Order" screen.|||
||2. Press the "Home" button|Home page is displayed with current customer name showing|||
||3. Press the "Customer Review" button|"Customer Review" screen is displayed|||
||4. Press the "Past Purchases" button|Past purchases are displayed - the denied transaction should not show up neither should any credit or status changes have occurred.|||
|10. Credit Reward Used - No Reward Given|1. Order $50.00 worth of smoothies with a test customer that has a credit reward available|The credit reward will reduce the amount owed to $45.00.  No future credit should be given.|||
||2. After making a payment, check the customer information on the "Customer Review" screen|The field 'Credit' should show a $0 balance|||
|11. Partial Credit Used|1. Order 1 smoothie less than $5 for a test customer that has a $5 credit|The balance owed should be $0.00|||
||2. Select "Pay"|The transaction should be saved, an e-receipt sent, and the credit amount should be reduced by the amount of the purchase.  Confirmation screen appears, redirected to home page|||
|12. Customer already has "gold" status - earns reward|1. Use existing test customer that has "gold" status.  Add $55.00 of smoothie to cart|Display discount on screen|||
||2. Press "Pay" button|Amount owed is reduced to $52.25 - credit is earned|||
||3. Swipe the credit card|Transaction saved, credit earned and date earned stored, email and e-receipt sent. Payment confirmation screen displayed and redirected to home page.|||
||4. Select the "Customer Review" button|Customer information is displayed along with 'Credit' displaying $5.00 |||
|12. Customer already has "gold" status - no reward|1. Use existing test customer that has "gold" status.  Add $50.00 of smoothie to cart|Display discount on screen|||
||2. Press "Pay" button|Amount owed is reduced to $47.50 - credit is not earned|||
||3. Swipe the credit card|Transaction saved. Payment confirmation screen displayed and redirected to home page.|||
||4. Select the "Customer Review" button|Customer information is displayed credit should be $0 |||





 
