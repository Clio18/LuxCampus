package com.luxcampus.Lection_11.staticServerV2;

import java.io.*;

public class ResourceReader {
    private String webAppPath;

    public ResourceReader(String webAppPath) {
        this.webAppPath = webAppPath;
    }

    public String getWebAppPath() {
        return webAppPath;
    }

    public void setWebAppPath(String webAppPath) {
        this.webAppPath = webAppPath;
    }

    public String readResources(String uri) throws IOException {
        File file = new File(webAppPath, uri);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        StringBuilder stringBuilder = new StringBuilder();
        while (true) {
            String s = bufferedReader.readLine();
            if (s == null || s.trim().length() == 0) {
                break;
            }
            stringBuilder.append(s + "\n");
        }
        String content = stringBuilder.toString();
        System.out.println("===== READ RESOURCES: =====");
        System.out.println(content);
        return content;
    }
}
