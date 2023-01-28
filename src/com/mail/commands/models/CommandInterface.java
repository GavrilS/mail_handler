package com.mail.commands.models;

import com.mail.commands.repository.TempState;

public interface CommandInterface {
    void executeCommand(TempState state);
}
