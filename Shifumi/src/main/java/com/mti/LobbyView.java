package com.mti;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;

/**
 * Created by JULIEN on 11/14/2014.
 */

public class LobbyView extends VerticalLayout implements View {
    public LobbyView() {
        setSizeFull();
        Panel panel = new Panel();
        VerticalLayout panelContent = new VerticalLayout();

        Label label = new Label("Waiting for an opponent...");

        panelContent.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        panelContent.addComponent(label);
        panel.setContent(panelContent);
        addComponent(panel);
        setComponentAlignment(panel, Alignment.MIDDLE_CENTER);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        UI ui = getUI();
        String username = (String)ui.getSession().getAttribute(Globals.SESSION_USERNAME);
        Notification.show(String.format("Hey %s ! Welcome in the ChifumiSpoke !", username));
    }
}