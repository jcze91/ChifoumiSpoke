package com.mti;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;

/**
 * Created by JULIEN on 11/14/2014.
 */
public class GameView extends VerticalLayout implements View {
    private final Label youLabel = new Label();
    private final Label youScore = new Label();
    private final Label iaScore = new Label();

    public GameView() {
        setSizeFull();
        Panel topPanel = new Panel();
        Panel bodyPanel = new Panel();
        topPanel.setHeight(50, Unit.PIXELS);
        VerticalLayout content = new VerticalLayout();
        HorizontalLayout topContent = new HorizontalLayout();
        HorizontalLayout bodyContent = new HorizontalLayout();

        HorizontalLayout youContent = new HorizontalLayout();
        HorizontalLayout iaContent = new HorizontalLayout();
        iaContent.setWidth(100, Unit.PIXELS);
        youContent.setWidth(100, Unit.PIXELS);

        Label iaLabel = new Label("IA");

        youContent.addComponent(youLabel);
        youContent.addComponent(youScore);
        iaContent.addComponent(iaLabel);
        iaContent.addComponent(iaScore);
        topContent.addComponent(youContent);
        topContent.addComponent(iaContent);
        topContent.setComponentAlignment(youContent, Alignment.MIDDLE_LEFT);
        topContent.setComponentAlignment(iaContent, Alignment.MIDDLE_RIGHT);

        bodyContent.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);

        Button rock = new Button("ROCK",
                new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                    }
                });
        Button paper = new Button("PAPER",
                new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                    }
                });
        Button scissors = new Button("SCISSORS",
                new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                    }
                });
        Button spoke = new Button("SPOKE",
                new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                    }
                });
        Button lizard = new Button("LIZARD",
                new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                    }
                });

        bodyContent.addComponents(rock, paper, scissors, spoke, lizard);

        topPanel.setContent(topContent);
        bodyPanel.setContent(bodyContent);

        setDefaultComponentAlignment(Alignment.TOP_CENTER);

        addComponent(topPanel);
        addComponent(bodyPanel);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        UI ui = getUI();
        String username = (String)ui.getSession().getAttribute(Globals.SESSION_USERNAME);
        youLabel.setValue(username);
    }
}