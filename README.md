<h1 align="center"> Integration tests </h1>

This repository contains integration tests for the Android and iOS code of the [Palace](https://thepalaceproject.org/) application

# Structure of framework

JDK 8 is used in this project. As a framework for automation testing was selected [aquality](https://github.com/aquality-automation/aquality-appium-mobile-java) framework.

This framework designed to simplify automation of Android and iOS mobile applications using Appium. Most of performed methods are logged using LOG4J, so it is easy to see a history of performed actions in log.

## Quick start

1. To start work with this package, simply add the dependency to pom.xml:

```
<dependency>
    <groupId>com.github.aquality-automation</groupId>
    <artifactId>aquality-appium-mobile</artifactId>
    <version>3.0.0-beta</version>
</dependency>
```

2. Configure the path to application, device name and platform at [settings.json](https://github.com/ThePalaceProject/mobile-integration-tests/blob/main/src/test/resources/settings.json)
3. Launch an application directly by calling `AqualityServices.getApplication();`.
4. That's it! Now you are able to work with Application via AqualityServices or via element services. Also there are some example tests [here](https://github.com/aquality-automation/aquality-appium-mobile-java/tree/master/src/test/java/samples/android).
5. To interact with Application's forms and elements, the Page/Screen Objects pattern is used. This approach is fully integrated into framework. To start with that, you will need to create a separate class for each window/form of your application, and inherit this class from the [Screen](https://github.com/aquality-automation/aquality-appium-mobile-java/blob/master/src/main/java/aquality/appium/mobile/screens/Screen.java).
6. From the Screen Object perspective, each Screen consists of elements on it (e.g. Buttons, TextBox, Labels and so on). To interact with elements, on your form class create fields of type IButton, ITextBox, ILabel, and initialize them using the `getElementFactory()`. Created elements have a various methods to interact with them.

## ScreenFactory

When you automate tests for both iOS and Android platforms it is good to have only one set of tests and different implementations of screens. ScreenFactory allows to do this. You can define abstract classes for your screens and have different implementations for iOS and Android platforms. After that you can use ScreenFactory to resolve a necessary screen depending on the chosen platform.

## Devices

Framework allows you to run tests on different devices and store their settings (like udid, name, etc.) in JSON files. All devices can be set in [device.json](https://github.com/ThePalaceProject/mobile-integration-tests/blob/main/src/test/resources/devices.json) file. It is possible to set default device for each platform in [settings.json](https://github.com/ThePalaceProject/mobile-integration-tests/blob/main/src/test/resources/settings.json) by defining `deviceKey` property which is a key of device settings from `devices.json` file.

## Structure

The common structure of the project:

* constants - constants that applied to the project classes. The main place of class data like labels names, timeouts, markup attributes, etc.
* factories - classes that implement any type of factory pattern
  * steps - classes with the functionality the same as page factories. Necessary because of different steps on the iOS Android applications to reach the same
* features - Cucumber features written on Gherkin language
* framework - support functionality to the aquality framework
  * configuration - providers of the test data
  * utilities - utility files that extend the built-in functionality of the framework
    * keyboard - utility working with physical OS keyboard
    * swipe - utility for any type of the swipe actions
    * feedXMLUtil - utility for gettnig XML file using retrofit framework
    * returningBooksUtil - utility for returning books from account in Palace app in the end of tests
* hooks - hooks that executing before/after every test
  * logout - log out hooks
* models - page objects models e.x. book data like title, author, etc.
* runners - Test runner of cucumber tests that allow run tests via class where built-in inner behaviors and dependencies
* screens - all classes with the description of every screen in the application. Every screen description separated on ios/android package. It is necessary for a specific platform screen description. Every screen realization must extend the abstract class where placed methods for this screen.
* stepdefinitions - steps for the cucumber. Written in the same principle as screens
* transformers - transformers to the object for the cucumber

## Cucumber + Gherkin

Cucumber is used in this project. It is a tool that supports BDD (behavior driver development). Central to the Cucumber BDD approach is its ordinary language parser Gherkin.  It allows expected software behaviors to be specified in a logical language that every person can understand.

## JInit

The next big part of the solution is a test runner. As the test runner JUnit is used. This framework permits validate results of the tests and run tests in pair with Cucumber.

# Running

Tests are created for Palace Bookshelf and LYRASIS Reads libraries and run on iOS and Android platforms on such devices:
* Android:
  * Google Pixel 6 (12 Android)
  * Samsung Galaxy S21 Ultra (11 Android)
  * Samsung S8+ (9 Android)
  * Samsung Galaxy Tab S7 (11 Android)
* iOS:
  * IPhone 13 Pro Max (15 iOS)
  * IPad Pro 12.9.2021 (14 iOS)
  * IPhone 8 (13 iOS)

## Running via Github actions

All tests run at night in BrowserStack. Runs are configured with github actions in [maven.yml](https://github.com/ThePalaceProject/mobile-integration-tests/blob/main/.github/workflows/maven.yml) file. Test run time on each device is configured in a file that describes it using cron expressions. These devices are described in repositories for [Android](https://github.com/ThePalaceProject/android-binaries/tree/main/.github/workflows) and [iOS](https://github.com/ThePalaceProject/ios-binaries/tree/master/.github/workflows) devices. In the [Actions](https://github.com/ThePalaceProject/mobile-integration-tests/actions) section you also could click the Run workflow button and start tests configured by tiers, app version and git branch. Below attached the screenshot with UI of the Github actions solution.

<img src="https://github.com/AEkaterina/pictures/blob/main/Screenshot_25.png" width="750" alt="Action section">

Here you should select the Test Run option at the left menu and after click the Run workflow.

* **Use workflow from** - git branch to launch
* **Tags to run** - tags (e.g. @tier1 @tier2 @tier3) if you wanna run specific tier
* **app_url** - the app URL that can be gotten by this link when you will upload the app
* **build name** - assembly name can be anything
* **Platform name** - ios/android

And you should press the Run workflow green button to apply run

## Run locally

Tests also can be run locally (e.g. by **Intellij Idea**). The [settings.json](https://github.com/ThePalaceProject/mobile-integration-tests/blob/main/src/test/resources/settings.json) file is used to configure device, application build and platform. There you need to write down target platformName, remoteConnectionUrl, username and access key from browserStack and app link.

<img src="https://github.com/AEkaterina/pictures/blob/main/Screenshot_26.png" height="550" alt="settings.json">

All devices are described in [device.json](https://github.com/ThePalaceProject/mobile-integration-tests/blob/main/src/test/resources/devices.json) file.

![alt text](https://github.com/AEkaterina/pictures/blob/main/Screenshot_27.png "devices.json file")

Also for LYRASIS Reads library creds are needed. It can be configured in [config.json](https://github.com/ThePalaceProject/mobile-integration-tests/blob/main/src/test/resources/config.json) file.

![alt text](https://github.com/AEkaterina/pictures/blob/main/Screenshot_28.png "config.json file")

To run test feature files are used.

![alt text](https://github.com/AEkaterina/pictures/blob/main/Screenshot_29.png "feature files")

Open one of them, right-click on the feature that is needed to run/debug and press run or debug button.

Test also can be run via **maven**:

```
mvn clean test -Daquality.buildName="NameOfTheBuild" -Daquality.token=TRACKING_API_TOKEN -Daquality.suiteName="@tier1/@tier2/@tier3" -Daquality.environment="ios/Android" -Dcredentials.LYRASIS.${{secrets.BookCard }}=${{secrets.BookPin }} -Dcredentials.LYRASIS.${{secrets.BookCardLyrasis2 }}=${{secrets.BookPinLyrasis2 }} -Dcredentials."The New York Public Library".${{secrets.BookCardNYPL }}=${{secrets.BookPinNYPL }} -DremoteConnectionUrl=RemoteUrlToTheBrowserstack -DplatformName=${{github.event.inputs.platform_name}} -DdriverSettings.${{github.event.inputs.platform_name}}.capabilities.app=${{github.event.inputs.bs_app_link}} -Dcucumber.options="--tags '${{github.event.inputs.test_tag}} and not @exclude_${{github.event.inputs.platform_name}}'"
```

Test results can be seen via Allure Reports.

![alt text](https://github.com/AEkaterina/pictures/blob/main/Screenshot_30.png "allure")
