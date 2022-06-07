Feature: Check sections from settings screen

  @tier1
  Scenario: About Palace
    When I add "Palace Bookshelf" account from welcomeScreen
      And I open Settings
      And I open About Palace on settings screen
    Then About Palace screen is opened

  @tier1
  Scenario: Privacy Policy
    When I add "Palace Bookshelf" account from welcomeScreen
      And I open Settings
      And I open Privacy Policy on settings screen
    Then Privacy Policy screen is opened

  @tier1
  Scenario: User Agreement
    When I add "Palace Bookshelf" account from welcomeScreen
      And I open Settings
      And I open User Agreement on settings screen
    Then User Agreement screen is opened
    When I scroll page to link "www.copyright.gov" on user agreement screen
      Then Link "www.copyright.gov" is available on user agreement screen
    When I scroll page to link "http://thepalaceproject.org/licenses/" on user agreement screen
      Then Link "http://thepalaceproject.org/licenses/" is available on user agreement screen

  @tier1
  Scenario: Software Licenses
    When I add "Palace Bookshelf" account from welcomeScreen
      And I open Settings
      And I open Software Licenses on settings screen
    Then Software Licenses screen is opened
    When I scroll page to link "www.apache.org/licenses" on software licenses screen
      Then Link "www.apache.org/licenses" is available on software licenses screen

  @tier1
  Scenario: Settings: User License Agreement
    When I add 'LYRASIS Reads' account from welcomeScreen
      And I open Settings
      And I open Libraries on settings screen
      And I open 'LYRASIS Reads' library on setting screen
      And I open User license agreement on account screen
    Then User License Agreement link is opened

  @tier1
  Scenario: Settings: Content Licenses
    When I add 'LYRASIS Reads' account from welcomeScreen
      And I open Settings
      And I open Libraries on settings screen
      And I open 'LYRASIS Reads' library on setting screen
      And I open Content Licenses on account screen
    Then Content Licenses screen is opened

  @logout @tier1
  Scenario: Setting: Advanced
    When I add 'LYRASIS Reads' account from welcomeScreen
      And I enter credentials for 'LYRASIS Reads' account
    Then Login is performed successfully
    When I open Settings
      And I open Libraries on settings screen
      And I open 'LYRASIS Reads' library on setting screen
    When I open Advanced on account screen
    Then Advanced screen contains "Delete Server Data" button
    When I click "Delete Server Data" button

