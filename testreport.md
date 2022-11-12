# Stuff Lending System Test Report

Version #9eb1c80236b427270bba5524a362eef132cc3ea6 (git commit hash/link)
Date: 2022.11.11
Environment: Windows, Java version 17.0.1. 
Performed by: Andrea Viola Caroline Åkesson

## Member Tests

| Case        | Result      | Note        |
| ----------- | ----------- | ----------- |
| 5.1      | OK | Although, I have more hardcoded members/items than the example. |
| 1.1   | OK | Although I do not quit the application to proceed with the next duplication tests. Allan is not hard coded. |
| 1.2 (duplicate email)   | OK | Application prompts to enter email again since it is taken.  |
| 1.2  (duplicate phone) | OK  | Application prompts to enter phone again since it is taken.  |
| 1.3 | OK        |  Member deleted.   |

## Item Tests

| Case        | Result      | Note        |
| ----------- | ----------- | ----------- |
| 2.1  | OK | Item created, credit increased |
| 2.2 | OK | Item deleted |
| 2.3 | NOT OK | Well, item was deleted, which includes it's contracts. It was not cancelled per se but neither can you view the item and see its historical/future contracts. |

## Contract Tests

| Case        | Result      | Note        |
| ----------- | ----------- | ----------- |
| 3.1  | OK/NOT OK | Contract was indeed created, but I have implemented the time in the app such as that it increments while the user is moving around the menus ultimately resulting in that this contract was created for a period in the "past" (FIXED THIS SO THAT APPLICATION DENIES CONTRACTS STARTING IN THE PAST)|
| 3.2 | OK | Application outputs "Contract was denied" |
| 3.3 & 3.4 | OK | Application outputs "Contract was denied" - Tested vs the hardcoded contract (Item: mem4item1 start: 7 end: 10) with the start day 10 and end day 13 |
| 3.5 | OK | Application outputs "Contract was denied" - Tested vs the hardcoded contract (Item: mem4item1 start: 7 end: 10) with the start day 7 and end day 12 |
| 3.6 | OK | Application outputs "Contract was denied" - Tested vs the hardcoded contract (Item: mem4item1 start: 7 end: 10) with the start day 10 and end day 10 |

## Advance time Tests

| Case        | Result      | Note        |
| ----------- | ----------- | ----------- |
| 4.1 | OK | Not exactly applicable on my application since here, you pay upfront when you are booking the item i.e. before the time period of rental. However, contract is visible in the "View item", credits are correctly deducted and during the time period of rental it is also visible in "List members full information" who it is currently lent to (this was tested with the hard coded contract so between day 7-10)|

