Feature: Login API

Scenario: Login con usuario generado por UI

* def user = read('file:target/testdata/user.json')
Given url "https://parabank.parasoft.com/parabank/services/bank"
And path 'login',user.username, user.password

When method get
Then status 200
* print 'el primer response'
* print response
* def customerId = response.customer.id
* print 'CustomerId:', customerId
* print user.username
* print user.password
* match response.customer.firstName == 'Maria'
* match response.customer.lastName == 'Tinoco'
