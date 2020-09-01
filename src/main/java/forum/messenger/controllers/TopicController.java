package forum.messenger.controllers;

import forum.messenger.DTO.MessageDTO;
import forum.messenger.Services.TopicService;
import forum.messenger.container.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TopicController {
    private final TopicService topicService;

    @Autowired
    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }


    @GetMapping("/")
    public String getTopics(Model model) {
        model.addAttribute("topic", topicService.getTopics(10));
        return "forum";
    }

    /**
     * get view of creating topic
     * @return
     */
    @GetMapping("/create_topics_form")
    public String getTopicsForm(@ModelAttribute("topic") Topic topic) {
        return "create_topics_form";
    }


    /**
     * creating topic
     * @return
     */
    @PostMapping("/create_topics")
    public String createTopics(@ModelAttribute("topic") Topic topic) {
        topicService.createTopic(topic);
        return "redirect:/";
    }


    /**
     * returns with a MESSAGEDTO which is needed for send message
     * and with the actual topics messages
     * @return
     */
    @GetMapping("/topics/{topicsId}")
    public String choose_topics(@PathVariable("topicsId") long topicsId,  @RequestParam(value = "sortBy", defaultValue = "name",required = false) String sortBy,
                                @RequestParam(value = "limit", defaultValue = "10" , required = false) int limit,
                                @RequestParam(value = "direction", required = false,defaultValue = "desc") String direction,
                                Model model) {
        // MessageDTO
        model.addAttribute("message", new MessageDTO(topicsId));
        //list all of the messages within a topic
        model.addAttribute("messageList", topicService.getSingleTopic(topicsId,sortBy,limit,direction).getMessages());
        return "topic";
    }
}
