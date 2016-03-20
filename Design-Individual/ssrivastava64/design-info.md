##DESIGN INFO

### DESIGN DETAIL:

In addition to the UML diagram following are a few more details about the design:

* Manager class will be exposed to the end user. This class provides interactions with other classes that accomplish the goals of the system.

* The manager class also handles the displaying of all the transaction and the reward information associated with any customer.

* Each type of action has been separated into different classes. 

* CustomerService helps in adding and editing customer information into the system.

* PaymentService deals with the processing of the payment once the customer has decided to checkout.

* RewardService uses the customer id and the cart details to calculate the reward that needs to be added/deleted from the customer.

* EmailService sends emails to customers based on the calculation result from the reward service.

* The dotted lines from the service to the system objects indicate dependency on those classes. For e.g. CustomerService uses the Customer class to add/edit customer information into the system.

* Customer class encompasses the name, billing address, email address, customer id, customer card, status and reward. The system updates the reward earned into the customer and their status as per the calculation.

* Smoothie class is the item that will be for sale and will be added to the cart and the transaction.

* Discount class stores the discount types and the amount associates with each discount type. The cart will contain one or more discount types.

* Every transaction is represented by the Transaction class and each transaction is associated with a Credit Card.

* Every customer has a customer card which is denoted by the CustomerCard class.

### ASSUMPTIONS:

* The design takes into consideration that the system is simple and single threaded.

* The manager is the only person who interacts with the system.

* Only one person interacts with the system at any instant.

* Only one order is placed at any instant.

* Email sending capabilities are provided out of the box and hence not considered in this design.

* Payment processing capabilities are provided out of the box and hence not considered in this design.

* Credit Cards are validated by the payment processor and not handled by this system.


