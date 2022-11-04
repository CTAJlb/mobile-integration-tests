Feature: Read PDF in Palace Bookshelf on IOS

  Background:
    When I add "Palace Bookshelf" account from welcomeScreen
      And I open Catalog
      And I open search modal
      And I search for 'Deep into Pharo' and save bookName as 'bookNameInfo'
      And Click GET action button on EBOOK book with 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
    Then EBOOK book with READ action button and 'bookInfo' bookInfo is present on catalog books screen
    When Open EBOOK book with READ action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click READ action button on book details screen
    Then Reader pdf screen is opened

  @tier2 @exclude_android
  Scenario: Navigate by Page
    Then I save page number as 'pageInfo' on pdf reader screen
    When I go to next page on reader pdf screen
    Then Page number increased by 1 from 'pageInfo' on pdf reader screen
    When I save page number as 'pageInfo2' on pdf reader screen
      And I go to previous page on reader pdf screen
    Then Page number decreased by 1 from 'pageInfo2' on pdf reader screen

  @tier2 @exclude_android
  Scenario: Open book to last page read
    When I swipe pdf page forward from 4 to 6 times on reader pdf screen
      And I save page number as 'pageNumber' on pdf reader screen
      And I return to previous screen for epub and pdf
      And Click READ action button on book details screen
    Then Reader pdf screen is opened
      And Page number is equal to 'pageNumber' on pdf reader screen
    When I restart app
      And I open Books
    Then EBOOK book with READ action button and 'bookInfo' bookInfo is present on books screen
    When Open EBOOK book with READ action button and 'bookInfo' bookInfo on books screen
      And Click READ action button on book details screen
    Then Reader pdf screen is opened
      And Page number is equal to 'pageNumber' on pdf reader screen

  @tier2 @exclude_android
  Scenario: Navigate by Thumbnails
    When I open table of contents on pdf reader screen
    Then There are content list with thumbnails and chapter content on pdf toc screen
    When I open random thumbnail and save the number as 'pageInfo' on pdf toc screen
    Then Page number is equal to 'pageInfo' on pdf reader screen

  @tier2 @exclude_android
  Scenario: Search Pdf Functionality
    When I open search pdf screen
    Then Search pdf screen is opened
    When I close pdf search screen
    Then Reader pdf screen is opened
    When I open search pdf screen
    Then Search pdf screen is opened
    When I enter 'try' text on search pdf screen
    And I delete text in search line on search pdf screen
    Then Search field is empty on search pdf screen
    When I enter 'try' text on search pdf screen
    Then Found lines should contain 'try' in themselves on search pdf screen
    When I close pdf search screen

  @tier2 @exclude_android
  Scenario: Navigate by Pdf Search Results
    When I open search pdf screen
    And I enter 'try' text on search pdf screen
    And I open random found text and save page number as 'pageNumber' on search pdf screen
    Then Page number is equal to 'pageNumber' on pdf reader screen

  @tier2 @exclude_android
  Scenario: Navigate by Page slider
    When I save page number as 'pageNumber' on pdf reader screen
    And Slide page slider RIGHT on reader pdf screen
    Then The 'pageNumber' saved page number is less than the current page number on the reader pdf screen
    When I save page number as 'pageNumber' on pdf reader screen
    And Slide page slider LEFT on reader pdf screen
    Then The 'pageNumber' saved page number is greater than the current page number on the reader pdf screen

  @tier2 @exclude_android
  Scenario: Navigate by pdf bookmarks
    When I open bookmarks pdf screen
    Then Bookmarks pdf screen is opened
      And Amount of bookmarks is 0 on bookmarks pdf screen
    When Close toc bookmarks pdf screen
      And I go to next page on reader pdf screen
      And Add bookmark on reader pdf screen
#    Then Bookmark is displayed on reader pdf screen
      And I save page number as 'pageNumberInfo' on pdf reader screen
      And I go to next page on reader pdf screen
      And Add bookmark on reader pdf screen
      And I save page number as 'pageNumberInfo2' on pdf reader screen
      And I go to next page on reader pdf screen
      And Add bookmark on reader pdf screen
#      And Delete bookmark on reader pdf screen
#    Then Bookmark is not displayed on reader pdf screen
      And I open bookmarks pdf screen
    Then Amount of bookmarks is 2 on bookmarks pdf screen
    When Open the 0 bookmark on bookmarks pdf screen
    Then Page number is equal to 'pageNumberInfo' on pdf reader screen

  @tier2 @exclude_android
  Scenario: Check table of contents in Lyrasis
    When I open table of contents on pdf reader screen
      And I open text chapter content on pdf toc screen
    Then Text chapter content is opened on pdf toc screen
    When I open content with thumbnails on pdf toc screen
    Then Thumbnails of the book pages are displayed

  @tier2 @exclude_android
  Scenario: Navigate by Chapters in Lyrasis
    When I open table of contents on pdf reader screen
      And I open text chapter content on pdf toc screen
      And I open random chapter and save the number as 'pageNumberInfo' on pdf toc screen
    Then Page number is equal to 'pageNumberInfo' on pdf reader screen