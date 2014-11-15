package com.mti.view;

import com.mti.*;
import com.mti.event.ShowStartViewEvent;
import com.mti.model.*;
import com.mti.presenter.GamePresenter;
import com.mti.presenter.LobbyPresenter;
import com.mvplite.event.EventBus;
import com.mvplite.event.EventHandler;
import com.mvplite.event.ShowViewEvent;
import com.mvplite.view.NavigationController;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by JULIEN on 11/14/2014.
 */
public class GameViewImpl extends VerticalLayout implements GameView {
    private final Label youLabel = new Label();
    private final Label youScore = new Label();
    private final Label iaScore = new Label();
    private final Label youShot = new Label();
    private final Label iaShot = new Label();
    private final Label result = new Label();
    private Button rock;
    private Button scissors;
    private Button paper;
    private Button lizard;
    private Button spoke;
    private Button nextPlay;
    private ProgressBar bar;

    private final NavigationController navigationController;
    private final EventBus eventBus;
    private final GamePresenter presenter;

    public GameViewImpl(User user, EventBus eventBus,
                  NavigationController navigationController) {
        this.eventBus = eventBus;
        this.navigationController = navigationController;
        this.presenter = new GamePresenter(this, eventBus, user);
        bind();
        setSizeFull();
        generateUI();
    }
    private void bind(){
        eventBus.addHandler(this);
    }

    private void generateUI()
    {
        Panel topPanel = new Panel();
        Panel bodyPanel = new Panel();

        HorizontalLayout topContent = new HorizontalLayout();
        VerticalLayout bodyContent = new VerticalLayout();
        topPanel.setStyleName("game_top_panel");
        topPanel.setHeight("50px");
        bodyPanel.setStyleName("game_body_panel");
        HorizontalLayout youContent = new HorizontalLayout();
        HorizontalLayout iaContent = new HorizontalLayout();
        HorizontalLayout quitBtnContent = new HorizontalLayout();
        youContent.setWidth(200, Unit.PIXELS);
        iaContent.setWidth(200, Unit.PIXELS);
        Button quitButton = new Button("Quit",
                new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        presenter.goBackToLobby();
                    }
                });
        bar = new ProgressBar(0.0f);
        bar.setWidth("400px");
        Label iaLabel = new Label("IA");
        youContent.addComponents(youLabel, youScore);
        iaContent.addComponents(iaLabel, iaScore);
        quitBtnContent.addComponents(quitButton, bar);
        quitBtnContent.setComponentAlignment(quitButton, Alignment.MIDDLE_CENTER);
        topContent.addComponents(youContent, iaContent, quitBtnContent);

        bodyContent.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);

        rock = new Button("ROCK",
                new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        presenter.shot(ShotKind.ROCK);
                    }
                });
        paper = new Button("PAPER",
                new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        presenter.shot(ShotKind.PAPER);
                    }
                });
        scissors = new Button("SCISSORS",
                new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        presenter.shot(ShotKind.SCISSORS);
                    }
                });
        spoke = new Button("SPOKE",
                new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        presenter.shot(ShotKind.SPOKE);
                    }
                });
        lizard = new Button("LIZARD",
                new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        presenter.shot(ShotKind.LIZARD);
                    }
                });
        nextPlay = new Button("Play next",
                new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        presenter.playNextShot();
                    }
                });
        bodyContent.addComponents(rock, paper, scissors, spoke, lizard);
        bodyContent.addComponents(youShot, iaShot, result, nextPlay);
        topPanel.setContent(topContent);
        bodyPanel.setContent(bodyContent);

        addComponents(topPanel, bodyPanel);
    }

    @Override
    public void playNextShot()
    {
        getUI().access(() -> {
            youShot.setVisible(false);
            iaShot.setVisible(false);
            rock.setVisible(true);
            spoke.setVisible(true);
            lizard.setVisible(true);
            scissors.setVisible(true);
            paper.setVisible(true);
            nextPlay.setVisible(false);
            result.setVisible(false);
            bar.setValue(0.0f);
        });
    }
    @Override
    public void setBar(float value) {
        getUI().access(() -> {
            bar.setValue(value);
        });
    }
    @Override
    public void increaseBar(float offset) {
        getUI().access(() -> {
            bar.setValue(bar.getValue() + offset);
        });
    }

    @Override
    public void displayWinner(User currentUser, User IA, Match m)
    {
        getUI().access(() -> {
            youShot.setVisible(true);
            iaShot.setVisible(true);
            youScore.setValue(currentUser.getScore().toString());
            iaScore.setValue(IA.getScore().toString());
            youShot.setValue(String.format("Your shot : %s", currentUser.getShot().toString()));
            iaShot.setValue(String.format("IA shot : %s", IA.getShot().toString()));
            rock.setVisible(false);
            spoke.setVisible(false);
            lizard.setVisible(false);
            scissors.setVisible(false);
            paper.setVisible(false);
            nextPlay.setVisible(true);
            result.setVisible(true);
            if (m.getWinner() == null)
                result.setValue("Egalité");
            else
                result.setValue(String.format("%s wins !", m.getWinner().getName()));
        });
    }

    public void init() {
        UI ui = getUI();
        youLabel.setValue(presenter.getCurrentUser().getName());
        presenter.playNextShot();
    }
    @EventHandler
    public void onShowStartViewRequired(ShowStartViewEvent e) {
    }
    @Override
    public String getUriFragment() {
        return "game";
    }

    @Override
    public String getBreadcrumbTitle() {
        return "Game";
    }

    @Override
    public ShowViewEvent getEventToShowThisView() {
        return null;
    }
}