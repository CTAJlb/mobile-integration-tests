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

  @tier1
  Scenario: Software Licenses
    When I add "Palace Bookshelf" account from welcomeScreen
      And I open Settings
      And I open Software Licenses on settings screen
    Then Software Licenses screen is opened