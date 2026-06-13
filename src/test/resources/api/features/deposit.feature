Feature: Deposit API

Scenario: Depositar dinero en una cuenta

* def user = read('file:target/testdata/user.json')

# Login
Given url 'https://parabank.parasoft.com/parabank/services/bank'
And path 'login', user.username, user.password
When method get
Then status 200

* def customerId = response.customer.id
* print 'CustomerId:', customerId

# Obtener cuentas
Given url 'https://parabank.parasoft.com/parabank/services/bank'
And path 'customers', customerId, 'accounts'
When method get
Then status 200

* def accountId = response.accounts.account.id
* print 'AccountId:', accountId
* print 'Saldo inicial'
* print response
* def initialBalance = response.accounts.account.balance
* print 'InitialBalance: ',initialBalance

# Depositar
Given url 'https://parabank.parasoft.com/parabank/services/bank/deposit'
And param accountId = accountId
And param amount = 500
When method post
Then status 200

* print response

# Volviendo a consultar
Given url 'https://parabank.parasoft.com/parabank/services/bank'
And path 'accounts', accountId
When method get
Then status 200

* print 'Saldo final'
* print response
* def finalBalance = parseFloat(response.account.balance)
* print 'FinalBalance:', finalBalance
* def expectedBalance = parseFloat(initialBalance) + 500
* print 'ExpectedBalance:', expectedBalance
* match finalBalance == expectedBalance