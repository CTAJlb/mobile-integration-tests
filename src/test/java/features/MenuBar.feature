Feature: Menu Bar

  @tier2
  Scenario Outline: Check of menu bar in Palace
    When Close tutorial screen
    Then Welcome screen is opened
    When Close welcome screen
    Then Add library screen is opened
    When Add library "Palace Bookshelf" on Add library screen
    Then Account "Palace Bookshelf" is present on Accounts screen
    Then There is a menu bar at the bottom of the screen
      And There are tabs '<tab1>', '<tab2>' and '<tab3>' on application screen

    Scenarios:
      | tab1    | tab2     | tab3     |
      | Catalog | My Books | Settings |

  @tier2
  Scenario: Check of the tabs in Palace
    When Close tutorial screen
    Then Welcome screen is opened
    When Close welcome screen
    Then Add library screen is opened
    When Add library "Palace Bookshelf" on Add library screen
    Then Account "Palace Bookshelf" is present on Accounts screen
    When Open Catalog
    Then Category rows are loaded
    When Open Books
    Then Books screen is loaded
    When Open Settings
    Then Settings screen is opened

  @tier2
  Scenario Outline: Check of menu bar in LYRASIS
    When Close tutorial screen
    Then Welcome screen is opened
    When Close welcome screen
    Then Add library screen is opened
    When Add library "LYRASIS Reads" on Add library screen
    Then Account "LYRASIS Reads" is present on Accounts screen
      And There is a menu bar at the bottom of the screen
      And There are tabs '<tab1>', '<tab2>', '<tab3>' and '<tab4>' on application screen

    Scenarios:
      | tab1    | tab2     | tab3         | tab4     |
      | Catalog | My Books | Reservations | Settings |

  @tier2
  Scenario: Check of the tabs in LYRASIS
    When Close tutorial screen
    Then Welcome screen is opened
    When Close welcome screen
    Then Add library screen is opened
    When Add library "LYRASIS Reads" on Add library screen
      And Open Catalog
    Then Category rows are loaded
    When Open Books
    Then Books screen is loaded
    When Open Holds
    Then Holds screen is loaded
    When Open Settings
    Then Settings screen is opened