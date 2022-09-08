Feature: Read EPUB from Overdrive in A1QA

  Background:
    Given I turn on test mode in "Palace Bookshelf" library
    When I open Catalog
      And I add "A1QA Test Library" account by the logo
    Then Account "A1QA Test Library" is present on Accounts screen
    When I enter credentials for "A1QA Test Library" account
    Then Login is performed successfully
    When I open Catalog
      And I open search modal

  @logout @returnBooks @tier1
  Scenario: Epub: Font settings: Check of increasing and reducing the text size
    When I search for "The Woman in White" and save bookName as 'bookNameInfo'
      And Click GET action button on EBOOK book with 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Open EBOOK book with READ action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click READ action button on book details screen
      And I scroll page forward from 7 to 9 times
      And I save font size as 'fontSize'
      And I open font settings
      And I INCREASE_FONT of text
    Then Font size 'fontSize' is increased
    When I restart app
      And I open Books
      And Open EBOOK book with READ action button and 'bookInfo' bookInfo on books screen
      And Click READ action button on book details screen
    Then Font size 'fontSize' is increased
    When I return to previous screen from epub
      And Click READ action button on book details screen
    Then Font size 'fontSize' is increased
    When I save font size as 'fontSize'
      And I open font settings
      And I DECREASE_FONT of text
    Then Font size 'fontSize' is decreased
    When I restart app
      And I open Books
      And Open EBOOK book with READ action button and 'bookInfo' bookInfo on books screen
      And Click READ action button on book details screen
    Then Font size 'fontSize' is decreased
    When I return to previous screen from epub
      And Click READ action button on book details screen
    Then Font size 'fontSize' is decreased

  @logout @returnBooks @tier1
  Scenario: Epub: Font settings: Check of font style
    When I search for "A Game of Thrones" and save bookName as 'bookNameInfo'
      And Click GET action button on EBOOK book with 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Open EBOOK book with READ action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click READ action button on book details screen
      And I scroll page forward from 7 to 9 times
      And I open font settings
      And I change font style to FONT_SERIF
    Then Book text displays in FONT_SERIF font
    When I restart app
      And I open Books
      And Open EBOOK book with READ action button and 'bookInfo' bookInfo on books screen
      And Click READ action button on book details screen
    Then Book text displays in FONT_SERIF font
    When I return to previous screen from epub
      And Click READ action button on book details screen
    Then Book text displays in FONT_SERIF font
    When I open font settings
      And I change font style to FONT_SANS
    Then Book text displays in FONT_SANS font
    When I restart app
      And I open Books
      And Open EBOOK book with READ action button and 'bookInfo' bookInfo on books screen
      And Click READ action button on book details screen
    Then Book text displays in FONT_SANS font
    When I return to previous screen from epub
      And Click READ action button on book details screen
    Then Book text displays in FONT_SANS font
    When I open font settings
      And I change font style to FONT_DYSLEXIC
    Then Book text displays in FONT_DYSLEXIC font
    When I restart app
      And I open Books
      And Open EBOOK book with READ action button and 'bookInfo' bookInfo on books screen
      And Click READ action button on book details screen
    Then Book text displays in FONT_DYSLEXIC font
    When I return to previous screen from epub
      And Click READ action button on book details screen
    Then Book text displays in FONT_DYSLEXIC font

  @logout @returnBooks @tier1
  Scenario: Epub: Font settings: Check of text theme
    When I search for "The Adventures of Sherlock Holmes" and save bookName as 'bookNameInfo'
      And Click GET action button on EBOOK book with 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Open EBOOK book with READ action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click READ action button on book details screen
      And I scroll page forward from 7 to 9 times
      And I open font settings
    When I change contrast to BLACK_TEXT_ON_WHITE
    Then The BLACK_TEXT_ON_WHITE background is correct
    When I restart app
      And I open Books
      And Open EBOOK book with READ action button and 'bookInfo' bookInfo on books screen
      And Click READ action button on book details screen
    Then The BLACK_TEXT_ON_WHITE background is correct
    When I return to previous screen from epub
      And Click READ action button on book details screen
    Then The BLACK_TEXT_ON_WHITE background is correct
    When I open font settings
      And I change contrast to BLACK_TEXT_ON_SEPIA
    Then The BLACK_TEXT_ON_SEPIA background is correct
    When I restart app
      And I open Books
      And Open EBOOK book with READ action button and 'bookInfo' bookInfo on books screen
      And Click READ action button on book details screen
    Then The BLACK_TEXT_ON_SEPIA background is correct
    When I return to previous screen from epub
      And Click READ action button on book details screen
    Then The BLACK_TEXT_ON_SEPIA background is correct
    When I open font settings
      And I change contrast to WHITE_TEXT_ON_BLACK
    Then The WHITE_TEXT_ON_BLACK background is correct
    When I restart app
      And I open Books
      And Open EBOOK book with READ action button and 'bookInfo' bookInfo on books screen
      And Click READ action button on book details screen
    Then The WHITE_TEXT_ON_BLACK background is correct
    When I return to previous screen from epub
      And Click READ action button on book details screen
    Then The WHITE_TEXT_ON_BLACK background is correct

  @logout @returnBooks @tier1
  Scenario: Epub: Open book to last page read
    When I search for "Romeo and Juliet" and save bookName as 'bookNameInfo'
      And Click GET action button on EBOOK book with 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Open EBOOK book with READ action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click READ action button on book details screen
      And I scroll page forward from 7 to 10 times
      And I save pageNumber as 'pageNumberKey' and chapterName as 'chapterNameKey' on epub reader screen
      And Wait for 3 seconds
      And I return to previous screen for epub and pdf
      And Click READ action button on book details screen
    Then 'bookInfo' book is present on epub reader screen
      And PageNumber 'pageNumberKey' is correct
      And I scroll page forward from 3 to 4 times
      And I save pageNumber as 'pageNumberKey' and chapterName as 'chapterNameKey' on epub reader screen
      And Wait for 3 seconds
      And I restart app
      And I open Books
    Then EBOOK book with READ action button and 'bookInfo' bookInfo is present on books screen
    When Open EBOOK book with READ action button and 'bookInfo' bookInfo on books screen
      And Click READ action button on book details screen
    Then 'bookInfo' book is present on epub reader screen
      And PageNumber 'pageNumberKey' is correct

  @logout @returnBooks @tier1
  Scenario: Epub: Navigate by Page
    When I search for "The Book Thief" and save bookName as 'bookNameInfo'
      And Click GET action button on EBOOK book with 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Open EBOOK book with READ action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click READ action button on book details screen
    Then 'bookInfo' book is present on epub reader screen
    When I click on right book corner on epub reader screen
      And I save pageNumber as 'pageNumberKey' and chapterName as 'chapterNameKey' on epub reader screen
      And I click on right book corner on epub reader screen
    Then Next page is opened and old page has 'pageNumberKey' pageNumber and 'chapterNameKey' chapterName on epub reader screen
    When I save pageNumber as 'pageNumberKey' and chapterName as 'chapterNameKey' on epub reader screen
      And I click on left book corner on epub reader screen
    Then Previous page is opened and old page has 'pageNumberKey' pageNumber and 'chapterNameKey' chapterName on epub reader screen

  @logout @returnBooks @tier1
  Scenario: Epub: Navigate by bookmarks
    When I search for "Moby Dick" and save bookName as 'bookNameInfo'
      And Click GET action button on EBOOK book with 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Open EBOOK book with READ action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click READ action button on book details screen
      And I click on right book corner on epub reader screen
      And Add bookmark on reader epub screen
    Then Bookmark is displayed on reader epub screen
    When I save pageNumber as 'pageNumberKey' and chapterName as 'chapterNameKey' on epub reader screen
      And Save device time and date as 'deviceTimeDateKey'
      And I scroll page forward from 7 to 9 times
      And Add bookmark on reader epub screen
      And I save pageNumber as 'pageNumberKey2' and chapterName as 'chapterNameKey2' on epub reader screen
      And Save device time and date as 'deviceTimeDateKey2'
      And I click on right book corner on epub reader screen
      And Open bookmarks epub screen
    Then Bookmark with 'chapterNameKey' and 'deviceTimeDateKey' is displayed on bookmarks epub screen
      And Bookmark with 'chapterNameKey2' and 'deviceTimeDateKey2' is displayed on bookmarks epub screen
    When Open random bookmark and save chapter name as 'chapterNameKey3' on bookmarks epub screen
    Then 'chapterNameKey3' chapter name is displayed on reader epub screen

  @logout @returnBooks @tier1
  Scenario: Epub: Delete bookmarks
    When I search for "Little Women" and save bookName as 'bookNameInfo'
      And Click GET action button on EBOOK book with 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Open EBOOK book with READ action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click READ action button on book details screen
      And I click on right book corner on epub reader screen
      And Add bookmark on reader epub screen
      And Delete bookmark on reader epub screen
    Then Bookmark is not displayed on reader epub screen
    When I scroll page forward from 7 to 9 times
      And Add bookmark on reader epub screen
      And I save pageNumber as 'pageNumberKey' and chapterName as 'chapterNameKey' on epub reader screen
      And Save device time and date as 'deviceTimeDateKey'
      And I click on right book corner on epub reader screen
      And Open bookmarks epub screen
      And Delete bookmark on bookmarks epub screen
    Then Bookmark with 'chapterNameKey' and 'deviceTimeDateKey' is not displayed on bookmarks epub screen
    When Return to reader epub screen from toc bookmarks epub screen
      And I click on left book corner on epub reader screen
    Then 'chapterNameKey' chapter name is displayed on reader epub screen
      And Bookmark is not displayed on reader epub screen