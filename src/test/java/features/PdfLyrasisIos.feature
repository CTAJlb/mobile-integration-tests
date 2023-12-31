Feature: Read PDF in LYRARIS Reads on IOS

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

  @logout @returnBooks @tier1 @exclude_android
  Scenario: Navigate by Page Lyrasis
    When Open search modal
      And Search 'available' book of distributor 'Biblioboard' and bookType 'EBOOK' from "LYRASIS Reads" and save as 'bookNameInfo'
      And Switch to 'eBooks' catalog tab
      And Click GET action button on EBOOK book with 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
    Then EBOOK book with READ action button and 'bookInfo' bookInfo is present on catalog books screen
    When Open EBOOK book with READ action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click READ action button on book details screen
    Then Reader pdf screen is opened
    When Save page number as 'pageInfo' on pdf reader screen
      And Go to next page on reader pdf screen
    Then Page number increased by 1 from 'pageInfo' on pdf reader screen
    When Save page number as 'pageInfo2' on pdf reader screen
      And Go to previous page on reader pdf screen
    Then Page number decreased by 1 from 'pageInfo2' on pdf reader screen

  @logout @returnBooks @tier1 @exclude_android
  Scenario: Open book to last page read Lyrasis
    When Open search modal
      And Search 'available' book of distributor 'Biblioboard' and bookType 'EBOOK' from "LYRASIS Reads" and save as 'bookNameInfo'
      And Switch to 'eBooks' catalog tab
      And Click GET action button on EBOOK book with 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
    Then EBOOK book with READ action button and 'bookInfo' bookInfo is present on catalog books screen
    When Open EBOOK book with READ action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click READ action button on book details screen
    Then Reader pdf screen is opened
    When Swipe pdf page forward from 4 to 6 times on reader pdf screen
      And Save page number as 'pageNumber' on pdf reader screen
      And Return to previous screen for epub and pdf
      And Click READ action button on book details screen
    Then Reader pdf screen is opened
      And Page number is equal to 'pageNumber' on pdf reader screen
    And Restart app
      And Open Books
    Then EBOOK book with READ action button and 'bookInfo' bookInfo is present on books screen
    When Open EBOOK book with READ action button and 'bookInfo' bookInfo on books screen
      And Click READ action button on book details screen
    Then Reader pdf screen is opened
      And Page number is equal to 'pageNumber' on pdf reader screen

  @logout @returnBooks @tier1 @exclude_android
  Scenario: Navigate by Thumbnails Lyrasis
    When Open search modal
      And Search 'available' book of distributor 'Biblioboard' and bookType 'EBOOK' from "LYRASIS Reads" and save as 'bookNameInfo'
      And Switch to 'eBooks' catalog tab
      And Click GET action button on EBOOK book with 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
    Then EBOOK book with READ action button and 'bookInfo' bookInfo is present on catalog books screen
    When Open EBOOK book with READ action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click READ action button on book details screen
    Then Reader pdf screen is opened
    When Open table of contents on pdf reader screen
    Then There are content list with thumbnails and chapter content on pdf toc screen
    When Open random thumbnail and save the number as 'pageInfo' on pdf toc screen
    Then Page number is equal to 'pageInfo' on pdf reader screen

  @logout @returnBooks @tier1 @exclude_android
  Scenario: Search Pdf Functionality Lyrasis
    When Open search modal
      And Search 'available' book of distributor 'Biblioboard' and bookType 'EBOOK' from "LYRASIS Reads" and save as 'bookNameInfo'
      And Switch to 'eBooks' catalog tab
      And Click GET action button on EBOOK book with 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
    Then EBOOK book with READ action button and 'bookInfo' bookInfo is present on catalog books screen
    When Open EBOOK book with READ action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click READ action button on book details screen
    Then Reader pdf screen is opened
    When Open search pdf screen
    Then Search pdf screen is opened
    When Close pdf search screen
    Then Reader pdf screen is opened
    When Open search pdf screen
    Then Search pdf screen is opened
    When Enter 'try' text on search pdf screen
      And Delete text in search line on search pdf screen
    Then Search field is empty on search pdf screen
    When Enter 'try' text on search pdf screen
    Then Found lines should contain 'try' in themselves on search pdf screen
    When Close pdf search screen
