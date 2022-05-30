Feature: Menu Bar

  @tier1
  Scenario Outline: Check of menu bar
    When I add 'Palace Bookshelf' account from welcomeScreen
    Then There is a menu bar at the bottom of the screen
      And There are tabs '<tab1>', '<tab2>' and '<tab3>' on application screen

    Scenarios:
      | tab1    | tab2     | tab3     |
      | Catalog | My Books | Settings |

  @tier1
  Scenario: Check of the tabs
    When I add 'Palace Bookshelf' account from welcomeScreen
      And I open Catalog
    Then Category rows are loaded
    When I open Books
    Then Books screen is loaded
    When I open Settings
    Then Settings screen is opened