package com.mti;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

/**
 * Created by JULIEN on 11/14/2014.
 */
public class GameView extends VerticalLayout implements View {
    private final Label youLabel = new Label();

    public GameView() {
        setSizeFull();
        Panel panel = new Panel();
        VerticalLayout panelContent = new VerticalLayout();
        Label iaLabel = new Label("IA");

        panelContent.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        panelContent.addComponent(youLabel);
        panelContent.addComponent(iaLabel);

        Button paperButton = new Button("Paper",
                new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        Notification.show("Click");
                    }
                });
        paperButton.setStyleName(ValoTheme.BUTTON_ICON_ONLY);
        paperButton.setIcon(new ThemeResource("~/pizza.jpg"));
        panelContent.addComponent(paperButton);

        Button rockButton = new Button("Rock",
                new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        Notification.show("Click");
                    }
                });
        rockButton.setStyleName(ValoTheme.BUTTON_ICON_ONLY);
        rockButton.setIcon(new ThemeResource("~/pizza.jpg"));
        panelContent.addComponent(rockButton);

        Button scissorsButton = new Button("Rock",
                new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        Notification.show("Click");
                    }
                });
        scissorsButton.setStyleName(ValoTheme.BUTTON_ICON_ONLY);
        scissorsButton.setIcon(new ThemeResource("~/pizza.jpg"));
        panelContent.addComponent(scissorsButton);

        Button spokeButton = new Button("Spoke",
                new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        Notification.show("Click");
                    }
                });
        spokeButton.setStyleName(ValoTheme.BUTTON_ICON_ONLY);
        spokeButton.setIcon(new ThemeResource("~/pizza.jpg"));
        panelContent.addComponent(spokeButton);

        Button lizardButton = new Button("Lizard",
                new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        Notification.show("Click");
                    }
                });
        lizardButton.setStyleName(ValoTheme.BUTTON_ICON_ONLY);
        lizardButton.setIcon(new ThemeResource("~/pizza.jpg"));
        panelContent.addComponent(lizardButton);

        panel.setContent(panelContent);
        addComponent(panel);
        setComponentAlignment(panel, Alignment.MIDDLE_CENTER);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        UI ui = getUI();
        String username = (String)ui.getSession().getAttribute(Globals.SESSION_USERNAME);
        youLabel.setValue(username);
    }
}