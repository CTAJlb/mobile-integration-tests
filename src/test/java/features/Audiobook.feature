Feature: Audiobook

  Background:
    Given I add "LYRASIS Reads" account from welcomeScreen
    Then Account 'LYRASIS Reads' is present on Accounts screen
    When I enter credentials for 'LYRASIS Reads' account
    Then Login is performed successfully
    When I open Catalog
    And I open search modal
    And I search for 'Red Country' and save bookName as 'bookNameInfo'
    And I switch to 'Audiobooks' catalog tab
    And Open AUDIOBOOK book with GET action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
    And I press on the book details view at the action button GET

  @logout @returnBooks @tier2 @oldOs @tablet
  Scenario: Navigate by Table of Contents Menu
    When I press on the book details view at the action button LISTEN
      And Open chapter list for an audiobook
    When I select the chapter not equal to the first 1 chapters and remember selected chapter text as 'newChapterText'
    Then I check that current chapter text equal to remembered 'newChapterText'
      And Pause button is present
      And Book is playing


  @logout @returnBooks @tier2 @oldOs
  Scenario: Loading chapters
    When I press on the book details view at the action button LISTEN
    Then Download has started and percentage value increased
    When Open chapter list for an audiobook
    Then All chapters loaded

  @logout @returnBooks @tier2 @oldOs
  Scenario: Return to Chapter (Bookmarking/Syncing)
    When I press on the book details view at the action button LISTEN
      And Open chapter list for an audiobook
    When I select the chapter not equal to the first 1 chapters and remember selected chapter text as 'newChapterText'
      And I wait for 3 seconds
      And I return to previous screen for audiobook
      And I press on the book details view at the action button LISTEN
    Then I check that current chapter text equal to remembered 'newChapterText'
      And Open chapter list for an audiobook
    When I select the chapter not equal to the first 2 chapters and remember selected chapter text as 'newChapterText2'
      And I wait for 3 seconds
      And I restart app
      And I open Books
      And Open EBOOK book with LISTEN action button and 'bookInfo' bookInfo on books screen
      And I press on the book details view at the action button LISTEN
    Then I check that current chapter text equal to remembered 'newChapterText2'

  @logout @returnBooks @tier2 @oldOs
  Scenario: Play Audiobook
    Then I check that book contains LISTEN action button on book details view
    When I press on the book details view at the action button LISTEN
      And I click play button on player screen
    Then Pause button is present
      And Book is playing
    When I click pause button on player screen
    Then Play button is present
      And Book is not playing

  @logout @returnBooks @tier2 @oldOs
  Scenario: Navigate Audiobook
    When I press on the book details view at the action button LISTEN
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
  Scenario Outline: Navigate playback options
    When I press on the book details view at the action button LISTEN
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
