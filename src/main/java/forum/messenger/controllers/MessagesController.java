package forum.messenger.controllers;

import forum.messenger.DTO.MessageDTO;
import forum.messenger.Services.MessageService;
import forum.messenger.entity.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class MessagesController {
    private static Logger logger = LoggerFactory.getLogger(MessagesController.class);
    private final MessageService msgService;

    @Autowired
    public MessagesController(MessageService msgService) {
        this.msgService = msgService;
    }

    @PostMapping("topics/messages/sendMessage")
    public String messagesForm(@ModelAttribute("message") @Valid MessageDTO m,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "topic";
        }
        msgService.createMessage(m);
        return "redirect:/topics/" + m.getTopicId();
    }

    //TODO MODIFY TO SHOW DETAILS ABOUT THE USER AND THE MESSAGE
    @RequestMapping(value = "/messages/{messageId}", method = RequestMethod.GET)
    public String showUserData(@PathVariable("messageId") long messageId, Model model) {
        model.addAttribute("messageById", msgService.getASingleMessage(messageId));
        return "oneMessage";
    }

    @RequestMapping("/messages/get_message_to_modify/{mID}")
    public String modifyMessage(@PathVariable("mID") long messageId, Model model) {
        model.addAttribute("message", msgService.getASingleMessage(messageId));
        return "modify_message";
    }

    @PostMapping("/messages/get_message_to_modify/{mID}")
    public String modifyMessage(@PathVariable("mID") long messageId, Message m) {
        msgService.updateMessage(m);
        return "redirect:/topics/"+ msgService.findTheTopicIdOfMessage(m.getId());
    }
}