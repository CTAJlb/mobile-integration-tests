Feature: Distributors

  @logout @returnBooks @tier2
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
      And I press on the book details screen at the action button RESERVE
    Then I check that opened book contains CANCEL button at book details screen

    Scenarios:
      | distributor | bookType  | tabName    |
      | Bibliotheca | EBOOK     | eBooks     |
      | Bibliotheca | AUDIOBOOK | Audiobooks |
      | Axis 360    | EBOOK     | eBooks     |
      | Axis 360    | AUDIOBOOK | Audiobooks |

  @logout @returnBooks @tier2
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
      And Get book on the book details screen
    Then Opened book contains read button at book details screen
    When I read <bookType> book
    Then Reader screen for <bookType> type book 'bookInfo' is present

    Scenarios:
      | distributor | bookType  | tabName    |
      | Bibliotheca | EBOOK     | eBooks     |
      | Bibliotheca | AUDIOBOOK | Audiobooks |
      | Axis 360    | EBOOK     | eBooks     |
      | Axis 360    | AUDIOBOOK | Audiobooks |

  @logout @returnBooks @tier2
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
      And Get book on the book details screen
      And I wait for 3 seconds
      And Press on the book details screen at the action button RETURN
    Then I check that opened book contains GET button at book details screen

    Scenarios:
      | distributor | bookType  | tabName    |
      | Bibliotheca | EBOOK     | eBooks     |
      | Bibliotheca | AUDIOBOOK | Audiobooks |
