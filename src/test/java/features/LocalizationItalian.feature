Feature: Localization testing in Italian

  @tier4
  Scenario: Italian: Tutorial and Welcome screen
    When Close tutorial screen
    Then Welcome screen is opened
      And Elements on welcome screen are translated correctly in Italian

  @tier4
  Scenario: Italian: Add Library screen
    When Close tutorial screen
      And Close welcome screen in Italian
    Then Elements on add account screen are translated correctly in Italian

  @tier4
  Scenario: Italian: Catalog screen
    When Close tutorial screen
      And Close welcome screen in Italian
      And Add library "LYRASIS Reads" on Add library screen
    Then Category rows are loaded
      And Elements on Catalog screen are translated correctly in Italian

  @tier4
  Scenario: Italian: Search screen
    When Close tutorial screen
      And Close welcome screen in Italian
      And Add library "LYRASIS Reads" on Add library screen
    Then Category rows are loaded
    When Open search modal
    Then Elements on Search screen are translated correctly in Italian

  @tier4
  Scenario: Italian: Find your library screen
    When Close tutorial screen
      And Close welcome screen in Italian
      And Add library "LYRASIS Reads" on Add library screen
    Then Category rows are loaded
    When Tap the logo on catalog screen
    Then Elements on Find your library screen are translated correctly in Italian

  @tier4
  Scenario: Italian: Subcategory screen
    When Close tutorial screen
      And Close welcome screen in Italian
      And Add library "LYRASIS Reads" on Add library screen
    Then Category rows are loaded
    When Open 'Audible Studios' category
    Then Elements on subcategory screen are translated correctly in Italian

  @tie4
  Scenario: Italian: Categories in Sort by
    When Close tutorial screen
      And Close welcome screen in Italian
      And Add library "LYRASIS Reads" on Add library screen
    Then Category rows are loaded
    When Open 'Audible Studios' category
    And Open Sort by on catalog screen
    Then Elements on Sort by tab are translated correctly in Italian

  @tier4
  Scenario: Italian: Categories in Availability
    When Close tutorial screen
      And Close welcome screen in Italian
      And Add library "LYRASIS Reads" on Add library screen
    Then Category rows are loaded
    When Open 'Audible Studios' category
    And Open Availability on catalog screen
    Then Elements on Availability tab are translated correctly in Italian

  @tier4:
  Scenario: Italian: Categories in Collection
    When Close tutorial screen
      And Close welcome screen in Italian
      And Add library "LYRASIS Reads" on Add library screen
    Then Category rows are loaded
    When Open 'Audible Studios' category
      And Open Collection on catalog screen
    Then Elements on Collection tab are translated correctly in Italian

  @tier4
  Scenario: Italian: Bottom Menu
    When Close tutorial screen
      And Close welcome screen in Italian
      And Add library "LYRASIS Reads" on Add library screen
    Then Category rows are loaded
      And Elements on Bottom menu are translated correctly in Italian

  @tier4
  Scenario: Italian: Book details view screen
    When Close tutorial screen
      And Close welcome screen in Italian
      And Add library "LYRASIS Reads" on Add library screen
    Then Category rows are loaded
    When Open search modal
      And Search for "Dune" and save bookName as 'bookNameInfo'
      And Open first book in Subcategory List and save it as 'bookInfo'
    Then Elements on Book detail view are translated correctly in Italian

  @tier4 @returnBooks @logoutIT
  Scenario: Italian: epub: reader screen
    When Close tutorial screen
      And Close welcome screen in Italian
      And Add library "LYRASIS Reads" on Add library screen
      And Enter credentials for "LYRASIS Reads" account in Italian
      And Open Catalog in Italian
    When Open search modal
    And Search for "Libertie" and save bookName as 'bookNameInfo'
    And Open first book in Subcategory List and save it as 'bookInfo'
    And Click GET_IT action button on book details screen
    Then Check that book contains READ_IT action button on book details screen
    When Click READ_IT action button on book details screen
    Then Elements on Reader epub screen are translated correctly in Italian

  @tier4 @returnBooks @logoutIT
  Scenario: Italian: epub: Table of contents
    When Close tutorial screen
      And Close welcome screen in Spanish
      And Add library "LYRASIS Reads" on Add library screen
      And Enter credentials for "LYRASIS Reads" account in Italian
      And Open Catalog in Italian
      And Open search modal
      And Search for "Libertie" and save bookName as 'bookNameInfo'
      And Open first book in Subcategory List and save it as 'bookInfo'
      And Click GET_IT action button on book details screen
    Then Check that book contains READ_IT action button on book details screen
    When Click READ_IT action button on book details screen
      And Open toc epub screen
    Then Elements on TOC in reader epub are translated correctly in Italian

  @tier4 @returnBooks @logoutIT
  Scenario: Italian: epub: Bookmarks
    When Close tutorial screen
      And Close welcome screen in Italian
      And Add library "LYRASIS Reads" on Add library screen
      And Enter credentials for "LYRASIS Reads" account in Italian
      And Open Catalog in Italian
      And Open search modal
      And Search for "Libertie" and save bookName as 'bookNameInfo'
      And Open first book in Subcategory List and save it as 'bookInfo'
      And Click GET_IT action button on book details screen
    Then Check that book contains READ_IT action button on book details screen
    When Click READ_IT action button on book details screen
      And Open bookmarks epub screen in Italian
    Then Elements on Bookmarks epub screen are translated correctly in Italian

  @tier4
  Scenario: Italian: Settings screen
    When Close tutorial screen
      And Close welcome screen in Italian
      And Add library "LYRASIS Reads" on Add library screen
      And Open Settings in Italian
    Then Elements on Settings screen are translated correctly in Italian

  @tier4
  Scenario: Italian: Reservations screen
    When Close tutorial screen
      And Close welcome screen in Italian
      And Add library "LYRASIS Reads" on Add library screen
      And Open Holds in Italian
    Then Elements on Holds screen are translated correctly

  @tier4
  Scenario: Italian: Account screen
    When Close tutorial screen
      And Close welcome screen in Italian
      And Add library "LYRASIS Reads" on Add library screen
      And Open Settings in Italian
      And Open Libraries on settings screen
      And Open "LYRASIS Reads" library on setting screen
    Then Elements on Account screen are translated correctly in Italian

  @tier4 @returnBooks @logoutIT
  Scenario: Italian: pdf: reader screen
    When Close tutorial screen
      And Close welcome screen in Italian
      And Add library "LYRASIS Reads" on Add library screen
      And Enter credentials for "LYRASIS Reads" account in Italian
      And Open Catalog in Italian
      And Open search modal
      And Search for "Helping Injured Animals" and save bookName as 'bookNameInfo'
      And Open first book in Subcategory List and save it as 'bookInfo'
      And Click GET_IT action button on book details screen
    Then Check that book contains READ_IT action button on book details screen
    When Click READ_IT action button on book details screen
    Then Reader pdf screen is opened
    When Open search pdf screen
    Then Elements on pdf search screen are translated correctly in Italian

  @tier4 @returnBooks @logoutIT
  Scenario: Italian: pdf: Bookmarks screen
    When Close tutorial screen
      And Close welcome screen in Italian
      And Add library "LYRASIS Reads" on Add library screen
      And Enter credentials for "LYRASIS Reads" account in Italian
      And Open Catalog in Italian
      And Open search modal
      And Search for "Helping Injured Animals" and save bookName as 'bookNameInfo'
      And Open first book in Subcategory List and save it as 'bookInfo'
      And Click GET_IT action button on book details screen
    Then Check that book contains READ_IT action button on book details screen
    When Click READ_IT action button on book details screen
    Then Reader pdf screen is opened
    When Open bookmarks pdf screen
    Then Elements on pdf bookmarks screen are translated correctly in Italian

  @tier4 @returnBooks @logoutIT
  Scenario: Spanish: Audiobooks: player screen
    When Close tutorial screen
      And Close welcome screen in Italian
      And Add library "LYRASIS Reads" on Add library screen
      And Enter credentials for "LYRASIS Reads" account in Italian
      And Open Catalog in Italian
      And Open search modal
      And Search for "Fault Lines" and save bookName as 'bookNameInfo'
      And Open first book in Subcategory List and save it as 'bookInfo'
      And Click GET_IT action button on book details screen
    Then Check that book contains LISTEN_IT action button on book details screen
    When Click LISTEN_IT action button on book details screen
    Then Elements on Audio Player screen are translated correctly in Italian


  @tier4 @returnBooks @logoutIT
  Scenario: Italian: Audiobooks: playback screen
    When Close tutorial screen
      And Close welcome screen in Italian
      And Add library "LYRASIS Reads" on Add library screen
      And Enter credentials for "LYRASIS Reads" account in Italian
      And Open Catalog in Italian
      And Open search modal
      And Search for "The Secret Garden" and save bookName as 'bookNameInfo'
      And Open first book in Subcategory List and save it as 'bookInfo'
      And Click GET_IT action button on book details screen
    Then Check that book contains LISTEN_IT action button on book details screen
    When Click LISTEN_IT action button on book details screen
      And Open playback speed on audio player screen
    Then Elements on Playback speed are translated correctly in Italian

  @tier4 @returnBooks @logoutIT
  Scenario: Italian: Audiobooks: sleep timer screen
    When Close tutorial screen
      And Close welcome screen in Italian
      And Add library "LYRASIS Reads" on Add library screen
      And Enter credentials for "LYRASIS Reads" account in Italian
      And Open Catalog in Italian
      And Open search modal
      And Search for "The Sentence" and save bookName as 'bookNameInfo'
      And Open first book in Subcategory List and save it as 'bookInfo'
      And Click GET_IT action button on book details screen
    Then Check that book contains LISTEN_IT action button on book details screen
    When Click LISTEN_IT action button on book details screen
      And Open sleep timer on audio player screen
    Then Elements on Sleep timer screen are translated correctly in Italian

  @tier4 @returnBooks @logoutIT
  Scenario: Italian: Audiobooks: table of contents screen
    When Close tutorial screen
      And Close welcome screen in Italian
      And Add library "LYRASIS Reads" on Add library screen
      And Enter credentials for "LYRASIS Reads" account in Italian
      And Open Catalog in Italian
      And Open search modal
      And Search for "L.A. Weather" and save bookName as 'bookNameInfo'
      And Open first book in Subcategory List and save it as 'bookInfo'
      And Click GET_IT action button on book details screen
    Then Check that book contains LISTEN_IT action button on book details screen
    When Click LISTEN_IT action button on book details screen
      And Open toc audiobook screen
    Then Elements on TOC audiobook screen are translated correctly