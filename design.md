# Boatclub OO-Design
This document describes the design according to the requirements presented in assignment 2.

I am seeing stuffLendingSystem as the actor organizing/processing all of the wishes a user might have.

I am assuming that when a certain item is wanted to be borrowed, the member trying to borrow it has "kind of been on the owning members home page/seen member and its items in the list members option" and therefore is aware of its memberId and the name of the item it wants to borrow.

## Architectural Overview
The application uses the model-view-controller (MVC) architectural pattern. The view is passive and gets called from the controller. The view may only read information from the model, not directly change it.

![class diagram](img/package_diagram.jpg)

## Detailed Design
### Class Diagram
Please provide at least one class diagram according to the assignment requirments.

### Sequence Diagram
Please provide at least one sequence diagram according to the assignment requirments.