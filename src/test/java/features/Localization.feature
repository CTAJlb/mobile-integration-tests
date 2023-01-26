Feature: Localization testing

  @tier3
  Scenario: Spanish: Tutorial and Welcome screen
    When Close tutorial screen
    Then Welcome screen is opened
      And Elements on welcome screen are translated correctly
    When Close welcome screen

  @tier3
  Scenario: Spanish: Add Library screen
    When Close tutorial screen
      And Close welcome screen
    Then Elements on add account screen are translated correctly

  @tier3
  Scenario: Spanish: Catalog screen
    When I add "LYRASIS Reads" account from welcomeScreen
    Then Category rows are loaded
      And Elements on Catalog screen are translated correctly

  @tier3
  Scenario: Spanish: Search screen
    When I add "LYRASIS Reads" account from welcomeScreen
    Then Category rows are loaded
    When I open search modal
    Then Elements on Search screen are translated correctly

  @tier3
  Scenario: Spanish: Find your library screen
    When I add "LYRASIS Reads" account from welcomeScreen
    Then Category rows are loaded
    When I tap the logo on catalog screen
    Then Find your library screen is opened
    And Find your library label, add library and cancel buttons are translated on find your library screen

  @tier3
  Scenario: Spanish: Subcategory screen
    When I add "LYRASIS Reads" account from welcomeScreen
    Then Category rows are loaded
    When I open 'Audible Studios' category
    Then Items from subcategory screen are translated on subcategory screen

  @tie3
  Scenario: Spanish: Categories in Sort by
    When I add "LYRASIS Reads" account from welcomeScreen
    Then Category rows are loaded
    When I open 'Audible Studios' category
    And I open Sort by sorting on catalog screen in "LYRASIS Reads" library
    Then Sort options are translated on subcategory screen

  @tier3
  Scenario: Spanish: Categories in Availability
    When I add "LYRASIS Reads" account from welcomeScreen
    Then Category rows are loaded
    When I open 'Audible Studios' category


  @tier3:
  Scenario: Spanish: Categories in Collection
    When I add "LYRASIS Reads" account from welcomeScreen
    Then Category rows are loaded
    When I open 'Audible Studios' category

  @tier3
  Scenario: Spanish: Bottom Menu
    When I add "LYRASIS Reads" account from welcomeScreen
    Then Category rows are loaded

  @tier3
  Scenario: Spanish: Book details view screen

  @tier3
  Scenario: Spanish: epub: reader screen

  @tier3
  Scenario: Spanish: epub: Table of contents

  @tier3
  Scenario: Spanish: epub: Bookmarks

  @tier3
  Scenario: Spanish: epub: Search

  @tier3
  Scenario: Spanish: Settings screen

  @tier3
  Scenario: Spanish: Alert: Reserve books

  @tier3
  Scenario: Spanish: Alert: Loan limit

  @tier3
  Scenario: Spanish: Reservations screen

  @tier3
  Scenario: Spanish: Account screen

  @tier3
  Scenario: Spanish: pdf: reader screen

  @tier3
  Scenario: Spanish: Audiobooks: player screen

  @tier3
  Scenario: Spanish: Audiobooks: playback screen

  @tier3
  Scenario: Spanish: Audiobooks: sleep timer screen

  @tier3
  Scenario: Spanish: Audiobooks: table of contents screen








#  @tier3
#  Scenario: Italian
#
#
#  @tier3
#  Scenario: German
#
#  @tier3
#  Scenario: French