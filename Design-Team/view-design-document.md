#**Smoothie Cart Design Document**

##1. User Interface

###1.1 Home Screen

The *Home Screen* is the default screen when the program loads. The home screen has buttons for the following options:
- Add New Customer 									(button)
- Select Customer  									(button)
- Exit    		   									(button)

Add New Customer will advance the user to a blank *Customer Edit* view.
Select Customer will advance the user to the *Customer Select* view.
Exit will exit the program.
The *Home Screen* also enables the QR Code reader. When a QR Code is read, the program will advance to the Order view with the inputed customer's information. 

**NOTE:** The QR Code Reader is disabled when not in the *Home Screen*. This prevents interuptions from other scans during a customer's servicing.

###1.2 Customer Select

The *Customer Select* view consists of the following:
- Search bar										(input field)
- List of customers by Last Name, First Name 		(list of objects)
- Home Screen 		 								(button)

The search bar will look for names (space delimited) and phone numbers. Matches will be shown in the list of customers area where they are selectable. The default will to have all customers visible. When selecting a customer, the user will be taken to the *Customer Review* view.
The Home Screen button will return the user to the *Home Screen*. The QR Code Reader is enabled during this screen. If a QR Code is read, it will be used as the sole input for the search bar and will display the associated customer.

###1.3 Customer Review

The *Customer Review* view consists of the following:
- Customer information 								(text fields)
- Edit 												(button)
- Previous Orders 									(button)
- New Order 										(button)
- Home Screen 										(button)
- Customer Select 									(button)

The information for the selected customer is displayed on this screen. Selecting the Edit button will forward the user to the Customer Edit view. The Previous Orders button will move the user to the *Past Purchases* view. Pressing the New Order button will take the user to the *Current Order* view. The final two buttons will take the user back to the previous screens.

###1.4 Customer Edit

The *Customer Edit* view consists of the following:
- Customer information								(input fields filled out with current information)
- Submit											(button)
- Home screen 										(button)
- Customer Select 									(button)
- Customer Review 									(button)

This screen will accept input for the needed fields. The Submit button will save the changes to the data base and return the user to the *Customer Review* page, which will contain the updated information. The other buttons will take the user back to the previous screens.

###1.5 Past Purchases

The *Past Purchases* view consists of the following:
- Past purchases 									(list of objects)
- Home screen 										(button)
- Customer Select 									(button)
- Customer Review 									(button)

The *Past Purchases* view will list all of the purchases of a customer by date from most recent to oldest. The buttons will take the user back to the previous screens.

###1.6 Current Order

The *Current Order* view consists of the following:
- Purchase list 									(list of objects)
- Pay 												(button)
- Add smoothie										(button)
- Remove smoothie 									(button)
- Home screen 										(button)
- Customer Review 									(button)

This view is used to create an order. There is a list of items that are being purchased. The Add/Remove smoothie buttons will add/remove a smoothie from the list of items to be purchased. The pay butoon will move the user to the *Payment* view.

###1.7 Payment

The *Payment* view consists of the following:
- Amount 											(text field)
- Card information 									(text fields)
- Status											(text field)
- Pay 												(button)

This view is used to finalize payment and provide feedback from the transaction. Status will initially be set to blank. Popups will be used when errors occure.

###1.8 Items

The *Items* view would list all the items that are available for ordering.
- This screen will essentially be a part of the customer add/edit flow and will also be available when the manager chooses to go into the shop mode.
- When a new customer is registered an option to *Shop* can be shown so that the manager can go ahead and start adding items to the cart for purchase.
- Clicking on *Shop* redirects the user to the *Items* screen. All the items available for sale are shown on this screen with their prices. Each item also has an add to cart button/image.
- If the customer is already registered and wants to shop, swiping the customer card takes the user to the *Items* screen.
- If the customer wants a profile change that can be done by clicking on the *My Account* link on the top of the *Items* screen.





