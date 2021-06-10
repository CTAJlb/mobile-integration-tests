Feature: Audiobook

  Background:
    Given I add "LYRASIS" account from welcomeScreen
    Then Account 'LYRASIS' is present on Accounts screen
    When I enter credentials for 'LYRASIS' account
    Then Login is performed successfully
    When I open Catalog
      And I switch to 'LYRASIS' from side menu
      And I switch to 'Audiobooks' catalog tab
    When I open category by chain:
      | Fiction     |
      | All Fiction |
      And I open the book details for the subsequent GET and save it as 'bookInfo'

  @logout @returnBooks @tier2
  Scenario: Navigate by Table of Contents Menu
    When Press on the book details screen at the action button LISTEN
      And Open chapter list for an audiobook
    When I select the chapter not equal to first chapter and remember selected chapter text as 'newChapterText'
    Then I check that current chapter text equal to remembered 'newChapterText'
      And Pause button is present
      And Book is playing


  @logout @returnBooks @tier2
  Scenario: Loading chapters
    When Press on the book details screen at the action button LISTEN
    Then Download has started and percentage value increased
    When Open chapter list for an audiobook
    Then I check that chapters are visible and check that all loaders are disappeared

  @logout @returnBooks @tier2
  Scenario: Return to Chapter (Bookmarking/Syncing)
    When Press on the book details screen at the action button LISTEN
      And Open chapter list for an audiobook
    When I select the chapter not equal to first chapter and remember selected chapter text as 'newChapterText'
      And I return to previous screen for audiobook
      And Press on the book details screen at the action button LISTEN
    Then I check that current chapter text equal to remembered 'newChapterText'
    When I restart app
      And I open Books
      And I open book 'bookInfo' details by clicking on cover
      And Press on the book details screen at the action button LISTEN
    Then I check that current chapter text equal to remembered 'newChapterText'

  @logout @returnBooks @tier2
  Scenario: Play Audiobook
    Then I check that opened book contains LISTEN button at book details screen
    When Press on the book details screen at the action button LISTEN
      And I click play button on player screen
    Then Pause button is present
      And Book is playing
    When I click pause button on player screen
    Then Play button is present
      And Book is not playing

  @logout @returnBooks @tier2
  Scenario: Navigate Audiobook
    When Press on the book details screen at the action button LISTEN
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

  @logout @returnBooks @tier2
  Scenario Outline: Navigate playback options
    When Press on the book details screen at the action button LISTEN
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
