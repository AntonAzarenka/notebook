package com.azarenka;

import com.azarenka.ui.scene.finance.FinanceWindow;
import com.azarenka.ui.scene.NewPasswordModalWindow;
import com.azarenka.ui.scene.MainWindow;
import com.azarenka.ui.scene.PasswordModalWindow;
import com.azarenka.ui.scene.PasswordsWindow;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Represents of provider for windows .
 * <p>
 * Copyright (C) 2023 antazarenko@gmail.com
 * <p>
 * Date: 01/09/2023
 *
 * @author Anton Azarenka
 */
@Component
public class WindowsProvider {

    @Autowired
    private MainWindow mainWindow;
    @Autowired
    private PasswordsWindow passwordsWindow;
    @Autowired
    private PasswordModalWindow passwordModalWindow;
    @Autowired
    private NewPasswordModalWindow newPasswordModalWindow;
    @Autowired
    private FinanceWindow financeWindow;

    public NewPasswordModalWindow getNewPasswordModalWindow() {
        return newPasswordModalWindow;
    }

    public MainWindow getMainWindow() {
        return mainWindow;
    }

    public PasswordsWindow getPasswordsWindow() {
        return passwordsWindow;
    }

    public PasswordModalWindow getPasswordModalWindow() {
        return passwordModalWindow;
    }

    public FinanceWindow getFinanceWindow() {
        return financeWindow;
    }
}
