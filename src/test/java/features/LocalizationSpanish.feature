Feature: Localization testing in Spanish

  @tier3
  Scenario: Spanish: Tutorial and Welcome screen
    When Close tutorial screen
    Then Welcome screen is opened in Spanish
      And Elements on welcome screen are translated correctly

  @tier3
  Scenario: Spanish: Add Library screen
    When Close tutorial screen
      And Close welcome screen in Spanish
    Then Elements on add account screen are translated correctly

  @tier3
  Scenario: Spanish: Catalog screen
    When Close tutorial screen
      And Close welcome screen in Spanish
      And Add library "LYRASIS Reads" on Add library screen
    Then Category rows are loaded
      And Elements on Catalog screen are translated correctly

  @tier3
  Scenario: Spanish: Search screen
    When Close tutorial screen
      And Close welcome screen in Spanish
      And Add library "LYRASIS Reads" on Add library screen
    Then Category rows are loaded
    When Open search modal
    Then Elements on Search screen are translated correctly

  @tier3
  Scenario: Spanish: Find your library screen
    When Close tutorial screen
      And Close welcome screen in Spanish
      And Add library "LYRASIS Reads" on Add library screen
    Then Category rows are loaded
    When Tap the logo on catalog screen
    Then Elements on Find your library screen are translated correctly

  @tier3
  Scenario: Spanish: Subcategory screen
    When Close tutorial screen
      And Close welcome screen in Spanish
      And Add library "LYRASIS Reads" on Add library screen
    Then Category rows are loaded
    When Open 'Audible Studios' category
    Then Elements on subcategory screen are translated correctly

  @tie3
  Scenario: Spanish: Categories in Sort by
    When Close tutorial screen
      And Close welcome screen in Spanish
      And Add library "LYRASIS Reads" on Add library screen
    Then Category rows are loaded
    When Open 'Audible Studios' category
      And Open Sort by on catalog screen
    Then Elements on Sort by tab are translated correctly

  @tier3
  Scenario: Spanish: Categories in Availability
    When Close tutorial screen
      And Close welcome screen in Spanish
      And Add library "LYRASIS Reads" on Add library screen
    Then Category rows are loaded
    When Open 'Audible Studios' category
      And Open Availability on catalog screen
    Then Elements on Availability tab are translated correctly

  @tier3:
  Scenario: Spanish: Categories in Collection
    When Close tutorial screen
      And Close welcome screen in Spanish
      And Add library "LYRASIS Reads" on Add library screen
    Then Category rows are loaded
    When Open 'Audible Studios' category
      And Open Collection on catalog screen
    Then Elements on Collection tab are translated correctly

  @tier3
  Scenario: Spanish: Bottom Menu
    When Close tutorial screen
      And Close welcome screen in Spanish
      And Add library "LYRASIS Reads" on Add library screen
    Then Category rows are loaded
      And Elements on Bottom menu are translated correctly

  @tier3
  Scenario: Spanish: Book details view screen
    When Close tutorial screen
      And Close welcome screen in Spanish
      And Add library "LYRASIS Reads" on Add library screen
    Then Category rows are loaded
    When Open search modal
      And Search for "Dune" and save bookName as 'bookNameInfo'
      And Open first book in Subcategory List and save it as 'bookInfo'
    Then Elements on Book detail view are translated correctly

  @tier3 @returnBooks @logoutES
  Scenario: Spanish: epub: reader screen
    When Close tutorial screen
      And Close welcome screen in Spanish
      And Add library "LYRASIS Reads" on Add library screen
      And Enter credentials for "LYRASIS Reads" account in Spanish
      And Open Catalog in Spanish
      And Open search modal
      And Search for "Libertie" and save bookName as 'bookNameInfo'
      And Open first book in Subcategory List and save it as 'bookInfo'
      And Click GET_ES action button on book details screen
    Then Check that book contains READ_ES action button on book details screen
    When Click READ_ES action button on book details screen
      Then Elements on Reader epub screen are translated correctly

  @tier3 @returnBooks @logoutES
  Scenario: Spanish: epub: Table of contents
    When Close tutorial screen
      And Close welcome screen in Spanish
      And Add library "LYRASIS Reads" on Add library screen
      And Enter credentials for "LYRASIS Reads" account in Spanish
      And Open Catalog in Spanish
      And Open search modal
      And Search for "Libertie" and save bookName as 'bookNameInfo'
      And Open first book in Subcategory List and save it as 'bookInfo'
      And Click GET_ES action button on book details screen
    Then Check that book contains READ_ES action button on book details screen
    When Click READ_ES action button on book details screen
      And Open toc epub screen
    Then Elements on TOC in reader epub are translated correctly

