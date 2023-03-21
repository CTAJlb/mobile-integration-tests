Feature: Audiobook in Lyrasis

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
      And Open search modal

  @logout @returnBooks @tier1
  Scenario Outline: Open the audiobook at the last open chapter and check time code
    When Search 'available' book of distributor '<distributor>' and bookType 'AUDIOBOOK' from "LYRASIS Reads" and save as 'bookNameInfo'
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

    Scenarios:
      | distributor        |
      | Bibliotheca        |
      | Palace Marketplace |
      | Axis 360           |

  @logout @returnBooks @tier1
  Scenario: Biblioboard: Open the audiobook at the last open chapter and check time code
    When Search for "Bocas : a novel" and save bookName as 'bookNameInfo'
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
  Scenario Outline: Navigate by Audiobook
    When Search 'available' book of distributor '<distributor>' and bookType 'AUDIOBOOK' from "LYRASIS Reads" and save as 'bookNameInfo'
      And Switch to 'Audiobooks' catalog tab
      And Open AUDIOBOOK book with GET action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click GET action button on book details screen
    Then Check that book contains LISTEN action button on book details screen
    When Click LISTEN action button on book details screen
    Then Audio player screen of book 'bookInfo' is opened
    When Tap play button on audio player screen
    Then Pause button is present on audio player screen
    When Tap pause button on audio player screen
    Then Play button is present on audio player screen
      And Book is not playing on audio player screen
    When Save book play time as 'timeAhead' on audio player screen
      And Save chapter time as 'chapterTimeKey' on audio player screen
      And Skip ahead 15 seconds on audio player screen
      And Tap pause button on audio player screen
    Then Play button is present on audio player screen
      And Playback has been moved forward by 15 seconds from 'timeAhead' and 'chapterTimeKey' seconds on audio player screen
    When Save book play time as 'timeBehind' on audio player screen
      And Skip behind 15 seconds on audio player screen
      And Tap pause button on audio player screen
    Then Play button is present on audio player screen
      And Playback has been moved behind by 15 seconds from 'timeBehind' and 'chapterTimeKey' seconds on audio player screen

    Scenarios:
      | distributor        |
      | Bibliotheca        |
      | Palace Marketplace |
      | Axis 360           |

  @logout @returnBooks @tier1
  Scenario: Biblioboard: Navigate by Audiobook
    When Search for "Kate & Ruby" and save bookName as 'bookNameInfo'
      And Switch to 'Audiobooks' catalog tab
      And Open AUDIOBOOK book with GET action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'  
      And Click GET action button on book details screen
    Then Check that book contains LISTEN action button on book details screen
    When Click LISTEN action button on book details screen
    Then Audio player screen of book 'bookInfo' is opened
    When Tap play button on audio player screen
    Then Pause button is present on audio player screen
    When Tap pause button on audio player screen
    Then Play button is present on audio player screen
      And Book is not playing on audio player screen
    When Save book play time as 'timeAhead' on audio player screen
      And Save chapter time as 'chapterTimeKey' on audio player screen
      And Skip ahead 15 seconds on audio player screen
      And Tap pause button on audio player screen
    Then Play button is present on audio player screen
      And Playback has been moved forward by 15 seconds from 'timeAhead' and 'chapterTimeKey' seconds on audio player screen
    When Save book play time as 'timeBehind' on audio player screen
      And Skip behind 15 seconds on audio player screen
      And Tap pause button on audio player screen
    Then Play button is present on audio player screen
      And Playback has been moved behind by 15 seconds from 'timeBehind' and 'chapterTimeKey' seconds on audio player screen

  @logout @returnBooks @tier1
  Scenario Outline: Check end of chapter sleep timer
    When Search 'available' book of distributor '<distributor>' and bookType 'AUDIOBOOK' from "LYRASIS Reads" and save as 'bookNameInfo'
      And Switch to 'Audiobooks' catalog tab
      And Open AUDIOBOOK book with GET action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click GET action button on book details screen
    Then Check that book contains LISTEN action button on book details screen
    When Click LISTEN action button on book details screen
    Then Audio player screen of book 'bookInfo' is opened
    When Open toc audiobook screen
    Then The first chapter is loaded
    When Open the 2 chapter on toc audiobook screen and save the chapter name as 'chapterName' and chapter number as 'chapterNumber'
      And Set END_OF_CHAPTER sleep timer on sleep timer audiobook screen
      And Select 2X playback speed on playback speed audiobook screen
      And Stretch slider on the time tracking line forward on audio player screen
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

  @logout @returnBooks @tier1
  Scenario: Biblioboard: Check end of chapter sleep timer
    When Search for "The Columbarium" and save bookName as 'bookNameInfo'
      And Switch to 'Audiobooks' catalog tab
      And Open AUDIOBOOK book with GET action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click GET action button on book details screen
    Then Check that book contains LISTEN action button on book details screen
    When Click LISTEN action button on book details screen
    Then Audio player screen of book 'bookInfo' is opened
    When Open toc audiobook screen
    Then The first chapter is loaded
    When Open the 2 chapter on toc audiobook screen and save the chapter name as 'chapterName' and chapter number as 'chapterNumber'
      And Set END_OF_CHAPTER sleep timer on sleep timer audiobook screen
      And Select 2X playback speed on playback speed audiobook screen
      And Stretch slider on the time tracking line forward on audio player screen
      And Listen a chapter on audio player screen
    Then Play button is present on audio player screen
    When Save the name of chapter as 'nextChapter' on audio player screen
      And Open toc audiobook screen
    Then Chapter name next to 'chapterNumber' on toc audiobook screen is equal to 'nextChapter' saved chapter name

  @logout @returnBooks @tier1 @exclude_android
  Scenario Outline: Check of line for time remaining
    When Search 'available' book of distributor '<distributor>' and bookType 'AUDIOBOOK' from "LYRASIS Reads" and save as 'bookNameInfo'
      And Switch to 'Audiobooks' catalog tab
      And Open AUDIOBOOK book with GET action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click GET action button on book details screen
    Then Check that book contains LISTEN action button on book details screen
    When Click LISTEN action button on book details screen
    Then Audio player screen of book 'bookInfo' is opened
      And Line for time remaining is displayed on audio player screen

    Scenarios:
      | distributor        |
      | Bibliotheca        |
      | Palace Marketplace |
      | Axis 360           |

  @logout @returnBooks @tier1 @exclude_android
  Scenario: Biblioboard: Check of line for time remaining
    When Search for "Two in the Bush" and save bookName as 'bookNameInfo'
      And Switch to 'Audiobooks' catalog tab
      And Open AUDIOBOOK book with GET action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click GET action button on book details screen
    Then Check that book contains LISTEN action button on book details screen
    When Click LISTEN action button on book details screen
    Then Audio player screen of book 'bookInfo' is opened
      And Line for time remaining is displayed on audio player screen

  @logout @returnBooks @tier1
  Scenario Outline: Check of switching to the next time
    When Search 'available' book of distributor '<distributor>' and bookType 'AUDIOBOOK' from "LYRASIS Reads" and save as 'bookNameInfo'
      And Switch to 'Audiobooks' catalog tab
      And Open AUDIOBOOK book with GET action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click GET action button on book details screen
    Then Check that book contains LISTEN action button on book details screen
    When Click LISTEN action button on book details screen
    Then Audio player screen of book 'bookInfo' is opened
    When Open toc audiobook screen
    Then The first chapter is loaded
    When Open the 1 chapter on toc audiobook screen and save the chapter name as 'chapterName' and chapter number as 'chapterNumber'
      And Select 2X playback speed on playback speed audiobook screen
      And Listen a chapter on audio player screen
    Then Next chapter play automatically and chapter name is not 'chapterName' on audio player screen

    Scenarios:
      | distributor        |
      | Bibliotheca        |
      | Palace Marketplace |
      | Axis 360           |

  @logout @returnBooks @tier1
  Scenario: Biblioboard: Check of switching to the next time
    When Search for "Elixir Project" and save bookName as 'bookNameInfo'
      And Switch to 'Audiobooks' catalog tab
      And Open AUDIOBOOK book with GET action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click GET action button on book details screen
    Then Check that book contains LISTEN action button on book details screen
    When Click LISTEN action button on book details screen
    Then Audio player screen of book 'bookInfo' is opened
    When Open toc audiobook screen
    Then The first chapter is loaded
    When Open the 1 chapter on toc audiobook screen and save the chapter name as 'chapterName' and chapter number as 'chapterNumber'
      And Select 2X playback speed on playback speed audiobook screen
      And Listen a chapter on audio player screen
    Then Next chapter play automatically and chapter name is not 'chapterName' on audio player screen

  @logout @returnBooks @tier1
  Scenario Outline: Check playback speed and sleep timer
    When Search 'available' book of distributor '<distributor>' and bookType 'AUDIOBOOK' from "LYRASIS Reads" and save as 'bookNameInfo'
      And Switch to 'Audiobooks' catalog tab
      And Open AUDIOBOOK book with GET action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click GET action button on book details screen
    Then Check that book contains LISTEN action button on book details screen
    When Click LISTEN action button on book details screen
    Then Audio player screen of book 'bookInfo' is opened
      And The speed by default is 1.0
    When Open playback speed on audio player screen
      And Close playback speed screen
    Then Play button is present on audio player screen
    When Set END_OF_CHAPTER sleep timer on sleep timer audiobook screen
    Then Sleep timer is set to endOfChapter on audio player screen
    When Open sleep timer on audio player screen
      And Close sleep timer screen
    Then Play button is present on audio player screen

    Scenarios:
      | distributor        |
      | Bibliotheca        |
      | Palace Marketplace |
      | Axis 360           |

  @logout @returnBooks @tier1
  Scenario: Biblioboard: Check playback speed and sleep timer
    When Search for "Nobody Move" and save bookName as 'bookNameInfo'
      And Switch to 'Audiobooks' catalog tab
      And Open AUDIOBOOK book with GET action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click GET action button on book details screen
    Then Check that book contains LISTEN action button on book details screen
    When Click LISTEN action button on book details screen
    Then Audio player screen of book 'bookInfo' is opened
      And The speed by default is 1.0
    When Open playback speed on audio player screen
      And Close playback speed screen
    Then Play button is present on audio player screen
    When Set END_OF_CHAPTER sleep timer on sleep timer audiobook screen
    Then Sleep timer is set to endOfChapter on audio player screen
    When Open sleep timer on audio player screen
      And Close sleep timer screen
    Then Play button is present on audio player screen

  @logout @returnBooks @tier1
  Scenario Outline: Check time tracking line
    When Search 'available' book of distributor '<distributor>' and bookType 'AUDIOBOOK' from "LYRASIS Reads" and save as 'bookNameInfo'
      And Switch to 'Audiobooks' catalog tab
      And Open AUDIOBOOK book with GET action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click GET action button on book details screen
    Then Check that book contains LISTEN action button on book details screen
    When Click LISTEN action button on book details screen
    Then Audio player screen of book 'bookInfo' is opened
    When Tap play button on audio player screen
    Then Pause button is present on audio player screen
    When Tap pause button on audio player screen
    Then Play button is present on audio player screen
    When Save book play time as 'timeInfo' on audio player screen
      And Stretch slider on the time tracking line forward on audio player screen
      And Wait for 5 seconds
    Then Playing time is not equal to 'timeInfo' on audio playing screen
    When Save book play time as 'timeInfo2' on audio player screen
      And Stretch slider on the time tracking line back on audio player screen
      And Wait for 5 seconds
    Then Playing time is not equal to 'timeInfo2' on audio playing screen

    Scenarios:
      | distributor        |
      | Bibliotheca        |
      | Palace Marketplace |
      | Axis 360           |

  @logout @returnBooks @tier1
  Scenario: Biblioboard: Check time tracking line
    When Search for "Baby Carter" and save bookName as 'bookNameInfo'
      And Switch to 'Audiobooks' catalog tab
      And Open AUDIOBOOK book with GET action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
    And Click GET action button on book details screen
    Then Check that book contains LISTEN action button on book details screen
    When Click LISTEN action button on book details screen
    Then Audio player screen of book 'bookInfo' is opened
    When Tap play button on audio player screen
    Then Pause button is present on audio player screen
    When Tap pause button on audio player screen
    Then Play button is present on audio player screen
    When Save book play time as 'timeInfo' on audio player screen
      And Stretch slider on the time tracking line forward on audio player screen
      And Wait for 5 seconds
    Then Playing time is not equal to 'timeInfo' on audio playing screen
    When Save book play time as 'timeInfo2' on audio player screen
      And Stretch slider on the time tracking line back on audio player screen
      And Wait for 5 seconds
    Then Playing time is not equal to 'timeInfo2' on audio playing screen

  @logout @returnBooks @tier1
  Scenario Outline: Check of not rewinding forward and back by tapping on time bar
    When Search 'available' book of distributor '<distributor>' and bookType 'AUDIOBOOK' from "LYRASIS Reads" and save as 'bookNameInfo'
      And Switch to 'Audiobooks' catalog tab
      And Open AUDIOBOOK book with GET action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click GET action button on book details screen
    Then Check that book contains LISTEN action button on book details screen
    When Click LISTEN action button on book details screen
    Then Audio player screen of book 'bookInfo' is opened
    When Tap play button on audio player screen
    Then Pause button is present on audio player screen
    When Tap pause button on audio player screen
    Then Play button is present on audio player screen
    When Stretch slider on the time tracking line forward on audio player screen
      And Wait for 5 seconds
      And Save book play time as 'timeBehind' on audio player screen
      And Tap on the time bar forward on audio player screen
      And Save book play time as 'timeForward' on audio player screen
    Then Play times 'timeBehind' and 'timeForward' are equals
    When Tap on the time bar back on audio player screen
      And Save book play time as 'timeBackward' on audio player screen
    Then Play times 'timeBehind' and 'timeBackward' are equals

    Scenarios:
      | distributor        |
      | Bibliotheca        |
      | Palace Marketplace |
      | Axis 360           |

  @logout @returnBooks @tier1
  Scenario: BiblioBoard: Check of not rewinding forward and back by tapping on time bar
    When Search for "Daykeeper" and save bookName as 'bookNameInfo'
      And Switch to 'Audiobooks' catalog tab
      And Open AUDIOBOOK book with GET action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click GET action button on book details screen
    Then Check that book contains LISTEN action button on book details screen
    When Click LISTEN action button on book details screen
    Then Audio player screen of book 'bookInfo' is opened
    When Tap play button on audio player screen
    Then Pause button is present on audio player screen
    When Tap pause button on audio player screen
    Then Play button is present on audio player screen
    When Stretch slider on the time tracking line forward on audio player screen
      And Wait for 5 seconds
      And Save book play time as 'timeBehind' on audio player screen
      And Tap on the time bar forward on audio player screen
      And Save book play time as 'timeForward' on audio player screen
    Then Play times 'timeBehind' and 'timeForward' are equals
    When Tap on the time bar back on audio player screen
      And Save book play time as 'timeBackward' on audio player screen
    Then Play times 'timeBehind' and 'timeBackward' are equals

  @logout @returnBooks @tier1
  Scenario Outline: Playback speed: Check of playback speed
    When Search 'available' book of distributor '<distributor>' and bookType 'AUDIOBOOK' from "LYRASIS Reads" and save as 'bookNameInfo'
      And Switch to 'Audiobooks' catalog tab
      And Open AUDIOBOOK book with GET action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click GET action button on book details screen
    Then Check that book contains LISTEN action button on book details screen
    When Click LISTEN action button on book details screen
    Then Audio player screen of book 'bookInfo' is opened
    When Select <speed>X playback speed on playback speed audiobook screen
    Then Current playback speed value is <speed>X on audio player screen
    When Return to previous screen from audio player screen
      And Click LISTEN action button on book details screen
    Then Audio player screen of book 'bookInfo' is opened
      And Current playback speed value is <speed>X on audio player screen
    When Restart app
      And Open Books
      And Open AUDIOBOOK book with LISTEN action button and 'bookInfo' bookInfo on books screen
      And Click LISTEN action button on book details screen
    Then Audio player screen of book 'bookInfo' is opened
      And Current playback speed value is <speed>X on audio player screen
    When Tap play button on audio player screen
      And Save book play time as 'timeAhead' on audio player screen
      And Save chapter time as 'chapterTimeKey' on audio player screen
      And Wait for <secondsForWaiting> seconds
    Then Playback has been moved forward by <moveForwardSeconds> seconds from 'timeAhead' and 'chapterTimeKey' seconds on audio player screen

    Scenarios:
      | distributor        | speed | secondsForWaiting | moveForwardSeconds |
      | Bibliotheca        | 0.75  | 8                 | 6                  |
      | Bibliotheca        | 1.25  | 8                 | 10                 |
      | Bibliotheca        | 1.50  | 6                 | 9                  |
      | Bibliotheca        | 2     | 5                 | 10                 |
      | Palace Marketplace | 0.75  | 8                 | 6                  |
      | Palace Marketplace | 1.25  | 8                 | 10                 |
      | Palace Marketplace | 1.50  | 6                 | 9                  |
      | Palace Marketplace | 2     | 5                 | 10                 |
      | Axis 360           | 0.75  | 8                 | 6                  |
      | Axis 360           | 1.25  | 8                 | 10                 |
      | Axis 360           | 1.50  | 6                 | 9                  |
      | Axis 360           | 2     | 5                 | 10                 |

  @logout @returnBooks @tier1
  Scenario Outline: Biblioboard: Playback speed: Check of playback speed
    When Search for "Four Women" and save bookName as 'bookNameInfo'
      And Switch to 'Audiobooks' catalog tab
      And Open AUDIOBOOK book with GET action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click GET action button on book details screen
    Then Check that book contains LISTEN action button on book details screen
    When Click LISTEN action button on book details screen
    Then Audio player screen of book 'bookInfo' is opened
    When Select <speed>X playback speed on playback speed audiobook screen
    Then Current playback speed value is <speed>X on audio player screen
    When Return to previous screen from audio player screen
      And Click LISTEN action button on book details screen
    Then Audio player screen of book 'bookInfo' is opened
      And Current playback speed value is <speed>X on audio player screen
    When Restart app
      And Open Books
      And Open AUDIOBOOK book with LISTEN action button and 'bookInfo' bookInfo on books screen
      And Click LISTEN action button on book details screen
    Then Audio player screen of book 'bookInfo' is opened
      And Current playback speed value is <speed>X on audio player screen
    When Tap play button on audio player screen
      And Save book play time as 'timeAhead' on audio player screen
      And Save chapter time as 'chapterTimeKey' on audio player screen
      And Wait for <secondsForWaiting> seconds
    Then Playback has been moved forward by <moveForwardSeconds> seconds from 'timeAhead' and 'chapterTimeKey' seconds on audio player screen

    Scenarios:
      | speed | secondsForWaiting | moveForwardSeconds |
      | 0.75  | 8                 | 6                  |
      | 1.25  | 8                 | 10                 |
      | 1.50  | 6                 | 9                  |
      | 2     | 5                 | 10                 |