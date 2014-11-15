package com.mti.view;

import com.mti.Globals;
import com.mti.event.ShowStartViewEvent;
import com.mti.model.User;
import com.mti.event.EnterLobbyViewEvent;
import com.mti.presenter.LobbyPresenter;
import com.mti.presenter.StartPresenter;
import com.mvplite.event.EventBus;
import com.mvplite.event.EventHandler;
import com.mvplite.event.ShowViewEvent;
import com.mvplite.view.NavigationController;
import com.vaadin.server.Page;
import com.vaadin.ui.*;

import java.util.Random;
import java.util.Timer;
import java.util.concurrent.*;

/**
 * Created by JULIEN on 11/14/2014.
 */

public class LobbyViewImpl extends VerticalLayout implements LobbyView {

    private final Label label = new Label();
    private final Timer timer = new Timer();
    private Button accept;
    private Button refuse;

    private final NavigationController navigationController;
    private final EventBus eventBus;
    private final LobbyPresenter presenter;
    private final User user;

    public LobbyViewImpl(User user, EventBus eventBus,
                         NavigationController navigationController) {
        this.user = user;
        this.eventBus = eventBus;
        this.navigationController = navigationController;
        this.presenter = new LobbyPresenter(this, eventBus, user.getName());
        bind();
        setSizeFull();
        generateUI();
    }
    private void bind(){
        eventBus.addHandler(this);
    }
    private void generateUI()
    {
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
                        presenter.acceptOpponent();
                    }
                });
        refuse = new Button("Refuse",
                new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        presenter.searchOpponent();
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

    @EventHandler
    public void onShowStartViewRequired(ShowStartViewEvent e) {
    }

    public void init() {
        Notification notif = new Notification(String.format("Hey %s ! Welcome in the ChifumiSpoke !", user.getName()));
        notif.setDelayMsec(1000);
        notif.show(Page.getCurrent());
        presenter.searchOpponent();
    }

    @Override
    public void searchOpponent()
    {
        label.setValue("Waiting for an opponent ...");
        accept.setVisible(false);
        refuse.setVisible(false);
    }

    @Override
    public void findOpponent()
    {
        getUI().access(() -> {
            accept.setVisible(true);
            refuse.setVisible(true);
            label.setValue("Opponent found !");
        });
    }
    @Override
    public String getUriFragment() {
        return "lobby";
    }

    @Override
    public String getBreadcrumbTitle() {
        return "Lobby";
    }

    @Override
    public ShowViewEvent getEventToShowThisView() {
        return null;
    }
}