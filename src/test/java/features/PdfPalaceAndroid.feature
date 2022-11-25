Feature: Read PDF in Palace Bookshelf on Android

  Background:
    When I add "Palace Bookshelf" account from welcomeScreen
    And I open Catalog
    And I open search modal
    And I search for 'Deep into Pharo' and save bookName as 'bookNameInfo'

  @tier2 @exclude_ios
  Scenario: Check of book title and back button Palace
    When Click GET action button on EBOOK book with 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
    Then EBOOK book with READ action button and 'bookInfo' bookInfo is present on catalog books screen
    When Open EBOOK book with READ action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click READ action button on book details screen
    Then Reader pdf screen is opened
      And The book name is 'bookNameInfo' on pdf reader screen
    When I close pdf reader by back button
    Then Book "bookInfo" is opened on book details screen

  @tier2 @exclude_ios
  Scenario: Check table of contents Palace
    When Click GET action button on EBOOK book with 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
    Then EBOOK book with READ action button and 'bookInfo' bookInfo is present on catalog books screen
    When Open EBOOK book with READ action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click READ action button on book details screen
    Then Reader pdf screen is opened
    When I open table of contents on pdf reader screen
    And There are content list with thumbnails and chapter content on pdf toc screen
    When I return to pdf reader screen from pdf toc screen
    Then PDF toc screen is closed
    When I open table of contents on pdf reader screen
      And I open table of contents on pdf reader screen
    Then PDF toc screen is closed
    When I open table of contents on pdf reader screen
      And I close pdf toc screen by back button
    Then PDF toc screen is closed

  @tier2 @exclude_ios
  Scenario: TOC: Contents with thumbnails: Check of Contents list and navigation Palace
   When Click GET action button on EBOOK book with 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
    Then EBOOK book with READ action button and 'bookInfo' bookInfo is present on catalog books screen
    When Open EBOOK book with READ action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click READ action button on book details screen
    Then Reader pdf screen is opened
    When I open table of contents on pdf reader screen
      And I open content with thumbnails on pdf toc screen
    Then Thumbnails of the book pages are displayed
    When I open random thumbnail and save the number as 'pageInfo' on pdf toc screen
      And I return to pdf reader screen from pdf toc screen
    Then Page number is equal to 'pageInfo' on pdf reader screen

  @tier2 @exclude_ios
  Scenario: TOC: Contents with text: Check of list of chapters and navigation Palace
    When Click GET action button on EBOOK book with 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
    Then EBOOK book with READ action button and 'bookInfo' bookInfo is present on catalog books screen
    When Open EBOOK book with READ action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click READ action button on book details screen
    Then Reader pdf screen is opened
    When I open table of contents on pdf reader screen
      And I open text chapter content on pdf toc screen
    Then Text chapter content is opened on pdf toc screen
    When I open random chapter and save the number as 'pageInfo' on pdf toc screen
      And I return to pdf reader screen from pdf toc screen
    Then Page number is equal to 'pageInfo' on pdf reader screen

  @tier2 @exclude_ios
  Scenario: Check of settings screen and page navigation Palace
    When Click GET action button on EBOOK book with 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
    Then EBOOK book with READ action button and 'bookInfo' bookInfo is present on catalog books screen
    When Open EBOOK book with READ action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click READ action button on book details screen
    Then Reader pdf screen is opened
    When I save the number of the last page as 'lastPageInfo' on pdf reader screen
      And I open pdf settings screen on pdf reader screen
    Then PDF settings screen is opened
      And Vertical scrolling is chosen by default on settings screen
    When I tap Go to last page button on pdf settings screen
    Then Page number is equal to 'lastPageInfo' on pdf reader screen
    When I open pdf settings screen on pdf reader screen
    Then PDF settings screen is opened
    When I tap Go to first page button on pdf settings screen
    Then The first page is opened on pdf reader screen

  @tier2 @exclude_ios
  Scenario: Settings: Check of Vertical scrolling Palace
    When Click GET action button on EBOOK book with 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
    Then EBOOK book with READ action button and 'bookInfo' bookInfo is present on catalog books screen
    When Open EBOOK book with READ action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click READ action button on book details screen
    Then Reader pdf screen is opened
    When I open pdf settings screen on pdf reader screen
      And I tap Vertical scrolling on pdf settings screen
      And I open pdf settings screen on pdf reader screen
    Then Vertical scrolling is chosen on settings screen
      And Spreads options are available on settings screen
    When I open pdf settings screen on pdf reader screen
      And I save page number as 'pageInfo' on pdf reader screen
      And I scroll page down on pdf reader screen
    Then Page number is not equal to 'pageInfo' on pdf reader screen
    When I save page number as 'pageInfo2' on pdf reader screen
      And I scroll page up on pdf reader screen
    Then Page number is not equal to 'pageInfo2' on pdf reader screen

  @tier2 @exclude_ios
  Scenario: Settings: Check of Horizontal scrolling Palace
    When Click GET action button on EBOOK book with 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
    Then EBOOK book with READ action button and 'bookInfo' bookInfo is present on catalog books screen
    When Open EBOOK book with READ action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click READ action button on book details screen
    Then Reader pdf screen is opened
    When I open pdf settings screen on pdf reader screen
      And I tap Horizontal scrolling on pdf settings screen
      And I open pdf settings screen on pdf reader screen
    Then Horizontal scrolling is chosen on settings screen
      And Spreads options are not available on settings screen
    When I open pdf settings screen on pdf reader screen
      And I save page number as 'pageInfo' on pdf reader screen
      And I go to next page on reader pdf screen
    Then Page number is not equal to 'pageInfo' on pdf reader screen
    When I save page number as 'pageInfo2' on pdf reader screen
      And I go to previous page on reader pdf screen
    Then Page number is not equal to 'pageInfo2' on pdf reader screen

  @tier2 @exclude_ios
  Scenario: Settings: Check of Wrapped scrolling Palace
    When Click GET action button on EBOOK book with 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
    Then EBOOK book with READ action button and 'bookInfo' bookInfo is present on catalog books screen
    When Open EBOOK book with READ action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click READ action button on book details screen
    Then Reader pdf screen is opened
    When I open pdf settings screen on pdf reader screen
      And I tap Wrapped scrolling on pdf settings screen
      And I open pdf settings screen on pdf reader screen
    Then Wrapped scrolling is chosen on settings screen
      And Spreads options are available on settings screen
    When I open pdf settings screen on pdf reader screen
    And I save page number as 'pageInfo' on pdf reader screen
    And I go to next page on reader pdf screen
    Then Page number is not equal to 'pageInfo' on pdf reader screen
    When I save page number as 'pageInfo2' on pdf reader screen
    And I go to previous page on reader pdf screen
    Then Page number is not equal to 'pageInfo2' on pdf reader screen

  @tier2 @exclude_ios
  Scenario: Open book to last page read Palace
    When Click GET action button on EBOOK book with 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
    Then EBOOK book with READ action button and 'bookInfo' bookInfo is present on catalog books screen
    When Open EBOOK book with READ action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click READ action button on book details screen
    Then Reader pdf screen is opened
    When I swipe pdf page down from 10 to 20 times on reader pdf screen
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