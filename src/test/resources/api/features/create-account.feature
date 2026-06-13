Feature: Create Saving Account API

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

Given url 'https://parabank.parasoft.com/parabank/services/bank'
And path 'customers', customerId, 'accounts'

When method get
Then status 200
* print karate.pretty(response)
* print 'El segundo responde'
* print response
* def accountId = response.accounts.account.id
* print 'accountId:',accountId
#* def secondAccountId = response.accounts.account[1].id
#* print 'secondAccountId:',secondAccountId

# 0 = CHECKING
# 1 = SAVINGS
Given url 'https://parabank.parasoft.com/parabank/services/bank'
And path 'createAccount'
And param customerId = customerId
And param newAccountType = 1
And param fromAccountId = accountId

When method post
* print 'Respuesta Create Account'
* print response
* def newAccountId = response.account.id
* print 'newAccountId:', newAccountId

* match response.account.type == 'SAVINGS'

#Validamos que la cuenta SAVINGS se haya creado correctamente
Given url 'https://parabank.parasoft.com/parabank/services/bank'
And path 'customers', customerId,'accounts'
When method get
Then status 200
* print 'Validación final'
* print karate.pretty(response)