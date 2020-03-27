/*
 * Copyright (c) 2016-2019 Niall Lindsay
 *
 */

package net.kitesoftware.holograms.updater;

import org.bukkit.plugin.Plugin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.CompletableFuture;

public class UpdateChecker {
    private static final String API_URL = "https://api.spigotmc.org/legacy/update.php?resource=";
    private Plugin plugin;
    private int resourceID;

    public UpdateChecker(Plugin plugin, int resourceID) {
        this.plugin = plugin;
        this.resourceID = resourceID;
    }

    public CompletableFuture<UpdateStatus> checkUpdates() {
        return CompletableFuture.supplyAsync(() -> {
            int responseCode;

            try {
                URL url = new URL(API_URL + resourceID);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                InputStreamReader reader = new InputStreamReader(connection.getInputStream());
                responseCode = connection.getResponseCode();

                if (responseCode != 200) return UpdateStatus.UNAVAILABLE;

                BufferedReader bufferedReader = new BufferedReader(reader);
                String latestVersion = bufferedReader.readLine();
                String currentVersion = plugin.getDescription().getVersion();

                reader.close();

                if (latestVersion.equals(currentVersion)) {
                    return UpdateStatus.UP_TO_DATE;
                } else {
                    return UpdateStatus.DIFFERENT_VERSION;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return UpdateStatus.UNAVAILABLE;
        });
    }

    public enum UpdateStatus {UP_TO_DATE, DIFFERENT_VERSION, UNAVAILABLE}
}
