Feature: Login API

Scenario: Login con usuario generado por UI

* def user = read('file:target/testdata/user.json')
Given url "https://parabank.parasoft.com/parabank/services/bank"
And path 'login',user.username, user.password
When method get
Then status 200
* print response
* print user.username
* print user.password