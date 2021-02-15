Specification Heading
=====================
 App
---------------------------------------
Tags:AppRunning
* Click element by "loginButton"
* Find element by "gsm" clear and send keys "5455430080"
* Find element by "password" clear and send keys "1234567890"
* Click element by "loginSubmit"
* Wait "4" seconds


Successful Login
-------------------------
Tags:successfullogin
* Wait "1" seconds
* Login

Unsuccessful Login
-------------------------
Tags:unsuccessfullogin
* Wait "1" seconds


Successful LogOut
-------------------------
Tags:successfullogout
* Wait "4" seconds
* Login
* Logout


AddProductOnCartAndRemove
---------------------------
Tags:addproductoncartandremove
* Wait "4" seconds


AddProductOnCartIncreaseCountPayAndRemove
---------------------------
Tags:addproductoncartincreasecountpayandremove
* Wait "4" seconds


Fail Scenario
----------------
Tags: failscenario
* Wait "1" seconds
* Fail Step