/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fhdo.swt2.udrive.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Properties;

/**
 *
 * @author Marcel
 */
public class LanguageManager {

    private static LanguageManager instance;
    public static ArrayList<String> LOCALES = new ArrayList<>();
    private static Locale locale;

    public static Locale getLocale() {
        return locale;
    }

    public static void setLocale(Locale locale) {
        LanguageManager.locale = locale;
    }

    private LanguageManager() {
    }

    public static LanguageManager getInstance() {
        if (LanguageManager.instance == null) {
            LanguageManager.instance = new LanguageManager();
        }
        return LanguageManager.instance;
    }
    
    public static ArrayList<String> getLanguages() throws FileNotFoundException, IOException
    {
        Properties properties = new Properties();
        BufferedInputStream stream = new BufferedInputStream(new FileInputStream("languages.properties"));
        properties.load(stream);
        stream.close();
        String sprache = properties.getProperty("lang");
        return null;
    }
    
}
