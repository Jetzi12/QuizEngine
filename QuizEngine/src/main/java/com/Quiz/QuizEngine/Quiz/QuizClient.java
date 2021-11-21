package com.Quiz.QuizEngine.Quiz;


import com.Quiz.QuizEngine.Quiz.Model.Example;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
@Component
public class QuizClient {

  public String returnUsers () throws IOException {
    URL url = new URL("https://jsonplaceholder.typicode.com/users/1");
    InputStreamReader reader = new InputStreamReader(url.openStream());
    Example jsonObject = new Gson().fromJson(reader,Example.class);

    return jsonObject.toString();
  }
}
