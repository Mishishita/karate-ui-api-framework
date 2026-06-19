Feature: Create Savings Account

Scenario:

    * def customerId = __arg.customerId
    * def accountId = __arg.accountId

    Given url 'https://parabank.parasoft.com/parabank/services/bank'
    And path 'createAccount'
    And param customerId = customerId
    And param newAccountType = 1
    And param fromAccountId = accountId

    When method post
    Then status 200

    * print 'Respuesta Create Account'
    * print response

    * def newAccountId = response.account.id

    * print 'New Savings Account:', newAccountId

    * match response.account.type == 'SAVINGS'