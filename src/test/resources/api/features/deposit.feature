Feature: Deposit API

Scenario: Depositar dinero en una cuenta

# Login
* def loginData = call read('classpath:api/common/login-helper.feature')
* def customerId = loginData.customerId

# Leer datos guardados
* def user = read('file:target/testdata/user.json')
* def accountId = user.checkingAccountId
* print 'AccountId:', accountId

# Obtener saldo inicial
Given url 'https://parabank.parasoft.com/parabank/services/bank'
And path 'accounts', accountId
When method get
Then status 200

* print 'Saldo inicial'
* print response
* def initialBalance = parseFloat(response.account.balance)
* print 'InitialBalance:', initialBalance

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