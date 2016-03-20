# Design Document

**Author**: Shivendra Srivastava 

**Company** Team 11 LLC

## 1 Design Considerations

Following are the points that need to be addressed prior to completing the design.

* All requirements need to be frozen before going into the design phase. Changing requirements after or during the design can cause unnecessary delays to the time line.
* All third party interactions need to finalized.
* All third party libraries to be used need to be decided.
* The minimum API level and the maximum API level for which the software is being designed needs to be decided.
* Error/Success messages that need to be shown need to be finalized by the end users.

Following are the issues that may influence the design process: -

* Change in requirements in the design phase.
* Change in API level that is to be used for the project.


### 1.1 Assumptions

Following are the assumptions for this project : -

* Third party payment processor licenses and approvals are all in place.
* Type of Credit card reader and scanner has been decided.
* The integration of the credit card scanner with the android device is assumed to be trivial and is the responsibility of the end user.
* The appropriate libraries to be used for credit card scan are provided and are not going to change for the life of the project.
* The project being developed is compatible with Android OS API 19 and above. 
* The database being used for this project will be SQLite.


### 1.2 Constraints

* **Programming Language** : - The programming language to be used is Java. Not all the team members have enought exposure to be able code without any hurdles.
* **Framework** : - The framework to be used is Android. All team members are new to this framework and this can cause delays/hurdles while programming.


### 1.3 System Environment

* The system will run on all Android devices which have Android 4.4 and above installed.
* The system will also interact with third party software provided to interact with payment systems and card scanning/swiping systems.
* The system will run on all Android supported hardware with the software version of 4.4 and above.
* The system will also utilize the default database system provided by Android OS.

## 2 Architectural Design

Following is a diagrammatic representation of the high level architecture for the app. The detailed explanation for each of the components is defined in the sub sections.

