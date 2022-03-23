<h1 align="center"> Integration tests </h1>

This repository contains integration tests for the Android and iOS code of the [Palace](https://thepalaceproject.org/) application

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