#  @tier3 @returnBooks
#  Scenario: Spanish: epub: Bookmarks
#    When I add "LYRASIS Reads" account from welcomeScreen
#      And I enter credentials for "LYRASIS Reads" account
#    Then Login is performed successfully
#    When I open Catalog
#    Then Category rows are loaded
#    When I open search modal
#      And I search for "Libertie" and save bookName as 'bookNameInfo'
#      And I open first book in Subcategory List and save it as 'bookInfo'
#      And Click GET_ES action button on book details screen
#    Then I check that book contains READ_ES action button on book details screen
#    When Click READ_ES action button on book details screen
#      And Open bookmarks epub screen
#    Then Elements on Bookmarks epub screen are translated correctly
#    When Close toc epub screen
#      And I return to previous screen from epub
#      And Click RETURN_ES action button on book details screen
#    Then I check that book contains GET_ES action button on book details screen
#
  @tier3
  Scenario: Spanish: Settings screen
    When Close tutorial screen
      And Close welcome screen in Spanish
      And Add library "LYRASIS Reads" on Add library screen
      And I open Settings in Spanish
    Then Elements on Settings screen are translated correctly

  @tier3
  Scenario: Spanish: Reservations screen
    When Close tutorial screen
      And Close welcome screen in Spanish
      And Add library "LYRASIS Reads" on Add library screen
      And I open Holds in Spanish
    Then Elements on Holds screen are translated correctly

  @tier3
  Scenario: Spanish: Account screen
    When Close tutorial screen
      And Close welcome screen in Spanish
      And Add library "LYRASIS Reads" on Add library screen
      And I open Settings in Spanish
      And Open Libraries on settings screen
      And Open "LYRASIS Reads" library on setting screen
    Then Elements on Account screen are translated correctly

#  @tier3 @returnBooks
#  Scenario: Spanish: pdf: search screen
#    When I add "LYRASIS Reads" account from welcomeScreen
#      And I enter credentials for "LYRASIS Reads" account
#    Then Login is performed successfully
#    When I open Catalog
#    Then Category rows are loaded
#    When I open search modal
#      And I search for "Helping Injured Animals" and save bookName as 'bookNameInfo'
#      And I open first book in Subcategory List and save it as 'bookInfo'
#      And Click GET_ES action button on book details screen
#    Then I check that book contains READ_ES action button on book details screen
#    When Click READ_ES action button on book details screen
#    Then Reader pdf screen is opened
#    When I open search pdf screen
#    Then Elements on pdf search screen are translated correctly
#    When I close pdf search screen
#      And I return to previous screen for epub and pdf
#      And Click RETURN_ES action button on book details screen
#    Then I check that book contains GET_ES action button on book details screen

#  @tier3 @returnBooks
#  Scenario: Spanish: pdf: Bookmarks screen
#    When I add "LYRASIS Reads" account from welcomeScreen
#      And I enter credentials for "LYRASIS Reads" account
#    Then Login is performed successfully
#    When I open Catalog
#    Then Category rows are loaded
#    When I open search modal
#      And I search for "Helping Injured Animals" and save bookName as 'bookNameInfo'
#      And I open first book in Subcategory List and save it as 'bookInfo'
#      And Click GET_ES action button on book details screen
#    Then I check that book contains READ_ES action button on book details screen
#    When Click READ_ES action button on book details screen
#    Then Reader pdf screen is opened
#    When I open bookmarks pdf screen
#    Then Elements on pdf bookmarks screen are translated correctly
#    When I return to pdf reader screen from pdf toc screen
#      And I return to previous screen for epub and pdf
#      And Click RETURN_ES action button on book details screen
#    Then I check that book contains GET_ES action button on book details screen

