Feature: Menu Bar

  @tier1
  Scenario Outline: Check of menu bar in Palace
    When I add 'Palace Bookshelf' account from welcomeScreen
    Then There is a menu bar at the bottom of the screen
      And There are tabs '<tab1>', '<tab2>' and '<tab3>' on application screen

    Scenarios:
      | tab1    | tab2     | tab3     |
      | Catalog | My Books | Settings |

  @tier1
  Scenario: Check of the tabs in Palace
    When I add 'Palace Bookshelf' account from welcomeScreen
      And I open Catalog
    Then Category rows are loaded
    When I open Books
    Then Books screen is loaded
    When I open Settings
    Then Settings screen is opened

  @tier1
  Scenario Outline: Check of menu bar in LYRASIS
    When I add 'LYRASIS Reads' account from welcomeScreen
    Then There is a menu bar at the bottom of the screen
    And There are tabs '<tab1>', '<tab2>', '<tab3>' and '<tab4>' on application screen

    Scenarios:
      | tab1    | tab2     | tab3         | tab4     |
      | Catalog | My Books | Reservations | Settings |

  @tier1
  Scenario: Check of the tabs in LYRASIS
    When I add 'LYRASIS Reads' account from welcomeScreen
    And I open Catalog
    Then Category rows are loaded
    When I open Books
    Then Books screen is loaded
    When I open Holds
    Then Holds screen is loaded
    When I open Settings
    Then Settings screen is opened