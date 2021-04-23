
Feature: Basic Arithmetic

  Background: Calculator
    Given a turned on calculator

    Scenario: Addition
      When I add 2 to 2
      Then 4

    Scenario: Subtraction
      When I subtract 3 to 5
      Then 2

    Scenario: Several Additions
      When I add <a> and <b>
      Then <c>

    Examples: Digits
      | a | b | c |
      | 0 | 2 | 2 |
      | 3 | 2 | 5 |
      | 1 | 7 | 8 |

