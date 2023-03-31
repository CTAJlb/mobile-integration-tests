package screens.bottommenu;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;

public enum BottomMenu {
    SETTINGS("tabSettings", "Settings"),
    SETTINGS_ES("Configuración", "Configuración"),
    SETTINGS_IT("Impostazioni", "Impostazioni"),
    HOLDS("tabHolds", "Reservations"),
    HOLD_ES("Reservas", "Reservas"),
    HOLD_IT("Prenotazioni", "Prenotazioni"),
    BOOKS("tabBooks", "My Books"),
    CATALOG("tabCatalog", "Catalog"),
    CATALOG_ES("Catálogo", "Catálogo"),
    CATALOG_IT("Catalogo", "Catalogo");

    private static final PlatformName platformName = AqualityServices.getApplication().getPlatformName();

    private String androidCatalogName;
    private String iosCatalogName;

    BottomMenu(String androidCatalogName, String iosCatalogName) {
        this.androidCatalogName = androidCatalogName;
        this.iosCatalogName = iosCatalogName;
    }

    public String getItemName() {
        return platformName.equals(PlatformName.ANDROID) ? androidCatalogName : iosCatalogName;
    }
}
