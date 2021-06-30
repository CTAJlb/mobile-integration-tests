Feature: Distributors

  @logout @returnBooks @tier2 @go
  Scenario Outline: Hold from Book Detail View
    When I add "LYRASIS" account from welcomeScreen
      And I enter credentials for 'LYRASIS' account
    Then Login is performed successfully
    When I open Catalog
      And I switch to 'LYRASIS' from side menu
      And I open search modal
      And I search 'unavailable' book of distributor '<distributor>' and bookType '<bookType>' and save as 'bookNameInfo'
      And I switch to '<tabName>' catalog tab
      And Open '<bookType>' book from Subcategory List View with title 'bookNameInfo' and save it as 'bookInfo'
      And I press on the book details view at the action button RESERVE
    Then I check that book contains CANCEL action button on book details view

    Scenarios:
      | distributor | bookType  | tabName    |
      | Bibliotheca | EBOOK     | eBooks     |
      | Bibliotheca | AUDIOBOOK | Audiobooks |
      | Axis 360    | EBOOK     | eBooks     |
      | Axis 360    | AUDIOBOOK | Audiobooks |

  @logout @returnBooks @tier2 @go
  Scenario Outline: Check out from Book Detail View
    When I add "LYRASIS" account from welcomeScreen
      And I enter credentials for 'LYRASIS' account
    Then Login is performed successfully
    When I open Catalog
      And I switch to 'LYRASIS' from side menu
      And I open search modal
      And I search 'available' book of distributor '<distributor>' and bookType '<bookType>' and save as 'bookNameInfo'
      And I switch to '<tabName>' catalog tab
      And Open '<bookType>' book from Subcategory List View with title 'bookNameInfo' and save it as 'bookInfo'
      And I press on the book details view at the action button GET
    Then I check that book contains READ action button on book details view
    When I read book with <bookType> type from book details view
    Then Reader screen for 'bookInfo' book with <bookType> type is present

    Scenarios:
      | distributor | bookType  | tabName    |
      | Bibliotheca | EBOOK     | eBooks     |
      | Bibliotheca | AUDIOBOOK | Audiobooks |
      | Axis 360    | EBOOK     | eBooks     |
      | Axis 360    | AUDIOBOOK | Audiobooks |

  @logout @returnBooks @tier2 @go
  Scenario Outline: Return book from Book Detail View
    When I add "LYRASIS" account from welcomeScreen
      And I enter credentials for 'LYRASIS' account
    Then Login is performed successfully
    When I open Catalog
      And I switch to 'LYRASIS' from side menu
      And I open search modal
      And I search 'available' book of distributor '<distributor>' and bookType '<bookType>' and save as 'bookNameInfo'
      And I switch to '<tabName>' catalog tab
      And Open '<bookType>' book from Subcategory List View with title 'bookNameInfo' and save it as 'bookInfo'
      And I press on the book details view at the action button GET
      And I wait for 3 seconds
      And I press on the book details view at the action button RETURN
    Then I check that book contains GET action button on book details view

    Scenarios:
      | distributor | bookType  | tabName    |
      | Bibliotheca | EBOOK     | eBooks     |
      | Bibliotheca | AUDIOBOOK | Audiobooks |
