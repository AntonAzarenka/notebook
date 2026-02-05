package com.azarenka.service.events;

import javafx.beans.value.ChangeListener;

/**
 * Marker interface.
 * <p>
 * Copyright (C) 2026 copyright.com
 * <p>
 * Date: 02/05/2026
 *
 * @author Anton Azarenka
 */
public interface IEvent {

    void changeStatusEvent();

    void addListener(ChangeListener<? super Boolean> var1);

    void bind();

    boolean isBind();

    void unbind();
}
