Feature: Main actions open access books

  Background:
    When I add "Palace Bookshelf" account from welcomeScreen
    And I open Catalog
    And I open search modal
    And I search for 'Flower Fables' and save bookName as 'bookNameInfo'

@tier2 @exclude_ios
Scenario: Check out from Book Detail View and Return from Books(ANDROID)
  When Open EBOOK book with DOWNLOAD action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
  And I press on the book details view at the action button DOWNLOAD
  Then I check that book contains READ action button on book details view
  When I open Books
  Then EBOOK book with READ action button and 'bookInfo' bookInfo is present on books screen
  When Open EBOOK book with READ action button and 'bookInfo' bookInfo on books screen
    And I press on the book details view at the action button DELETE
    And I open Books
  Then EBOOK book with READ action button and 'bookInfo' bookInfo is present on books screen

  @tier2 @exclude_android
  Scenario: Check out from Book Detail View and Return from Books(IOS)
    When Open EBOOK book with GET action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
    And I press on the book details view at the action button GET
      And I close Book Details for IOSTab
      And I open Books
    Then EBOOK book with READ action button and 'bookInfo' bookInfo is present on books screen
    When Open EBOOK book with READ action button and 'bookInfo' bookInfo on books screen
      And I press on the book details view at the action button DELETE
      And I close Book Details for IOSTab
      And I open Books
    Then EBOOK book with READ action button and 'bookInfo' bookInfo is not present on books screen

  @tier2 @exclude_ios
  Scenario: Check out from Book Detail View and Return from Book Detail View(ANDROID)
    When Open EBOOK book with DOWNLOAD action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
    And I press on the book details view at the action button DOWNLOAD
      And I press on the book details view at the action button DELETE
    Then I check that book contains DOWNLOAD action button on book details view

  @tier2 @exclude_android
  Scenario: Check out from Book Detail View and Return from Book Detail View(IOS)
    When Open EBOOK book with GET action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
    And I press on the book details view at the action button GET
    And I press on the book details view at the action button DELETE
    Then I check that book contains GET action button on book details view

  @tier2 @exclude_ios
  Scenario: Check out from Book Detail View and Read from Book Detail View(ANDROID)
    When Open EBOOK book with DOWNLOAD action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
    And I press on the book details view at the action button DOWNLOAD
      And I press on the book details view at the action button READ
    Then Book 'bookInfo' is present on screen

  @tier2 @exclude_android
  Scenario: Check out from Book Detail View and Read from Book Detail View(IOS)
    When Open EBOOK book with GET action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
    And I press on the book details view at the action button GET
    And I press on the book details view at the action button READ
    Then Book 'bookInfo' is present on screen

  @tier2 @exclude_ios
  Scenario: Check out from Subcategory List View and Return from Books(ANDROID)
    When DOWNLOAD book from Subcategory List view and save it as 'bookInfo'
      And I open Books
    Then EBOOK book with READ action button and 'bookInfo' bookInfo is present on books screen
    When Open EBOOK book with READ action button and 'bookInfo' bookInfo on books screen
      And I press on the book details view at the action button DELETE
      And I open Books
    Then EBOOK book with READ action button and 'bookInfo' bookInfo is present on books screen

  @tier2 @exclude_android
  Scenario: Check out from Subcategory List View and Return from Books(IOS)
    When DOWNLOAD book from Subcategory List view and save it as 'bookInfo'
    And I open Books
    Then EBOOK book with READ action button and 'bookInfo' bookInfo is present on books screen
    When Open EBOOK book with READ action button and 'bookInfo' bookInfo on books screen
    And I press on the book details view at the action button DELETE
    And I close Book Details for IOSTab
    And I open Books
    Then EBOOK book with READ action button and 'bookInfo' bookInfo is not present on books screen

  @tier2
  Scenario: Check out from Subcategory List View and Read from Subcategory List View
    When DOWNLOAD book from Subcategory List view and save it as 'bookInfo'
    Then Book saved as 'bookInfo' should contain READ button on Subcategory List View
    When I click READ button on the 'bookInfo' book on Subcategory List view
    Then Book 'bookInfo' is present on screen

  @tier2 @exclude_android
  Scenario: Check out from Subcategory List View and Return from Subcategory List View(IOS)
    When DOWNLOAD book from Subcategory List view and save it as 'bookInfo'
      And I click DELETE button on the 'bookInfo' book on Subcategory List view
    Then Book saved as 'bookInfo' should contain GET button on Subcategory List View
