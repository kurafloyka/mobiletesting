Specification Heading
=====================

Successful Login
-------------------------
Tags:successfullogin
* Login
* Check element visibility "profile"

Unsuccessful Login
-------------------------
Tags:unsuccessfullogin
* Login1
* Wait "2" seconds
* Check element visibility "submit"

Successful LogOut
-------------------------
Tags:successfullogout
* Login
* Logout
* Take a screenshot
* Check element visibility "submit"

AddProductOnCartAndRemove
---------------------------
Tags:addproductoncartandremove
* Login
* "Meyve ve Sebze" is click and randomly select product
* Click element by "backButton"
* "Süt & Kahvaltı" is click and randomly select product
* Click element by "backButton"
* Move to bottom
* "Bebek" is click and randomly select product
* Click element by "backButton"
* Click element by "basketButton"
* Move to bottom
* RemoveAllAddedProduct

AddProductOnCartIncreaseCountPayAndRemove
---------------------------
Tags:addproductoncartincreasecountpayandremove
* Wait "1" seconds


Fail Scenario
----------------
Tags: failscenario
* Wait "1" seconds
* Fail Step