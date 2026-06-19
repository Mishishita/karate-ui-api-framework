@regression
Feature: Create Saving Account API

Scenario: Reutilizar o crear cuenta SAVINGS

    # Recibimos el customerId desde login.feature
    * def customerId = __arg.customerId

    # Consultar cuentas del cliente
    Given url 'https://parabank.parasoft.com/parabank/services/bank'
    And path 'customers', customerId, 'accounts'
    When method get
    Then status 200

    * def accounts = response.accounts.account

    # Si Parabank devuelve una sola cuenta, convertirla en lista
    * if (accounts.id) accounts = [accounts]

    # Obtener cuenta CHECKING
    * def checkingAccounts = karate.filter(accounts, function(x){ return x.type == 'CHECKING' })
    * def accountId = checkingAccounts[0].id

    # Buscar cuenta SAVINGS
    * def savingsAccounts = karate.filter(accounts, function(x){ return x.type == 'SAVINGS' })
    * def savingsExists = savingsAccounts.length > 0

    * print 'Checking accountId:', accountId
    * print 'Savings exists:', savingsExists

    * def TestDataExporter = Java.type('utils.TestDataExporter')
    * def newAccountId = null

    # Si existe una SAVINGS, reutilizarla
    * if (savingsExists) newAccountId = savingsAccounts[0].id

    # Si no existe, crearla
    * if (!savingsExists) createResult = karate.call('classpath:api/common/create-savings.feature', { customerId: customerId, accountId: accountId })

    # Recuperar el id creado
    * if (!savingsExists) newAccountId = createResult.newAccountId

    # Actualizar user.json
    * eval TestDataExporter.updateAccountData(customerId, accountId, newAccountId)

    * print 'Checking Account:', accountId
    * print 'Savings Account:', newAccountId

    # Datos para el flujo E2E
    # Devolver datos al feature que llama
    * def result =
    """
    {
      customerId: '#(customerId)',
      accountId: '#(accountId)',
      newAccountId: '#(newAccountId)'
    }
    """