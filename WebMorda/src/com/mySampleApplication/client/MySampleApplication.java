package com.mySampleApplication.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.DOM;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;

/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class MySampleApplication implements EntryPoint {

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        final Button bRefreshAll = new Button("RefreshAll");
        final Button bNextStep = new Button("NextStep");
        final TextArea tNow = new TextArea();
        final TextArea tNextStep = new TextArea();

        bRefreshAll.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                MySampleApplicationService.App.getInstance().getMessage("Hello, World!", new bRefreshAllClick(tNow));
            }
        });

        bNextStep.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                MySampleApplicationService.App.getInstance().getMessage("Hello, World!", new bNextStepClick(tNextStep));
            }
        });

        //TA now
        tNow.setReadOnly(true);
        tNow.setHeight("50%");
        tNow.setWidth("30%");

        //TA nextstep
        tNextStep.setReadOnly(true);
        tNextStep.setHeight("50%");
        tNextStep.setWidth("30%");


        RootPanel.get("buttons").add(bRefreshAll);
        RootPanel.get("buttons").add(bNextStep);
        RootPanel.get("textareas").add(tNow);
        RootPanel.get("textareas").add(tNextStep);

    }

    private static class bRefreshAllClick implements AsyncCallback<String> {
        private TextArea tNow;

        public bRefreshAllClick(TextArea tNow) {
            this.tNow = tNow;
        }

        public void onSuccess(String result) {
            tNow.setText(tNow.getText() + result + "\n");
        }

        public void onFailure(Throwable throwable) {
            tNow.setText("Failed to receive answer from server!");
        }
    }

    private static class bNextStepClick implements AsyncCallback<String> {
        private TextArea tNextStep;

        public bNextStepClick(TextArea tNextStep) {
            this.tNextStep = tNextStep;
        }

        public void onSuccess(String result) {
            tNextStep.setText(tNextStep.getText() + result + "\n");
        }

        public void onFailure(Throwable throwable) {
            tNextStep.setText("Failed to receive answer from server!");
        }
    }
}
