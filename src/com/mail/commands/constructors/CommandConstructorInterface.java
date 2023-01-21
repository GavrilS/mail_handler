package com.mail.commands.constructors;

import com.mail.commands.models.CommandInterface;

public interface CommandConstructorInterface {
    CommandInterface returnCommand(String command);
}
