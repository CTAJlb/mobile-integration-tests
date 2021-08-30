Feature: Main actions open access books

  Background:
    When I add "Digital Public Library of America" account from welcomeScreen
      And I open categories by chain and chain starts from CategoryScreen:
        | Our Picks |

@tier2 @exclude_ios
Scenario: Check out from Book Detail View and Return from Books
  When I open the book details for the subsequent DOWNLOAD on Subcategory List View and save it as 'bookInfo'
  Then I check that book contains READ action button on book details view
  When I open Books
  Then Book 'bookInfo' is present in Books List
  When I open book 'bookInfo' details by clicking on cover
    And I press on the book details view at the action button DELETE
    And I open Books
  Then Book 'bookInfo' is present in Books List

  @tier2 @exclude_android
  Scenario: Check out from Book Detail View and Return from Books(IOS)
    When I open the book details for the subsequent DOWNLOAD on Subcategory List View and save it as 'bookInfo'
      And I close Book Details for IOSTab
      And I open Books
      And I open book 'bookInfo' details by clicking on cover
      And I press on the book details view at the action button DELETE
      And I close Book Details for IOSTab
      And I open Books
    Then Book 'bookInfo' is not present in Books List

  @tier2 @exclude_ios
  Scenario: Check out from Book Detail View and Return from Book Detail View
    When I open the book details for the subsequent DOWNLOAD on Subcategory List View and save it as 'bookInfo'
      And I press on the book details view at the action button DELETE
    Then I check that book contains DOWNLOAD action button on book details view

  @tier2
  Scenario: Check out from Book Detail View and Read from Book Detail View
    When I open the book details for the subsequent DOWNLOAD on Subcategory List View and save it as 'bookInfo'
      And I press on the book details view at the action button READ
    Then Book 'bookInfo' is present on screen

  @tier2 @exclude_android
  Scenario: Check out from Book Detail View and Return from Book Detail View(IOS)
    When I open the book details for the subsequent DOWNLOAD on Subcategory List View and save it as 'bookInfo'
      And I press on the book details view at the action button DELETE
    Then I check that book contains GET action button on book details view

  @tier2 @exclude_ios
  Scenario: Check out from Subcategory List View and Return from Books
    When DOWNLOAD book from subcategory list view and save it as 'bookInfo'
      And I open Books
    Then Book 'bookInfo' is present in Books List
    When I open book 'bookInfo' details by clicking on cover
      And I press on the book details view at the action button DELETE
      And I open Books
    Then Book 'bookInfo' is present in Books List

  @tier2
  Scenario: Check out from Subcategory List View and Read from Subcategory List View
    When DOWNLOAD book from subcategory list view and save it as 'bookInfo'
    Then Book saved as 'bookInfo' should contain READ button on Subcategory List View
    When I click on the book 'bookInfo' button READ on subcategory list view
    Then Book 'bookInfo' is present on screen

  @tier2 @exclude_android
  Scenario: Check out from Subcategory List View and Return from Books(IOS)
    When DOWNLOAD book from subcategory list view and save it as 'bookInfo'
      And I open Books
    Then Book 'bookInfo' is present in Books List
    When I open book 'bookInfo' details by clicking on cover
      And I press on the book details view at the action button DELETE
      And I close Book Details for IOSTab
      And I open Books
    Then Book 'bookInfo' is not present in Books List

  @tier2 @exclude_android
  Scenario: Check out from Subcategory List View and Return from Subcategory List View(IOS)
    When DOWNLOAD book from subcategory list view and save it as 'bookInfo'
      And I click on the book 'bookInfo' button DELETE on subcategory list view
    Then Book saved as 'bookInfo' should contain GET button on Subcategory List View
