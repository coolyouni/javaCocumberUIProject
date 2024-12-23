package commonUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Config {
    private static final Logger LOGGER = Logger.getLogger(Config.class.getName());
    private Properties properties;
    private static final String PROPERTIES_FILE = "config.properties";

    // Supplier mappings as a Map for easy lookups
    private static final Map<String, String> SUPPLIER_MAP = new HashMap<>();
    static {
        SUPPLIER_MAP.put("QR", "Qatar Airways");
        SUPPLIER_MAP.put("AA", "American Airlines");
        SUPPLIER_MAP.put("BA", "British Airways");
        SUPPLIER_MAP.put("SQ", "Singapore Airline");
        SUPPLIER_MAP.put("1S", "Sabre");
    }

    public Config() {
        properties = new Properties();
        try (InputStream input = new FileInputStream(PROPERTIES_FILE)) {
            properties.load(input);
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Error loading configuration file: " + PROPERTIES_FILE, ex);
        }
    }

    // Helper method to retrieve a property as a string with a default value
    private String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    // Helper for boolean properties
    public boolean getBooleanProperty(String key) {
        return Boolean.parseBoolean(getProperty(key, "false"));
    }

    // Helper for integer properties with default value
    public int getIntProperty(String key, int defaultValue) {
        String value = getProperty(key, String.valueOf(defaultValue));
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            LOGGER.log(Level.WARNING, "Invalid integer for property " + key + ": " + value, e);
            return defaultValue;
        }
    }

    // Specific properties
    public String getBaseUrl() {
        return getProperty("baseUrl", "http://cbtqa.airretailer.local");
    }

    public String getBrowser() {
        return getProperty("browser", "chrome");
    }
    
    public boolean isHeadless() {
        return getBooleanProperty("headless");
    }

    public int getDefaultTimeout() {
        return getIntProperty("timeout", 7); // Default to 7 seconds
    }
    
    public String getUsername() {
        return getProperty("username", "defaultUser");
    }

    public String getPassword() {
        return getProperty("password", "");
    }
    
    public String getSource() {
        return getProperty("source", "DXB");
    }
    
    public String getDestination() {
        return getProperty("destination", "DOH");
    }
    
    public String getTravellingDate() {
        return getProperty("travellingDate", "01/01/2024");
    }
    
    public String getSupplierFullName() {
        String supplierCode = getSupplierCode();
        return SUPPLIER_MAP.getOrDefault(supplierCode.toUpperCase(), "Unknown Supplier");
    }
    
    public String getSupplierCode() {
        return getProperty("supplier", "QR");
    }
    
    public String getAirline() {
        return getProperty("airlineCode", "").toLowerCase();
    }
    
    public String getTripName() {
        return getProperty("tripname", "Default Trip");
    }

    // Card details methods
    public String getCardNumber() {
        return getProperty("card_Number", "");
    }

    public String getExpiry() {
        return getProperty("expiry", "").replace("/", ""); // Remove the slash from the expiry date
    }

    public String getCvv() {
        return getProperty("cvv", "");
    }

    public String getName() {
        return getProperty("name", "Card Holder");
    }

    // Get first 4 letters of country
    public String getCountry() {
        String country = getProperty("country", "UAE");
        return (country.length() >= 4) ? country.substring(0, 4) : country;
    }

    // Get first 4 letters of city
    public String getCity() {
        String city = getProperty("city", "Dubai");
        return (city.length() >= 4) ? city.substring(0, 4) : city;
    }

    public String getPostalCode() {
        return getProperty("postal_code", "0000");
    }

    public String getStreet() {
        return getProperty("street", "Default Street");
    }
}
