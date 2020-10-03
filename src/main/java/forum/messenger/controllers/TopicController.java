package forum.messenger.controllers;

import forum.messenger.DTO.TopicCreationDTO;
import forum.messenger.Services.TopicService;
import forum.messenger.entity.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/forum")
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
    public List<Topic> getTopicsList() {
        List<Topic> topics = topicService.getTopics(10);
        return topics;
    }

    /**
     * creating a topic
     */
    @PostMapping()
    public void createTopic(@RequestBody TopicCreationDTO topic) {
        topicService.createTopic(topic);
    }


    /**
     * return with a topic and with messages
     *
     * @return
     */
    @GetMapping("/topics/{topicsId}")
    public Topic choose_topics(@PathVariable("topicsId") long topicsId, @RequestParam(value = "sortBy", defaultValue = "name", required = false) String sortBy,
                               @RequestParam(value = "limit", defaultValue = "10", required = false) int limit,
                               @RequestParam(value = "direction", required = false, defaultValue = "desc") String direction) {
        return topicService.getTopic(topicsId);
    }
}