![alt text](https://github.com/gt-ud-softeng/6300Fall15Team11/blob/master/Project2/Docs/high-level-architecture.png "ssrivastava64")

### 2.1 Component Diagram

There are six mandatory components for this design and two secondary components. The mandatory components are:

1. Android Platform
1. Android Compatible Device
1. SmoothieCart Software
1. Printer
1. Customer Card
1. Network

The secondary components are:

1. Credit Card Reader
1. QR Code Reader

The *SmoothieCart Software* employs the *Android Platform* to make use of the *Android Compatible Device's* hardware resources. This will include a connected, wired or wireless, *Printer* for use of making the *Customer Cards*. The *SmoothieCart Software* will use a *Customer Card* to to identify customers. This can be done with the *QR Code Reader* or with the *Android Compatible Device's* hardware. When processing a payment, *SmoothieCart Software* will employ the *Network* to complete the transaction. The *Credit Card Reader* can be used to populate a customer's card information.

### 2.2 Deployment Diagram

![alt text](https://github.com/gt-ud-softeng/6300Fall15Team11/blob/master/Project2/Docs/deployment_diagram.png "ssrivastava64")

## 3 Low-Level Design

To better understand the working of the system, this section will go into detail about the components defined above.

**SmoothieCart.apk** 

* This is the unsigned final application that will be delivered. 
* Users will be able to install the application on to their devices and use them immediately.

**Webserver**

* The software is intended for mobile/tab use and hence the device acts as a webserver where the application is hosted.
* The software is capable of contacting any third party software/sites for payment processing and to send out emails to customers.

**External Storage**

* In order to store all the data related to the customer SQLite database will be used.
* As there can be a lot of data over a period of time a separate standalone database outside of the device is suggested. This database should primarily be used to get the customer/transaction/product details. (out of scope)
* A trivial implementation is being considered for this project and the SQLite database on the device will be used as the only database.

**Technical Design**

The software will be implemented using the MVC (Model View Controller) software architectural pattern. Following points refer to the class diagram attached to the design document.

* In accordance with MVC the Manager class will act as the Controller. This class will be responsible to route any requests coming in to the application and also route the responses back to the user.
* All the views indicated in the class diagram will be defined as Activity classes having their own activity XMLs. These screens correspond to the View of the MVC pattern.
* The activity XML will have the UI components related to every screen.
* Following are the screens that are going to be on the final application:-
	- **Home Screen** :- On application initialization the home screen is shown. This screen has the option to add a new customer, read the customer id of an existing customer via the qr code scanner or select an existing customer. On choosing to add a customer the user is redirected to the Customer Edit Screen.  If the customer qr code is scanned the customer information is fetched and the user is redirected to the customer review view.  The select customer selection also redirects the user to the customer review view. If a customer has already been selected, user will have 3 additional option: to place a new order or continue with existing order, to clear current customer and cart or view customer's purchase transactions.
	- **Customer Select Screen** :- The user can choose to select an existing customer from this screen. The user can browse through the customer list and select a customer to go to the Customer Review Screen.
	- **Customer Review Screen** :- This screen provides the user with the customer current information. Options are provided to start a new order (or continue with an existing order) for the customer, edit the customer information with the Customer Edit Screen and view the customer's past purchases with the Past Purchases Screen.
    - **Customer Edit Screen** :- This screen provides the user with the option to edit existing or add new customer information.  New customers will have a unique customerID generated that is encoded on the QRcode of the customer's card.
	- **Product Order Screen** :- This screen enables the user to fulfill the order for a customer. Products can be added to the cart of the customer. The subtotal, tax, discount and purchase total are displayed.  The user has the option to proceed to confirm their order,reset their order, proceed to the customer review or to home screens. Once on the home screen if the user chooses to select a new customer the current cart will be discarded and a new cart will be initialized for the new customer being selected.
	- **Order Confirmation / Payment Screen** :- On the confirmation/payment view screen the user has the option to either enter the credit card information or scan the credit card using the scanner. Order details including subtotal, discount, tax and total purchase amount are displayed.  After the transaction is complete the system shows the credit card confirmation made to a third party credit card processing system.  The user can optionally navigate to the home screen with the current customer chosen. The user can either choose a new customer or make a new order for the earlier customer.
	- **Past Purchases Screen** :- This screen shows the past orders of the customer. The past orders view shows the date and time when the transaction was made along with the rewards credited/debited with every transaction.
* The controller on receiving a request uses the model layer to process the requests and then dispatches the appropriate view.
* **Customer Service** :- The customer service provides the functionality to add/edit a customer, fetching a customer when the user scans the qr code from a customer card, returning one or all customers and displaying customer transactions.
* **Payment Processing Service** :- The payment service reads the credit card and processing payment by contacting the third party to get the credit card transaction authorized. All exception handling with respect to the payment processing is done in this class and the appropriate error/success message is parsed and the appropriate view is decided which the controller can then dispatch.
* **Reward Service** :- The reward service handles the calculation of reward that needs to be credited or debited to the customer's order. 
* **Send Email Service** :- This service sends out gold status and/or credit earned emails to the customer based on the calling logic defined in the Reward Service class.
* **Product Service** :- This service fetches all products or an individual product.  
* **Cart Service** :- This service adds and removes items from an order, calculates the order totals and deducts discounts.  
* **QRCode Service** :- This service scans the customer card.  

**Database Design**

The application will be supported on the back end by an SQLite database. The database will have the following tables:-

* **Customer** :- This table will be used to store all the details pertaining to a customer. Following are the attributes of this table:-
    - Name (CHAR(50))               NOT NULL
    - Billing Address (CHAR(200))   NOT NULL
    - Email Address (CHAR(50))      NOT NULL
    - Customer ID (INT(9))          NOT NULL
    - Status (CHAR(10))         
    - Reward (REAL)
    - Reward Date (DATE)
    - Discount (REAL) 


* **Product** :- This table will be used to store the products and its details. Following are the attributes of this table:-
    - Product ID (CHAR(50))         NOT NULL
    - Product Desc (CHAR(50))       NOT NULL
    - Price (CHAR(50))              NOT NULL

* **Transaction** :- This table will store all the transactions pertaining to all the customers who make a purchase using the application. The table will have the following attributes:-
    - Customer ID (INT(9))          NOT NULL
    - Transaction ID (INT(9))	    NOT NULL
    - Order ID (INT(9))             NOT NULL
    - Product ID (CHAR(50))         NOT NULL
    - Product Desc (CHAR(50))       NOT NULL
    - Amount (REAL)                 NOT NULL
    - Discount (REAL)               NOT NULL
    - Discount Type (CHAR(5)))      NOT NULL
    - Credit Card No (INT(30))      NOT NULL
    - Auth Response (CHAR(50))      NOT NULL
    - Transaction_Date (CHAR(20))   NOT NULL

* **Order** :- This table will store all the data related to every order made by any customer who have made purchases using the application. Following are the attributes of the table:-
    - Order ID (INT(9))             NOT NULL
    - Customer ID (INT(9))          NOT NULL
    - Total Price (REAL)            NOT NULL
    - Tax (REAL)                    NOT NULL
    - Discount (REAL)               NOT NULL
    - Discount Type (CHAR(50))
    - Order Entry ID (INT(9))       NOT NULL
    - Order_Place_date (CHAR(20))   NOT NULL


* **Order Entry** :- Any given order can have multiple types of items and to handle that we need to have an Order Entry table which will have the following attributes:-
    - Order Entry ID (INT(9))       NOT NULL
    - Order ID (INT(9))             NOT NULL
    - Customer ID (INT(9))          NOT NULL
    - Product ID (CHAR(50))         NOT NULL
    - Product Desc (Char(50))       NOT NULL
    - Discount (REAL)
    - Discount Type (CHAR(50))
    - Order Entry Price (REAL)      NOT NULL
    - QTY (INT(2))                  NOT NULL
         
The above tables will be created along with the construction of the application and appropriate constraints and keys will be applied.

### 3.1 Class Diagram

Below is the class diagram that represents the low level detailed design for the software.

<img src="https://github.com/gt-ud-softeng/6300Fall15Team11/blob/master/Project2/Docs/SmoothieCartUMLDesign.png" width="600" height="900" />

### 3.2 Other Diagrams

The use case diagram is added as a separate document to the list of submissions.

## 4 User Interface Design

The below images show the teams vision for the application in a rough format. Not all screens are covered and shown screen are likely to change.

**Home Screen**

<img src="https://github.com/gt-ud-softeng/6300Fall15Team11/blob/master/Project2/Docs/HomeScreen2.png" width="110" height="200" />

**Select Customer Screen**

<img src="https://github.com/gt-ud-softeng/6300Fall15Team11/blob/master/Project2/Docs/CustSelect.png" width="110" height="200" />

**Product Order Screen**

<img src="https://github.com/gt-ud-softeng/6300Fall15Team11/blob/master/Project2/Docs/Products.png" width="110" height="200" />

**Order Confirmation/Payment Screen**

<img src="https://github.com/gt-ud-softeng/6300Fall15Team11/blob/master/Project2/Docs/OrderConfirmation.png" width="110" height="200" />

**Customer Review Screen**

<img src="https://github.com/gt-ud-softeng/6300Fall15Team11/blob/master/Project2/Docs/CustomerReview.png" width="110" height="200" />

**Customer Edit Screen**

<img src="https://github.com/gt-ud-softeng/6300Fall15Team11/blob/master/Project2/Docs/AddCustomer.png" width="110" height="200" />

**Prior Purchases**

<img src="https://github.com/gt-ud-softeng/6300Fall15Team11/blob/master/Project2/Docs/Purchases.png" width="110" height="200" />

