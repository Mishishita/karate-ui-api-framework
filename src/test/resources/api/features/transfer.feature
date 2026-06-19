@regression
Feature: Transfer Funds API

  Scenario: Transferir dinero de CHEKING a SAVINGS
    #Datos guardados
    * def user = read('file:target/testdata/user.json')

    * def fromAccountId = user.checkingAccountId
    * def toAccountId = user.savingsAccountId

    * print 'From account:', fromAccountId
    * print 'To account:', toAccountId

    # Saldo origen antes de transferir
    Given url 'https://parabank.parasoft.com/parabank/services/bank'
    And path 'accounts', fromAccountId
    When method get
    Then status 200

    * def sourceInitialBalance = parseFloat(response.account.balance)

    # Saldo destino antes de transferir
    Given url 'https://parabank.parasoft.com/parabank/services/bank'
    And path 'accounts', toAccountId
    When method get
    Then status 200

    * def targetInitialBalance = parseFloat(response.account.balance)

    # Transferencia
    Given url 'https://parabank.parasoft.com/parabank/services/bank/transfer'
    And param fromAccountId = fromAccountId
    And param toAccountId = toAccountId
    And param amount = 100

    When method post
    Then status 200

    * print response

    # Saldo origen después
    Given url 'https://parabank.parasoft.com/parabank/services/bank'
    And path 'accounts', fromAccountId
    When method get
    Then status 200

    * def sourceFinalBalance = parseFloat(response.account.balance)

    # Saldo destino después
    Given url 'https://parabank.parasoft.com/parabank/services/bank'
    And path 'accounts', toAccountId
    When method get
    Then status 200

    * def targetFinalBalance = parseFloat(response.account.balance)

    # Validaciones
    * print 'Source Initial:', sourceInitialBalance
    * print 'Source Final:', sourceFinalBalance

    * print 'Target Initial:', targetInitialBalance
    * print 'Target Final:', targetFinalBalance
    * match sourceFinalBalance == sourceInitialBalance - 100
    * match targetFinalBalance == targetInitialBalance + 100
    
