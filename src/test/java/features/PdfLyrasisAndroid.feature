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
    And I search for "If You Were a Set :" and save bookName as 'bookNameInfo'
#    And I search 'available' book of distributor 'Biblioboard' and bookType 'EBOOK' from "LYRASIS Reads" and save as 'bookNameInfo'
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
    Scenario: Check of settings screen
    When I open search modal
    And I search for "If You Were a Set :" and save bookName as 'bookNameInfo'
#      And I search 'available' book of distributor 'Biblioboard' and bookType 'EBOOK' from "LYRASIS Reads" and save as 'bookNameInfo'
    And Click GET action button on EBOOK book with 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
    Then EBOOK book with READ action button and 'bookInfo' bookInfo is present on catalog books screen
    When Open EBOOK book with READ action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
    And Click READ action button on book details screen
    Then Reader pdf screen is opened
    When I open table of contents on pdf reader screen









  @ignore @logout @returnBooks @tier1 @exclude_ios
  Scenario: Open pages Lyrasis
    When I open search modal
      And I search 'available' book of distributor 'Biblioboard' and bookType 'EBOOK' from "LYRASIS Reads" and save as 'bookNameInfo'
      And Click GET action button on EBOOK book with 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
    Then EBOOK book with READ action button and 'bookInfo' bookInfo is present on catalog books screen
    When Open EBOOK book with READ action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click READ action button on book details screen
    Then Pdf page number is 1 on reader pdf screen
    When I go to next page on reader pdf screen
    Then Pdf page number is 2 on reader pdf screen
    When I go to previous page on reader pdf screen
    Then Pdf page number is 1 on reader pdf screen

  @ignore @logout @returnBooks @tier1 @exclude_ios
  Scenario: Open book to last page read Lyrasis
    When I open search modal
      And I search 'available' book of distributor 'Biblioboard' and bookType 'EBOOK' from "LYRASIS Reads" and save as 'bookNameInfo'
      And Click GET action button on EBOOK book with 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
    Then EBOOK book with READ action button and 'bookInfo' bookInfo is present on catalog books screen
    When Open EBOOK book with READ action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click READ action button on book details screen
      And I swipe pdf page forward from 7 to 10 times on reader pdf screen
      And I save pdf page number as 'pageNumber' on reader pdf screen
      And I return to previous screen for epub and pdf
      And Click READ action button on book details screen
    Then 'bookInfo' book is present on reader pdf screen
      And The 'pageNumber' saved page number is equal to the current page number on the reader pdf screen
    When I restart app
      And I open Books
    Then EBOOK book with READ action button and 'bookInfo' bookInfo is present on books screen
    When Open EBOOK book with READ action button and 'bookInfo' bookInfo on books screen
      And Click READ action button on book details screen
    Then 'bookInfo' book is present on reader pdf screen
      And The 'pageNumber' saved page number is equal to the current page number on the reader pdf screen

  @ignore @logout @returnBooks @tier1 @exclude_ios
  Scenario: Navigate by Page slider Lyrasis
    When I open search modal
      And I search 'available' book of distributor 'Biblioboard' and bookType 'EBOOK' from "LYRASIS Reads" and save as 'bookNameInfo'
      And Click GET action button on EBOOK book with 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
    Then EBOOK book with READ action button and 'bookInfo' bookInfo is present on catalog books screen
    When Open EBOOK book with READ action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click READ action button on book details screen
      And I save pdf page number as 'pageNumber' on reader pdf screen
      And Slide page slider RIGHT on reader pdf screen
    Then The 'pageNumber' saved page number is less than the current page number on the reader pdf screen
    When I save pdf page number as 'pageNumber' on reader pdf screen
      And Slide page slider LEFT on reader pdf screen
    Then The 'pageNumber' saved page number is greater than the current page number on the reader pdf screen

  @ignore @logout @returnBooks @tier1 @exclude_ios
  Scenario: Navigate by Chapters Lirasis
    When I open search modal
      And I search for 'Mountains' and save bookName as 'bookNameInfo'
      And Click GET action button on EBOOK book with 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
    Then EBOOK book with READ action button and 'bookInfo' bookInfo is present on catalog books screen
    When Open EBOOK book with READ action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click READ action button on book details screen
      And I open table of contents
      And I open random chapter on list of contents pdf screen and save pdf page number as 'pageNumberInfo'
    Then Chapter with 'pageNumberInfo' is opened on pdf screen