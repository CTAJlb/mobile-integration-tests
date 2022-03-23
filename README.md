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

All tests run at night in BrowserStack. Runs are configured with github actions in [maven.yml](https://github.com/ThePalaceProject/mobile-integration-tests/blob/main/.github/workflows/maven.yml) file. Test run time on each device is configured in a file that describes it using cron expressions. These devices are described in repositories for [Android](https://github.com/ThePalaceProject/android-binaries/tree/main/.github/workflows) and [iOS](https://github.com/ThePalaceProject/ios-binaries/tree/master/.github/workflows) devices.

Tests also can be run locally. The [settings.json](https://github.com/ThePalaceProject/mobile-integration-tests/blob/main/src/test/resources/settings.json) file is used to configure device, application build and platform. All devices are described in [device.json](https://github.com/ThePalaceProject/mobile-integration-tests/blob/main/src/test/resources/devices.json) file. Also for LYRASIS Reads library creds are needed. It can be configured in [config.json](https://github.com/ThePalaceProject/mobile-integration-tests/blob/main/src/test/resources/config.json) file.

Test results can be seen via Allure Reports.