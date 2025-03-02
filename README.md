# L4TgB

A library for routing user commands sent to the bot  
and managing the completion of data transfer objects between the user and the bot

## Problem solved by the library

When conducting a dialog interaction between a user 
and a bot, the user sends commands, data, or data as part
of commands to the bot.

To store user data, a dto is filled
gradually when each new data batch is received.

The library makes it easy to fill a dto and manage that filling.

## How to use

### Managing bot commands 

#### @CommandNames annotation on class
Annotation say library, that marked class - command handler for commands with id,
equals with value in value() property or, if custom callback handler don't registered for button in @Button,
callback value handler for callback, that callback value equals with value in value() property.
Class, marked @CommandNames 

Properties:
1. value - value, that will be compared with command id or callback value,
if true, then this handler will be used for handle user action.
2. type:
   - MESSAGE - handle command with same id
   - CALLBACK - handle callback with same cb value

#### @CommandFirst annotation on method, owner class must be marked @CommandNames
Marked method, that must be called first, when handling command or cb is executed

#### @CommandOther annotation on method, owner class must be marked @CommandNames
Marked method, that must be called after method, marked @CommandFirst
on next user action in same command.
For example, user sent command, received from bot keyboard, tap on one button, callback value 
from button sent to bot and, if no @CommandNames is registered for this callback value, then 
callback will handle in @CommandOther

### Managing dto filling

#### @Fillee annotation on class
Annotation say library, that is dto class for filling.
Dto must impl ifc com.github.lltal.filler.shared.ifc.Countable

Properties: 
1. senderBeanName - bean with this name will be
created by the library at compile time. This bean may be used to get next message for user.
Next message for user will be extracted from @FilleeField#text on next filled field. 
2. fillerBeanName - bean with this name will be
created by the library at compile time. This bean may be used to fill next dto field by user data.
3. resolverBeanName - bean with this name will be
created by the library at compile time. This bean may be used to resolve what need to do with next field:
    - Resolve, where data was sent from - message, command, button
    - Resolve, that filler used to fill current field - default or user custom
    - Resolve, that button used to fill current field - default or user custom

#### @FilleeField annotation on field, owner class must be marked @Fillee:
Annotation say library, that is dto's field, that will to handle in beans's, registered in @Fillee
If @Keyboard exists on same field, then @FilleeField will be ignored.

Properties:
1. text - text, that will be shown user, when current field will be filled on next step
2. customFillHandler - custom fill handler bean name, bean must be registered by developer and 
impl CustomFilleeHandler<T>, where T - filled dto class type

#### @Keyboard annotation on field, owner class must be marked @Fillee:
Annotation say library, that if current field will to be filled on next step, then next message to user
will be keyboard
1. buttons - array of @Buttons, each @Button represent one button in keyboard

#### @Buttons annotation in @Keyboard, owner class must be marked @Fillee:
Annotation say library, how to present button for user

Properties:
1. userView - text on button, that shown to user
2. cbValue - on button click this value will be sent and handle in own @CommandNames, if @CommandNames is existed
for current cbValue or handle in @CommandNames in method, marked @CommandOther, that @CommandNames was used for 
send keyboard to user, or if registered customClickHandler, then cbValue will be sent to there.
3. customCLickHandler - own button click handler bean name, if registered, then handle button click.

### How to specify packages to scan

In .yaml or .properties file:

dto-filler:
  packages-to-scan:
    - "package1"
    - "package2"


##### Sorry for gramma errors, English is not my first language:)

 

