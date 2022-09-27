Feature: Read PDF in LYRASIS Reads on Android

  Background:
    Given I add "LYRASIS Reads" account from welcomeScreen
    Then Account 'LYRASIS Reads' is present on Accounts screen
    When I enter credentials for 'LYRASIS Reads' account
    Then Login is performed successfully
    When I open Catalog

  @logout @returnBooks @tier1 @exclude_ios
  Scenario: Check of book title and back button
    When I open search modal
      And I search 'available' book of distributor 'Biblioboard' and bookType 'EBOOK' from "LYRASIS Reads" and save as 'bookNameInfo'
      And Click GET action button on EBOOK book with 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
    Then EBOOK book with READ action button and 'bookInfo' bookInfo is present on catalog books screen
    When Open EBOOK book with READ action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click READ action button on book details screen
    Then Reader pdf screen is opened
      And The book name is 'bookNameInfo' on pdf reader screen
    When I close pdf reader by back button
    Then Book "bookNameInfo" is opened on book details screen

  @logout @returnBooks @tier1 @exclude_ios
    Scenario: Check table of contents
    When I open search modal
      And I search 'available' book of distributor 'Biblioboard' and bookType 'EBOOK' from "LYRASIS Reads" and save as 'bookNameInfo'
      And Click GET action button on EBOOK book with 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
    Then EBOOK book with READ action button and 'bookInfo' bookInfo is present on catalog books screen
    When Open EBOOK book with READ action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click READ action button on book details screen
    Then Reader pdf screen is opened
    When I open table of contents on pdf reader screen
    Then There are content list with thumbnails and chapter content on pdf toc screen
    When I return to pdf reader screen from pdf toc screen
    Then PDF toc screen is closed
    When I open table of contents on pdf reader screen
      And I open table of contents on pdf reader screen
    Then PDF toc screen is closed
    When I open table of contents on pdf reader screen
      And I close pdf toc screen by back button
    Then PDF toc screen is closed

  @logout @returnBooks @tier1 @exclude_ios
    Scenario: TOC: Contents with thumbnails: Check of Contents list and navigation
    When I open search modal
      And I search 'available' book of distributor 'Biblioboard' and bookType 'EBOOK' from "LYRASIS Reads" and save as 'bookNameInfo'
      And Click GET action button on EBOOK book with 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
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

  @logout @returnBooks @tier1 @exclude_ios
    Scenario: TOC: Contents with text: Check of list of chapters and navigation
    When I open search modal
      And I search 'available' book of distributor 'Biblioboard' and bookType 'EBOOK' from "LYRASIS Reads" and save as 'bookNameInfo'
      And Click GET action button on EBOOK book with 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
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

  @logout @returnBooks @tier1 @exclude_ios
    Scenario: Check of settings screen and page navigation
    When I open search modal
      And I search 'available' book of distributor 'Biblioboard' and bookType 'EBOOK' from "LYRASIS Reads" and save as 'bookNameInfo'
      And Click GET action button on EBOOK book with 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
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

  @logout @returnBooks @tier1 @exclude_ios
    Scenario: Settings: Check of Vertical scrolling
    When I open search modal
      And I search 'available' book of distributor 'Biblioboard' and bookType 'EBOOK' from "LYRASIS Reads" and save as 'bookNameInfo'
      And Click GET action button on EBOOK book with 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
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
#    When I save page number as 'pageInfo2' on pdf reader screen
#      And I scroll page up
#    Then Page number is not equal to 'pageInfo2' on pdf reader screen

  @logout @returnBooks @tier1 @exclude_ios
    Scenario: Settings: Check of Horizontal scrolling
    When I open search modal
      And I search 'available' book of distributor 'Biblioboard' and bookType 'EBOOK' from "LYRASIS Reads" and save as 'bookNameInfo'
      And Click GET action button on EBOOK book with 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
    Then EBOOK book with READ action button and 'bookInfo' bookInfo is present on catalog books screen
    When Open EBOOK book with READ action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click READ action button on book details screen
    Then Reader pdf screen is opened
    When I open pdf settings screen on pdf reader screen
      And I tap Horizontal scrolling on pdf settings screen
      And I open pdf settings screen on pdf reader screen
    Then Horizontal scrolling is chosen on settings screen
      And Spreads options are not available on settings screen
#    When I open pdf settings screen on pdf reader screen
#      And I save page number as 'pageInfo' on pdf reader screen
#      And I scroll to the next page
#    Then Page number is not equal to 'pageInfo'
#    When I save page number as 'pageInfo2' on pdf reader screen
#      And I scroll to the previous page
#    Then Page number is not equal to 'pageInfo2'

  @logout @returnBooks @tier1 @exclude_ios
  Scenario: Settings: Check of Wrapped scrolling
    When I open search modal
      And I search 'available' book of distributor 'Biblioboard' and bookType 'EBOOK' from "LYRASIS Reads" and save as 'bookNameInfo'
      And Click GET action button on EBOOK book with 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
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
#    And I save page number as 'pageInfo' on pdf reader screen
#    And I scroll to the next page
#    Then Page number is not equal to 'pageInfo'
#    When I save page number as 'pageInfo2' on pdf reader screen
#    And I scroll to the previous page
#    Then Page number is not equal to 'pageInfo2'


#  @ignore @logout @returnBooks @tier1 @exclude_ios
#  Scenario: Open book to last page read Lyrasis
#    When I open search modal
#      And I search 'available' book of distributor 'Biblioboard' and bookType 'EBOOK' from "LYRASIS Reads" and save as 'bookNameInfo'
#      And Click GET action button on EBOOK book with 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
#    Then EBOOK book with READ action button and 'bookInfo' bookInfo is present on catalog books screen
#    When Open EBOOK book with READ action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
#      And Click READ action button on book details screen
#      And I swipe pdf page forward from 7 to 10 times on reader pdf screen
#      And I save pdf page number as 'pageNumber' on reader pdf screen
#      And I return to previous screen for epub and pdf
#      And Click READ action button on book details screen
#    Then 'bookInfo' book is present on reader pdf screen
#      And The 'pageNumber' saved page number is equal to the current page number on the reader pdf screen
#    When I restart app
#      And I open Books
#    Then EBOOK book with READ action button and 'bookInfo' bookInfo is present on books screen
#    When Open EBOOK book with READ action button and 'bookInfo' bookInfo on books screen
#      And Click READ action button on book details screen
#    Then 'bookInfo' book is present on reader pdf screen
#      And The 'pageNumber' saved page number is equal to the current page number on the reader pdf screen