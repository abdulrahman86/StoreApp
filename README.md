# Store Application

## Getting started
1. Download Typesafe Activator from [http://typesafe.com/get-started]
2. Unzip the contents the StoreApp zip file.
3. Enter the above directory in a shell (e.g. cmd) and execute the "activator run" command.
4. Open a web browser and navigate to localhost:9000, you should see the (extremely basic) homepage for the Store App.

### Play Framework
If you're unfamiliar with the Play framework the documentation at [http://www.playframework.com/documentation/2.3.x/Home]
 should help you to complete this task.

## Task Purpose
For this task you will be completing some basic functionality for a retail e-commerce web site using Play Framework. 
You do not need to delve into UI and design concerns (assume someone else will worry about making things pretty). We are
looking for a solution that reacts appropriately to requests, produces the correct data in responses, is resilient and 
performs well. Provide documentation in the code for any special considerations or concerns regarding your solution. 

## Task Requirements
1. Create a category page that displays:
    * The name of the category,
    * A list of the items in the category (show their name) with each item being a link to the product details page.
2. Create a product details page that displays:
    * The product name,
    * The product's price,
    * Any attributes that are defined in the product info. (Format as you like, we don’t care, we’re backend developers.)
