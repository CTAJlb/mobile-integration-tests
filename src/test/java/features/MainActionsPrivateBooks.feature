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
      And Click RESERVE action button on EBOOK book with 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
    Then EBOOK book with REMOVE action button and 'bookInfo' bookInfo is present on catalog books screen
    And I close Book Details for IOSTab
    When I open Holds
    Then EBOOK book with REMOVE action button and 'bookInfo' bookInfo is present on holds screen
    When Open EBOOK book with REMOVE action button and 'bookInfo' bookInfo on holds screen
    And Click REMOVE action button on book details screen
    And I open Holds
    And Wait for 7 seconds
    Then EBOOK book with REMOVE action button and 'bookInfo' bookInfo is not present on holds screen

  @logout @returnBooks @tier2
  Scenario: Hold from Subcategory List View and Remove a Reserved Book from Subcategory List View
    When I add "LYRASIS Reads" account from welcomeScreen
      And I enter credentials for 'LYRASIS Reads' account
    Then Login is performed successfully
    When I open Catalog
      And I open search modal
      And I search 'unavailable' book of distributor 'Bibliotheca' and bookType 'EBOOK' and save as 'bookNameInfo'
      And I switch to 'eBooks' catalog tab
    And Click RESERVE action button on EBOOK book with 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
    And Click REMOVE action button on EBOOK book with 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
    Then EBOOK book with RESERVE action button and 'bookInfo' bookInfo is present on catalog books screen

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
    When Open EBOOK book with RESERVE action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
    Then Book 'bookInfo' is opened on book details screen
    When Click RESERVE action button on book details screen
      And I close Book Details for IOSTab
    When I open Holds
    Then EBOOK book with REMOVE action button and 'bookInfo' bookInfo is present on holds screen
    When Open EBOOK book with REMOVE action button and 'bookInfo' bookInfo on holds screen
    And Click REMOVE action button on book details screen
    And I open Holds
    And Wait for 7 seconds
    Then EBOOK book with REMOVE action button and 'bookInfo' bookInfo is not present on holds screen

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
    When Open EBOOK book with RESERVE action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
    Then Book 'bookInfo' is opened on book details screen
    When Click RESERVE action button on book details screen
      And Click REMOVE action button on book details screen
    Then I check that book contains RESERVE action button on book details screen

  @logout @returnBooks @tier2
  Scenario: Check out from Book Detail View and Return from Books
    When I add "LYRASIS Reads" account from welcomeScreen
      And I enter credentials for 'LYRASIS Reads' account
    Then Login is performed successfully
    When I open Catalog
      And I open search modal
      And I search 'available' book of distributor 'Bibliotheca' and bookType 'EBOOK' and save as 'bookNameInfo'
      And I switch to 'eBooks' catalog tab
    When Open EBOOK book with GET action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click GET action button on book details screen
      And I close Book Details for IOSTab
      And I open Books
    Then EBOOK book with READ action button and 'bookInfo' bookInfo is present on books screen
    When Open EBOOK book with READ action button and 'bookInfo' bookInfo on books screen
      And Click RETURN action button on book details screen
      And I close Book Details for IOSTab
      And I open Books
      And Wait for 10 seconds
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
    And Click GET action button on EBOOK book with 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
    And EBOOK book with READ action button and 'bookInfo' bookInfo is present on catalog books screen
    And Click READ action button on EBOOK book with 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Wait for 3 seconds
    Then 'bookInfo' book is present on epub reader screen
    When I return to previous screen for epub and pdf
      And I open Books
    Then EBOOK book with READ action button and 'bookInfo' bookInfo is present on books screen
    When Open EBOOK book with READ action button and 'bookInfo' bookInfo on books screen
      And Click RETURN action button on book details screen
      And I open Books
      And Wait for 7 seconds
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
    And Click GET action button on EBOOK book with 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
    And Click RETURN action button on EBOOK book with 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
    And EBOOK book with GET action button and 'bookInfo' bookInfo is present on catalog books screen

  @logout @returnBooks @tier2
  Scenario: Check out from Subcategory List View and Read from Books
    When I add "LYRASIS Reads" account from welcomeScreen
      And I enter credentials for 'LYRASIS Reads' account
    Then Login is performed successfully
    When I open Catalog
      And I open search modal
      And I search 'available' book of distributor 'Bibliotheca' and bookType 'EBOOK' and save as 'bookNameInfo'
      And I switch to 'eBooks' catalog tab
      And Click GET action button on EBOOK book with 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
    Then EBOOK book with READ action button and 'bookInfo' bookInfo is present on catalog books screen
    When I open Books
      And Open EBOOK book with READ action button and 'bookInfo' bookInfo on books screen
      And Click READ action button on book details screen
      And Wait for 3 seconds
    Then 'bookInfo' book is present on epub reader screen

  @logout @returnBooks @tier3 @exclude_android
  Scenario: Hold from Book Detail View and Cancel remove from holds tab (IOS)
    When I add "LYRASIS Reads" account from welcomeScreen
      And I enter credentials for 'LYRASIS Reads' account
    Then Login is performed successfully
    When I open Catalog
      And I open search modal
      And I search 'unavailable' book of distributor 'Bibliotheca' and bookType 'EBOOK' and save as 'bookNameInfo'
      And I switch to 'eBooks' catalog tab
      And Open EBOOK book with RESERVE action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
    Then Book 'bookInfo' is opened on book details screen
    When Click RESERVE action button on book details screen
    Then I check that book contains REMOVE action button on book details screen
    When Click REMOVE action button on book details screen and click CANCEL action button on alert. Only for ios
    Then I check that book contains REMOVE action button on book details screen
    When I close Book Details for IOSTab
      And I open Holds
    Then EBOOK book with REMOVE action button and 'bookInfo' bookInfo is present on holds screen
