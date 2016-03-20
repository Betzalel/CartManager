# Development Task List

## 1. Shivendra

**Task List**

* Initial project set up - will be done by Saturday evening.
* Database table creation - will be done by Sunday evening.
* Manager/Controller class creation
* Home Screen (QR Scan integration, View) - story-home
* Select Customer Screen (QR Scan integration, View) - story-sel-cust
* Sample data for transactions, bug fixes.
* Work on the product screen to update tax, total, discount, etc
* Hook up with existing reward and order services.


## 2. Robert

**Task List**

* Select Customer Screen (DAO, Service Component) - story-sel-cust-det
* Home Screen (DAO, Service Component) - story-sel-home-det
* Work on the customer edit screen to fix the issue with the crash
* Work on the order screen to hook up with the order and transactions saving functionality.
* Ensure that 0 value transactions do not have to pay and are saved.


## 3. Gail

**Task List**

* Customer Review Screen (View, Service and DAO Component) - story-cust-review
* Customer Edit Screen (View, Service and DAO Component) - story-cust-edit
* Complete the manual and the documentation update to reflect current app.
* Work on add and edit customer.
* Remove Cancel button and replace by place order that takes the customer to the place order screen.
* Enable disable buttons and add validations on the customer edit/add screen

## 4. Monica

**Task List**

* Products List Screen (View, Service and DAO Component) - story-prod --> awaiting completion
* Order Screen (View, Service and DAO Component) - story-order --> awaiting completion
* Testing the app for obvious bugs.
* UI corrections to the previous transaction screen.
* Remove select and review buttons.
* Make sure error message is shown if no transactions are present.

## 5. Branching Strategy

* Develop branch to be created from master (name: develop) - Shivendra
* All story branches to be created and to be merged into develop.

**Merging Strategy**

* Story branches need to be tested on local emulator to ensure working.
* Resolve bugs in story branch that arise from local testing.
* Pull Request to be submitted to dev lead from github.com.
* Story branch will then be merged into develop.
* Sanity test to be done by developer on develop branch to ensure everything is intact.
* Once verified, developers pull develop into local and perform integration testing - running the app and doing functionality testing.
* Once verified on develop, develop is then merged into master.