#      And I open search pdf screen
#    Then Found lines should contain 'try' in themselves on search pdf screen

  @logout @returnBooks @tier1 @exclude_android
  Scenario: Navigate by Pdf Search Results Lyrasis
    When Open search modal
      And Search 'available' book of distributor 'Biblioboard' and bookType 'EBOOK' from "LYRASIS Reads" and save as 'bookNameInfo'
      And Switch to 'eBooks' catalog tab
      And Click GET action button on EBOOK book with 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
    Then EBOOK book with READ action button and 'bookInfo' bookInfo is present on catalog books screen
    When Open EBOOK book with READ action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click READ action button on book details screen
    Then Reader pdf screen is opened
    When Open search pdf screen
      And Enter 'try' text on search pdf screen
      And Open random found text and save page number as 'pageNumber' on search pdf screen
    Then Page number is equal to 'pageNumber' on pdf reader screen

  @logout @returnBooks @tier1 @exclude_android
  Scenario: Navigate by Page slider Lyrasis
    When Open search modal
      And Search 'available' book of distributor 'Biblioboard' and bookType 'EBOOK' from "LYRASIS Reads" and save as 'bookNameInfo'
      And Switch to 'eBooks' catalog tab
      And Click GET action button on EBOOK book with 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
    Then EBOOK book with READ action button and 'bookInfo' bookInfo is present on catalog books screen
    When Open EBOOK book with READ action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click READ action button on book details screen
    Then Reader pdf screen is opened
    When Save page number as 'pageNumber' on pdf reader screen
      And Slide page slider RIGHT on reader pdf screen
    Then The 'pageNumber' saved page number is less than the current page number on the reader pdf screen
    When Save page number as 'pageNumber' on pdf reader screen
      And Slide page slider LEFT on reader pdf screen
    Then The 'pageNumber' saved page number is greater than the current page number on the reader pdf screen

  @logout @returnBooks @tier1 @exclude_android
  Scenario: Bookmarks Functionality Lyrasis
    When Open search modal
      And Search 'available' book of distributor 'Biblioboard' and bookType 'EBOOK' from "LYRASIS Reads" and save as 'bookNameInfo'
      And Switch to 'eBooks' catalog tab
      And Click GET action button on EBOOK book with 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
    Then EBOOK book with READ action button and 'bookInfo' bookInfo is present on catalog books screen
    When Open EBOOK book with READ action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click READ action button on book details screen
    Then Reader pdf screen is opened
    When Open bookmarks pdf screen
    Then Bookmarks pdf screen is opened
      And Amount of bookmarks is 0 on bookmarks pdf screen
    When Close toc bookmarks pdf screen
      And Go to next page on reader pdf screen
      And Add bookmark on reader pdf screen
#    Then Bookmark is displayed on reader pdf screen
      And Save page number as 'pageNumberInfo' on pdf reader screen
      And Go to next page on reader pdf screen
      And Add bookmark on reader pdf screen
      And Save page number as 'pageNumberInfo2' on pdf reader screen
      And Go to next page on reader pdf screen
      And Add bookmark on reader pdf screen
#      And Delete bookmark on reader pdf screen
#    Then Bookmark is not displayed on reader pdf screen
      And Open bookmarks pdf screen
    Then Amount of bookmarks is 2 on bookmarks pdf screen
    When Open the 0 bookmark on bookmarks pdf screen
    Then Page number is equal to 'pageNumberInfo' on pdf reader screen

  @logout @returnBooks @tier1 @exclude_android
  Scenario: Check table of contents Lyrasis
    When Open search modal
      And Search 'available' book of distributor 'Biblioboard' and bookType 'EBOOK' from "LYRASIS Reads" and save as 'bookNameInfo'
      And Switch to 'eBooks' catalog tab
      And Click GET action button on EBOOK book with 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
    Then EBOOK book with READ action button and 'bookInfo' bookInfo is present on catalog books screen
    When Open EBOOK book with READ action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click READ action button on book details screen
    Then Reader pdf screen is opened
    When Open table of contents on pdf reader screen
      And Open text chapter content on pdf toc screen
    Then Text chapter content is opened on pdf toc screen
    When Open content with thumbnails on pdf toc screen
    Then Thumbnails of the book pages are displayed

  @logout @returnBooks @tier1 @exclude_android
  Scenario: Navigate by Chapters Lyrasis
    When Open search modal
    And Search 'available' book of distributor 'Biblioboard' and bookType 'EBOOK' from "LYRASIS Reads" and save as 'bookNameInfo'
      And Switch to 'eBooks' catalog tab
      And Click GET action button on EBOOK book with 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
    Then EBOOK book with READ action button and 'bookInfo' bookInfo is present on catalog books screen
    When Open EBOOK book with READ action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click READ action button on book details screen
    Then Reader pdf screen is opened
    When Open table of contents on pdf reader screen
      And Open text chapter content on pdf toc screen
      And Open random chapter and save the number as 'pageNumberInfo' on pdf toc screen
    Then Page number is equal to 'pageNumberInfo' on pdf reader screen

  @logout @returnBooks @tier1 @exclude_android
  Scenario Outline: Check invalid data in searching Lyrasis
    When Open search modal
    And Search 'available' book of distributor 'Biblioboard' and bookType 'EBOOK' from "LYRASIS Reads" and save as 'bookNameInfo'
      And Switch to 'eBooks' catalog tab
      And Click GET action button on EBOOK book with 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
    Then EBOOK book with READ action button and 'bookInfo' bookInfo is present on catalog books screen
    When Open EBOOK book with READ action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click READ action button on book details screen
    Then Reader pdf screen is opened
    When Open search pdf screen
    Then Search pdf screen is opened
    When Enter word <data> and save as 'data' on search pdf screen
    Then Search result is empty on search pdf screen

    Scenarios:
      | data                                  |
      | книга                                 |
      | 4561                                  |
      | <font color=red>Red text</font>       |
      | <script>alert(‘hello world’)</script> |
      | @!&                                   |

  @logout @returnBooks @tier1 @exclude_android
  Scenario Outline: Check valid data in searching Lyrasis
    When Open search modal
      And Search 'available' book of distributor 'Biblioboard' and bookType 'EBOOK' from "LYRASIS Reads" and save as 'bookNameInfo'
      And Switch to 'eBooks' catalog tab
      And Click GET action button on EBOOK book with 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
    Then EBOOK book with READ action button and 'bookInfo' bookInfo is present on catalog books screen
    When Open EBOOK book with READ action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click READ action button on book details screen
    Then Reader pdf screen is opened
    When Open search pdf screen
    Then Search pdf screen is opened
    When Enter word <data> and save as 'data' on search pdf screen
    Then Search result is shown on search pdf screen

    Scenarios:
    | data |
    | cat  |
    | CAT  |
    | CaT  |