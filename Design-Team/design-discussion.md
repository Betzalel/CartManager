# **Design Discussion -- Team 11**

## Design 1:

![alt text](https://github.com/gt-ud-softeng/6300Fall15Team11/blob/master/Project2/Design-Team/mruth3designclass.png "mruth3 design")

#### Pros:  
*  The Customer and Transaction are linked with the Purchase association.
*  Has the basic concepts needed for the project
*  All the base classes required for the project have been designed.

#### Cons:  
*  Not the most thorough diagram of the group.
*  Does not include views and associations seem lacking.
*  A "cart" or "order" class would be good for associating purchases
*  The Reward Credit class should be associated with the Customer class since the reward is earned by a customer.

## Design 2:

![alt text](https://github.com/gt-ud-softeng/6300Fall15Team11/blob/master/Project2/Design-Team/glarkin6designclass.png "glarkin6 design")

#### Pros:  
* Addresses transaction approved or declined status
* Interfaces the external libraries
* Has the basic concepts needed for the project
* The class associations give a good view of how the actual system will look like.

#### Cons:  
* Missing Purchase association
* Does not include views
* The transaction class does not contain a list of items purchased
* Rewards, while it can be its own class, may be better under customer
* Date and Money util classes may not be needed as we can use the in built java libraries.

## Design 3:

![alt text](https://github.com/gt-ud-softeng/6300Fall15Team11/blob/master/Project2/Design-Team/rhouck3designclass.png "rhouck3")

#### Pros:  
* Credit Status included in the Customer class.  
* Clear separation of object functionality for viewing
* External interfaces defined clearly along with the view classes.

#### Cons:  
* No data types provided in diagram makes it difficult to interpret the design intent
* Controller missing that would actually parse the requests coming in.

## Design 4:

![alt text](https://github.com/gt-ud-softeng/6300Fall15Team11/blob/master/Project2/Design-Team/ssrivastava64designclass.png "ssrivastava64")

#### Pros:   
* The most thorough of the group designs.
* Includes the manager class     
* Very detailed and organized.  Uses MVC pattern to organize class diagram
* Includes an actual item to be purchased.
* The Manager class will be the link between the models and views.
            
#### Cons:  
* Needs to be able to store credit card transaction responses
* Lack of view classes
* Needs to address reward credit earned date attribute


## Team Design

![alt text](https://github.com/gt-ud-softeng/6300Fall15Team11/blob/master/Project2/Design-Team/team11designclass.png "Team11")


## Summary

The team design was built off of ssrivastava64's individual design.  This design was the most thorough of the group.  It was decided that it would be more beneficial to make changes
to this design, rather than start a design from scratch.  Ideas from each team member's diagram were incorporated into the final team design.  Discussing the various designs of the 
group was a great learning process as each team member approached the problem differently.  Class names and level of detail varied greatly.  There were disagreements however 
these were easily resolved as explanations were given.  It was beneficial to have a couple of team members with experience designing class diagrams.  Some of the topics specifically discussed were: view classes,
business rule separation, the need for a type of "controller" class, the differences between the video example and the project requirements.  
