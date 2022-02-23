Feature: Read EPUB

  Background:
    When I add "Palace Bookshelf" account from welcomeScreen
      And I open Catalog
      And I open search modal
      And I search for 'Flower Fables' and save bookName as 'bookNameInfo'

  @tier1
  Scenario: Navigate by Page
    When Click GET action button on EBOOK book with 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
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

  @tier1
  Scenario: Navigate by bookmarks
    When Click GET action button on EBOOK book with 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
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

  @tier1
  Scenario: Delete bookmarks
    When Click GET action button on EBOOK book with 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
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

  @tier1
  Scenario: Navigate by Table of Contents Menu
    When Click GET action button on EBOOK book with 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Open EBOOK book with READ action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click READ action button on book details screen
    Then Random chapter of epub can be opened from toc epub screen

  @tier1
  Scenario: Navigate View options
    When Click GET action button on EBOOK book with 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Open EBOOK book with READ action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click READ action button on book details screen
      And I return to previous screen for epub and pdf
      And Click READ action button on book details screen
    Then 'bookInfo' book is present on epub reader screen
    When Open toc epub screen
    Then Toc epub screen is opened
    When Close toc epub screen
      And Open font and background settings epub screen
    Then Font and background settings epub screen is opened

  @tier1
  Scenario: Change, View Font and Contrast Settings
    When Click GET action button on EBOOK book with 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Open EBOOK book with READ action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click READ action button on book details screen
      And I scroll page forward from 7 to 9 times
      And I save font size as 'fontSize'
      And I INCREASE_FONT of text
    Then Font size 'fontSize' is increased
    When I save font size as 'fontSize'
      And I DECREASE_FONT of text
    Then Font size 'fontSize' is decreased
    When I change font style to FONT_SERIF
    Then Book text displays in FONT_SERIF font
    When I change font style to FONT_SANS
    Then Book text displays in FONT_SANS font
    When I change font style to FONT_DYSLEXIC
    Then Book text displays in FONT_DYSLEXIC font
    When I change contrast to BLACK_TEXT_ON_WHITE
    Then The BLACK_TEXT_ON_WHITE background is correct
    When I change contrast to BLACK_TEXT_ON_SEPIA
    Then The BLACK_TEXT_ON_SEPIA background is correct
    When I change contrast to WHITE_TEXT_ON_BLACK
    Then The WHITE_TEXT_ON_BLACK background is correct
    When Wait for 3 seconds
      And I restart app
      And I open Books
      And Open EBOOK book with READ action button and 'bookInfo' bookInfo on books screen
      And Click READ action button on book details screen
    Then The WHITE_TEXT_ON_BLACK background is correct
      And Book text displays in FONT_DYSLEXIC font
      And Font size 'fontSize' is decreased

  @tier1
  Scenario: Open book to last page read
    When Click GET action button on EBOOK book with 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
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
