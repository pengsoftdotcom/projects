package com.pengsoft.system.biz.messaging;

import java.io.Serializable;

/**
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public class MessageBody implements Serializable {

    private static final long serialVersionUID = -4443657507172956692L;
    
    private Object[] args;

    private Object result;

    private Messaging messaging;

    public MessageBody(final Object[] args, final Object result, final Messaging messaging) {
        this.args = args;
        this.result = result;
        this.messaging = messaging;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(final Object[] args) {
        this.args = args;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(final Object result) {
        this.result = result;
    }

    public Messaging getMessaging() {
        return messaging;
    }

    public void setMessaging(final Messaging messaging) {
        this.messaging = messaging;
    }

}
