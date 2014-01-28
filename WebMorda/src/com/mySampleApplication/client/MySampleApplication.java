package com.mySampleApplication.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.rpc.AsyncCallback;
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
        final TextBox tbStatus = new TextBox();
        final TextArea taNow = new TextArea();
        final TextArea taNextStep = new TextArea();
        final CheckBox cbUpdate = new CheckBox();

        final Timer tAutoUpdate = new Timer() {
            @Override
            public void run() {
                tbStatus.setText(tbStatus.getText() + ".");
            }
        };

        bRefreshAll.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                MySampleApplicationService.App.getInstance().getMessage("Hello, World!", new bRefreshAllClick(taNow));
            }
        });

        bNextStep.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                MySampleApplicationService.App.getInstance().getMessage("Hello, World!", new bNextStepClick(taNextStep));
            }
        });

        cbUpdate.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {

                if (cbUpdate.getValue())
                    tAutoUpdate.scheduleRepeating(1000);
                else tAutoUpdate.cancel();

            }
        });

        //TA now
        taNow.setReadOnly(true);
        taNow.setHeight("50%");
        taNow.setWidth("30%");

        //TA nextstep
        taNextStep.setReadOnly(true);
        taNextStep.setHeight("50%");
        taNextStep.setWidth("30%");

        //TB status
        tbStatus.setReadOnly(true);
        tbStatus.setWidth("200px");
        tbStatus.setText("");

        //CB update
        cbUpdate.setEnabled(true);
        cbUpdate.setText("AutoUpdate");

        RootPanel.get("config").add(cbUpdate);
        RootPanel.get("status").add(tbStatus);

        RootPanel.get("buttons").add(bRefreshAll);
        RootPanel.get("buttons").add(bNextStep);

        RootPanel.get("textareas").add(taNow);
        RootPanel.get("textareas").add(taNextStep);
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
