Feature: Read PDF in LYRARIS Reads on IOS

  Background:
    Given I add "LYRASIS Reads" account from welcomeScreen
    Then Account 'LYRASIS Reads' is present on Accounts screen
    When I enter credentials for 'LYRASIS Reads' account
    Then Login is performed successfully
    When I open Catalog

  @logout @returnBooks @tier1 @exclude_android
  Scenario: Navigate by Page Lyrasis
    When I open search modal
      And I search 'available' book of distributor 'Biblioboard' and bookType 'EBOOK' and save as 'bookNameInfo'
      And I switch to 'eBooks' catalog tab
      And Click GET action button on EBOOK book with 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
    Then EBOOK book with READ action button and 'bookInfo' bookInfo is present on catalog books screen
    When Open EBOOK book with READ action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click READ action button on book details screen
    Then Pdf page number is 1 on reader pdf screen
    When I go to next page on reader pdf screen
    Then Pdf page number is 2 on reader pdf screen
    When I go to previous page on reader pdf screen
    Then Pdf page number is 1 on reader pdf screen

  @logout @returnBooks @tier1 @exclude_android
  Scenario: Open book to last page read Lyrasis
    When I open search modal
      And I search 'available' book of distributor 'Biblioboard' and bookType 'EBOOK' and save as 'bookNameInfo'
      And I switch to 'eBooks' catalog tab
      And Click GET action button on EBOOK book with 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
    Then EBOOK book with READ action button and 'bookInfo' bookInfo is present on catalog books screen
    When Open EBOOK book with READ action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click READ action button on book details screen
    When I swipe pdf page forward from 4 to 6 times on reader pdf screen
      And I save pdf page number as 'pageNumber' on reader pdf screen
      And Wait for 3 seconds
      And I return to previous screen for epub and pdf
      And Click READ action button on book details screen
    Then The 'pageNumber' saved page number is equal to the current page number on the reader pdf screen
    When Wait for 3 seconds
    And I restart app
      And I open Books
    Then EBOOK book with READ action button and 'bookInfo' bookInfo is present on books screen
    When Open EBOOK book with READ action button and 'bookInfo' bookInfo on books screen
      And Click READ action button on book details screen
    Then 'bookInfo' book is present on reader pdf screen
      And The 'pageNumber' saved page number is equal to the current page number on the reader pdf screen

  @logout @returnBooks @tier1 @exclude_android
  Scenario: Navigate by Gallery Lyrasis
    When I open search modal
      And I search 'available' book of distributor 'Biblioboard' and bookType 'EBOOK' and save as 'bookNameInfo'
      And I switch to 'eBooks' catalog tab
      And Click GET action button on EBOOK book with 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
    Then EBOOK book with READ action button and 'bookInfo' bookInfo is present on catalog books screen
    When Open EBOOK book with READ action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click READ action button on book details screen
      And I open gallery pdf screen
    Then Gallery pdf screen is opened
    When I open random pdf page on gallery pdf screen and save pdf page number as 'pageNumber'
    Then The 'pageNumber' saved page number is equal to the current page number on the reader pdf screen
      And 'bookInfo' book is present on reader pdf screen

  @logout @returnBooks @tier1 @exclude_android
  Scenario: Search Pdf Functionality Lyrasis
    When I open search modal
      And I search 'available' book of distributor 'Biblioboard' and bookType 'EBOOK' and save as 'bookNameInfo'
      And I switch to 'eBooks' catalog tab
      And Click GET action button on EBOOK book with 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
    Then EBOOK book with READ action button and 'bookInfo' bookInfo is present on catalog books screen
    When Open EBOOK book with READ action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click READ action button on book details screen
      And I open search pdf screen
    Then Search pdf screen is opened
    When I close pdf search screen
    Then 'bookInfo' book is present on reader pdf screen
    When I open search pdf screen
    Then Search pdf screen is opened
    When I enter 'try' text on search pdf screen
      And I delete text in search line on search pdf screen
    Then Search field is empty on search pdf screen
    When I search 'try' text on search pdf screen
    Then Found lines should contain 'try' in themselves on search pdf screen
    When I close pdf search screen
      And I open search pdf screen
    Then Found lines should contain 'try' in themselves on search pdf screen

  @logout @returnBooks @tier1 @exclude_android
  Scenario: Navigate by Pdf Search Results Lyrasis
    When I open search modal
      And I search 'available' book of distributor 'Biblioboard' and bookType 'EBOOK' and save as 'bookNameInfo'
      And I switch to 'eBooks' catalog tab
      And Click GET action button on EBOOK book with 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
    Then EBOOK book with READ action button and 'bookInfo' bookInfo is present on catalog books screen
    When Open EBOOK book with READ action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click READ action button on book details screen
      And I open search pdf screen
      And I search 'try' text on search pdf screen
      And I save page number as 'pageNumber' of the first found text on search pdf screen
      And I open the first found text on search pdf screen
    Then The 'pageNumber' saved page number is equal to the current page number on the reader pdf screen

  @logout @returnBooks @tier1 @exclude_android
  Scenario: Navigate by Page slider Lyrasis
    When I open search modal
      And I search 'available' book of distributor 'Biblioboard' and bookType 'EBOOK' and save as 'bookNameInfo'
      And I switch to 'eBooks' catalog tab
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

  @logout @returnBooks @tier1 @exclude_android
  Scenario: Bookmarks Functionality Lyrasis
    When I open search modal
      And I search 'available' book of distributor 'Biblioboard' and bookType 'EBOOK' and save as 'bookNameInfo'
      And I switch to 'eBooks' catalog tab
      And Click GET action button on EBOOK book with 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
    Then EBOOK book with READ action button and 'bookInfo' bookInfo is present on catalog books screen
    When Open EBOOK book with READ action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click READ action button on book details screen
      And I open bookmarks pdf screen
    Then Bookmarks pdf screen is opened
      And Amount of bookmarks is 0 on bookmarks pdf screen
    When Close tocBookmarksGallery pdf screen
      And I go to next page on reader pdf screen
      And Add bookmark on reader pdf screen
    Then Bookmark is displayed on reader pdf screen
    When I save pdf page number as 'pageNumberInfo' on reader pdf screen
      And I go to next page on reader pdf screen
      And Add bookmark on reader pdf screen
      And I save pdf page number as 'pageNumberInfo2' on reader pdf screen
      And I go to next page on reader pdf screen
      And Add bookmark on reader pdf screen
      And Delete bookmark on reader pdf screen
    Then Bookmark is not displayed on reader pdf screen
      And I open bookmarks pdf screen
    Then Amount of bookmarks is 2 on bookmarks pdf screen
    When Open the 0 bookmark on bookmarks pdf screen
    Then The 'pageNumberInfo' saved page number is equal to the current page number on the reader pdf screen

  @logout @returnBooks @tier1 @exclude_android
  Scenario: Check table of contents in Lyrasis
    When I open search modal
      And I search 'available' book of distributor 'Biblioboard' and bookType 'EBOOK' and save as 'bookNameInfo'
      And I switch to 'eBooks' catalog tab
      And Click GET action button on EBOOK book with 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
    Then EBOOK book with READ action button and 'bookInfo' bookInfo is present on catalog books screen
    When Open EBOOK book with READ action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click READ action button on book details screen
      And I open bookmarks pdf screen
    Then Bookmarks pdf screen is opened
    When I switch to List of contents on pdf screen
    Then TOC pdf screen is opened
    When I switch to Gallery on pdf screen
    Then Gallery pdf screen is opened

  @logout @returnBooks @tier1 @exclude_android
  Scenario: Navigate by Chapters in Lyrasis
    When I open search modal
    And I search 'available' book of distributor 'Biblioboard' and bookType 'EBOOK' and save as 'bookNameInfo'
      And I switch to 'eBooks' catalog tab
      And Click GET action button on EBOOK book with 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
    Then EBOOK book with READ action button and 'bookInfo' bookInfo is present on catalog books screen
    When Open EBOOK book with READ action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click READ action button on book details screen
      And I open bookmarks pdf screen
      And I switch to List of contents on pdf screen
      And I open random chapter on list of contents pdf screen and save pdf page number as 'pageNumberInfo'
    Then Chapter with 'pageNumberInfo' is opened on pdf screen

  @logout @returnBooks @tier1 @exclude_android
  Scenario Outline: Check invalid data in searching
    When I open search modal
    And I search for 'Mountains' and save bookName as 'bookNameInfo'
