Feature: Check of View Sample

  @tier2 @exclude_android
  Scenario: Palace Marketplace: Check of view sample
    When I add "LYRASIS Reads" account from welcomeScreen
    Then Account "LYRASIS Reads" is present on Accounts screen
    When I open Catalog
      And I open search modal
      And I search for "The Optimistic Decade" and save bookName as 'bookNameInfo'
      And I switch to "eBooks" catalog tab
      And Open EBOOK book with GET action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click VIEW_SAMPLE action button on book details screen
    Then 'bookInfo' book is present on epub reader screen
    When Open toc epub screen
    Then Toc epub screen is opened
    When Close toc epub screen
      And I return to previous screen from epub
    Then Book 'bookInfo' is opened on book details screen

  @tier2 @exclude_android
  Scenario: Overdrive: Check of view sample
    When I turn on test mode in "Palace Bookshelf" library
      And Enable hidden libraries
      And I open Catalog
      And I add "A1QA Test Library" account by the logo
    Then Account "A1QA Test Library" is present on Accounts screen
    When I open search modal
      And I search for "The Namesake" and save bookName as 'bookNameInfo'
      And I switch to "eBooks" catalog tab
      And Open EBOOK book with GET action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click VIEW_SAMPLE action button on book details screen
    Then 'book' book is present on epub reader screen
    When Open toc epub screen
    Then Toc epub screen is opened
    When Close toc epub screen
      And I return to previous screen from epub
    Then Book 'bookInfo' is opened on book details screen
    