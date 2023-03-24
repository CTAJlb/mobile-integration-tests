Feature: Audiobooks from Audible studios in LYRASIS

  Background:
    Given Close tutorial screen
    Then Welcome screen is opened
    When Close welcome screen
    Then Add library screen is opened
    When Add library "LYRASIS Reads" on Add library screen
    Then Account "LYRASIS Reads" is present on Accounts screen
    When Enter credentials for 'LYRASIS Reads' account
    Then Login is performed successfully
    When Open Catalog

  @logout @returnBooks @tier1
  Scenario: Open the audiobook at the last open chapter and check time code
    When Open categories by chain and chain starts from CategoryScreen:
      | Audible Studios |
      And Get names of books on screen and save them as 'listOfBooks'
      And Open Catalog
      And Open search modal
      And Search for a AUDIOBOOK book from 'listOfBooks' and save as 'bookNameInfo'
      And Switch to 'Audiobooks' catalog tab
      And Open AUDIOBOOK book with GET action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click GET action button on book details screen
    Then Check that book contains LISTEN action button on book details screen
    When Click LISTEN action button on book details screen
    Then Audio player screen of book 'bookInfo' is opened
    When Open toc audiobook screen
      And Wait for 5 seconds
      And Open the 3 chapter on toc audiobook screen and save the chapter name as 'chapterNameKey'
    Then Audio player screen of book 'bookInfo' is opened
      And Chapter name on audio player screen is equal to 'chapterNameKey' saved chapter name
      And Pause button is present on audio player screen
    When Select 2X playback speed on playback speed audiobook screen
      And Wait for 3 seconds
      And Tap pause button on audio player screen
    Then Play button is present on audio player screen
    When Save book play time as 'timeAhead' on audio player screen
      And Return to previous screen from audio player screen
      And Click LISTEN action button on book details screen
    Then Audio player screen of book 'bookInfo' is opened
      And Chapter name on audio player screen is equal to 'chapterNameKey' saved chapter name
      And Play time is the same with 'timeAhead' play time before restart on books detail screen
    When Open toc audiobook screen
      And Open random chapter on toc audiobook screen and save chapter name as 'chapterNameKey2'
    Then Audio player screen of book 'bookInfo' is opened
    When Wait for 3 seconds
      And Tap pause button on audio player screen
    Then Play button is present on audio player screen
    When Save book play time as 'timeAhead' on audio player screen
      And Restart app
      And Open Books
      And Open AUDIOBOOK book with LISTEN action button and 'bookInfo' bookInfo on books screen
      And Click LISTEN action button on book details screen
    Then Audio player screen of book 'bookInfo' is opened
      And Chapter name on audio player screen is equal to 'chapterNameKey2' saved chapter name
      And Play time is the same with 'timeAhead' play time before restart on books detail screen

  @logout @returnBooks @tier1
  Scenario: Navigate by Audiobook

  @logout @returnBooks @tier1
  Scenario: Check end of chapter sleep timer

  @logout @returnBooks @tier1 @exclude_android
  Scenario: Check of line for time remaining

  @logout @returnBooks @tier1
  Scenario: Check of switching to the next time

  @logout @returnBooks @tier1
  Scenario: Check playback speed and sleep timer

  @logout @returnBooks @tier1
  Scenario: Check time tracking line

  @logout @returnBooks @tier1
  Scenario: Check of not rewinding forward and back by tapping on time bar

  @logout @returnBooks @tier1
  Scenario: Playback speed: Check of playback speed