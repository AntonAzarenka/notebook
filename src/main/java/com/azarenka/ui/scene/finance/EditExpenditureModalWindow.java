package com.azarenka.ui.scene.finance;

import com.azarenka.javafx.load.CommonWidget;
import com.azarenka.javafx.load.FxmlFileLoader;
import com.azarenka.javafx.load.IFxmlWindow;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javafx.scene.Scene;

/**
 * Represents of .. .
 * <p>
 * Copyright (C) 2023 antazarenko@gmail.com
 * <p>
 * Date: 06/28/2023
 *
 * @author Anton Azarenka
 */
@Component
public class EditExpenditureModalWindow extends CommonWidget implements IFxmlWindow {

    @Value("classpath:fxml/finance/edit-expenditure-window.fxml")
    private Resource resource;
    private Scene scene;

    public EditExpenditureModalWindow(ApplicationContext applicationContext) {
        super(applicationContext);
    }

    @Override
    public Scene getScene() {
        return scene;
    }

    @Override
    public void load() {
        scene = loadBean(new FxmlFileLoader(resource));
    }
}
