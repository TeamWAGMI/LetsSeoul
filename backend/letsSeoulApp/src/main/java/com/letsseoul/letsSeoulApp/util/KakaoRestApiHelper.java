package com.letsseoul.letsSeoulApp.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class KakaoRestApiHelper {

    public enum HttpMethodType { POST, GET, DELETE }

    private static final String API_SERVER_HOST  = "https://kapi.kakao.com";

    private static final String USER_SIGNUP_PATH = "/v1/user/signup";
    private static final String USER_UNLINK_PATH = "/v1/user/unlink";
    private static final String USER_LOGOUT_PATH = "/v1/user/logout";
    private static final String USER_ME_PATH = "/v1/user/me";
    private static final String USER_UPDATE_PROFILE_PATH = "/v1/user/update_profile";
    private static final String USER_IDS_PATH = "/v1/user/ids";

    private static final String STORY_PROFILE_PATH = "/v1/api/story/profile";
    private static final String STORY_ISSTORYUSER_PATH = "/v1/api/story/isstoryuser";
    private static final String STORY_MYSTORIES_PATH = "/v1/api/story/mystories";
    private static final String STORY_MYSTORY_PATH = "/v1/api/story/mystory";
    private static final String STORY_DELETE_MYSTORY_PATH = "/v1/api/story/delete/mystory";
    private static final String STORY_POST_NOTE_PATH = "/v1/api/story/post/note";
    private static final String STORY_UPLOAD_MULTI_PATH = "/v1/api/story/upload/multi";
    private static final String STORY_POST_PHOTO_PATH = "/v1/api/story/post/photo";
    private static final String STORY_LINKINFO_PATH = "/v1/api/story/linkinfo";
    private static final String STORY_POST_LINK_PATH = "/v1/api/story/post/link";

    private static final String TALK_PROFILE_PATH = "/v1/api/talk/profile";

    private static final String PUSH_REGISTER_PATH = "/v1/push/register";
    private static final String PUSH_TOKENS_PATH = "/v1/push/tokens";
    private static final String PUSH_DEREGISTER_PATH = "/v1/push/deregister";
    private static final String PUSH_SEND_PATH = "/v1/push/send";

    private static final ObjectMapper JACKSON_OBJECT_MAPPER = new ObjectMapper();
    //private static final Gson GSON = new Gson();
    private static final String PROPERTIES_PARAM_NAME = "properties";

    private static final List<String> adminApiPaths = new ArrayList<String>();

    static {
        adminApiPaths.add(USER_IDS_PATH);
        adminApiPaths.add(PUSH_REGISTER_PATH);
        adminApiPaths.add(PUSH_TOKENS_PATH);
        adminApiPaths.add(PUSH_DEREGISTER_PATH);
        adminApiPaths.add(PUSH_SEND_PATH);
    }

    private String accessToken;
    private String adminKey;

    public void setAccessToken(final String accessToken) {
        this.accessToken = accessToken;
    }

    public void setAdminKey(final String adminKey) {
        this.adminKey = adminKey;
    }

    ///////////////////////////////////////////////////////////////
    // User Management
    ///////////////////////////////////////////////////////////////

    public String signup() {
        return request(HttpMethodType.POST, USER_SIGNUP_PATH);
    }

    public String signup(final Map<String, String> params) throws JsonProcessingException {
        return request(HttpMethodType.POST, USER_SIGNUP_PATH, PROPERTIES_PARAM_NAME + "=" + mapToJsonStr(params));
    }

    public String unlink() {
        return request(HttpMethodType.POST, USER_UNLINK_PATH);
    }

    public String logout() {
        return request(HttpMethodType.POST, USER_LOGOUT_PATH);
    }

    public String me() {
        return request(USER_ME_PATH);
    }

    public String updatProfile(final Map<String, String> params) throws JsonProcessingException {
        return request(HttpMethodType.POST, USER_UPDATE_PROFILE_PATH, PROPERTIES_PARAM_NAME + "=" + mapToJsonStr(params));
    }

    public String getUserIds() {
        return request(USER_IDS_PATH);
    }

    public String getUserIds(final Map<String, String> params) {
        return request(HttpMethodType.GET, USER_IDS_PATH, mapToParams(params));
    }

    ///////////////////////////////////////////////////////////////
    // Kakao Story
    ///////////////////////////////////////////////////////////////

    public String isStoryUser() {
        return request(STORY_ISSTORYUSER_PATH);
    }

    public String storyProfile() {
        return request(STORY_PROFILE_PATH);
    }

    public String postNote(final Map<String, String> params) {
        return request(HttpMethodType.POST, STORY_POST_NOTE_PATH, mapToParams(params));
    }

    public String postLink(final Map<String, String> params) {
        return request(HttpMethodType.POST, STORY_POST_LINK_PATH, mapToParams(params));
    }

    public String postPhoto(final Map<String, String> params) {
        return request(HttpMethodType.POST, STORY_POST_PHOTO_PATH, mapToParams(params));
    }

    public String getMyStory(final Map<String, String> params) {
        return request(HttpMethodType.GET, STORY_MYSTORY_PATH, mapToParams(params));
    }

    public String getMyStories() {
        return request(STORY_MYSTORIES_PATH);
    }

    public String getMyStories(final Map<String, String> params) {
        return request(HttpMethodType.GET, STORY_MYSTORIES_PATH, mapToParams(params));
    }

    public String deleteMyStory(final String id) {
        return request(HttpMethodType.DELETE, STORY_DELETE_MYSTORY_PATH, "?id=" + id);
    }

    public String deleteMyStory(final Map<String, String> params) {
        return request(HttpMethodType.DELETE, STORY_DELETE_MYSTORY_PATH, mapToParams(params));
    }

    public String getLinkInfo(String url) {
        return request(HttpMethodType.GET, STORY_LINKINFO_PATH, "?url=" + url);
    }

    public String uploadMulti(File[] files) {

        if (files.length == 0)
            return null;

        String CRLF = "\r\n";
        String TWO_HYPHENS = "--";
        String BOUNDARY = "---------------------------012345678901234567890123456";
        HttpsURLConnection conn = null;
        DataOutputStream dos = null;
        FileInputStream fis = null;

        int bytesRead, bytesAvailable, bufferSize;
        byte[] buffer;
        int maxBufferSize = 1 * 1024 * 1024;

        // Request
        try {
            URL url = new URL(API_SERVER_HOST + STORY_UPLOAD_MULTI_PATH);
            conn = (HttpsURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + BOUNDARY);
            conn.setRequestProperty("Authorization", "Bearer " + accessToken);
            conn.setRequestProperty("Cache-Control", "no-cache");

            dos = new DataOutputStream(conn.getOutputStream());

            for (File f : files) {
                dos.writeBytes(TWO_HYPHENS + BOUNDARY + CRLF);
                dos.writeBytes("Content-Disposition: form-data; name=\"file\";" + " filename=\"" + f.getName() + "\"" + CRLF);
                dos.writeBytes(CRLF);
                fis = new FileInputStream(f);
                bytesAvailable = fis.available();
                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                buffer = new byte[bufferSize];
                bytesRead = fis.read(buffer, 0, bufferSize);
                while (bytesRead > 0) {
                    dos.write(buffer, 0, bufferSize);
                    bytesAvailable = fis.available();
                    bufferSize = Math.min(bytesAvailable, maxBufferSize);
                    bytesRead = fis.read(buffer, 0, bufferSize);
                }
                dos.writeBytes(CRLF);
            }

            // finish delimiter
            dos.writeBytes(TWO_HYPHENS + BOUNDARY + TWO_HYPHENS + CRLF);

            fis.close();
            dos.flush();
            dos.close();

        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            if (dos != null) try { dos.close(); } catch (IOException ignore) {}
            if (fis != null) try { fis.close(); } catch (IOException ignore) {} }

        // Response
        InputStream inputStream = null;
        BufferedReader reader = null;
        try {
            inputStream = new BufferedInputStream(conn.getInputStream());
            reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            String line;
            StringBuilder builder = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                builder.append(line).append("\n");
            }
            reader.close();
            return builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try { reader.close(); } catch (IOException ignore) {}
            }
            if (inputStream != null) {
                try { inputStream.close(); } catch (IOException ignore) {}
            }
            conn.disconnect();
        }

        return null;
    }

    ///////////////////////////////////////////////////////////////
    // Kakao Talk
    ///////////////////////////////////////////////////////////////

    public String talkProfile() {
        return request(TALK_PROFILE_PATH);
    }

    ///////////////////////////////////////////////////////////////
    // Push Notification
    ///////////////////////////////////////////////////////////////

    public String registerPush(final Map<String, String> params) {
        return request(HttpMethodType.POST, PUSH_REGISTER_PATH, mapToParams(params));
    }

    public String getPushTokens(final Map<String, String> params) {
        return request(HttpMethodType.GET, PUSH_TOKENS_PATH, mapToParams(params));
    }

    public String deregisterPush(final Map<String, String> params) {
        return request(HttpMethodType.POST, PUSH_DEREGISTER_PATH, mapToParams(params));
    }

    public String sendPush(final Map<String, String> params) {
        return request(HttpMethodType.POST, PUSH_SEND_PATH, mapToParams(params));
    }

    public String request(final String apiPath) {
        return request(HttpMethodType.GET, apiPath, null);
    }

    public String request(final HttpMethodType httpMethod, final String apiPath) {
        return request(httpMethod, apiPath, null);
    }

    public String request(HttpMethodType httpMethod, final String apiPath, final String params) {

        String requestUrl = API_SERVER_HOST + apiPath;
        if (httpMethod == null) {
            httpMethod = HttpMethodType.GET;
        }
        if (params != null && params.length() > 0
                && (httpMethod == HttpMethodType.GET || httpMethod == HttpMethodType.DELETE)) {
            requestUrl += params;
        }

        HttpsURLConnection conn;
        OutputStreamWriter writer = null;
        BufferedReader reader = null;
        InputStreamReader isr = null;

        try {
            final URL url = new URL(requestUrl);
            conn = (HttpsURLConnection) url.openConnection();
            conn.setRequestMethod(httpMethod.toString());

            if (adminApiPaths.contains(apiPath)) {
                conn.setRequestProperty("Authorization", "KakaoAK " + this.adminKey);
            } else {
                conn.setRequestProperty("Authorization", "Bearer " + this.accessToken);
            }

            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("charset", "utf-8");

            if (params != null && params.length() > 0 && httpMethod == HttpMethodType.POST) {
                conn.setDoOutput(true);
                writer = new OutputStreamWriter(conn.getOutputStream());
                writer.write(params);
                writer.flush();
            }

            final int responseCode = conn.getResponseCode();
            System.out.println(String.format("\nSending '%s' request to URL : %s", httpMethod, requestUrl));
            System.out.println("Response Code : " + responseCode);
            if (responseCode == 200)
                isr = new InputStreamReader(conn.getInputStream());
            else
                isr = new InputStreamReader(conn.getErrorStream());

            reader = new BufferedReader(isr);
            final StringBuffer buffer = new StringBuffer();
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            System.out.println(buffer.toString());
            return buffer.toString();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) try { writer.close(); } catch (Exception ignore) { }
            if (reader != null) try { reader.close(); } catch (Exception ignore) { }
            if (isr != null) try { isr.close(); } catch (Exception ignore) { }
        }

        return null;
    }

    public String urlEncodeUTF8(String s) {
        try {
            return URLEncoder.encode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new UnsupportedOperationException(e);
        }
    }

    public String mapToParams(Map<String, String > map) {
        StringBuilder paramBuilder = new StringBuilder();
        for (String key : map.keySet()) {
            paramBuilder.append(paramBuilder.length() > 0 ? "&" : "");
            paramBuilder.append(String.format("%s=%s", urlEncodeUTF8(key),
                    urlEncodeUTF8(map.get(key).toString())));
        }
        return paramBuilder.toString();
    }

    public String mapToJsonStr(Map<String, String > map) throws JsonProcessingException {
        return JACKSON_OBJECT_MAPPER.writeValueAsString(map);
        // return GSON.toJson(map);
    }
}