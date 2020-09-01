package forum.messenger.controllers.UserControllers;

import forum.messenger.Services.AdminService;
import forum.messenger.Services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminController {

    MessageService msgService;
    AdminService adminService;

    @Autowired
    public AdminController(MessageService msgService, AdminService adminService) {
        this.msgService = msgService;
        this.adminService = adminService;
    }

    @GetMapping("/filter_messages_and_users")
    public String getFilterMessagesUsers(Model model,
                                         @RequestParam(value = "sortBy", defaultValue = "name", required = false) String sortBy,
                                         @RequestParam(value = "limit", defaultValue = "10", required = false) int limit,
                                         @RequestParam(value = "direction", required = false, defaultValue = "desc") String direction,
                                         @RequestParam(value = "user", required = false) String user) {

        model.addAttribute("messageList", adminService.getUserMessages("text", "desc", user));
        model.addAttribute("users", adminService.getUsers());
        return "user_messages_filter";
    }

    @PostMapping("/admin/messages/deleteMessage/{messageId}")
    public String deleteMessage(@PathVariable("messageId") long messageId) {
        msgService.deleteMessage(messageId);
        return "redirect:/topics/" + msgService.fromWhichTopicTheMessage(messageId);
    }

    @GetMapping("/admin/messages/deletedMessages/")
    public String getDeleteMessages(Model model) {
        model.addAttribute("messages", msgService.getDeletedMessages());
        return "deleted_messages";
    }

    @GetMapping("/admin/getUsers")
    public String getUsers(Model model) {
        return "deleted_messages";
    }

    @PostMapping("/admin/messages/recoveryMessage/{messageId}")
    public String recoveryMessage(@PathVariable("messageId") long messageId) {
        msgService.recoveryMessage(messageId);
        return "redirect:/topics/" + msgService.fromWhichTopicTheMessage(messageId);
    }
}
