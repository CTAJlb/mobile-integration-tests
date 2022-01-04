Feature: Audiobook

  Background:
    Given I add "LYRASIS Reads" account from welcomeScreen
    Then Account 'LYRASIS Reads' is present on Accounts screen
    When I enter credentials for 'LYRASIS Reads' account
    Then Login is performed successfully
    When I open Catalog
    And I open search modal
    And I search for 'The Santa Suit' and save bookName as 'bookNameInfo'
    And I switch to 'Audiobooks' catalog tab
    And Open AUDIOBOOK book with GET action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
    And Click GET action button on book details screen

  @logout @returnBooks @tier2 @oldOs @tablet
  Scenario: Navigate by Table of Contents
    When Click LISTEN action button on book details screen
      And Open chapter list for an audiobook
    When Select a random chapter that is not equal to the first chapter and save the chapter as 'chapterKey'
      And I wait for 12 seconds
    Then Chapter is equal to 'chapterKey' saved chapter
      And Pause button is present
      And Book is playing

  @logout @returnBooks @tier2 @oldOs
  Scenario: Loading chapters
    When Click LISTEN action button on book details screen
    Then Download has started and percentage value increased
    When Open chapter list for an audiobook
    Then All chapters loaded

  @logout @returnBooks @tier2 @oldOs
  Scenario: Open the audiobook at the last open chapter
    When Click LISTEN action button on book details screen
      And Open chapter list for an audiobook
    When Select the 2 chapter and save the chapter as 'chapterKey'
      And I wait for 12 seconds
      And I return to previous screen for audiobook
      And Click LISTEN action button on book details screen
      And I wait for 12 seconds
    Then Chapter is equal to 'chapterKey' saved chapter
    When Open chapter list for an audiobook
      And Select the 4 chapter and save the chapter as 'chapterKey2'
      And I wait for 12 seconds
      And I restart app
      And I open Books
      And Open AUDIOBOOK book with LISTEN action button and 'bookInfo' bookInfo on books screen
      And Click LISTEN action button on book details screen
      And I wait for 12 seconds
    Then Chapter is equal to 'chapterKey2' saved chapter

  @logout @returnBooks @tier2 @oldOs
  Scenario: Playing Audiobook
    Then I check that book contains LISTEN action button on book details screen
    When Click LISTEN action button on book details screen
      And I click play button on player screen
    Then Pause button is present
      And Book is playing
    When I click pause button on player screen
    Then Play button is present
      And Book is not playing

  @logout @returnBooks @tier2 @oldOs
  Scenario: Navigate by Audiobook
    When Click LISTEN action button on book details screen
      And I click play button on player screen
      And I save chapter length as 'chapterLength'
      And I save book play time as 'timeAhead'
      And I skip ahead 15 seconds
    Then Playback 'timeAhead' moves forward by 15 seconds increment
    When I save book play time as 'timeBehind'
      And I skip behind 15 seconds
    Then Playback 'timeBehind' moves behind by 15 seconds increment
    When I move to middle part of chapter
    Then Saved play time 'chapterLength' is close to middle part of chapter

  @logout @returnBooks @tier2 @oldOs
  Scenario Outline: Navigate by Playback Options
    When Click LISTEN action button on book details screen
      And I click play button on player screen
    When I select playback speed <speed>X
      And I save book play time as 'timeAhead'
      And I wait for <secondsForWaiting> seconds
    Then Playback 'timeAhead' moves forward by <moveForwardSeconds> seconds increment
      And Current playback speed value is <speed>X
    When I click pause button on player screen
    When I set sleep timer as END_OF_CHAPTER
    Then Sleep timer is set to endOfChapter

    Scenarios:
      | speed | secondsForWaiting | moveForwardSeconds |
      | 2     | 5                 | 10                 |
      | 0.75  | 6                 | 8                  |
