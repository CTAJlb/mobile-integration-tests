Feature: Check of View Sample

  @tier2 @exclude_android
  Scenario: Palace Marketplace: Check of view sample
    When Close tutorial screen
    Then Welcome screen is opened
    When Close welcome screen
    Then Add library screen is opened
    When Add library "LYRASIS Reads" on Add library screen
    Then Account "LYRASIS Reads" is present on Accounts screen
    When Open Catalog
      And Open search modal
      And Search for "The Optimistic Decade" and save bookName as 'bookNameInfo'
      And Switch to "eBooks" catalog tab
      And Open EBOOK book with GET action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click VIEW_SAMPLE action button on book details screen
    Then 'bookInfo' book is present on epub reader screen
    When Open toc epub screen
    Then Toc epub screen is opened
    When Close toc epub screen
      And Return to previous screen from epub
    Then Book 'bookInfo' is opened on book details screen

  @tier2 @exclude_android
  Scenario: Overdrive: Check of view sample in epub
    When Close tutorial screen
    Then Welcome screen is opened
    When Close welcome screen
    Then Add library screen is opened
    When Add library "Palace Bookshelf" on Add library screen
    Then Account "Palace Bookshelf" is present on Accounts screen
    When Turn on test mode
      And Enable hidden libraries
      And Open Catalog
      And Add "A1QA Test Library" account by the logo
    Then Account "A1QA Test Library" is present on Accounts screen
    When Open Catalog
      And Open search modal
      And Search for "Alice in Wonderland" and save bookName as 'bookNameInfo'
      And Switch to "eBooks" catalog tab
      And Open EBOOK book with GET action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click VIEW_SAMPLE action button on book details screen
    Then Reader epub screen is opened

  @tier2 @exclude_android
  Scenario: Overdrive: Check of view sample in audiobook
    When Close tutorial screen
    Then Welcome screen is opened
    When Close welcome screen
    Then Add library screen is opened
    When Add library "Palace Bookshelf" on Add library screen
    Then Account "Palace Bookshelf" is present on Accounts screen
    When Turn on test mode
      And Enable hidden libraries
      And Open Catalog
      And Add "A1QA Test Library" account by the logo
    Then Account "A1QA Test Library" is present on Accounts screen
    When Open Catalog
      And Open search modal
      And Search for "The Lost Symbol" and save bookName as 'bookNameInfo'
      And Switch to "Audiobooks" catalog tab
      And Open AUDIOBOOK book with GET action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click PLAY_SAMPLE action button on book details screen
    Then Play button is present on audio player screen
    When Tap play button on audio player screen
    Then Pause button is present on audio player screen
    When Tap pause button on audio player screen
    Then Play button is present on audio player screen
    