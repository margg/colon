Feature: Verify submitting task to Runner Service

  Scenario: Submitting task
    Given Runner Service is started
    When I submit task of id 1 to the Runner Service
    Then Task 1 should be in progress.