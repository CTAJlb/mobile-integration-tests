Feature: Main actions private access books

  @logout @returnBooks @tier2
  Scenario: Hold from Subcategory List View and Remove a Reserved Book from Holds
    When I add "LYRASIS Reads" account from welcomeScreen
      And I enter credentials for 'LYRASIS Reads' account
    Then Login is performed successfully
    When I open Catalog
      And I open search modal
      And I search 'unavailable' book of distributor 'Bibliotheca' and bookType 'EBOOK' and save as 'bookNameInfo'
      And I switch to 'eBooks' catalog tab
      And I RESERVE book from Subcategory List View with title 'bookNameInfo' and save it as 'bookInfo'
    Then Book saved as 'bookInfo' should contain CANCEL_RESERVATION button on Subcategory List View
    And I close Book Details for IOSTab
    When I open Holds
    Then Book 'bookInfo' is present in Holds List
    When I click CANCEL_RESERVATION button on the 'bookInfo' book on holds screen
    Then Book 'bookInfo' is not present in Holds List

  @logout @returnBooks @tier2
  Scenario: Hold from Subcategory List View and Remove a Reserved Book from Subcategory List View
    When I add "LYRASIS Reads" account from welcomeScreen
      And I enter credentials for 'LYRASIS Reads' account
    Then Login is performed successfully
    When I open Catalog
      And I open search modal
      And I search 'unavailable' book of distributor 'Bibliotheca' and bookType 'EBOOK' and save as 'bookNameInfo'
      And I switch to 'eBooks' catalog tab
      And I RESERVE book from Subcategory List View with title 'bookNameInfo' and save it as 'bookInfo'
    When I click CANCEL_RESERVATION button on the 'bookInfo' book on Subcategory List view
    Then Book saved as 'bookInfo' should contain RESERVE button on Subcategory List View

  @logout @returnBooks @tier2
  Scenario: Hold from Book Detail View and and Remove a Reserved Book from Holds
    When I add "LYRASIS Reads" account from welcomeScreen
      And I enter credentials for 'LYRASIS Reads' account
    Then Login is performed successfully
    When I open Catalog
      And I open search modal
      And I search 'unavailable' book of distributor 'Bibliotheca' and bookType 'EBOOK' and save as 'bookNameInfo'
      And I switch to 'eBooks' catalog tab
    Then Subcategory screen is present
    When Open 'EBOOK' book with RESERVE button from Subcategory List View with title 'bookNameInfo' and save it as 'bookInfo'
    Then Book 'bookInfo' is opened
    When I press on the book details view at the action button RESERVE
      And I close Book Details for IOSTab
      And I open Holds
    Then Book 'bookInfo' is present in Holds List
    When I click CANCEL_RESERVATION button on the 'bookInfo' book on holds screen
    Then Book 'bookInfo' is not present in Holds List

  @logout @returnBooks @tier2
  Scenario: Hold from Book Detail View and Remove a Reserved Book from Book Detail View
    When I add "LYRASIS Reads" account from welcomeScreen
      And I enter credentials for 'LYRASIS Reads' account
    Then Login is performed successfully
    When I open Catalog
      And I open search modal
      And I search 'unavailable' book of distributor 'Bibliotheca' and bookType 'EBOOK' and save as 'bookNameInfo'
      And I switch to 'eBooks' catalog tab
    Then Subcategory screen is present
    When Open 'EBOOK' book with RESERVE button from Subcategory List View with title 'bookNameInfo' and save it as 'bookInfo'
    Then Book 'bookInfo' is opened
    When I press on the book details view at the action button RESERVE
      And I press on the book details view at the action button CANCEL_RESERVATION
    Then I check that book contains RESERVE action button on book details view

  @logout @returnBooks @tier2
  Scenario: Check out from Book Detail View and Return from Books
    When I add "LYRASIS Reads" account from welcomeScreen
      And I enter credentials for 'LYRASIS Reads' account
    Then Login is performed successfully
    When I open Catalog
      And I open search modal
      And I search 'available' book of distributor 'Bibliotheca' and bookType 'EBOOK' and save as 'bookNameInfo'
      And I switch to 'eBooks' catalog tab
      And Open 'EBOOK' book with GET button from Subcategory List View with title 'bookNameInfo' and save it as 'bookInfo'
      And I press on the book details view at the action button GET
      And I close Book Details for IOSTab
      And I open Books
    Then EBOOK book with READ action button and 'bookInfo' bookInfo is present on books screen
    When Open EBOOK book with READ action button and 'bookInfo' bookInfo on books screen
      And I press on the book details view at the action button RETURN
      And I close Book Details for IOSTab
      And I open Books
    Then EBOOK book with READ action button and 'bookInfo' bookInfo is not present on books screen

  @logout @returnBooks @tier2
  Scenario: Check out from Subcategory List View and Return from Books
    When I add "LYRASIS Reads" account from welcomeScreen
      And I enter credentials for 'LYRASIS Reads' account
    Then Login is performed successfully
    When I open Catalog
      And I open search modal
      And I search 'available' book of distributor 'Bibliotheca' and bookType 'EBOOK' and save as 'bookNameInfo'
      And I switch to 'eBooks' catalog tab
      And I GET book from Subcategory List View with title 'bookNameInfo' and save it as 'bookInfo'
    Then Book saved as 'bookInfo' should contain READ button on Subcategory List View
    When I click READ button on the 'bookInfo' book on Subcategory List view
    Then Book 'bookInfo' is present on screen
    When I return to previous screen for epub and pdf
      And I open Books
    Then EBOOK book with READ action button and 'bookInfo' bookInfo is present on books screen
    When Open EBOOK book with READ action button and 'bookInfo' bookInfo on books screen
      And I press on the book details view at the action button RETURN
      And I open Books
    Then EBOOK book with READ action button and 'bookInfo' bookInfo is not present on books screen

  @logout @returnBooks @tier2 @exclude_android
  Scenario: Check out from Subcategory List View and Return from Subcategory List View
    When I add "LYRASIS Reads" account from welcomeScreen
      And I enter credentials for 'LYRASIS Reads' account
    Then Login is performed successfully
    When I open Catalog
      And I open search modal
      And I search 'available' book of distributor 'Bibliotheca' and bookType 'EBOOK' and save as 'bookNameInfo'
      And I switch to 'eBooks' catalog tab
      And I GET book from Subcategory List View with title 'bookNameInfo' and save it as 'bookInfo'
      And I RETURN book from Subcategory List View with title 'bookNameInfo' and save it as 'bookInfo'
    Then Book saved as 'bookInfo' should contain GET button on Subcategory List View

  @logout @returnBooks @tier2
  Scenario: Check out from Subcategory List View and Read from Books
    When I add "LYRASIS Reads" account from welcomeScreen
      And I enter credentials for 'LYRASIS Reads' account
    Then Login is performed successfully
    When I open Catalog
      And I open search modal
      And I search 'available' book of distributor 'Bibliotheca' and bookType 'EBOOK' and save as 'bookNameInfo'
      And I switch to 'eBooks' catalog tab
      And I GET book from Subcategory List View with title 'bookNameInfo' and save it as 'bookInfo'
    Then Book saved as 'bookInfo' should contain READ button on Subcategory List View
    When I open Books
      And Open EBOOK book with READ action button and 'bookInfo' bookInfo on books screen
      And I press on the book details view at the action button READ
    Then Book 'bookInfo' is present on screen

  @logout @returnBooks @tier3 @exclude_android
  Scenario: Hold from Book Detail View and Cancel remove from holds tab(IOS)
    When I add "LYRASIS Reads" account from welcomeScreen
      And I enter credentials for 'LYRASIS Reads' account
    Then Login is performed successfully
    When I open Catalog
      And I open search modal
      And I search 'unavailable' book of distributor 'Bibliotheca' and bookType 'EBOOK' and save as 'bookNameInfo'
      And I switch to 'eBooks' catalog tab
    Then Subcategory screen is present
    When Open 'EBOOK' book with RESERVE button from Subcategory List View with title 'bookNameInfo' and save it as 'bookInfo'
    Then Book 'bookInfo' is opened
    When I press on the book details view at the action button RESERVE
      And I close Book Details for IOSTab
      And I open Holds
    Then Book 'bookInfo' is present in Holds List
    When I click on the book 'bookInfo' button CANCEL_RESERVATION on the holds screen and don't click on the popup button
    Then I click CANCEL_POPUP button on alert for ios
      And Book saved as 'bookInfo' should contain CANCEL_RESERVATION button at the hold screen
