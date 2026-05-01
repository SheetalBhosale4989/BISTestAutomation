Feature: Message Queue Test

#  Background: User is logged in with valid credentials

  @debug
  Scenario: Enqueue and Dequeue a message
    Given User is logged in with valid credentials
    And message is published to queue
    When user navigates to queue and streams tab
    And user clicks on Queue
    And user fetches a message by clicking on Get message button
    Then message should be visible