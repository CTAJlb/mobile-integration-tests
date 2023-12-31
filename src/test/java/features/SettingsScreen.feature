Feature: Check sections from settings screen

  @tier2 @exclude_android
  Scenario: About Palace
    When Close tutorial screen
    Then Welcome screen is opened
    When Close welcome screen
    Then Add library screen is opened
    When Add library "Palace Bookshelf" on Add library screen
      And Open Settings
      And Open About Palace on settings screen
    Then About Palace screen is opened

  @tier2
  Scenario: Privacy Policy
    When Close tutorial screen
    Then Welcome screen is opened
    When Close welcome screen
    Then Add library screen is opened
    When Add library "Palace Bookshelf" on Add library screen
      And Open Settings
      And Open Privacy Policy on settings screen
    Then Privacy Policy screen is opened

  @tier2
  Scenario: User Agreement
    When Close tutorial screen
    Then Welcome screen is opened
    When Close welcome screen
    Then Add library screen is opened
    When Add library "Palace Bookshelf" on Add library screen
      And Open Settings
      And Open User Agreement on settings screen
    Then User Agreement screen is opened
    When Scroll page to link "www.copyright.gov" on user agreement screen
      Then Link "www.copyright.gov" is available on user agreement screen
    When Scroll page to link "http://thepalaceproject.org/licenses/" on user agreement screen
      Then Link "http://thepalaceproject.org/licenses/" is available on user agreement screen

  @tier2
  Scenario: Software Licenses
    When Close tutorial screen
    Then Welcome screen is opened
    When Close welcome screen
    Then Add library screen is opened
    When Add library "Palace Bookshelf" on Add library screen
      And Open Settings
      And Open Software Licenses on settings screen
    Then Software Licenses screen is opened
    When Scroll page to link "www.apache.org/licenses" on software licenses screen
      Then Link "www.apache.org/licenses" is available on software licenses screen

  @tier2
  Scenario: Settings: User License Agreement
    When Close tutorial screen
    Then Welcome screen is opened
    When Close welcome screen
    Then Add library screen is opened
    When Add library "LYRASIS Reads" on Add library screen
      And Open Settings
      And Open Libraries on settings screen
      And Open 'LYRASIS Reads' library on setting screen
      And Open User license agreement on account screen
    Then User License Agreement link is opened

  @tier2
  Scenario: Settings: Content Licenses
    When Close tutorial screen
    Then Welcome screen is opened
    When Close welcome screen
    Then Add library screen is opened
    When Add library "LYRASIS Reads" on Add library screen
      And Open Settings
      And Open Libraries on settings screen
      And Open 'LYRASIS Reads' library on setting screen
      And Open Content Licenses on account screen
    Then Content Licenses screen is opened

  @logout @tier1 @exclude_android
  Scenario: Setting: Advanced
    When Close tutorial screen
    Then Welcome screen is opened
    When Close welcome screen
    Then Add library screen is opened
    When Add library "LYRASIS Reads" on Add library screen
      And Enter credentials for 'LYRASIS Reads' account
    Then Login is performed successfully
    When Open Settings
      And Open Libraries on settings screen
      And Open 'LYRASIS Reads' library on setting screen
    When Open Advanced on account screen
    Then Advanced screen contains "Delete Server Data" button
    When Click "Delete Server Data" button and cancel it on Advanced screen
    Then Advanced screen contains "Delete Server Data" button