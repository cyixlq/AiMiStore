package top.cyixlq.cy.delegates.web.event;

import top.cyixlq.cy.util.log.CyLog;

public class UndefineEvent extends Event {
    @Override
    public String execute(String params) {
        CyLog.e("UndefineEvent", params);
        return null;
    }
}
