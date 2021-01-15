package com.lab5.resteventhub.service;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

@Component
public class SendDataConsoleService {

    @Autowired
    private Environment env;

    private static final boolean USE_SSL = true;
    private static final int MAX_NUMBER = 1000;

    public void sendAndLog(String url) {
        JedisShardInfo info = new JedisShardInfo(env.getProperty("connections.cache.host"), Integer.parseInt(env.getProperty("PORT")), USE_SSL);
        info.setPassword(env.getProperty("CACHE_KEY"));
        Jedis jedis = new Jedis(info);

        try {
            URL data = new URL(url);
            HttpURLConnection con = (HttpURLConnection) data.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            JSONArray jsonArray;
            jedis.hset(env.getProperty("MAP_NAME"), "File", "None");
            Map<String, String> redisData = jedis.hgetAll(env.getProperty("MAP_NAME"));

            int count = 1;
            int startRaw = 1;
            int limit = 100;
            int endRaw = 0;
            if (checkIfFileExist(jedis, redisData)) {

                while ((inputLine = br.readLine()) != null) {
                    response.append(inputLine);
                    if (count == limit) {
                        jsonArray = new JSONArray(response.toString() + ']');
                        System.out.println(response.toString());
                        System.out.println("LENGTH: " + jsonArray.length());
                        endRaw = endRaw + count;
                        jedis.set("Raws", startRaw + ":" + endRaw);
                        startRaw = startRaw + count;
                        showData(jsonArray.length(), jsonArray, jedis, redisData);
                        count = 0;
                    }
                    count += 1;
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showData(int count, JSONArray jsonArray, Jedis jedis, Map<String, String> map) {
        jedis.hset(env.getProperty("MAP_NAME"), "Raws", "" + count);
        if (jsonArray.length() != MAX_NUMBER) {
            System.out.println("Raws from file " + "'" + env.getProperty("FILE_NAME") + "': " + jedis.get("Raws"));
            jedis.hset(env.getProperty("MAP_NAME"), "File", env.getProperty("FILE_NAME"));

            jedis.hset(env.getProperty("MAP_NAME"), "Status", "NotFinished");
        } else {
            System.out.println("Raws from file " + "'" + env.getProperty("FILE_NAME") + "': " + jedis.get("Raws"));
            jedis.hset(env.getProperty("MAP_NAME"), "Raws", "" + count);
            jedis.hset(env.getProperty("MAP_NAME"), "Status", "Completed");
            jedis.hset(env.getProperty("MAP_NAME"), "Info", "First attempt to input this file");
            System.out.println(map.get("Status"));
            jedis.close();
        }
    }

    public boolean checkIfFileExist(Jedis jedis, Map<String, String> map) {
        map = jedis.hgetAll(env.getProperty("MAP_NAME"));
        String name = map.get("File");
        String status = map.get("Status");

        if (!name.equals(env.getProperty("FILE_NAME"))) {
            jedis.hset(env.getProperty("MAP_NAME"), "File",env.getProperty("FILE_NAME"));
            System.out.println("NAME IS ALREADY EXISTS");
        } else {
            if (status.equals("Completed")) {
                jedis.hset(env.getProperty("MAP_NAME"), "Info", "Retry to input this file");
                System.out.println("Such file: " + "'" + env.getProperty("FILE_NAME") + "'" + " already exists. Application stop");
                jedis.close();
                return false;
            }
        }
        return true;
    }
}