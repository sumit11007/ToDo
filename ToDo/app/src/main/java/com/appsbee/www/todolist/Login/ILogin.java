package com.appsbee.www.todolist.Login;

import com.appsbee.www.todolist.IBase;

/**
 * Created by root on 9/2/16.
 */
public interface ILogin extends IBase {
    void setEmailError(String string);

    void requestFocus();

    void setPsdError(String s);

    String getEmail();

    String getPassword();

    void setFocusPsdView();

    void setFocusEmailView();

    void focusView();

    void startTodoActivity();
}
