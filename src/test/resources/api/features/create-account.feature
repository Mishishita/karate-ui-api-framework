@regression
Feature: Create Saving Account API

  Scenario: Login con usuario generado por UI

    # Login
    #* def loginData = call read('classpath:api/common/login-helper.feature')
    #* def customerId = loginData.customerId
    * def customerId = __arg.customerId

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
    * def TestDataExporter = Java.type('utils.TestDataExporter')
    * eval TestDataExporter.updateAccountData(customerId,accountId,newAccountId)

    * match response.account.type == 'SAVINGS'

    #Validamos que la cuenta SAVINGS se haya creado correctamente
    Given url 'https://parabank.parasoft.com/parabank/services/bank'
    And path 'customers', customerId,'accounts'
    When method get
    Then status 200
    * print 'Validación final'
    * print karate.pretty(response)
    
    * def accountIds = karate.jsonPath(response, '$.accounts.account[*].id')
    * print accountIds
    * def accountIds = karate.jsonPath(response, '$.accounts.account[*].id')
    * print accountIds