#  @tier3 @returnBooks
#  Scenario: Spanish: Audiobooks: player screen
#    When I add "LYRASIS Reads" account from welcomeScreen
#      And I enter credentials for "LYRASIS Reads" account
#    Then Login is performed successfully
#    When I open Catalog
#    Then Category rows are loaded
#    When I open search modal
#      And I search for "L.A. Weather" and save bookName as 'bookNameInfo'
#      And I open first book in Subcategory List and save it as 'bookInfo'
#      And Click GET_ES action button on book details screen
#    Then I check that book contains LISTEN_ES action button on book details screen
#    When Click LISTEN_ES action button on book details screen
#    Then Audio player screen of book 'bookNameInfo' is opened
#      And Elements on Audio Player screen are translated correctly
#    When Return to previous screen from audio player screen
#      And Click RETURN_ES action button on book details screen
#    Then I check that book contains GET_ES action button on book details screen

#  @tier3 @returnBooks
#  Scenario: Spanish: Audiobooks: playback screen
#    When I add "LYRASIS Reads" account from welcomeScreen
#      And I enter credentials for "LYRASIS Reads" account
#    Then Login is performed successfully
#    When I open Catalog
#    Then Category rows are loaded
#    When I open search modal
#    And I search for "L.A. Weather" and save bookName as 'bookNameInfo'
#    And I open first book in Subcategory List and save it as 'bookInfo'
#    And Click GET_ES action button on book details screen
#    Then I check that book contains LISTEN_ES action button on book details screen
#    When Click LISTEN_ES action button on book details screen
#    Then Audio player screen of book 'bookNameInfo' is opened
#    When Open playback speed on audio player screen
#    Then Elements on Playback speed are translated correctly
#    When Close playback speed screen
#      And Return to previous screen from audio player screen
#      And Click RETURN_ES action button on book details screen
#    Then I check that book contains GET_ES action button on book details screen

#  @tier3 @returnBooks
#  Scenario: Spanish: Audiobooks: sleep timer screen
#    When I add "LYRASIS Reads" account from welcomeScreen
#      And I enter credentials for "LYRASIS Reads" account
#    Then Login is performed successfully
#    When I open Catalog
#    Then Category rows are loaded
#    When I open search modal
#      And I search for "L.A. Weather" and save bookName as 'bookNameInfo'
#      And I open first book in Subcategory List and save it as 'bookInfo'
#      And Click GET_ES action button on book details screen
#    Then I check that book contains LISTEN_ES action button on book details screen
#    When Click LISTEN_ES action button on book details screen
#    Then Audio player screen of book 'bookNameInfo' is opened
#    When Open sleep timer on audio player screen
#    Then Elements on Sleep timer screen are translated correctly
#    When Close sleep timer screen
#      And Return to previous screen from audio player screen
#      And Click RETURN_ES action button on book details screen
#    Then I check that book contains GET_ES action button on book details screen

#  @tier3 @returnBooks
#  Scenario: Spanish: Audiobooks: table of contents screen
#    When I add "LYRASIS Reads" account from welcomeScreen
#      And I enter credentials for "LYRASIS Reads" account
#    Then Login is performed successfully
#    When I open Catalog
#    Then Category rows are loaded
#    When I open search modal
#      And I search for "L.A. Weather" and save bookName as 'bookNameInfo'
#      And I open first book in Subcategory List and save it as 'bookInfo'
#      And Click GET_ES action button on book details screen
#    Then I check that book contains LISTEN_ES action button on book details screen
#    When Click LISTEN_ES action button on book details screen
#    Then Audio player screen of book 'bookNameInfo' is opened
#    When Open toc audiobook screen
#    Then Elements on TOC audiobook screen are translated correctly
#    When Close toc audiobook screen
#      And Return to previous screen from audio player screen
#      And Click RETURN_ES action button on book details screen
#    Then I check that book contains GET_ES action button on book details screen