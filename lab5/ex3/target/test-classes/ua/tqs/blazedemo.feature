Feature: Search in Google

    Scenario: Book flight from Boston to Cairo
        Given I navigate to "https://blazedemo.com/"
            When I select and fill form for flight with departure in "Boston" and destination in "Cairo"
        Then I navigate to "https://blazedemo.com/confirmation.php" where it shows "Thank you for your purchase today!"