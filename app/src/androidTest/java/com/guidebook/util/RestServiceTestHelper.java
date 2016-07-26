package com.guidebook.util;

import android.content.Context;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Rest service helper.
 *
 * @author Wiebe
 */
public class RestServiceTestHelper {

    /**
     * Convert {@link InputStream} to {@link String}
     *
     * @param is {@link InputStream}
     * @return String
     * @throws Exception
     */
    private static String _convertStreamToString(InputStream is) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        reader.close();
        return sb.toString();
    }

    /**
     * Get String from file.
     *
     * @param context  {@link Context}
     * @param filePath filePath
     * @return String
     * @throws Exception
     */
    public static String getStringFromFile(Context context, String filePath) throws Exception {
        final InputStream stream = context.getResources().getAssets().open(filePath);

        String result = _convertStreamToString(stream);
        stream.close();
        return result;
    }
}
