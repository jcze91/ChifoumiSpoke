package com.mti.presenter;

import com.mashape.unirest.http.Headers;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mti.model.Lobby;
import com.mti.model.User;
import com.mti.event.EnterLobbyViewEvent;
import com.mti.event.ShowStartViewEvent;
import com.mti.view.StartView;
import com.mvplite.event.EventBus;
import com.mvplite.event.EventHandler;
import com.mvplite.presenter.Presenter;
import org.json.JSONException;

import java.io.InputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.concurrent.Future;

/**
 * Created by JULIEN on 11/15/2014.
 */

public class StartPresenter extends Presenter<StartView> implements Serializable {

    private static final long serialVersionUID = -9824276818586872L;


    public StartPresenter(StartView view, EventBus eventBus) {
        super(view, eventBus);
        bind();
    }

    public void enterLobby(String name){
        User u = new User();
        u.setName(name);

        Future<HttpResponse<JsonNode>> future = Unirest.get("http://localhost:8888/createUser/" + name)
                .header("accept", "application/json")
                .asJsonAsync(new Callback<JsonNode>() {

                    public void failed(UnirestException e) {
                    }

                    public void completed(HttpResponse<JsonNode> response) {
                        int code = response.getCode();
                        Headers headers = response.getHeaders();
                        JsonNode body = response.getBody();
                        try {
                            body.getObject().get("callback");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    public void cancelled() {
                    }

                });
        getEventBus().fireEvent(new EnterLobbyViewEvent(u));
    }

    @EventHandler
    public void onShowStartViewRequired(ShowStartViewEvent e) {
    }

    private void bind(){
        getEventBus().addHandler(this);
    }



}