Feature: Audiobook

  Background:
    Given I add "LYRASIS" account from welcomeScreen
    Then Account 'LYRASIS' is present on Accounts screen
    When I enter credentials for 'LYRASIS' account
    Then Login is performed successfully
    When I open Catalog
      And I switch to 'LYRASIS' from side menu
    Then Books feed is loaded
    When I switch to 'Audiobooks' catalog tab
    Then Books feed is loaded
    When I open first present category
      And I open the book details for the subsequent GET and save it as 'bookInfo'
    Then I check that opened book contains LISTEN button at book details screen

  @logout @returnBooks @tier2 @exclude_android
  Scenario: Navigate by Table of Contents Menu
    When Press on the book details screen at the action button LISTEN
      And Open the menu-based position in the audiobook
    Then I check that chapters are visible
      And Wait and check that all loaders are disappeared
    When I select 2 chapter and remember selected chapter as 'newChapter'
    Then I check that current chapter equal to remembered 'newChapter'

  @logout @returnBooks @tier2 @exclude_android
  Scenario: Return to Chapter (Bookmarking/Syncing)
    When Press on the book details screen at the action button LISTEN
      And Remember current book chapter in 'defaultChapter'
      And Open the menu-based position in the audiobook
    Then I check that chapters are visible
      And Wait and check that all loaders are disappeared
    When I select 2 chapter and remember selected chapter as 'newChapter'
    Then I check that current chapter equal to remembered 'newChapter'
    When I restart app
      And I open Catalog
      And I switch to 'LYRASIS' from side menu
      And I open Books
      And I open book 'bookInfo' details by clicking on cover
      And Press on the book details screen at the action button LISTEN
    Then I check that current chapter equal to remembered 'newChapter'

  @logout @returnBooks @tier2 @exclude_android
  Scenario: Play Audiobook
    When Press on the book details screen at the action button LISTEN
      And I click play button on player screen
    Then Pause button is present
      And Book is playing
    When I click pause button on player screen
    Then Play button is present
      And Book is not playing

  @logout @returnBooks @tier2 @exclude_android
  Scenario: Navigate Audiobook
    When Press on the book details screen at the action button LISTEN
      And I click play button on player screen
      And I save chapter length as 'chapterLength'
    Then Pause button is present
      And Book is playing
    When I move to middle part of chapter
    Then Saved play time 'chapterLength' is close to middle part of chapter
    When I save book play time as 'timeAhead'
      And I skip ahead 15 seconds
    Then Playback 'timeAhead' moves forward by 15 seconds increment
    When I save book play time as 'timeBehind'
      And I skip behind 15 seconds
    Then Playback 'timeBehind' moves behind by 15 seconds increment

  @logout @returnBooks @tier2 @exclude_android
  Scenario: Navigate playback options
    When Press on the book details screen at the action button LISTEN
      And I click play button on player screen
    Then Pause button is present
      And Book is playing
    When I select playback speed 2X
      And I save book play time as 'timeAhead'
      And I wait for 5 seconds
    Then Playback 'timeAhead' moves forward by 10 seconds increment
      And Current playback speed value is 2X
    When I click pause button on player screen
      And I set sleep timer as END_OF_CHAPTER
    Then Sleep timer shows time till chapter finish
