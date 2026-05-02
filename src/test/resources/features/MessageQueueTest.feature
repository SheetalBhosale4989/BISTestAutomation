Feature: Message Queue Test

# @debug
  @sequence=3
  Scenario: Enqueue and Dequeue a message
    Given User is logged in with valid credentials
    And message is published to queue
    When user navigates to queue and streams tab
    And user clicks on Queue
    And user clicks on Get messages header
#    And user selects automatic acknowledgement mode
    And user fetches a message by clicking on Get message button
    Then message should be visible
#    And verify zero messages remaining
#    And user deletes Queue

