Feature: Audiobook in Lyrasis

  Background:
    Given I add "LYRASIS Reads" account from welcomeScreen
    Then Account 'LYRASIS Reads' is present on Accounts screen
    When I enter credentials for 'LYRASIS Reads' account
    Then Login is performed successfully
    When I open Catalog
      And I open search modal

  @logout @returnBooks @tier2
  Scenario Outline: Open the audiobook at the last open chapter
    When I search 'available' book of distributor '<distributor>' and bookType 'AUDIOBOOK' and save as 'bookNameInfo'
      And I switch to 'Audiobooks' catalog tab
      And Open AUDIOBOOK book with GET action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click GET action button on book details screen
    Then I check that book contains LISTEN action button on book details screen
    When Click LISTEN action button on book details screen
      And Open toc audiobook screen
      And Open random chapter on toc audiobook screen and save chapter name as 'chapterNameKey'
      And Wait for 12 seconds
    Then Chapter name on audio player screen is equal to 'chapterNameKey' saved chapter name
      And Pause button is present on audio player screen
      And Book is playing on audio player screen
    When Return to previous screen from audio player screen
      And Click LISTEN action button on book details screen
      And Wait for 12 seconds
    Then Chapter name on audio player screen is equal to 'chapterNameKey' saved chapter name
    When Open toc audiobook screen
      And Open the 4 chapter on toc audiobook screen and save the chapter name as 'chapterNameKey2'
      And Wait for 12 seconds
      And I restart app
      And I open Books
      And Open AUDIOBOOK book with LISTEN action button and 'bookInfo' bookInfo on books screen
      And Click LISTEN action button on book details screen
      And Wait for 12 seconds
    Then Chapter name on audio player screen is equal to 'chapterNameKey2' saved chapter name

    Scenarios:
      | distributor        |
      | Bibliotheca        |
      | Palace Marketplace |
      | Axis 360           |
      | Biblioboard        |

  @logout @returnBooks @tier2
  Scenario Outline: Navigate by Audiobook
    When I search 'available' book of distributor '<distributor>' and bookType 'AUDIOBOOK' and save as 'bookNameInfo'
      And I switch to 'Audiobooks' catalog tab
      And Open AUDIOBOOK book with GET action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click GET action button on book details screen
    Then I check that book contains LISTEN action button on book details screen
    When Click LISTEN action button on book details screen
      And Tap play button on audio player screen
    Then Pause button is present on audio player screen
    When Tap pause button on audio player screen
    Then Play button is present on audio player screen
      And Book is not playing on audio player screen
    When Tap play button on audio player screen
      And Save book play time as 'timeAhead' on audio player screen
      And Skip ahead 15 seconds on audio player screen
    Then Playback has been moved forward by 15 seconds from 'timeAhead' seconds on audio player screen
    When Save book play time as 'timeBehind' on audio player screen
      And Skip behind 15 seconds on audio player screen
    Then Playback has been moved behind by 15 seconds from 'timeBehind' seconds on audio player screen

    Scenarios:
      | distributor        |
      | Bibliotheca        |
      | Palace Marketplace |
      | Axis 360           |
      | Biblioboard        |

  @logout @returnBooks @tier2
  Scenario Outline: Navigate by Playback Options
    When I search 'available' book of distributor 'Bibliotheca' and bookType 'AUDIOBOOK' and save as 'bookNameInfo'
      And I switch to 'Audiobooks' catalog tab
      And Open AUDIOBOOK book with GET action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click GET action button on book details screen
    Then I check that book contains LISTEN action button on book details screen
    When Click LISTEN action button on book details screen
      And Tap play button on audio player screen
      And Select <speed>X playback speed on playback speed audiobook screen
      And Save book play time as 'timeAhead' on audio player screen
      And Wait for <secondsForWaiting> seconds
    Then Playback has been moved forward by <moveForwardSeconds> seconds from 'timeAhead' seconds on audio player screen
      And Current playback speed value is <speed>X on audio player screen
    When Tap pause button on audio player screen
      And Set END_OF_CHAPTER sleep timer on sleep timer audiobook screen
    Then Sleep timer is set to endOfChapter on audio player screen

    Scenarios:
      | speed | secondsForWaiting | moveForwardSeconds |
      | 2     | 5                 | 10                 |
      | 0.75  | 6                 | 8                  |

  @logout @returnBooks @tier2
  Scenario Outline: Check time code of track after reload app
    When I search 'available' book of distributor '<distributor>' and bookType 'AUDIOBOOK' and save as 'bookNameInfo'
      And I switch to 'Audiobooks' catalog tab
      And Open AUDIOBOOK book with GET action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click GET action button on book details screen
    Then I check that book contains LISTEN action button on book details screen
    When Click LISTEN action button on book details screen
      And Tap play button on audio player screen
      And Wait for 5 seconds
      And I tap on the middle of chapter on audio player screen
      And  Tap pause button on audio player screen
      And Save book play time as 'timeAhead' on audio player screen
      And Return to previous screen from audio player screen
      And Click LISTEN action button on book details screen
      And Wait for 12 seconds
    Then Play time is the same with 'timeAhead' play time before restart on books detail screen
      And I restart app
      And I open Books
      And Open AUDIOBOOK book with LISTEN action button and 'bookInfo' bookInfo on books screen
      And Click LISTEN action button on book details screen
    Then Play time is the same with 'timeAhead' play time before restart on books detail screen

    Scenarios:
      | distributor        |
      | Bibliotheca        |
      | Palace Marketplace |
      | Axis 360           |
      | Biblioboard        |

  @logout @returnBooks @tier2
  Scenario Outline: Check sleep timer
    When I search 'available' book of distributor '<distributor>' and bookType 'AUDIOBOOK' and save as 'bookNameInfo'
      And I switch to 'Audiobooks' catalog tab
      And Open AUDIOBOOK book with GET action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click GET action button on book details screen
    Then I check that book contains LISTEN action button on book details screen
    When Click LISTEN action button on book details screen
      And Open toc audiobook screen
      And Open the 1 chapter on toc audiobook screen and save the chapter name as 'chapterName' and chapter number as 'chapterNumber'
      And Set END_OF_CHAPTER sleep timer on sleep timer audiobook screen
      And Select 2X playback speed on playback speed audiobook screen
      And I tap on the middle of chapter on audio player screen
      And Listen a chapter on audio player screen
    Then Play button is present on audio player screen
      When Save the name of chapter as 'nextChapter' on audio player screen
      And Open toc audiobook screen
    Then Chapter name next to 'chapterNumber' on toc audiobook screen is equal to 'nextChapter' saved chapter name

    Scenarios:
      | distributor        |
      | Bibliotheca        |
      | Palace Marketplace |
      | Axis 360           |
      | Biblioboard        |