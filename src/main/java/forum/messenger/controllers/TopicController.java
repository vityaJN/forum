package forum.messenger.controllers;

import forum.messenger.DTO.MessageDTO;
import forum.messenger.Services.TopicService;
import forum.messenger.entity.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TopicController {
    private final TopicService topicService;

    @Autowired
    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    /**
     * return with x topics order by xyz
     */
    //todo get xyz des asc amount
    @GetMapping()
    public List<Topic> getTopics() {
        List<Topic>topics = topicService.getTopics(10);
        return topics;
    }

    /**
     * creating a topic
     */
    @PostMapping()
    public String createTopic(@ModelAttribute("topic") Topic topic) {
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
