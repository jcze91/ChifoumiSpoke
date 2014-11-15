package com.mti.view;

import com.mti.Globals;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Page;
import com.vaadin.ui.*;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.*;

/**
 * Created by JULIEN on 11/14/2014.
 */

public class LobbyView extends VerticalLayout implements View {

    private final Label label = new Label();
    private final Timer timer = new Timer();
    private final Button accept;
    private final Button refuse;

    public LobbyView() {
        setSizeFull();

        Panel panel = new Panel();
        panel.setHeight(800, Unit.PIXELS);

        VerticalLayout panelContent = new VerticalLayout();
        HorizontalLayout labelLayout = new HorizontalLayout();
        labelLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        labelLayout.addComponent(label);
        panelContent.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);

        accept = new Button("Accept",
                new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        UI ui = getUI();
                        getUI().getNavigator().navigateTo(Globals.VIEW_GAME);
                    }
                });
        refuse = new Button("Refuse",
                new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        searchOpponent();
                    }
                });

        HorizontalLayout buttons = new HorizontalLayout();
        buttons.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        buttons.addComponent(accept);
        buttons.addComponent(refuse);
        panelContent.addComponent(labelLayout);
        panelContent.addComponent(buttons);
        panel.setContent(panelContent);
        addComponent(panel);
        setComponentAlignment(panel, Alignment.MIDDLE_CENTER);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        UI ui = getUI();
        String username = (String)ui.getSession().getAttribute(Globals.SESSION_USERNAME);
        Notification notif = new Notification(String.format("Hey %s ! Welcome in the ChifumiSpoke !", username));
        notif.setDelayMsec(1000);
        notif.show(Page.getCurrent());
        searchOpponent();
    }

    private void searchOpponent()
    {
        label.setValue("Waiting for an opponent ...");
        accept.setVisible(false);
        refuse.setVisible(false);

        int timeout = randInt(0, 60) * 1000;

        final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.schedule(new Runnable(){
            @Override
            public void run(){
                findOpponent();
            }
        }, timeout, TimeUnit.MILLISECONDS);
    }

    private void findOpponent()
    {
        getUI().access(() -> {
            accept.setVisible(true);
            refuse.setVisible(true);
            label.setValue("Opponent found !");
        });
    }

    private int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }
}