#    And I search 'available' book of distributor 'Biblioboard' and bookType 'EBOOK' and save as 'bookNameInfo'
      And I switch to 'eBooks' catalog tab
      And Click GET action button on EBOOK book with 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
    Then EBOOK book with READ action button and 'bookInfo' bookInfo is present on catalog books screen
    When Open EBOOK book with READ action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click READ action button on book details screen
      And I open search pdf screen
    Then Search pdf screen is opened
    When I enter word <data> and save as 'data' on search pdf screen
    Then Search result is empty on search pdf screen

    Scenarios:
      | data                                  |
      | книга                                 |
      | 4561                                  |
      | <font color=red>Red text</font>       |
      | <script>alert(‘hello world’)</script> |
      | @!&                                   |

  @logout @returnBooks @tier1 @exclude_android
  Scenario Outline: Check valid data in searching
    When I open search modal
      And I search 'available' book of distributor 'Biblioboard' and bookType 'EBOOK' and save as 'bookNameInfo'
      And I switch to 'eBooks' catalog tab
      And Click GET action button on EBOOK book with 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
    Then EBOOK book with READ action button and 'bookInfo' bookInfo is present on catalog books screen
    When Open EBOOK book with READ action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click READ action button on book details screen
      And I open search pdf screen
    Then Search pdf screen is opened
    When I enter word <data> and save as 'data' on search pdf screen
    Then Search result is shown on search pdf screen


    Scenarios:
    | data |
    | cat  |
    | CAT  |
    | CaT  |