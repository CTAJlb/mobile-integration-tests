Feature: Read PDF

  Background:
    When I add "Palace Bookshelf" account from welcomeScreen
    And I open Catalog
    And I open search modal
    And I search for 'Deep into Pharo' and save bookName as 'bookNameInfo'

  @tier1 @exclude_ios
  Scenario: Open document
    When Click GET action button on EBOOK book with 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
    Then EBOOK book with READ action button and 'bookInfo' bookInfo is present on catalog books screen
    When Open EBOOK book with READ action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click READ action button on book details screen
    Then 'bookInfo' book is present on reader pdf screen
      And Pdf page number is 1 on reader pdf screen

  @tier1 @exclude_ios
  Scenario: Navigate by page
    When Click GET action button on EBOOK book with 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
    Then EBOOK book with READ action button and 'bookInfo' bookInfo is present on catalog books screen
    When Open EBOOK book with READ action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click READ action button on book details screen
    Then 'bookInfo' book is present on reader pdf screen
      And Pdf page number is 1 on reader pdf screen
    When I go to next page on reader pdf screen
    Then Pdf page number is 2 on reader pdf screen
    When I go to previous page on reader pdf screen
    Then Pdf page number is 1 on reader pdf screen

  @tier1 @exclude_ios
  Scenario: Navigate by Table of Contents Menu
    When Click GET action button on EBOOK book with 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
    Then EBOOK book with READ action button and 'bookInfo' bookInfo is present on catalog books screen
    When Open EBOOK book with READ action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click READ action button on book details screen
    Then 'bookInfo' book is present on reader pdf screen
      And Random chapter of pdf book can be opened from toc pdf screen

  @tier1 @exclude_ios
  Scenario: Open book to last page read
    When Click GET action button on EBOOK book with 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
    Then EBOOK book with READ action button and 'bookInfo' bookInfo is present on catalog books screen
    When Open EBOOK book with READ action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click READ action button on book details screen
    Then 'bookInfo' book is present on reader pdf screen
    When I swipe pdf page forward from 10 to 20 times on reader pdf screen
      And I save pdf page number as 'pageNumber' on reader pdf screen
      And I return to previous screen for epub and pdf
      And Click READ action button on book details screen
    Then 'bookInfo' book is present on reader pdf screen
      And 'pageNumber' pdf saved page number should be equal to current page number on reader pdf screen
    When I restart app
      And I open Books
    Then EBOOK book with READ action button and 'bookInfo' bookInfo is present on books screen
    When Open EBOOK book with READ action button and 'bookInfo' bookInfo on books screen
      And Click READ action button on book details screen
    Then 'bookInfo' book is present on reader pdf screen
      And 'pageNumber' pdf saved page number should be equal to current page number on reader pdf screen

  @tier1 @exclude_ios
  Scenario: Close book
    When Click GET action button on EBOOK book with 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
    Then EBOOK book with READ action button and 'bookInfo' bookInfo is present on catalog books screen
    When Open EBOOK book with READ action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click READ action button on book details screen
    Then 'bookInfo' book is present on reader pdf screen
    When I return to previous screen for epub and pdf
    Then I check that book contains READ action button on book details screen

  @tier1 @exclude_ios
  Scenario: Navigate by Page slider
    When Click GET action button on EBOOK book with 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
    Then EBOOK book with READ action button and 'bookInfo' bookInfo is present on catalog books screen
    When Open EBOOK book with READ action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click READ action button on book details screen
    Then 'bookInfo' book is present on reader pdf screen
    When I save pdf page number as 'pageNumber' on reader pdf screen
      And Slide page slider RIGHT on reader pdf screen
    Then The 'pageNumber' saved page number is less than the current page number on the reader pdf screen
    When I save pdf page number as 'pageNumber' on reader pdf screen
      And Slide page slider LEFT on reader pdf screen
    Then The 'pageNumber' saved page number is greater than the current page number on the reader pdf screen
